package com.autos24.test.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.*;

@Entity
@Table(name = "listing")
public class ListingEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Make can not be null!")
    private String make;

    @Column(nullable = false)
    @NotNull(message = "Price can not be null!")
    @Positive(message = "Price have to be higher than zero!")
    private Double price;

    @Column(nullable = false)
    @NotNull(message = "Mileage can not be null!")
    @PositiveOrZero(message = "Mileage have to be higher or equal to zero!")
    private Long mileage;

    @Column(nullable = false)
    @NotNull(message = "Seller type can not be null!")
    private String sellerType;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Long> contacts;


    public ListingEntity() {
        this(0l, null, 0.0, 0l, null, null);
    }

    public ListingEntity(Long id, String make, Double price, Long mileage, String sellerType, Set<Long> contacts) {
        this.id = id;
        this.make = make;
        this.price = price;
        this.mileage = mileage;
        this.sellerType = sellerType;
        this.contacts = contacts != null ? contacts : new HashSet<>();
    }


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


    public Double getPrice() {
        return price;
    }


    public void setPrice(Double price) {
        this.price = price;
    }


    public Long getMileage() {
        return mileage;
    }


    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }


    public String getSellerType() {
        return sellerType;
    }


    public void setSellerType(String sellerType) {
        this.sellerType = sellerType;
    }


    public Set<Long> getContacts() {
        return contacts;
    }


    public void setContacts(Set<Long> contacts) {
        this.contacts = contacts;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ListingEntity that = (ListingEntity) o;
        return Objects.equals(id, that.id) && make.equals(that.make) && price.equals(that.price) && mileage.equals(that.mileage) && sellerType.equals(that.sellerType);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, make, price, mileage, sellerType);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ListingEntity{");
        sb.append("id=").append(id);
        sb.append(", make='").append(make).append('\'');
        sb.append(", price=").append(price);
        sb.append(", mileage=").append(mileage);
        sb.append(", sellerType='").append(sellerType).append('\'');
        sb.append(", contacts=").append(contacts);
        sb.append('}');
        return sb.toString();
    }
}
