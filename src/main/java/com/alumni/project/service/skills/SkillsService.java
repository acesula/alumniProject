package com.alumni.project.service.skills;

import com.alumni.project.dal.entity.Skills;
import com.alumni.project.dto.skills.SkillsDto;
import com.alumni.project.dto.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface SkillsService {
    void save(Skills skills);

    ResponseEntity<ErrorResponse> saveSkill(Skills skill);

    List<SkillsDto> findAll();

    List<SkillsDto> findById();

    SkillsDto update(UUID id, Skills dto);

    void delete(UUID id);
}
