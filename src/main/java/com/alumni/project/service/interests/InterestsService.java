package com.alumni.project.service.interests;

import com.alumni.project.dal.entity.Interests;
import com.alumni.project.dto.interests.InterestsDto;
import com.alumni.project.security.ErrorResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface InterestsService {

    void save(UUID uuid, Interests interests);

    ResponseEntity<ErrorResponse> saveInterest(UUID uuid, Interests interests);

    List<InterestsDto> findAll();

    List<InterestsDto> findByUser(UUID uuid);

    InterestsDto findById(UUID id);

    void deleteById(UUID id);

    InterestsDto update(UUID id, InterestsDto interests);
}
