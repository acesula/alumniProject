package com.alumni.project.dal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Announcements extends Base {

    @Column(columnDefinition = "TEXT")
    private String announcementDescription;
    private String announcementTitle;
    @Column(columnDefinition = "TEXT")
    private String image;
    private String announcementDate;

    @ManyToOne
    private User user;

}
