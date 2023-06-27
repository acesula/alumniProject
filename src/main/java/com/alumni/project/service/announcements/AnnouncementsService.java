package com.alumni.project.service.announcements;

import com.alumni.project.dal.entity.Announcements;
import java.util.List;
import java.util.UUID;

public interface AnnouncementsService {

    Announcements save(Announcements announcement);

    List<Announcements> findAll();

    void delete(UUID uuid);

    Announcements update(UUID uuid,Announcements announcement);


}
