package com.alumni.project.dal.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Friends extends Base{
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user1;

    @ManyToOne
    @JoinColumn(name="friend_id")
    private User friend;
    private LocalDate startDate;
    private LocalDate endDate;

    public Friends(User user1, User friend) {
       this.user1 = user1;
       this.friend= friend;
    }
}
