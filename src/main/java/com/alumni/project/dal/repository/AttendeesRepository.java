package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.Attendees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AttendeesRepository extends JpaRepository<Attendees, UUID> {
    List<Attendees> findByEvent_Id(UUID id);

    @Query("select a from Attendees a where a.attendeeEmail = ?1")
    List<Attendees> findByAttendeeEmail(String attendeeEmail);


}
