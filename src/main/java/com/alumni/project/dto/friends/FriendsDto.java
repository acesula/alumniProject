package com.alumni.project.dto.friends;

import com.alumni.project.dal.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FriendsDto {

    private User user1;
    private User friend;

}
