package com.alumni.project.service.education;

import com.alumni.project.dal.entity.Education;
import com.alumni.project.dal.entity.Skills;
import com.alumni.project.dto.education.EducationDto;

import java.util.List;
import java.util.UUID;

public interface EducationService {
    void save(String username, Education education);

    List<EducationDto> findAll();

    EducationDto findById(UUID id);

    List<EducationDto> findByUser(String username);
    Education update(UUID id, Education dto);

    void delete(UUID id);
}
