package com.alumni.project.service.employment;

import com.alumni.project.dal.entity.Employment;
import com.alumni.project.dal.entity.User;
import com.alumni.project.dal.repository.EmploymentRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.employment.EmploymentDto;
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
public class EmploymentServiceImpl implements EmploymentService {

    private final EmploymentRepository employmentRepository;
    private final UserRepository userRepository;
    private final MappingServiceImpl mappingService;

    @Override
    public void save(UUID uuid, Employment employment) {
        var user = userRepository.findById(uuid).orElseThrow(RuntimeException::new);
        employment.setUser(user);
        user.getEmployments().add(employment);
        employmentRepository.save(employment);
    }

    public ResponseEntity<ErrorResponse> saveEmployment(UUID uuid, Employment employment) {
        try {
            if (userRepository.existsById(uuid)) {
                if (employmentRepository.existsByCompanyAndJob(employment.getCompany(), employment.getJob())) {
                    ErrorResponse errorResponse = new ErrorResponse();
                    errorResponse.setMessage("Employment already exists!");
                    errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
                    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
                }
                save(uuid, employment);
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
    public List<EmploymentDto> findById(UUID id) {
        return employmentRepository.findByUser_Id(id)
                .stream()
                .map(mappingService::convertToEmploymentDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmploymentDto> findByUser(String username) {
        return employmentRepository.findByUser_Username(username)
                .stream()
                .map(mappingService::convertToEmploymentDto)
                .collect(Collectors.toList());
    }

    @Override
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

