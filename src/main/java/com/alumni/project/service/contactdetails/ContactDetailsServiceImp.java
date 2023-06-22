package com.alumni.project.service.contactdetails;

import com.alumni.project.dal.entity.ContactDetails;
import com.alumni.project.dal.repository.ContactDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactDetailsServiceImp implements ContactDetailsService {

    private final ContactDetailsRepository contactDetailsRepository;

    @Override
    public ContactDetails save(ContactDetails contactDetails) {
        return contactDetailsRepository.save(contactDetails);
    }

    @Override
    public List<ContactDetails> findAll() {
        return contactDetailsRepository.findAll();
    }

    @Override
    public ContactDetails findById(UUID id) {
        var optional = contactDetailsRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new RuntimeException("Could not find contact details");
    }

    @Override
    public void deleteById(UUID id) {
        contactDetailsRepository.deleteById(id);
    }

    @Override
    public ContactDetails update(UUID id, ContactDetails contactDetails) {
        var contact = contactDetailsRepository.findById(id).orElseThrow(RuntimeException::new);
        contact.setAddress(contactDetails.getAddress());
        contact.setEmail(contactDetails.getEmail());
        contact.setCountry(contactDetails.getCountry());
        contact.setCity(contactDetails.getCity());
        contact.setPhoneNumber(contactDetails.getPhoneNumber());
        contact.setLinkedIn(contactDetails.getLinkedIn());
        contact.setZipCode(contactDetails.getZipCode());

        return contactDetailsRepository.save(contact);
    }
}
