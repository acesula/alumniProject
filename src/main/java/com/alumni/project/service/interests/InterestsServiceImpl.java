package com.alumni.project.service.interests;

import com.alumni.project.dal.entity.Interests;
import com.alumni.project.dal.repository.InterestsRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.interests.InterestsDto;
import com.alumni.project.dto.error.ErrorResponse;
import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.mapping.MappingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterestsServiceImpl implements InterestsService {

    private final InterestsRepository interestsRepository;
    private final UserRepository userRepository;
    private final MappingServiceImpl mappingService;

    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    @Transactional
    public void save(Interests interests) {
        var user = userRepository.findById(authenticatedUser().getId()).orElseThrow(RuntimeException::new);
        interests.setUser(user);
        user.getInterests().add(interests);
        interestsRepository.save(interests);
    }

    public ResponseEntity<ErrorResponse> saveInterest(Interests interests) {
        try {
            if (userRepository.existsById(authenticatedUser().getId())) {
                save(interests);
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
    public List<InterestsDto> findAll() {
        return interestsRepository.findAll()
                .stream()
                .map(mappingService::convertToInterestsDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InterestsDto> findByUser() {
        return interestsRepository.findByUser_Id(authenticatedUser().getId())
                .stream()
                .map(mappingService::convertToInterestsDto)
                .collect(Collectors.toList());
    }

    @Override
    public InterestsDto findById(UUID id) {
        var optional = interestsRepository.findById(id);
        if (optional.isPresent()) {
            return mappingService.convertToInterestsDto(optional.get());
        }
        throw new RuntimeException("No interest found");
    }

    @Override
    public void deleteById(UUID id) {
        interestsRepository.deleteById(id);
    }

    @Override
    @Transactional
    public InterestsDto update(UUID id, InterestsDto interests) {
        var interest = interestsRepository.findById(id).orElseThrow(RuntimeException::new);
        interest.setInterestDescription(interests.getInterestDescription());

        return mappingService.convertToInterestsDto(interestsRepository.save(interest));
    }
}
