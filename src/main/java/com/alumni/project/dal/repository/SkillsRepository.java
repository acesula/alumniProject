package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, UUID> {
    List<Skills> findByUser_Id(UUID id);

}
