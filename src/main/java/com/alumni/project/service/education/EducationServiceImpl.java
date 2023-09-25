package com.alumni.project.service.education;

import com.alumni.project.dal.entity.Education;
import com.alumni.project.dal.repository.EducationRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.education.EducationDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.mapping.MappingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;
    private final UserRepository userRepository;
    private final MappingServiceImpl mappingService;

    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    @Transactional
    public void save(Education education) {
        var user = userRepository.findById(authenticatedUser().getId()).orElseThrow(RuntimeException::new);
        education.setUser(user);
        user.getEducations().add(education);
        educationRepository.save(education);
    }

    public ResponseEntity<ErrorResponse> saveEducation(Education education) {
        try {
            if (userRepository.existsById(authenticatedUser().getId())) {
                if (educationRepository.existsByInstitutionAndDegreeAndFieldOfStudyAndUser_Id(education.getInstitution(),
                        education.getDegree(),
                        education.getFieldOfStudy(),
                        authenticatedUser().getId())) {
                    ErrorResponse errorResponse = new ErrorResponse();
                    errorResponse.setMessage("Education already exists!");
                    errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
                    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
                }
                save(education);
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
    public List<EducationDto> findByUserId() {
        return educationRepository.findByUser_Id(authenticatedUser().getId())
                .stream()
                .map(mappingService::convertToEducationDto)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public EducationDto update(UUID id, EducationDto education) {
        var ed = educationRepository.findById(id).orElseThrow(RuntimeException::new);
        ed.setInstitution(education.getInstitution());
        ed.setDegree(education.getDegree());
        ed.setFieldOfStudy(education.getFieldOfStudy());
        ed.setStartYear(education.getStartYear());
        ed.setEndYear(education.getEndYear());
        ed.setFinished(education.isFinished());

        return mappingService.convertToEducationDto(educationRepository.save(ed));
    }

    @Override
    public void delete(UUID id) {
        educationRepository.deleteById(id);
    }
}
