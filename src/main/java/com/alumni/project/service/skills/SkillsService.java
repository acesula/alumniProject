package com.alumni.project.service.skills;

import com.alumni.project.dal.entity.Skills;
import com.alumni.project.dal.entity.User;

import java.util.List;
import java.util.UUID;

public interface SkillsService {
    void save(String username, Skills skills);

    List<Skills> findAll();

    Skills findById(UUID id);

    Skills update(UUID id, Skills dto);

    void delete(UUID id);
}
