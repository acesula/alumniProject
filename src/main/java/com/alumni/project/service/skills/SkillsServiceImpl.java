package com.alumni.project.service.skills;

import com.alumni.project.dal.entity.Skills;
import com.alumni.project.dal.repository.SkillsRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.skills.SkillsDto;
import com.alumni.project.dto.user.UserDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.mapping.MappingServiceImpl;
import com.alumni.project.service.user.UserServiceImpl;
import jakarta.transaction.Transactional;
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
    private final MappingServiceImpl mappingService;


    @Override
    public void save(UUID uuid, Skills skills) {
        var user = userRepository.findById(uuid).orElseThrow(RuntimeException::new);
        skills.setUser(user);
        user.getSkills().add(skills);
        skillsRepository.save(skills);
    }

    public ResponseEntity<ErrorResponse> saveSkill(UUID uuid, Skills skills) {
        try {
            if (userRepository.existsById(uuid)) {
                save(uuid, skills);
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
                .map(mappingService::convertToSkillsDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<SkillsDto> findById(UUID id) {
        return skillsRepository.findByUser_Id(id)
                .stream()
                .map(mappingService::convertToSkillsDto)
                .collect(Collectors.toList());
    }




    @Override
    public SkillsDto update(UUID id, Skills skillDto) {

        var skill = skillsRepository.findById(id).orElseThrow(RuntimeException::new);
        skill.setSkillField(skillDto.getSkillField());
        skill.setSkillDescription(skillDto.getSkillDescription());


        return mappingService.convertToSkillsDto(skillsRepository.save(skill));
    }

    @Override
    public void delete(UUID id) {
        skillsRepository.deleteById(id);
    }

}
