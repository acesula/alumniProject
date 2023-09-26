package com.alumni.project.service.user;

import com.alumni.project.dto.user.*;
import com.alumni.project.dto.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface UserService{

    void save(RegisterDto registerDto);

    ResponseEntity<ErrorResponse> register(RegisterDto registerDto);

    List<AdminUserInfoDto> findAll();

    UserDto findById(UUID id);

    UpdatePersonalInfoDto update(UpdatePersonalInfoDto user);

    ResponseEntity<ErrorResponse> checkPassword(String password);
    void deleteByAdmin(UUID id);

    void delete();

    void uploadProfilePicture(MultipartFile multipartFile) throws IOException;

    void updateBio(String bio);

    void updateUsername(String username);

    void updateEmail(String email);

    void updatePassword(ChangePasswordDto password);

    void updateUserByAdmin(UUID id, AdminUserInfoDto user);

    void changeAccountStatus(UUID id, boolean status);

    void updatePasswordByAdmin(UUID id, String password);

}
