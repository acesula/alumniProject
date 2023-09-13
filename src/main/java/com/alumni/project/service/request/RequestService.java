package com.alumni.project.service.request;

import com.alumni.project.dal.entity.Request;
import com.alumni.project.dto.request.RequestDto;
import com.alumni.project.dto.user.UserRequestDto;

import java.util.List;
import java.util.UUID;

public interface RequestService {

    void sendRequest(UUID id1, UUID id2);

    List<UserRequestDto> findAllById(UUID id);

    RequestDto findById(UUID id);

    void delete(UUID id);


    void acceptRequest(UUID id);



}
