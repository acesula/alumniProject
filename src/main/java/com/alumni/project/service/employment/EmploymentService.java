package com.alumni.project.service.employment;

import com.alumni.project.dal.entity.Employment;
import com.alumni.project.dto.employment.EmploymentDto;
import com.alumni.project.dto.error.ErrorResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface EmploymentService {

    void save(Employment employment);

    ResponseEntity<ErrorResponse> saveEmployment(Employment employment);

    List<EmploymentDto> findAll();

    List<EmploymentDto> findByUserId();


    EmploymentDto update(UUID uuid, EmploymentDto employment);

    void delete(UUID id);


}
