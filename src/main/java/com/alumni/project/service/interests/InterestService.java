package com.alumni.project.service.interests;

import com.alumni.project.dal.entity.Interests;
import java.util.List;
import java.util.UUID;

public interface InterestService {

    Interests save(Interests interests);

    List<Interests> findAll();

    Interests findById(UUID id);

    void deleteById(UUID id);

    Interests update(UUID id, Interests interests);
}
