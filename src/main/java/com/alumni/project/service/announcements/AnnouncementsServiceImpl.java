package com.alumni.project.service.announcements;

import com.alumni.project.dal.entity.Announcements;
import com.alumni.project.dal.repository.AnnouncementsRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.security.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementsServiceImpl implements AnnouncementsService {

    private final AnnouncementsRepository announcementsRepository;
    private final UserRepository userRepository;

    @Override
    public void save(String username, Announcements announcement) {
        var user = userRepository.findByUsername(username);
        announcement.setUser(user);
        user.getAnnouncements().add(announcement);
        announcementsRepository.save(announcement);
    }

    public ResponseEntity<ErrorResponse> saveAnnouncement(String username,Announcements announcements){
        try {
            if (userRepository.existsByUsername(username)) {
                save(username, announcements);
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
    public List<Announcements> findAll() {
        return announcementsRepository.findAll();
    }

    @Override
    public void delete(UUID uuid) {
        announcementsRepository.deleteById(uuid);
    }

    @Override
    public Announcements update(UUID uuid, Announcements announcement) {
        var announc = announcementsRepository.findById(uuid).orElseThrow(RuntimeException::new);
        announc.setAnnouncementDescription(announcement.getAnnouncementDescription());

        return announcementsRepository.save(announc);
    }
}
