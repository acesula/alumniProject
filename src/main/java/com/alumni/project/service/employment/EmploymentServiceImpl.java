package com.alumni.project.service.employment;

import com.alumni.project.dal.entity.Employment;
import com.alumni.project.dal.repository.EmploymentRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.employment.EmploymentDto;
import com.alumni.project.dto.error.ErrorResponse;
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
public class EmploymentServiceImpl implements EmploymentService {

    private final EmploymentRepository employmentRepository;
    private final UserRepository userRepository;
    private final MappingServiceImpl mappingService;

    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    @Transactional
    public void save(Employment employment) {
        var user = userRepository.findById(authenticatedUser().getId()).orElseThrow(RuntimeException::new);
        employment.setUser(user);
        user.getEmployments().add(employment);
        employmentRepository.save(employment);
    }

    public ResponseEntity<ErrorResponse> saveEmployment(Employment employment) {
        try {
            if (userRepository.existsById(authenticatedUser().getId())) {
                if (employmentRepository.existsByCompanyAndJobAndUser_Id(employment.getCompany(), employment.getJob(), authenticatedUser().getId())) {
                    ErrorResponse errorResponse = new ErrorResponse();
                    errorResponse.setMessage("Employment already exists!");
                    errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
                    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
                }
                save(employment);
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
    public List<EmploymentDto> findAll() {
        return employmentRepository.findAll()
                .stream()
                .map(mappingService::convertToEmploymentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmploymentDto> findByUserId() {
        return employmentRepository.findByUser_Id(authenticatedUser().getId())
                .stream()
                .map(mappingService::convertToEmploymentDto)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public EmploymentDto update(UUID id, EmploymentDto employmentNew) {
        var employment = employmentRepository.findById(id).orElseThrow(RuntimeException::new);
        employment.setCompany(employmentNew.getCompany());
        employment.setJob(employmentNew.getJob());
        employment.setStartDate(employmentNew.getStartDate());
        employment.setEndDate(employmentNew.getEndDate());
        employment.setStatus(employmentNew.isStatus());

        return mappingService.convertToEmploymentDto(employmentRepository.save(employment));
    }

    @Override
    public void delete(UUID id) {
        employmentRepository.deleteById(id);
    }

}

