package com.autos24.test.domain.specification;

import com.autos24.test.domain.ListingEntity;
import org.springframework.data.jpa.domain.Specification;

/**
 * Create Specification for attributes of the listing to be use on the search
 * To use the like functionality of String attributes a '%' should be add it
 * at the end or at the beginning of the String parameter
 */
public class ListingSpecification {

    public static Specification<ListingEntity> withId(Long id) {
        return id != null ?
                (root, query, builder) -> builder.equal(root.get("id"), id) : null;
    }

    public static Specification<ListingEntity> withMake(String make) {
        return make != null ?
                (root, query, builder) -> builder.like(root.get("make"), make) : null;
    }

    public static Specification<ListingEntity> withPriceGreaterThanOrEqual(Double price) {
        return price != null ?
                (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("price"), price) : null;
    }


    public static Specification<ListingEntity> withPriceLessThan(Double price) {
        return price != null ?
                (root, query, builder) -> builder.lessThan(root.get("price"), price) : null;
    }


    public static Specification<ListingEntity> withMileageGreaterThanOrEqual(Integer mileage) {
        return mileage != null ?
                (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("mileage"), mileage) : null;
    }


    public static Specification<ListingEntity> withMileageLessThan(Integer mileage) {
        return mileage != null ?
                (root, query, builder) -> builder.lessThan(root.get("mileage"), mileage) : null;
    }

    public static Specification<ListingEntity> withSellerType(String sellerType) {
        return sellerType != null ?
                (root, query, builder) -> builder.like(root.get("sellerType"), sellerType) : null;
    }

}
