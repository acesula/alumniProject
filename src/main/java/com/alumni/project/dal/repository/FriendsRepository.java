package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FriendsRepository extends JpaRepository<Friends, UUID> {
//    List<Friends> findByUser_Username(String username);
//    long deleteByUsername(String username);
}
