package com.alumni.project.controller.skills;

import com.alumni.project.dal.entity.Skills;
import com.alumni.project.dto.skills.SkillsDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.skills.SkillsService;
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

    private final SkillsService skillsService;

    @PostMapping("/{id}")
    public ResponseEntity<ErrorResponse> save(@Valid @PathVariable UUID id, @RequestBody Skills skill) {
        return skillsService.saveSkill(id, skill);
    }
    @GetMapping
    public List<SkillsDto> findAll() {
        return skillsService.findAll();
    }



    @GetMapping("/{id}")
    public List<SkillsDto> findByUser(@PathVariable UUID id){
        return skillsService.findById(id);
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

