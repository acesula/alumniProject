package com.alumni.project.service.contactdetails;

import com.alumni.project.dal.entity.ContactDetails;
import com.alumni.project.dal.repository.ContactDetailsRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.contactdetails.ContactDetailsDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.mapping.MappingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactDetailsServiceImp implements ContactDetailsService {

    private final UserRepository userRepository;
    private final ContactDetailsRepository contactDetailsRepository;
    private final MappingServiceImpl mappingService;



    @Override
    public void save(String username, ContactDetails contactDetails) {
        var user = userRepository.findByUsername(username);
        contactDetails.setUser(user);
        user.setContactDetails(contactDetails);
        contactDetailsRepository.save(contactDetails);
    }

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
    public ContactDetailsDto findByUserId(UUID uuid) {
        var optional = contactDetailsRepository.findByUser_Id(uuid);
        if(optional.isPresent()){
            return mappingService.convertToContactDetailsDto(optional.get());
        }
        throw new RuntimeException("Could not find contact details");
    }

    @Override
    public List<ContactDetailsDto> findByUser(String username) {
        return contactDetailsRepository.findByUser_Username(username)
                .stream()
                .map(mappingService::convertToContactDetailsDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByEmail(String email) {
        contactDetailsRepository.deleteByEmail(email);
    }

    @Override
    @Transactional
    public ContactDetailsDto update(UUID uuid, ContactDetailsDto contactDetailsDto) {
        var contact = contactDetailsRepository.findByUser_Id(uuid).orElseThrow(RuntimeException::new);
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
