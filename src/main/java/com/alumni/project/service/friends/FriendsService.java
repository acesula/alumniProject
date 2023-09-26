package com.alumni.project.service.friends;

import com.alumni.project.dto.friends.FriendsDto;
import com.alumni.project.dto.friends.GetFriendsDto;

import java.util.List;
import java.util.UUID;

public interface FriendsService {
    String save(UUID sender,UUID receiver);

    public boolean areTheyAlreadyFriends(UUID friend);
    List<GetFriendsDto> findAllFriendsPerUser();

    FriendsDto findById(UUID id);

    void delete(UUID id);

}
