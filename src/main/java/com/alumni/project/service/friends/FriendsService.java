package com.alumni.project.service.friends;

import com.alumni.project.dal.entity.Friends;

import java.util.List;
import java.util.UUID;

public interface FriendsService {
    String save(String sender, String receiver);

    List<Friends> findAll();

    List<Friends> findByUser(String username);

    Friends findById(UUID id);

    void delete(UUID id);

}