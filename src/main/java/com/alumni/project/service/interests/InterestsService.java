package com.alumni.project.service.interests;

import com.alumni.project.dal.entity.Interests;
import com.alumni.project.dto.interests.InterestsDto;
import com.alumni.project.dto.error.ErrorResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface InterestsService {

    void save(Interests interests);

    ResponseEntity<ErrorResponse> saveInterest(Interests interests);

    List<InterestsDto> findAll();

    List<InterestsDto> findByUser();

    InterestsDto findById(UUID id);

    void deleteById(UUID id);

    InterestsDto update(UUID id, InterestsDto interests);
}
