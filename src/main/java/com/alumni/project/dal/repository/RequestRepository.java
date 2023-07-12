package com.alumni.project.dal.repository;

import com.alumni.project.dal.entity.Interests;
import com.alumni.project.dal.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RequestRepository  extends JpaRepository<Request, UUID>, JpaSpecificationExecutor<Request> {
    //List<Request> findByUser_UsernameUsername(String username);
    //long delete(String username);

}
