package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.GroupChat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GroupChatRepository extends JpaRepository<GroupChat, UUID> {
    List<GroupChat> findByUser_Id(UUID id);

}
