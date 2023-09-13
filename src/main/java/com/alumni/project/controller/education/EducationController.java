package com.alumni.project.controller.education;

import com.alumni.project.dal.entity.Education;
import com.alumni.project.dto.education.EducationDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.education.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping
    public ResponseEntity<ErrorResponse> save(@RequestBody Education education) {
        return educationService.saveEducation(authenticatedUser().getId(), education);
    }

//    @GetMapping
//    public List<EducationDto> findAll() {
//        return educationService.findAll();
//    }


    @GetMapping
    public List<EducationDto> findAll() {
        return educationService.findAll();
    }

    @GetMapping("/{username}")
    public List<EducationDto> findByUser(@PathVariable String username) {
        return educationService.findByUser(username);
    }

    @GetMapping("/user-id/{id}")
    public List<EducationDto> findById(@PathVariable UUID id) {
        return educationService.findByUserId(id);
    }

    @PatchMapping("/{id}")
    public EducationDto update(@PathVariable UUID id, @RequestBody EducationDto dto) {
        return educationService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        educationService.delete(id);
    }
}
