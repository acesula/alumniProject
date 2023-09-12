package com.alumni.project.controller.contactsdetails;

import com.alumni.project.dal.entity.ContactDetails;
import com.alumni.project.dto.contactdetails.ContactDetailsDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.contactdetails.ContactDetailsServiceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/contactDetails")
@RequiredArgsConstructor
public class ContactDetailsController {

    private final ContactDetailsServiceImp contactDetailsService;

    @PostMapping("/{username}")
    public ResponseEntity<ErrorResponse> save(@Valid @PathVariable String username, @RequestBody ContactDetails contactDetails) {
        return contactDetailsService.saveContactDetails(username, contactDetails);
    }

    @GetMapping("/{email}")
    public ContactDetailsDto findByEmail(@PathVariable String email) {
        return contactDetailsService.findByEmail(email);
    }

    @GetMapping("/{username}")
    public List<ContactDetailsDto> findByUser(@PathVariable String username) {
        return contactDetailsService.findByUser(username);
    }

    @DeleteMapping("/{email}")
    public void deleteByEmail(@PathVariable String email) {
        contactDetailsService.deleteByEmail(email);
    }

    @PatchMapping("/{email}")
    public ContactDetailsDto update(@PathVariable String email, @RequestBody ContactDetailsDto contactDetailsDto) {
        return contactDetailsService.update(email, contactDetailsDto);
    }
}
