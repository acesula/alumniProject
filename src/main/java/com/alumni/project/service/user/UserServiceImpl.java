package com.alumni.project.service.user;

import com.alumni.project.dal.entity.ContactDetails;
import com.alumni.project.dal.entity.User;
import com.alumni.project.dal.repository.ContactDetailsRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.user.UserDto;
import com.alumni.project.dto.user.UserLoginDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.mapping.MappingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ContactDetailsRepository contactDetailsRepository;
    private final MappingServiceImpl mappingService;
    private final PasswordEncoder passwordEncoder;


    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public void save(UserDto userDto) {
        var user = mappingService.convertToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var contact = new ContactDetails();
        contact.setEmail(userDto.getEmail());
        contact.setUser(user);
        contactDetailsRepository.save(contact);

    }

    public ResponseEntity<ErrorResponse> register(UserDto userDto) {
        try {
            if (existsByUsername(userDto.getUsername())) {
                ErrorResponse error = new ErrorResponse();
                error.setMessage("Username already exists");
                error.setErrorCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            } else if (existsByEmail(userDto.getEmail())) {
                ErrorResponse error = new ErrorResponse();
                error.setMessage("Email already exists");
                error.setErrorCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
            save(userDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse();
            error.setMessage(e.getMessage());
            error.setErrorCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<ErrorResponse> login(UserLoginDto login) {
        try {
            User user = findByUsernameAndPassword(login.getUsername(), login.getPassword());
            if (user == null) {
                ErrorResponse error = new ErrorResponse();
                error.setMessage("Wrong credentials");
                error.setErrorCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse();
            error.setMessage(e.getMessage());
            error.setErrorCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(mappingService::convertToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(UUID id) {
        var optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return mappingService.convertToUserDto(optional.get());
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public UserDto update(UUID id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setBirthDate(userDto.getBirthDate());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setGender(userDto.getGender());

        return mappingService.convertToUserDto(userRepository.save(user));
    }

    @Override
    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

}
