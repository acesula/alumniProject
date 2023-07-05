package com.alumni.project.service.connection;

import com.alumni.project.dal.entity.UserConnection;
import com.alumni.project.dal.repository.UserConnectionRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.connection.UserConnectionDto;
import com.alumni.project.security.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserConnectionServiceImpl implements UserConnectionService {

    private final UserConnectionRepository userConnectionRepository;
    private final UserRepository userRepository;

    @Override
    public void save(String username, UserConnection userConnection) {
        var user = userRepository.findByUsername(username);
        userConnection.setUserConnection(user);
        user.getUserConnections().add(userConnection);
        userConnectionRepository.save(userConnection);
    }

    public ResponseEntity<ErrorResponse> saveConnection(String username, UserConnection userConnection) {
        try {
            if (userRepository.existsByUsername(username) && userRepository.existsByUsername(userConnection.getFriend())) {
                if (Objects.equals(username, userConnection.getFriend())) {
                    ErrorResponse errorResponse = new ErrorResponse();
                    errorResponse.setMessage("Cannot add yourself as a connection!");
                    errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
                    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
                } else {
                    if (userConnectionRepository.existsByUserConnection_Username(username)) {
                        ErrorResponse errorResponse = new ErrorResponse();
                        errorResponse.setMessage("Connection already exists!");
                        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.value());
                        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
                    } else {
                        save(username, userConnection);
                        return new ResponseEntity<>(HttpStatus.OK);
                    }
                }
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
    public List<UserConnectionDto> findAll() {
        return userConnectionRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserConnectionDto> findByUser(String username) {
        return userConnectionRepository.findByUserConnection_Username(username)
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public UserConnection findById(UUID uuid) {
        var optional = userConnectionRepository.findById(uuid);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new RuntimeException("Connection not found");
    }

    @Override
    public void delete(String username) {
        userConnectionRepository.deleteByFriend(username);
    }

    public UserConnectionDto map(UserConnection userConnection) {
        var dto = new UserConnectionDto();
        dto.setFriend(userConnection.getFriend());
        dto.setStatus(userConnection.isStatus());
        return dto;
    }
}
