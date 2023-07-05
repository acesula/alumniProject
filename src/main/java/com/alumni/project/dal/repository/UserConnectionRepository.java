package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.User;
import com.alumni.project.dal.entity.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserConnectionRepository extends JpaRepository<UserConnection, UUID> {
    boolean existsByUserConnection_Username(String username);

    List<UserConnection> findByUserConnection_Username(String username);


    long deleteByFriend(String friend);

}
