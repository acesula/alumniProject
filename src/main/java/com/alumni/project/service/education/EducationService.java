package com.alumni.project.service.education;

import com.alumni.project.dal.entity.Education;
import com.alumni.project.dal.entity.Skills;
import com.alumni.project.dto.education.EducationDto;
import com.alumni.project.security.ErrorResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface EducationService {
    void save(UUID uuid, Education education);

    ResponseEntity<ErrorResponse> saveEducation(UUID uuid, Education education);

    List<EducationDto> findAll();

    List<EducationDto> findByUserId(UUID id);

    List<EducationDto> findByUser(String username);
    EducationDto update(UUID id, EducationDto dto);

    void delete(UUID id);
}
