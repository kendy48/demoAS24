package com.autos24.test.service;

import com.autos24.test.controller.criteria.ListingFindCriteria;
import com.autos24.test.domain.ListingEntity;
import com.autos24.test.exception.EntityNotFoundException;
import com.autos24.test.exception.InvalidCSVFormatException;
import com.autos24.test.exception.ReadFileException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Defines the contract of the listing related business functionalities.
 */
public interface ListingService {

    /**
     * Find all listings and filter the search by the attributes of the criteria
     * Every attribute assigned will be use as an and operator.
     *
     * @param criteria driver attributes to filter the query search
     * @return List of the founded ListingEntity
     */
    List<ListingEntity> find(ListingFindCriteria criteria);

    /**
     * Upload csv file with listing data and create listings records.
     *
     * @param file                    a csv file with listing data
     * @param csvDelimiter            character to delimiter the csv file, default is ','
     * @param replacePreviousListings is set to true to delete existing listing with the same id, default = false
     * @return void
     * @throws InvalidCSVFormatException in case the csv header doesn't match listing properties or if one of the csv record doesn't content the right data
     * @throws ReadFileException         is throw when is not possible to read the file
     */
    void uploadListingCSV(MultipartFile file, String csvDelimiter, boolean replacePreviousListings) throws InvalidCSVFormatException, ReadFileException;

    /**
     * Upload csv file with contact data and create contact records.
     *
     * @param file                  a csv file with contact data
     * @param csvDelimiter          character to delimiter the csv file, default is ','
     * @param clearPreviousContacts is set to true to delete existing contacts of listings ids in the file, default = false
     * @return void
     * @throws EntityNotFoundException   in case that there are not listings with the provided id
     * @throws InvalidCSVFormatException in case the csv header doesn't match contact properties or if one of the csv record doesn't content the right data
     * @throws ReadFileException         is throw when is not possible to read the file
     */
    void uploadContactCSV(MultipartFile file, String csvDelimiter, boolean clearPreviousContacts) throws EntityNotFoundException, InvalidCSVFormatException, ReadFileException;
}
