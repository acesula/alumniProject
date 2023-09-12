package com.alumni.project.service.request;

import com.alumni.project.dal.entity.Request;
import com.alumni.project.dto.user.UserRequestDto;

import java.util.List;
import java.util.UUID;

public interface RequestService {

    String sendRequest(String sender, String receiver);

    List<UserRequestDto> findAllByUsername(String username);

    Request findById(UUID id);
<<<<<<< HEAD
    void delete(UUID id);
=======
//    void deleteByUsername(UUID id);
>>>>>>> 6ef8658e2fe6a08ac60418cd7b077e0b96c20368

    Request update(UUID id, Request request, String newStatus);



}
