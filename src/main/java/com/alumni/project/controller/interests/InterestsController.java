package com.alumni.project.controller.interests;

import com.alumni.project.dal.entity.Interests;
import com.alumni.project.dto.interests.InterestsDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.interests.InterestsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/interests")
@RequiredArgsConstructor
public class InterestsController {

    private final InterestsService interestsService;


    @PostMapping()
    public ResponseEntity<ErrorResponse> save(@RequestBody Interests interest) {
        return interestsService.saveInterest(interest);
    }


    @GetMapping
    public List<InterestsDto> findByUser() {
        return interestsService.findByUser();
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        interestsService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public InterestsDto update(@PathVariable UUID id, @RequestBody InterestsDto interest) {
        return interestsService.update(id, interest);
    }

}
