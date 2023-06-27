package com.alumni.project.controller;

import com.alumni.project.dal.entity.Skills;
import com.alumni.project.dto.user.GetUserDto;
import com.alumni.project.dto.user.UserDto;
import com.alumni.project.service.skills.SkillsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/skills")
@RequiredArgsConstructor
public class SkillsController {

    private final SkillsServiceImpl skillsService;
    @PostMapping
    public Skills save(@RequestBody Skills skill) {
        return skillsService.save(skill);
    }

    @GetMapping
    public List<Skills> findAll() {
        return skillsService.findAll();
    }

    @GetMapping("/{id}")
    public Skills findById(@PathVariable UUID id) {
        return skillsService.findById(id);
    }
    @PatchMapping("/{id}")
    public Skills update(@PathVariable UUID id, @RequestBody Skills dto) {
        return skillsService.update(id,dto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        skillsService.delete(id);
    }
}

