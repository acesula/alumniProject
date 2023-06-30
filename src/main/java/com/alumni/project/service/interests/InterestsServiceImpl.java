package com.alumni.project.service.interests;

import com.alumni.project.dal.entity.Interests;
import com.alumni.project.dal.repository.InterestsRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.security.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InterestsServiceImpl implements InterestService {

    private final InterestsRepository interestsRepository;
    private final UserRepository userRepository;

    @Override
    public void save(String username, Interests interests) {
        var user = userRepository.findByUsername(username);
        interests.setUser(user);
        user.getInterests().add(interests);
        interestsRepository.save(interests);
    }

    public ResponseEntity<ErrorResponse> saveInterest(String username,Interests interests){
        try {
            if (userRepository.existsByUsername(username)) {
                save(username, interests);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage("User could not be found!");
                errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse();
            error.setMessage(e.getMessage());
            error.setErrorCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Interests> findAll() {
        return interestsRepository.findAll();
    }

    @Override
    public Interests findById(UUID id) {
        var optional = interestsRepository.findById(id);
        if (optional.isPresent()) {
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
