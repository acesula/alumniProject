package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.Interests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InterestsRepository extends JpaRepository<Interests, UUID> {
    List<Interests> findByUser_Id(UUID id);


}
