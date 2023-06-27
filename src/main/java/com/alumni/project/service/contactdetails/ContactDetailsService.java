package com.alumni.project.service.contactdetails;

import com.alumni.project.dal.entity.ContactDetails;
import com.alumni.project.dto.contactdetails.ContactDetailsDto;

import java.util.List;
import java.util.UUID;

public interface ContactDetailsService {

    ContactDetails save(ContactDetails contactDetails);

    List<ContactDetails> findAll();

    ContactDetails findById(UUID id);

    void deleteById(UUID id);

    ContactDetails update(UUID id, ContactDetails contactDetails);
}
