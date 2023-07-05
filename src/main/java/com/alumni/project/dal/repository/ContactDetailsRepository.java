package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, UUID> {
    long deleteByEmail(String email);
    Optional<ContactDetails> findByEmail(String email);

    List<ContactDetails> findByUser_Username(String username);
    boolean existsByEmail(String email);

}
