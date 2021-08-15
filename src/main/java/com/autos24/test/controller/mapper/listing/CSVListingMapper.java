package com.autos24.test.controller.mapper.listing;

import com.autos24.test.domain.ListingEntity;
import com.autos24.test.exception.InvalidCSVFormatException;

/**
 * This class validates that imported csv files has the right format for listings
 */
public class CSVListingMapper {

    private static int FIELDS_TO_READ_FROM_CSV = 5;

    /**
     * header position
     **/
    private int idPosition = 0;
    private int makePosition = 0;
    private int pricePosition = 0;
    private int mileagePosition = 0;
    private int sellerTypePosition = 0;

    public CSVListingMapper(String[] headers) throws InvalidCSVFormatException {
        if (headers == null || headers.length != FIELDS_TO_READ_FROM_CSV)
            throw new InvalidCSVFormatException("Invalid headers on csv file");
        for (int i = 0; i < headers.length; i++) {
            switch (headers[i].replace("\"", "").toLowerCase()) {
                case "id":
                    idPosition = i;
                    break;
                case "make":
                    makePosition = i;
                    break;
                case "price":
                    pricePosition = i;
                    break;
                case "mileage":
                    mileagePosition = i;
                    break;
                case "seller_type":
                    sellerTypePosition = i;
                    break;
                default:
                    throw new InvalidCSVFormatException("Unknown header for Listing entity");
            }
        }
    }

    public ListingEntity makeListingEntity(String[] values) throws InvalidCSVFormatException {
        if (values == null || values.length != FIELDS_TO_READ_FROM_CSV)
            throw new InvalidCSVFormatException("Invalid import format for listing");

        return new ListingEntity(Long.valueOf(values[idPosition]), removeQuotes(values[makePosition]), Double.valueOf(values[pricePosition]),
                Long.valueOf(values[mileagePosition]), removeQuotes(values[sellerTypePosition]), null);
    }

    private String removeQuotes(String value){
        return value.replaceAll("\"", "");
    }
}
