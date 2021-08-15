package com.autos24.test.controller.criteria;


/**
 * This class has the attributes to filter the listing search
 */
public class ListingFindCriteria {
    private Long id;
    private String make;
    private Double priceGreaterThanOrEqual;
    private Double priceLessThan;
    private Integer mileageGreaterThanOrEqual;
    private Integer mileageLessThan;
    private String sellerType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Double getPriceGreaterThanOrEqual() {
        return priceGreaterThanOrEqual;
    }

    public void setPriceGreaterThanOrEqual(Double priceGreaterThanOrEqual) {
        this.priceGreaterThanOrEqual = priceGreaterThanOrEqual;
    }

    public Double getPriceLessThan() {
        return priceLessThan;
    }

    public void setPriceLessThan(Double priceLessThan) {
        this.priceLessThan = priceLessThan;
    }

    public Integer getMileageGreaterThanOrEqual() {
        return mileageGreaterThanOrEqual;
    }

    public void setMileageGreaterThanOrEqual(Integer mileageGreaterThanOrEqual) {
        this.mileageGreaterThanOrEqual = mileageGreaterThanOrEqual;
    }

    public Integer getMileageLessThan() {
        return mileageLessThan;
    }

    public void setMileageLessThan(Integer mileageLessThan) {
        this.mileageLessThan = mileageLessThan;
    }

    public String getSellerType() {
        return sellerType;
    }

    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }
}
