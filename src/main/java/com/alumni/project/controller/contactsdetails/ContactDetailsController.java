package com.alumni.project.controller.contactsdetails;

import com.alumni.project.dto.contactdetails.ContactDetailsDto;
import com.alumni.project.service.contactdetails.ContactDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/contactDetails")
@RequiredArgsConstructor
public class ContactDetailsController {

    private final ContactDetailsService contactDetailsService;



    @GetMapping
    public ContactDetailsDto findByUserId() {
        return contactDetailsService.findByUserId();
    }


    @DeleteMapping("/{email}")
    public void deleteByEmail(@PathVariable String email) {
        contactDetailsService.deleteByEmail(email);
    }

    @PatchMapping
    public ContactDetailsDto update(@RequestBody ContactDetailsDto contactDetailsDto) {
        return contactDetailsService.update(contactDetailsDto);
    }
}
