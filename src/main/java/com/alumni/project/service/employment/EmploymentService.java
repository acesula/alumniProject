package com.alumni.project.service.employment;

import com.alumni.project.dal.entity.Employment;
import com.alumni.project.dto.employment.EmploymentDto;

import java.util.List;
import java.util.UUID;

public interface EmploymentService {

    void save(String username, Employment employment);

    List<EmploymentDto> findAll();

    EmploymentDto findById(UUID id);

    List<EmploymentDto> findByUser(String username);

    EmploymentDto update(UUID uuid,EmploymentDto employment);

    void delete(UUID id);


}
