package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.User;
import com.alumni.project.dto.user.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    long deleteByUsername(String username);
    boolean existsByEmail(String email);


    boolean existsByUsername(String username);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);


}
