package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID>, JpaSpecificationExecutor<Chat> {

    List<Chat> findAllByChatRoomId(UUID chatRoomId);
}
