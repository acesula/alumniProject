package com.alumni.project.controller.skills;

import com.alumni.project.dal.entity.Skills;
import com.alumni.project.dto.skills.SkillsDto;
import com.alumni.project.dto.user.GetUserDto;
import com.alumni.project.dto.user.UserDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.skills.SkillsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/skills")
@RequiredArgsConstructor
public class SkillsController {

    private final SkillsServiceImpl skillsService;

    @PostMapping("/{username}")
    public ResponseEntity<ErrorResponse> save(@Valid @PathVariable String username, @RequestBody Skills skill) {
        return skillsService.saveSkill(username, skill);
    }
    @GetMapping
    public List<SkillsDto> findAll() {
        return skillsService.findAll();
    }

    @GetMapping("/{id}")
    public SkillsDto findById(@PathVariable UUID id) {
        return skillsService.findById(id);
    }

    @GetMapping("/{username}")
    public List<SkillsDto> findByUser(@PathVariable UUID id, String username){
        return skillsService.findByUser(id,username);
    }

    @PatchMapping("/{id}")
    public SkillsDto update(@PathVariable UUID id, @RequestBody Skills dto) {
        return skillsService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        skillsService.delete(id);
    }
}

