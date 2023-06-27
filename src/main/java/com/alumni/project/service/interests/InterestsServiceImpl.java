package com.alumni.project.service.interests;

import com.alumni.project.dal.entity.Interests;
import com.alumni.project.dal.repository.InterestsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InterestsServiceImpl implements InterestService{

    private final InterestsRepository interestsRepository;

    @Override
    public Interests save(Interests interests) {
       return interestsRepository.save(interests);
    }

    @Override
    public List<Interests> findAll() {
       return interestsRepository.findAll();
    }

    @Override
    public Interests findById(UUID id) {
        var optional = interestsRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new RuntimeException("No interest found");
    }

    @Override
    public void deleteById(UUID id) {
        interestsRepository.deleteById(id);
    }

    @Override
    public Interests update(UUID id, Interests interests) {
        var interest = interestsRepository.findById(id).orElseThrow(RuntimeException::new);
        interest.setInterestDescription(interests.getInterestDescription());

        return interestsRepository.save(interest);
    }
}
