package com.alumni.project.dal.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetails extends Base {
    private String email;
    private String phoneNumber;
    private String country;
    private String city;
    private String zipCode;
    private String address;
    private String linkedIn;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "contactDetails")
    private User user;
}
