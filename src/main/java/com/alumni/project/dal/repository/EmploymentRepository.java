package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.Employment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmploymentRepository extends JpaRepository<Employment, UUID> {
}
