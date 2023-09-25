package com.alumni.project.service.request;

import com.alumni.project.dto.request.RequestDto;
import com.alumni.project.dto.request.UserRequestDto;

import java.util.List;
import java.util.UUID;

public interface RequestService {

    void sendRequest(UUID id);

    List<UserRequestDto> findAllById();

    boolean isRequestSentBefore(UUID receiverId);

    RequestDto findById(UUID id);

    void delete(UUID id);


    void acceptRequest(UUID id);



}
