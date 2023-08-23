package com.alumni.project.service.employment;

import com.alumni.project.dal.entity.Employment;
import com.alumni.project.dto.employment.EmploymentDto;
import com.alumni.project.security.ErrorResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface EmploymentService {

    void save(UUID uuid, Employment employment);

    ResponseEntity<ErrorResponse> saveEmployment(UUID uuid, Employment employment);

    List<EmploymentDto> findAll();

    List<EmploymentDto> findById(UUID id);

    List<EmploymentDto> findByUser(String username);

    EmploymentDto update(UUID uuid,EmploymentDto employment);

    void delete(UUID id);


}
