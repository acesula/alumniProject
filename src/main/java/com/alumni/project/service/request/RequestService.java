package com.alumni.project.service.request;

import com.alumni.project.dal.entity.Interests;
import com.alumni.project.dal.entity.Request;
import com.alumni.project.dal.entity.Role;
import com.alumni.project.dto.interests.InterestsDto;
import com.alumni.project.dto.user.GetUserDto;
import com.alumni.project.dto.user.UserDto;

import java.util.List;
import java.util.UUID;

public interface RequestService {

    String sendRequest(String sender, String receiver);

    List<Request> findAllByUsername(String username);

    List<Request> findByUser(String username);

    Request findById(UUID id);
    void deleteByUsername(UUID id);

    Request update(UUID id, Request request, String newStatus);



}
