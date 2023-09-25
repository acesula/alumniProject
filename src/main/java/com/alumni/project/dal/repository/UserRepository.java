package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    @Transactional
    @Modifying
    @Query("update User u set u.enabled = ?1 where u.id = ?2")
    void updateEnabledById(boolean enabled, UUID id);
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    User findByUsername(String username);

}
