package com.alumni.project.controller.skills;

import com.alumni.project.dal.entity.Skills;
import com.alumni.project.dto.skills.SkillsDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.skills.SkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/skills")
@RequiredArgsConstructor
public class SkillsController {

    private final SkillsService skillsService;


    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping
    public ResponseEntity<ErrorResponse> save(@RequestBody Skills skill) {
        return skillsService.saveSkill(authenticatedUser().getId(), skill);
    }

    @GetMapping
    public List<SkillsDto> findByUser() {
        return skillsService.findById(authenticatedUser().getId());
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

