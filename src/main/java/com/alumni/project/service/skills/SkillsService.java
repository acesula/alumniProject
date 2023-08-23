package com.alumni.project.service.skills;

import com.alumni.project.dal.entity.Skills;
import com.alumni.project.dal.entity.User;
import com.alumni.project.dto.skills.SkillsDto;
import com.alumni.project.security.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface SkillsService {
    void save(UUID uuid, Skills skills);

    ResponseEntity<ErrorResponse> saveSkill(UUID id, Skills skill);

    List<SkillsDto> findAll();

    List<SkillsDto> findById(UUID id);

    SkillsDto update(UUID id, Skills dto);

    void delete(UUID id);
}
