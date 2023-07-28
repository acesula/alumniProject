package com.alumni.project.service.request;

import com.alumni.project.dal.entity.Request;

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
