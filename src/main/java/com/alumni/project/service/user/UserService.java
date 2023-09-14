package com.alumni.project.service.user;

import com.alumni.project.dto.user.*;
import com.alumni.project.security.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface UserService{

    void save(RegisterDto registerDto);

    ResponseEntity<ErrorResponse> register(RegisterDto registerDto);

    List<UserDto> findAll();

    UserDto findById(UUID id);

    UpdatePersonalInfoDto update(UUID id, UpdatePersonalInfoDto user);

    ResponseEntity<ErrorResponse> checkPassword(UUID id, String password);
    void delete(UUID id);

    void uploadProfilePicture(MultipartFile multipartFile, UUID id) throws IOException;

    void updateBio(UUID id, String bio);

    void updateUsername(UUID id, String username);

    void updateEmail(UUID id, String email);

    void updatePassword(UUID id, ChangePasswordDto password);

}
