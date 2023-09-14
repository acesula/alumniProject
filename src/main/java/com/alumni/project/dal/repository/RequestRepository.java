package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.Request;
import com.alumni.project.dto.user.UserRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RequestRepository extends JpaRepository<Request, UUID>, JpaSpecificationExecutor<Request> {

    @Query(value = "SELECT u.id, u.username,u.profile_picture as image,r.id as mainId\n" +
            " from users_table u \n" +
            " inner join request r on r.user_id = u.id\n" +
            " where r.friend_id = :uuid", nativeQuery = true)
    List<UserRequestDto> findAllById(UUID uuid);




}
