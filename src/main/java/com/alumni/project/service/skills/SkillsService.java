package com.alumni.project.service.skills;

import com.alumni.project.dal.entity.Skills;
import com.alumni.project.dal.entity.User;
import com.alumni.project.dto.skills.SkillsDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface SkillsService {
    void save(String username, Skills skills);

    List<SkillsDto> findAll();

    List<SkillsDto> findByUser(UUID id,String username);

    SkillsDto findById(UUID id);

    SkillsDto update(UUID id, Skills dto);

    void delete(UUID id);
}
