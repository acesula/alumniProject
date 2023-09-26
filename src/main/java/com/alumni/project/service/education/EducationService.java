package com.alumni.project.service.education;

import com.alumni.project.dal.entity.Education;
import com.alumni.project.dto.education.EducationDto;
import com.alumni.project.dto.error.ErrorResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface EducationService {
    void save(Education education);

    ResponseEntity<ErrorResponse> saveEducation(Education education);

    List<EducationDto> findAll();

    List<EducationDto> findByUserId();

    EducationDto update(UUID id, EducationDto dto);

    void delete(UUID id);
}
