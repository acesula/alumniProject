package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EducationRepository extends JpaRepository<Education, UUID> {
    List<Education> findByUser_Id(UUID id);

    boolean existsByInstitutionAndDegreeAndFieldOfStudy(String institution, String degree, String fieldOfStudy);

    List<Education> findByUser_Username(String username);



}
