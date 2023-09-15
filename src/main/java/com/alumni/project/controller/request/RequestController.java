package com.alumni.project.controller.request;


import com.alumni.project.dto.request.RequestDto;
import com.alumni.project.dto.user.UserRequestDto;

import com.alumni.project.security.ErrorResponse;
import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.request.RequestService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestsService;

    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping("/{id}")
    public void save(@Valid @PathVariable UUID id) {
        requestsService.sendRequest(authenticatedUser().getId(), id);
    }

    @GetMapping
    public List<UserRequestDto> findAllById() {
        return requestsService.findAllById(authenticatedUser().getId());
    }

    @GetMapping("/requestSent/{id}")
    public boolean requestSent(@Valid @PathVariable UUID id){
        return requestsService.isRequestSentBefore(authenticatedUser().getId(), id);
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

