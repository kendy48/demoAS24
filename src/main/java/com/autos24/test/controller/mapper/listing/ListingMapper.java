package com.autos24.test.controller.mapper.listing;

import com.autos24.test.domain.ListingEntity;
import com.autos24.test.dto.ListingDTO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ListingMapper {

    public static ListingDTO makeListingDTO(ListingEntity listingEntity) {
        if (listingEntity == null)
            return null;

        return new ListingDTO(listingEntity.getId(), listingEntity.getMake(), listingEntity.getPrice(),
                listingEntity.getMileage(), listingEntity.getSellerType(), listingEntity.getContacts());
    }

    public static List<ListingDTO> makeListingDTOList(Collection<ListingEntity> listings) {
        return listings.stream()
                .map(ListingMapper::makeListingDTO)
                .collect(Collectors.toList());
    }
}
