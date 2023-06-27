package com.alumni.project.controller;

import com.alumni.project.dal.entity.Education;
import com.alumni.project.dal.entity.Skills;
import com.alumni.project.dto.user.GetUserDto;
import com.alumni.project.dto.user.UserDto;
import com.alumni.project.service.education.EducationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationServiceImpl educationService;
    @PostMapping
    public Education save(@RequestBody Education dto) {
        return educationService.save(dto);
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
