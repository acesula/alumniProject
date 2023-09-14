package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.Announcements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnnouncementsRepository extends JpaRepository<Announcements, UUID> {
    List<Announcements> findByUser_Id(UUID id);


}
