package com.alumni.project.controller.announcements;

import com.alumni.project.dal.entity.Announcements;
import com.alumni.project.service.announcements.AnnouncementsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/announcements")
@RequiredArgsConstructor
public class AnnouncementsController {

    private final AnnouncementsServiceImpl announcementService;

    @PostMapping
    public Announcements save(@RequestBody Announcements announcement) {
        return announcementService.save(announcement);
    }

    @GetMapping
    public List<Announcements> findAll() {
        return announcementService.findAll();
    }

    @PatchMapping("/{id}")
    public Announcements update(@PathVariable UUID id, @RequestBody Announcements announcement){
        return announcementService.update(id, announcement);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        announcementService.delete(id);
    }
}
