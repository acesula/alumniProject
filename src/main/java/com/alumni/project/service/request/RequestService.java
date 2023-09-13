package com.alumni.project.service.request;

import com.alumni.project.dal.entity.Request;
import com.alumni.project.dto.user.UserRequestDto;

import java.util.List;
import java.util.UUID;

public interface RequestService {

    String sendRequest(String sender, String receiver);

    List<UserRequestDto> findAllByUsername(String username);

    Request findById(UUID id);

    void delete(UUID id);


    Request update(UUID id, Request request, String newStatus);



}
