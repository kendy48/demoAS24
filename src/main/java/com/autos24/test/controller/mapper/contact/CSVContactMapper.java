package com.autos24.test.controller.mapper.contact;

import com.autos24.test.dto.ContactDTO;
import com.autos24.test.exception.InvalidCSVFormatException;

/**
 * This class validates that imported csv files has the right format for contact
 */
public class CSVContactMapper {

    private static int FIELDS_TO_READ_FROM_CSV = 2;

    /**
     * header position
     **/
    private int listingIdPosition = 0;
    private int contactDatePosition = 0;

    public CSVContactMapper(String[] headers) throws InvalidCSVFormatException {
        if (headers == null || headers.length != FIELDS_TO_READ_FROM_CSV)
            throw new InvalidCSVFormatException("Invalid headers on csv file");
        for (int i = 0; i < headers.length; i++) {
            switch (headers[i].replace("\"", "").toLowerCase()) {
                case "listing_id":
                    listingIdPosition = i;
                    break;
                case "contact_date":
                    contactDatePosition = i;
                    break;
                default:
                    throw new InvalidCSVFormatException("Unknown header for Contact entity");
            }
        }
    }

    public ContactDTO makeContactDTO(String[] values) throws InvalidCSVFormatException {
        if (values == null || values.length != FIELDS_TO_READ_FROM_CSV)
            throw new InvalidCSVFormatException("Invalid import format for contact");

        return new ContactDTO(Long.valueOf(values[listingIdPosition]), Long.valueOf(values[contactDatePosition]));
    }
}
