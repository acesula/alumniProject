package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.Interests;
import com.alumni.project.dal.entity.Request;
import com.alumni.project.dto.user.UserRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RequestRepository  extends JpaRepository<Request, UUID>, JpaSpecificationExecutor<Request> {
    //List<Request> findByUser_UsernameUsername(String username);
    //long delete(String username);

    @Query(value = "SELECT u.id, u.username,u.profile_picture as image,\n" +
            " r.status, r.start_date as startDate, r.user_id as user1, r.friend_id as user2,\n " +
            "r.end_date as endDate, r.id as mainId\n" +
            " from users_table u \n" +
            "left join request r on u.id = r.user_id\n" +
            "  where u.username = :username", nativeQuery = true)
    List<UserRequestDto> findAllByUsername(String username);


}
