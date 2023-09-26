package com.alumni.project.service.announcements;

import com.alumni.project.dal.entity.Announcements;
import com.alumni.project.dto.announcements.AnnouncementsDto;
import com.alumni.project.dto.error.ErrorResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface AnnouncementsService {

    void save(Announcements announcement);

    ResponseEntity<ErrorResponse> saveAnnouncement(Announcements announcements);

    List<AnnouncementsDto> findAll();

    List<AnnouncementsDto> findByUser();
    void delete(UUID uuid);

    AnnouncementsDto update(UUID uuid,AnnouncementsDto announcement);


}
