package com.alumni.project.service.education;

import com.alumni.project.dal.entity.Education;
import com.alumni.project.dal.repository.EducationRepository;
import com.alumni.project.dal.repository.SkillsRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.education.EducationDto;
import com.alumni.project.dto.user.GetUserDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.mapping.MappingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;
    private final UserRepository userRepository;
    private final MappingServiceImpl mappingService;

    @Override
    public void save(String username, Education education) {
        var user = userRepository.findByUsername(username);
        education.setUser(user);
        user.getEducations().add(education);
        educationRepository.save(education);
    }

    public ResponseEntity<ErrorResponse> saveEducation(String username, Education education) {
        try {
            if (userRepository.existsByUsername(username)) {
                if (educationRepository.existsByInstitutionAndDegreeAndFieldOfStudy(education.getInstitution(),
                        education.getDegree(),
                        education.getFieldOfStudy())) {
                    ErrorResponse errorResponse = new ErrorResponse();
                    errorResponse.setMessage("Education already exists!");
                    errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
                    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
                }
                save(username, education);
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
    public List<EducationDto> findAll() {
        return educationRepository.findAll()
                .stream()
                .map(mappingService::convertToEducationDto)
                .collect(Collectors.toList());
    }

    @Override
    public EducationDto findById(UUID id) {
        var optional = educationRepository.findById(id);
        if (optional.isPresent()) {
            return mappingService.convertToEducationDto(optional.get());
        }
        throw new RuntimeException("Education not found");
    }

    @Override
    public List<EducationDto> findByUser(String username) {
        return educationRepository.findByUser_Username(username)
                .stream()
                .map(mappingService::convertToEducationDto)
                .collect(Collectors.toList());
    }

    @Override
    public EducationDto update(UUID id, EducationDto education) {
        var ed = educationRepository.findById(id).orElseThrow(RuntimeException::new);
        ed.setInstitution(education.getInstitution());
        ed.setDegree(education.getDegree());
        ed.setFieldOfStudy(education.getFieldOfStudy());
        ed.setStartDate(education.getStartDate());
        ed.setEndDate(education.getEndDate());
        ed.setFinished(education.isFinished());

        return mappingService.convertToEducationDto(educationRepository.save(ed));
    }

    @Override
    public void delete(UUID id) {
        educationRepository.deleteById(id);
    }
}
