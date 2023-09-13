package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.Attendees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttendeesRepository extends JpaRepository<Attendees, UUID> {
    List<Attendees> findByEvent_Id(UUID id);



}
