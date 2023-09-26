package com.alumni.project.service.contactdetails;

import com.alumni.project.dal.entity.ContactDetails;
import com.alumni.project.dal.repository.ContactDetailsRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.contactdetails.ContactDetailsDto;
import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.mapping.MappingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactDetailsServiceImp implements ContactDetailsService {

    private final UserRepository userRepository;
    private final ContactDetailsRepository contactDetailsRepository;
    private final MappingServiceImpl mappingService;


    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    @Override
    @Transactional
    public void save(ContactDetails contactDetails) {
        var user = userRepository.findById(authenticatedUser().getId()).orElseThrow(RuntimeException::new);
        contactDetails.setUser(user);
        user.setContactDetails(contactDetails);
        contactDetailsRepository.save(contactDetails);
    }


    @Override
    public ContactDetailsDto findByUserId() {
        var optional = contactDetailsRepository.findByUser_Id(authenticatedUser().getId());
        if(optional.isPresent()){
            return mappingService.convertToContactDetailsDto(optional.get());
        }
        throw new RuntimeException("Could not find contact details");
    }


    @Override
    public void deleteByEmail(String email) {
        contactDetailsRepository.deleteByEmail(email);
    }

    @Override
    @Transactional
    public ContactDetailsDto update(ContactDetailsDto contactDetailsDto) {
        var contact = contactDetailsRepository.findByUser_Id(authenticatedUser().getId()).orElseThrow(RuntimeException::new);
        contact.setAddress(contactDetailsDto.getAddress());
        contact.setEmail(contactDetailsDto.getEmail());
        contact.setCountry(contactDetailsDto.getCountry());
        contact.setCity(contactDetailsDto.getCity());
        contact.setPhoneNumber(contactDetailsDto.getPhoneNumber());
        contact.setLinkedIn(contactDetailsDto.getLinkedIn());
        contact.setZipCode(contactDetailsDto.getZipCode());

        return mappingService.convertToContactDetailsDto(contactDetailsRepository.save(contact));
    }
}
