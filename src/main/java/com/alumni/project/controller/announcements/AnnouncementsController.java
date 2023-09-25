package com.alumni.project.controller.announcements;

import com.alumni.project.dal.entity.Announcements;
import com.alumni.project.dto.announcements.AnnouncementsDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.announcements.AnnouncementsService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/announcements")
@RequiredArgsConstructor
public class AnnouncementsController {

    private final AnnouncementsService announcementService;


    @PostMapping()
    public ResponseEntity<ErrorResponse> save(@RequestBody Announcements announcement) {
        return announcementService.saveAnnouncement(announcement);
    }

    @GetMapping
    public List<AnnouncementsDto> findAll() {
        return announcementService.findAll();
    }

    @GetMapping("/announcementsByUser")
    public List<AnnouncementsDto> findAnnouncementsByUser() {
        return announcementService.findByUser();
    }

    @PatchMapping("/{id}")
    public AnnouncementsDto update(@PathVariable UUID id, @RequestBody AnnouncementsDto announcement) {
        return announcementService.update(id, announcement);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        announcementService.delete(id);
    }
}
