package com.autos24.test.dto;

public class ContactDTO {

    private Long listingId;

    private Long contactDate;

    public ContactDTO(Long listingId, Long contactDate) {
        this.listingId = listingId;
        this.contactDate = contactDate;
    }


    public Long getListingId() {
        return listingId;
    }


    public void setListingId(Long listingId) {
        this.listingId = listingId;
    }


    public Long getContactDate() {
        return contactDate;
    }


    public void setContactDate(Long contactDate) {
        this.contactDate = contactDate;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContactDTO{");
        sb.append("listingId=").append(listingId);
        sb.append(", contactDate=").append(contactDate);
        sb.append('}');
        return sb.toString();
    }
}
