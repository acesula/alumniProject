package com.alumni.project.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ContactDetails extends Base {
    private String email;
    private String phoneNumber;
    private String country;
    private String city;
    private String zipCode;
    private String address;
    private String linkedIn;


    @OneToOne(mappedBy = "contactDetails")
    private User user;
}
