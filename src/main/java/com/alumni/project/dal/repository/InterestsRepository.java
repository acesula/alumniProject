package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.Interests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestsRepository extends JpaRepository<Interests, Long> {
}
