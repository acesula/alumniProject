package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.Announcements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AnnouncementsRepository extends JpaRepository<Announcements, UUID> {
    @Query("select a from Announcements a where a.user.username = ?1")
    List<Announcements> findByUser_Username(String username);



}
