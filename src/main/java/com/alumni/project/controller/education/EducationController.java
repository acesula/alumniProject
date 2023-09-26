package com.alumni.project.controller.education;

import com.alumni.project.dal.entity.Education;
import com.alumni.project.dto.education.EducationDto;
import com.alumni.project.dto.error.ErrorResponse;
import com.alumni.project.service.education.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;



    @PostMapping
    public ResponseEntity<ErrorResponse> save(@RequestBody Education education) {
        return educationService.saveEducation(education);
    }

    @GetMapping
    public List<EducationDto> findByUserId() {
        return educationService.findByUserId();
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
