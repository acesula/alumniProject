package com.alumni.project.controller.skills;

import com.alumni.project.dal.entity.Skills;
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
    public List<Skills> findAll() {
        return skillsService.findAll();
    }

    @GetMapping("/{id}")
    public Skills findById(@PathVariable UUID id) {
        return skillsService.findById(id);
    }

    @PatchMapping("/{id}")
    public Skills update(@PathVariable UUID id, @RequestBody Skills dto) {
        return skillsService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        skillsService.delete(id);
    }
}

