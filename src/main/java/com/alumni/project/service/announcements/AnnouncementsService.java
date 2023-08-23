package com.alumni.project.service.announcements;

import com.alumni.project.dal.entity.Announcements;
import com.alumni.project.dto.announcements.AnnouncementsDto;
import com.alumni.project.security.ErrorResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface AnnouncementsService {

    void save(String username,Announcements announcement);

    ResponseEntity<ErrorResponse> saveAnnouncement(String username, Announcements announcements);

    List<AnnouncementsDto> findAll();

    List<AnnouncementsDto> findByUser(String username);
    void delete(UUID uuid);

    Announcements update(UUID uuid,Announcements announcement);


}
