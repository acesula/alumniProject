package com.alumni.project.controller.contactsdetails;

import com.alumni.project.dto.contactdetails.ContactDetailsDto;
import com.alumni.project.service.contactdetails.ContactDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/contactDetails")
@RequiredArgsConstructor
public class ContactDetailsController {

    private final ContactDetailsService contactDetailsService;


    @GetMapping("/{id}")
    public ContactDetailsDto findByUserId(@PathVariable UUID id) {
        return contactDetailsService.findByUserId(id);
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
