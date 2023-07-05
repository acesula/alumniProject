package com.alumni.project.service.contactdetails;

import com.alumni.project.dal.entity.ContactDetails;
import com.alumni.project.dal.repository.ContactDetailsRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.contactdetails.ContactDetailsDto;
import com.alumni.project.security.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactDetailsServiceImp implements ContactDetailsService {

    private final ContactDetailsRepository contactDetailsRepository;
    private final UserRepository userRepository;

//    @Override
//    public void save(String username, ContactDetails contactDetails) {
//        var user = userRepository.findByUsername(username);
//        contactDetails.setUser(user);
//        user.setContactDetails(contactDetails);
//        contactDetailsRepository.save(contactDetails);
//    }
//
//    public ResponseEntity<ErrorResponse> saveContactDetails(String username, ContactDetails contactDetails){
//        try{
//            if (userRepository.existsByUsername(username)) {
//                if(contactDetailsRepository.existsByEmail(contactDetails.getEmail())){
//                    ErrorResponse errorResponse = new ErrorResponse();
//                    errorResponse.setMessage("User has already been given contact details!");
//                    errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
//                    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//                }
//                else{
//                    save(username, contactDetails);
//                    return new ResponseEntity<>(HttpStatus.OK);
//                }
//            } else {
//                ErrorResponse errorResponse = new ErrorResponse();
//                errorResponse.setMessage("User could not be found!");
//                errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
//                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//            }
//        }catch (IllegalArgumentException e){
//            ErrorResponse error = new ErrorResponse();
//            error.setMessage(e.getMessage());
//            error.setErrorCode(HttpStatus.BAD_REQUEST.value());
//            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//        }
//    }

    @Override
    public ContactDetailsDto findByEmail(String email) {
        var optional = contactDetailsRepository.findByEmail(email);
        if(optional.isPresent()){
            return map(optional.get());
        }
        throw new RuntimeException("Could not find contact details");
    }

    @Override
    public List<ContactDetailsDto> findByUser(String username) {
        return contactDetailsRepository.findByUser_Username(username)
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByEmail(String email) {
        contactDetailsRepository.deleteByEmail(email);
    }

    @Override
    public ContactDetailsDto update(String email, ContactDetailsDto contactDetailsDto) {
        var contact = contactDetailsRepository.findByEmail(email).orElseThrow(RuntimeException::new);
        contact.setAddress(contactDetailsDto.getAddress());
        contact.setEmail(contactDetailsDto.getEmail());
        contact.setCountry(contactDetailsDto.getCountry());
        contact.setCity(contactDetailsDto.getCity());
        contact.setPhoneNumber(contactDetailsDto.getPhoneNumber());
        contact.setLinkedIn(contactDetailsDto.getLinkedIn());
        contact.setZipCode(contactDetailsDto.getZipCode());

        return map(contactDetailsRepository.save(contact));
    }

    public ContactDetailsDto map(ContactDetails contactDetails){
        var dto = new ContactDetailsDto();
        dto.setEmail(contactDetails.getEmail());
        dto.setAddress(contactDetails.getAddress());
        dto.setCity(contactDetails.getCity());
        dto.setCountry(contactDetails.getCountry());
        dto.setZipCode(contactDetails.getZipCode());
        dto.setLinkedIn(contactDetails.getLinkedIn());

        return dto;
    }
}
