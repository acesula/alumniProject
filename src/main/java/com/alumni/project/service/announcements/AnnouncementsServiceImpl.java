package com.alumni.project.service.announcements;

import com.alumni.project.dal.entity.Announcements;
import com.alumni.project.dal.repository.AnnouncementsRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.announcements.AnnouncementsDto;
import com.alumni.project.dto.error.ErrorResponse;
import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.mapping.MappingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementsServiceImpl implements AnnouncementsService {

    private final AnnouncementsRepository announcementsRepository;
    private final UserRepository userRepository;
    private final MappingServiceImpl mappingService;


    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    @Transactional
    public void save(Announcements announcement) {
        var user = userRepository.findById(authenticatedUser().getId()).orElseThrow(RuntimeException::new);
        announcement.setUser(user);
        announcement.setAnnouncementDate(LocalDateTime.of(
                LocalDateTime.now().getYear(),
                LocalDateTime.now().getMonth(),
                LocalDateTime.now().getDayOfMonth(),
                LocalDateTime.now().getHour(),
                LocalDateTime.now().getMinute()).toString());
        user.getAnnouncements().add(announcement);
        announcementsRepository.save(announcement);
    }

    public ResponseEntity<ErrorResponse> saveAnnouncement( Announcements announcements) {
        try {
            if (userRepository.existsById(authenticatedUser().getId())) {
                save(announcements);
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
    public List<AnnouncementsDto> findAll() {
        return announcementsRepository.findAll()
                .stream()
                .map(mappingService::convertToAnnouncementsDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnouncementsDto> findByUser() {
        return announcementsRepository.findByUser_Id(authenticatedUser().getId())
                .stream()
                .map(mappingService::convertToAnnouncementsDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID uuid) {
        announcementsRepository.deleteById(uuid);
    }

    @Override
    @Transactional
    public AnnouncementsDto update(UUID uuid, AnnouncementsDto announcement) {
        var announc = announcementsRepository.findById(uuid).orElseThrow(RuntimeException::new);
        announc.setAnnouncementDescription(announcement.getAnnouncementDescription());
        announc.setAnnouncementTitle(announcement.getAnnouncementTitle());
        announc.setImage(announcement.getImage());

        return mappingService.convertToAnnouncementsDto(announcementsRepository.save(announc));
    }
}
