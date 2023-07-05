package com.alumni.project.service.interests;

import com.alumni.project.dal.entity.Interests;
import com.alumni.project.dto.interests.InterestsDto;

import java.util.List;
import java.util.UUID;

public interface InterestService {

    void save(String username, Interests interests);

    List<InterestsDto> findAll();

    List<InterestsDto> findByUser(String username);

    InterestsDto findById(UUID id);

    void deleteById(UUID id);

    Interests update(UUID id, Interests interests);
}
