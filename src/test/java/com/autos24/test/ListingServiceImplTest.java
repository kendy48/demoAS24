package com.autos24.test;

import com.autos24.test.domain.ListingEntity;
import com.autos24.test.exception.InvalidCSVFormatException;
import com.autos24.test.repository.ListingRepository;
import com.autos24.test.service.ListingServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests to validate the right functionality of ListingServiceImpl
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ListingServiceImplTest {

    private static final String VALID_LISTING_FILE_WITH_RIGHT_HEADER_ORDER = "src/test/resources/valid_listing_file_with_right_header_order.csv";
    private static final String VALID_LISTING_FILE_WITH_DIFFERENT_HEADER_ORDER = "src/test/resources/valid_listing_file_with_different_header_order.csv";
    private static final String INVALID_LISTING_FILE_WITH_WRONG_HEADER_NAME = "src/test/resources/invalid_listing_file_with_wrong_header_names.csv";
    private static final String INVALID_LISTING_FILE_WITH_MISSING_VALUES = "src/test/resources/invalid_listing_file_with_missing_values.csv";

    /**
     * Main Component to test
     */
    private ListingServiceImpl listingService;

    @Autowired
    private ListingRepository listingRepository;

    @Before
    public void before() {
        listingService = new ListingServiceImpl(listingRepository);
    }

    @Test
    public void uploadListingCSVWithRightHeaderOrderHappyPath() throws Exception {
        listingService.uploadListingCSV(createMultipartFromPath(VALID_LISTING_FILE_WITH_RIGHT_HEADER_ORDER), ",", false);
        List<ListingEntity> listings = listingRepository.findAll();
        assertEquals(listings.size(), 2);
    }

    @Test
    public void uploadListingCSVWithDifferentHeaderOrderHappyPath() throws Exception {
        listingService.uploadListingCSV(createMultipartFromPath(VALID_LISTING_FILE_WITH_DIFFERENT_HEADER_ORDER), ",", false);
        List<ListingEntity> listings = listingRepository.findAll();
        assertEquals(listings.size(), 2);
    }

    @Test(expected = InvalidCSVFormatException.class)
    public void uploadListingCSVWithInvalidHeaderNames() throws Exception {
        listingService.uploadListingCSV(createMultipartFromPath(INVALID_LISTING_FILE_WITH_WRONG_HEADER_NAME), ",", false);
    }

    @Test(expected = InvalidCSVFormatException.class)
    public void uploadListingCSVWithMissingValues() throws Exception {
        listingService.uploadListingCSV(createMultipartFromPath(INVALID_LISTING_FILE_WITH_MISSING_VALUES), ",", false);
    }

    @Test
    public void uploadListingCSVWithMissingValuesCheckDataIntegrity() {
        try {
            listingService.uploadListingCSV(createMultipartFromPath(INVALID_LISTING_FILE_WITH_MISSING_VALUES), ",", false);
        } catch (Exception e) {
            assertEquals(e instanceof InvalidCSVFormatException, true);
        }
        List<ListingEntity> listings = listingRepository.findAll();
        assertEquals(listings.size(), 0);
    }

    private MultipartFile createMultipartFromPath(String path) {
        try {
            return new MockMultipartFile("file.csv", new FileInputStream(new File(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
