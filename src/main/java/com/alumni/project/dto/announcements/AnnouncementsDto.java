package com.alumni.project.dto.announcements;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class AnnouncementsDto {
    private UUID id;
    private String announcementDescription;
    private String announcementTitle;
    private String image;
    private String announcementDate;
    private UUID userId;
}
