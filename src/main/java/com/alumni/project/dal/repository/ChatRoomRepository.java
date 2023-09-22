package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.ChatRoom;
import com.alumni.project.dto.user.GetChatRoomDto;
import com.alumni.project.dto.user.UserRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID>, JpaSpecificationExecutor<ChatRoom> {


    @Query(value = "SELECT ch.id as mainId, u.id, u.name, u.surname, u.username,u.profile_picture as image  \n " +
            " from users_table u  \n" +
            " inner join chat_room ch  on ch.friend_id = u.id  \n" +
            " where ch.user_id = :id" +
            "\n"+
            "UNION\n" +
            "\n" +
            "SELECT ch.id as mainId, u.id, u.name, u.surname, u.username, u.profile_picture as image \n" +
            "from users_table u \n" +
            "inner join chat_room ch on ch.user_id = u.id \n" +
            "where ch.friend_id = :id", nativeQuery = true)
    List<GetChatRoomDto> findAllChatRoomsById(UUID id);
}
