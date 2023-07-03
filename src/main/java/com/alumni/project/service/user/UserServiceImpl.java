package com.alumni.project.service.user;

import com.alumni.project.dal.entity.ContactDetails;
import com.alumni.project.dal.entity.Role;
import com.alumni.project.dal.entity.User;
import com.alumni.project.dal.repository.ContactDetailsRepository;
import com.alumni.project.dal.repository.RoleRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.user.GetUserDto;
import com.alumni.project.dto.user.UserDto;
import com.alumni.project.dto.user.UserLoginDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.contactdetails.ContactDetailsServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ContactDetailsRepository contactDetailsRepository;
    private final ContactDetailsServiceImp contactDetailsServiceImp;

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public GetUserDto save(UserDto userDto) {
        var user = new User(
                userDto.getName(),
                userDto.getSurname(),
                userDto.getEmail(),
                userDto.getBirthDate(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getGender()
        );
        Role role = roleRepository.findByName("USER");
        var contact = new ContactDetails();
        contact.setEmail(userDto.getEmail());
        contact.setUser(user);
        contactDetailsRepository.save(contact);
        user.setRoles(Arrays.asList(role));
        return map(userRepository.save(user));
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
    public List<GetUserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public GetUserDto findById(UUID id) {
        var optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return map(optional.get());
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public GetUserDto update(UUID id, UserDto userDto) {
        var user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setBirthDate(userDto.getBirthDate());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setGender(userDto.getGender());

        return map(userRepository.save(user));

    }

    @Override
    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }


    private GetUserDto map(User user) {
        var dto = new GetUserDto();
        dto.setUserId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setGender(user.getGender());
        dto.setEmail(user.getEmail());
        dto.setBirthDate(user.getBirthDate());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
