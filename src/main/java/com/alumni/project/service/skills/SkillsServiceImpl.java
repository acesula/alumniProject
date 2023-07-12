package com.alumni.project.service.skills;

import com.alumni.project.dal.entity.Skills;
import com.alumni.project.dal.entity.User;
import com.alumni.project.dal.repository.SkillsRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.skills.SkillsDto;
import com.alumni.project.dto.user.GetUserDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillsServiceImpl implements SkillsService {
    private final SkillsRepository skillsRepository;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;


    @Override
    public void save(String username, Skills skills) {
        var user = userRepository.findByUsername(username);
        skills.setUser(user);
        user.getSkills().add(skills);
        skillsRepository.save(skills);
    }

    public ResponseEntity<ErrorResponse> saveSkill(String username, Skills skills) {
        try {
            if (userRepository.existsByUsername(username)) {
                save(username, skills);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage("User could not be found!");
                errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse();
            error.setMessage(e.getMessage());
            error.setErrorCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<SkillsDto> findAll() {

        return skillsRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<SkillsDto> findByUser(UUID id, String username) {
        GetUserDto user = this.userService.findById(id);
        return skillsRepository.findByUser_Username(user.getUsername())
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public SkillsDto findById(UUID id) {

        var optional = skillsRepository.findById(id);
        if (optional.isPresent()) {
            return map(optional.get());
        }
        throw new RuntimeException("Skill not found");
    }


    @Override
    public SkillsDto update(UUID id, Skills skillDto) {

        var skill = skillsRepository.findById(id).orElseThrow(RuntimeException::new);
        skill.setSkillField(skillDto.getSkillField());
        skill.setSkillField(skillDto.getSkillField());


        return map(skillsRepository.save(skill));
    }

    @Override
    public void delete(UUID id) {
        skillsRepository.deleteById(id);
    }

    private SkillsDto map(Skills skills) {
        var dto = new SkillsDto();
        dto.setSkillDescription(skills.getSkillDescription());
        dto.setSkillField(skills.getSkillField());

        return dto;
    }
}
