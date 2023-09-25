package com.alumni.project.service.user;

import com.alumni.project.dal.entity.ContactDetails;
import com.alumni.project.dal.entity.User;
import com.alumni.project.dal.repository.ContactDetailsRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.user.*;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.security.exception.AuthServerException;
import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.mapping.MappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ContactDetailsRepository contactDetailsRepository;
    private final MappingService mappingService;
    private final PasswordEncoder passwordEncoder;

    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findEntity() {
        return userRepository.findById(authenticatedUser().getId()).orElseThrow(RuntimeException::new);
    }

    public void save(RegisterDto registerDto) {
        var user = mappingService.convertToUser(registerDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var contact = new ContactDetails();
        contact.setEmail(registerDto.getEmail());
        contact.setUser(user);
        contactDetailsRepository.save(contact);

    }

    public ResponseEntity<ErrorResponse> register(RegisterDto registerDto) {
        try {
            if (existsByUsername(registerDto.getUsername())) {
                ErrorResponse error = new ErrorResponse();
                error.setMessage("Username already exists");
                error.setErrorCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            } else if (existsByEmail(registerDto.getEmail())) {
                ErrorResponse error = new ErrorResponse();
                error.setMessage("Email already exists");
                error.setErrorCode(HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
            }
            save(registerDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            ErrorResponse error = new ErrorResponse();
            error.setMessage(e.getMessage());
            error.setErrorCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }



    @Override
    public List<AdminUserInfoDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(mappingService::convertToAdminUserInfoDto)
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
    public UpdatePersonalInfoDto update(UpdatePersonalInfoDto userDto) {
        var user = findEntity();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setBirthDate(userDto.getBirthDate());
        user.setGender(userDto.getGender());

        return mappingService.convertToUpdatePersonalInfoDto(userRepository.save(user));
    }

    public ResponseEntity<ErrorResponse> checkPassword(String password) {
        var user = findEntity();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            ErrorResponse error = new ErrorResponse();
            error.setMessage("Password does not match");
            error.setErrorCode(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public void deleteByAdmin(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public void delete() {
        userRepository.deleteById(authenticatedUser().getId());
    }



    @Override
    public void uploadProfilePicture(MultipartFile multipartFile) throws IOException {
        var user = findEntity();
        byte[] imageBytes = multipartFile.getBytes();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        user.setProfilePicture(base64Image);

        userRepository.save(user);


    }

    public void updateBio(String bio) {
        var user = findEntity();
        user.setDescription(bio);
        userRepository.save(user);
    }

    public void updateUsername(String username) {
        var user = findEntity();
        user.setUsername(username);
        userRepository.save(user);
    }

    public void updateEmail(String email) {
        var user = findEntity();
        var contactDetails = contactDetailsRepository.findByUser_Id(authenticatedUser().getId()).orElseThrow(RuntimeException::new);
        contactDetails.setEmail(email);
        contactDetailsRepository.save(contactDetails);
        user.setContactDetails(contactDetails);
        user.setEmail(email);
        userRepository.save(user);
    }

    public void updatePassword(ChangePasswordDto changePasswordDto) {
        var user = findEntity();
        if (!passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())) {
            throw new AuthServerException("Password does not match");
        }
        user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        userRepository.save(user);
    }

    public void updatePasswordByAdmin(UUID id, String password){
        var user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public void updateUserByAdmin(UUID id, AdminUserInfoDto user) {
        var userEntity = userRepository.findById(id).orElseThrow(RuntimeException::new);
        userEntity.setName(user.getName());
        userEntity.setSurname(user.getSurname());
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setBirthDate(user.getBirthDate());
        userEntity.setGender(user.getGender());
        userEntity.setRole(user.getRole());

        userRepository.save(userEntity);
    }

    @Override
    public void changeAccountStatus(UUID id, boolean status) {
        userRepository.updateEnabledById(status, id);
    }
}
