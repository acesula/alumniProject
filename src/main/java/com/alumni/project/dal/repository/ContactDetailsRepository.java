package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.ContactDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactDetailsRepository extends JpaRepository<ContactDetails, UUID> {
}
