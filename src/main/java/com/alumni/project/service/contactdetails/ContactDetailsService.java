package com.alumni.project.service.contactdetails;

import com.alumni.project.dal.entity.ContactDetails;
import com.alumni.project.dto.contactdetails.ContactDetailsDto;


public interface ContactDetailsService {
    void save(ContactDetails contactDetails);

    ContactDetailsDto findByUserId();


    void deleteByEmail(String username);

    ContactDetailsDto update(ContactDetailsDto contactDetailsDto);
}
