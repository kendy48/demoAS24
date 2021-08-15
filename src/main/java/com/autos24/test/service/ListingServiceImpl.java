package com.autos24.test.service;

import com.autos24.test.controller.criteria.ListingFindCriteria;
import com.autos24.test.controller.mapper.contact.CSVContactMapper;
import com.autos24.test.controller.mapper.listing.CSVListingMapper;
import com.autos24.test.domain.ListingEntity;
import com.autos24.test.dto.ContactDTO;
import com.autos24.test.exception.EntityNotFoundException;
import com.autos24.test.exception.InvalidCSVFormatException;
import com.autos24.test.exception.ReadFileException;
import com.autos24.test.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;
import static com.autos24.test.domain.specification.ListingSpecification.*;

@Service
public class ListingServiceImpl implements ListingService {

    private final ListingRepository listingRepository;

    @Autowired
    public ListingServiceImpl(final ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    @Override
    public List<ListingEntity> find(ListingFindCriteria criteria) {
        return listingRepository.findAll(where(withId(criteria.getId()))
                .and(withMake(criteria.getMake()))
                .and(withPriceGreaterThanOrEqual(criteria.getPriceGreaterThanOrEqual()))
                .and(withPriceLessThan(criteria.getPriceLessThan()))
                .and(withMileageGreaterThanOrEqual(criteria.getMileageGreaterThanOrEqual()))
                .and(withMileageLessThan(criteria.getMileageLessThan()))
                .and(withSellerType(criteria.getSellerType())));
    }

    @Override
    @Transactional
    public void uploadListingCSV(MultipartFile file, String csvDelimiter, boolean replacePreviousListings) throws InvalidCSVFormatException, ReadFileException {
        List<ListingEntity> listings = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line = br.readLine();
            CSVListingMapper csvListingMapper = new CSVListingMapper(line.split(csvDelimiter));
            while ((line = br.readLine()) != null) {
                listings.add(csvListingMapper.makeListingEntity(line.split(csvDelimiter)));
            }
            if (replacePreviousListings)
                deletePreviousListings(listings);
            listingRepository.saveAll(listings);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ReadFileException(e.getMessage());
        }
    }


    @Override
    @Transactional
    public void uploadContactCSV(MultipartFile file, String csvDelimiter, boolean clearPreviousContacts)
            throws EntityNotFoundException, InvalidCSVFormatException, ReadFileException {
        Map<Long, ListingEntity> listingMap = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line = br.readLine();
            CSVContactMapper csvContactMapper = new CSVContactMapper(line.split(csvDelimiter));
            while ((line = br.readLine()) != null) {
                ContactDTO contactDTO = csvContactMapper.makeContactDTO(line.split(csvDelimiter));
                ListingEntity listing = null;
                if (listingMap.get(contactDTO.getListingId()) == null) {
                    listing = findListingChecked(contactDTO.getListingId());
                    listingMap.put(listing.getId(), listing);
                    if (clearPreviousContacts) {
                        listing.getContacts().clear();
                    }
                } else {
                    listing = listingMap.get(contactDTO.getListingId());
                }
                listing.getContacts().add(contactDTO.getContactDate());
            }
            listingRepository.saveAll(listingMap.values());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ReadFileException(e.getMessage());
        }
    }

    private void deletePreviousListings(List<ListingEntity> listings) {
        List<Long> ids = listings.parallelStream()
                .map(ListingEntity::getId).collect(Collectors.toList());
        listingRepository.deleteAllById(ids);
    }

    private ListingEntity findListingChecked(Long listingId) throws EntityNotFoundException {
        return listingRepository.findById(listingId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find listing entity"));
    }
}
