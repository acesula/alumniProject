package com.alumni.project.controller.contactsdetails;

import com.alumni.project.dto.contactdetails.ContactDetailsDto;
import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.contactdetails.ContactDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/contactDetails")
@RequiredArgsConstructor
public class ContactDetailsController {

    private final ContactDetailsService contactDetailsService;

<<<<<<< HEAD
    @PostMapping("/{username}")
    public ResponseEntity<ErrorResponse> save(@Valid @PathVariable String username, @RequestBody ContactDetails contactDetails) {
        return contactDetailsService.saveContactDetails(username, contactDetails);
    }

    @GetMapping("/{email}")
    public ContactDetailsDto findByEmail(@PathVariable String email) {
        return contactDetailsService.findByEmail(email);
=======
    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
>>>>>>> 6ef8658e2fe6a08ac60418cd7b077e0b96c20368
    }

    @GetMapping
    public ContactDetailsDto findByUserId() {
        return contactDetailsService.findByUserId(authenticatedUser().getId());
    }


    @DeleteMapping("/{email}")
    public void deleteByEmail(@PathVariable String email) {
        contactDetailsService.deleteByEmail(email);
    }

    @PatchMapping("/{id}")
    public ContactDetailsDto update(@PathVariable UUID id, @RequestBody ContactDetailsDto contactDetailsDto) {
        return contactDetailsService.update(id, contactDetailsDto);
    }
}
