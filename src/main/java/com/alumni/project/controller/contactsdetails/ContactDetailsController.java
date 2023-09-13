package com.alumni.project.controller.contactsdetails;

import com.alumni.project.dto.contactdetails.ContactDetailsDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.contactdetails.ContactDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/contactDetails")
@RequiredArgsConstructor
public class ContactDetailsController {

    private final ContactDetailsService contactDetailsService;

    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
