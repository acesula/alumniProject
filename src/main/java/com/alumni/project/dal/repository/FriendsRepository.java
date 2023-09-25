package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.Friends;
import com.alumni.project.dto.friends.GetFriendsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FriendsRepository extends JpaRepository<Friends, UUID> {

    @Query(value = "SELECT f.id as mainId, u.id, u.name, u.surname, u.username, u.profile_picture as image \n " +
            " from users_table u  \n" +
            " inner join friends f on f.friend_id = u.id  \n" +
            " where f.user_id = :id" +
            "\n"+
            "UNION\n" +
            "\n" +
            "SELECT f.id as mainId, u.id, u.name, u.surname, u.username, u.profile_picture as image \n" +
            "from users_table u \n" +
            "inner join friends f on f.user_id = u.id \n" +
            "where f.friend_id = :id", nativeQuery = true)
    List<GetFriendsDto> findAllFriendsPerUser(UUID id);

    @Query(value = "SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END FROM Friends f WHERE (f.user1.id = :user1 AND f.friend.id = :friend1) OR (f.user1.id = :friend1 AND f.friend.id = :user1)")
    boolean areTheyAlreadyFriends(UUID user1, UUID friend1);
}
