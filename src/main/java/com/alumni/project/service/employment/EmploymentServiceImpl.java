package com.alumni.project.service.employment;

import com.alumni.project.dal.entity.Employment;
import com.alumni.project.dal.entity.User;
import com.alumni.project.dal.repository.EmploymentRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.security.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmploymentServiceImpl implements EmploymentService {

    private final EmploymentRepository employmentRepository;
    private final UserRepository userRepository;

    @Override
    public void save(String username, Employment employment) {
        var user = userRepository.findByUsername(username);
        employment.setUser(user);
        user.getEmployments().add(employment);
        employmentRepository.save(employment);
    }

    public ResponseEntity<ErrorResponse> saveEmployment(String username, Employment employment) {
        try {
            if (userRepository.existsByUsername(username)) {
                if (employmentRepository.existsByCompanyAndJob(employment.getCompany(), employment.getJob())) {
                    ErrorResponse errorResponse = new ErrorResponse();
                    errorResponse.setMessage("Employment already exists!");
                    errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
                    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
                }
                save(username, employment);
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
    public List<Employment> findAll() {
        return employmentRepository.findAll();
    }

    @Override
    public Employment findById(UUID id) {
        var optional = employmentRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RuntimeException("Employment not found");
    }

    @Override
    public Employment update(UUID id, Employment employmentNew) {
        var employment = employmentRepository.findById(id).orElseThrow(RuntimeException::new);
        employment.setCompany(employmentNew.getCompany());
        employment.setJob(employmentNew.getJob());
        employment.setStartDate(employmentNew.getStartDate());
        employment.setEndDate(employmentNew.getEndDate());
        employment.setStatus(employmentNew.isStatus());

        return employmentRepository.save(employment);
    }

    @Override
    public void delete(UUID id) {
        employmentRepository.deleteById(id);
    }

}

