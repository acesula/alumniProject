package com.alumni.project.controller.contactsdetails;

import com.alumni.project.dal.entity.ContactDetails;
import com.alumni.project.service.contactdetails.ContactDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/contactDetails")
@RequiredArgsConstructor
public class ContactDetailsController {

    private final ContactDetailsServiceImp contactDetailsService;

    @PostMapping
    public ContactDetails save(@RequestBody ContactDetails contactDetails){
        return contactDetailsService.save(contactDetails);
    }

    @GetMapping
    public List<ContactDetails> findAll(){
        return contactDetailsService.findAll();
    }

    @GetMapping("/{id}")
    public ContactDetails findById(@PathVariable UUID id){
        return contactDetailsService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id){
        contactDetailsService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public ContactDetails update(@PathVariable UUID id, @RequestBody ContactDetails contactDetails){
        return contactDetailsService.update(id, contactDetails);
    }
}
