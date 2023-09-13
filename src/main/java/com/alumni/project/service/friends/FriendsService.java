package com.alumni.project.service.friends;

import com.alumni.project.dal.entity.Friends;
import com.alumni.project.dto.friends.FriendsDto;
import com.alumni.project.dto.user.GetFriendsDto;

import java.util.List;
import java.util.UUID;

public interface FriendsService {
    String save(UUID sender, UUID receiver);

//    List<Friends> findAll();

    List<GetFriendsDto> findAllFriendsPerUser(UUID id);

    FriendsDto findById(UUID id);

    void delete(UUID id);

}
