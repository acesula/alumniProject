package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.Friends;
import com.alumni.project.dto.user.GetFriendsDto;
import com.alumni.project.dto.user.UserRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FriendsRepository extends JpaRepository<Friends, UUID> {
    //    List<Friends> findByUser_Username(String username);
//    long deleteByUsername(String username);
    @Query(value = "SELECT f.id as mainId, u.id,  u.username, u.profile_picture as image \n " +
            " from users_table u  \n" +
            " inner join friends f on f.friend_id = u.id  \n" +
            " where f.user_id = :id" +
            "\n"+
            "UNION\n" +
            "\n" +
            "SELECT f.id as mainId, u.id, u.username, u.profile_picture as image \n" +
            "from users_table u \n" +
            "inner join friends f on f.user_id = u.id \n" +
            "where f.friend_id = :id", nativeQuery = true)
    List<GetFriendsDto> findAllFriendsPerUser(UUID id);
}
