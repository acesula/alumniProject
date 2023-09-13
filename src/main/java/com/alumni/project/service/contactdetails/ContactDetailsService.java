package com.alumni.project.service.contactdetails;

import com.alumni.project.dal.entity.ContactDetails;
import com.alumni.project.dto.contactdetails.ContactDetailsDto;
import java.util.*;
import java.util.UUID;

public interface ContactDetailsService {



    void save(String username, ContactDetails contactDetails);

    ContactDetailsDto findByUserId(UUID id);

    List<ContactDetailsDto> findByUser(String username);
    void deleteByEmail(String username);

    ContactDetailsDto update(UUID uuid, ContactDetailsDto contactDetailsDto);
}
