package com.alumni.project.controller.announcements;

import com.alumni.project.dal.entity.Announcements;
import com.alumni.project.dto.announcements.AnnouncementsDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.announcements.AnnouncementsService;
import jakarta.validation.Valid;
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

    @PostMapping("/{username}")
    public ResponseEntity<ErrorResponse> save(@Valid @PathVariable String username, @RequestBody Announcements announcement) {
        return announcementService.saveAnnouncement(username, announcement);
    }

    @GetMapping
    public List<AnnouncementsDto> findAll() {
        return announcementService.findAll();
    }

    @GetMapping("/{username}")
    public List<AnnouncementsDto> findAnnouncementsByUser(@Valid @PathVariable String username) {
        return announcementService.findByUser(username);
    }

    @PatchMapping("/{id}")
    public Announcements update(@PathVariable UUID id, @RequestBody Announcements announcement) {
        return announcementService.update(id, announcement);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        announcementService.delete(id);
    }
}
