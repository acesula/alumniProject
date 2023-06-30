package com.alumni.project.controller.education;

import com.alumni.project.dal.entity.Education;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.education.EducationServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationServiceImpl educationService;
    @PostMapping
    public ResponseEntity<ErrorResponse> save(@Valid @PathVariable String username, @RequestBody Education education) {
        return educationService.saveEducation(username, education);
    }
    @GetMapping
    public List<Education> findAll() {
        return educationService.findAll();
    }

    @GetMapping("/{id}")
    public Education findById(@PathVariable UUID id) {
        return educationService.findById(id);
    }
    @PatchMapping("/{id}")
    public Education update(@PathVariable UUID id, @RequestBody Education dto) {
        return educationService.update(id,dto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        educationService.delete(id);
    }
}
