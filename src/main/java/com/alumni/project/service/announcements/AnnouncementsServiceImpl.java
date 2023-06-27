package com.alumni.project.service.announcements;

import com.alumni.project.dal.entity.Announcements;
import com.alumni.project.dal.repository.AnnouncementsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementsServiceImpl implements AnnouncementsService {

    private final AnnouncementsRepository announcementsRepository;

    @Override
    public Announcements save(Announcements announcement) {
        return announcementsRepository.save(announcement);
    }

    @Override
    public List<Announcements> findAll() {
        return announcementsRepository.findAll();
    }

    @Override
    public void delete(UUID uuid) {
        announcementsRepository.deleteById(uuid);
    }

    @Override
    public Announcements update(UUID uuid, Announcements announcement) {
        var announc = announcementsRepository.findById(uuid).orElseThrow(RuntimeException::new);
        announc.setAnnouncementDescription(announcement.getAnnouncementDescription());

        return announcementsRepository.save(announc);
    }
}
