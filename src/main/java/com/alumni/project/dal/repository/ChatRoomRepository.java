package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.ChatRoom;
import com.alumni.project.dto.user.UserRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID>, JpaSpecificationExecutor<ChatRoom> {

    @Query(value = "SELECT u.id, u.username,\n" +
            " ch.id as mainId, ch.user1 as user1, ch.user2 as user2\n " +
            " from users_table u \n" +
            "left join chat_room ch on u.id = ch.user_id\n" +
            "  where u.username = :username", nativeQuery = true)
    List<UserRequestDto> findAllByUsername(String username);
}
