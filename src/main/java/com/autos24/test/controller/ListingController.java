package com.autos24.test.controller;

import com.autos24.test.controller.criteria.ListingFindCriteria;
import com.autos24.test.controller.mapper.listing.ListingMapper;
import com.autos24.test.dto.ListingDTO;
import com.autos24.test.exception.EntityNotFoundException;
import com.autos24.test.exception.InvalidCSVFormatException;
import com.autos24.test.exception.ReadFileException;
import com.autos24.test.service.ListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("v1/listings")
public class ListingController {

    private final ListingService listingService;

    @Autowired
    public ListingController(final ListingService listingService) {
        this.listingService = listingService;
    }


    @GetMapping
    public List<ListingDTO> findListings(ListingFindCriteria criteria) {
        return ListingMapper.makeListingDTOList(listingService.find(criteria));
    }

    @PostMapping("/csv")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadListingCSV(@RequestParam("file") MultipartFile file,
                                 @RequestParam(value = "csv_delimiter", defaultValue = ",") String csvDelimiter,
                                 @RequestParam(value = "replace_previous_listings", defaultValue = "false") boolean replacePreviousListings)
            throws InvalidCSVFormatException, ReadFileException {
        listingService.uploadListingCSV(file, csvDelimiter, replacePreviousListings);
    }

    @PostMapping("/contacts/csv")
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadContactCSV(@RequestParam("file") MultipartFile file,
                                 @RequestParam(value = "csv_delimiter", defaultValue = ",") String csvDelimiter,
                                 @RequestParam(value = "replace_previous_listings", defaultValue = "false") boolean replacePreviousContacts)
            throws EntityNotFoundException, InvalidCSVFormatException, ReadFileException {
        listingService.uploadContactCSV(file, csvDelimiter, replacePreviousContacts);
    }

}
