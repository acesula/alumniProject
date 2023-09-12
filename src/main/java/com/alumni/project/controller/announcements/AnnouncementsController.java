package com.alumni.project.controller.announcements;

import com.alumni.project.dal.entity.Announcements;
import com.alumni.project.dto.announcements.AnnouncementsDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.announcements.AnnouncementsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/announcements")
@RequiredArgsConstructor
public class AnnouncementsController {

    private final AnnouncementsService announcementService;

    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping()
    public ResponseEntity<ErrorResponse> save(@RequestBody Announcements announcement) {
        return announcementService.saveAnnouncement(authenticatedUser().getId(), announcement);
    }

    @GetMapping
    public List<AnnouncementsDto> findAll() {
        return announcementService.findAll();
    }

    @GetMapping("/announcementsByUser")
    public List<AnnouncementsDto> findAnnouncementsByUser() {

        return announcementService.findByUser(authenticatedUser().getId());
    }

    @PatchMapping("/{id}")
    public AnnouncementsDto update(@PathVariable UUID id,@RequestBody AnnouncementsDto announcement) {
        return announcementService.update(id, announcement);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        announcementService.delete(id);
    }
}
