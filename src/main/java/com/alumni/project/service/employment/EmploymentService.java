package com.alumni.project.service.employment;

import com.alumni.project.dal.entity.Employment;

import java.util.List;
import java.util.UUID;

public interface EmploymentService {

    void save(String username, Employment employment);

    List<Employment> findAll();

    Employment findById(UUID id);

    Employment update(UUID uuid,Employment employment);

    void delete(UUID id);


}
