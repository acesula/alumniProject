package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, UUID> {
    boolean existsByCompanyAndJobAndUser_Id(String company, String job, UUID id);

    List<Employment> findByUser_Username(String username);

    List<Employment> findByUser_Id(UUID id);

}
