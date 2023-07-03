package com.alumni.project.service.announcements;

import com.alumni.project.dal.entity.Announcements;
import com.alumni.project.dto.announcements.AnnouncementsDto;

import java.util.List;
import java.util.UUID;

public interface AnnouncementsService {

    void save(String username,Announcements announcement);

    List<AnnouncementsDto> findAll();

    void delete(UUID uuid);

    Announcements update(UUID uuid,Announcements announcement);


}
