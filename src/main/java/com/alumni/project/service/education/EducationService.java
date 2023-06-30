package com.alumni.project.service.education;

import com.alumni.project.dal.entity.Education;
import com.alumni.project.dal.entity.Skills;

import java.util.List;
import java.util.UUID;

public interface EducationService {
    void save(String username, Education education);

    List<Education> findAll();

    Education findById(UUID id);

    Education update(UUID id, Education dto);

    void delete(UUID id);
}
