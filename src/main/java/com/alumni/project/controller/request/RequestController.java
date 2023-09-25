package com.alumni.project.controller.request;


import com.alumni.project.dto.request.RequestDto;
import com.alumni.project.dto.request.UserRequestDto;

import com.alumni.project.service.request.RequestService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestsService;



    @PostMapping("/{id}")
    public void save(@Valid @PathVariable UUID id) {
        requestsService.sendRequest(id);
    }

    @GetMapping
    public List<UserRequestDto> findAllById() {
        return requestsService.findAllById();
    }

    @GetMapping("/requestSent/{id}")
    public boolean requestSent(@Valid @PathVariable UUID id){
        return requestsService.isRequestSentBefore(id);
    }

    @GetMapping("/{id}")
    public RequestDto findById(@PathVariable UUID id) {
        return requestsService.findById(id);
    }

    @PostMapping("/accept/{id}")
    public void acceptRequest(@PathVariable UUID id) {
        requestsService.acceptRequest(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        requestsService.delete(id);
    }

}

