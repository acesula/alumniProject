package com.alumni.project.controller.Request;

import com.alumni.project.dal.entity.Request;
import com.alumni.project.dal.entity.Skills;
import com.alumni.project.dto.skills.SkillsDto;
import com.alumni.project.dto.user.GetUserDto;
import com.alumni.project.dto.user.UserDto;
import com.alumni.project.security.ErrorResponse;
import com.alumni.project.service.request.RequestServiceImpl;
import com.alumni.project.service.skills.SkillsServiceImpl;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestServiceImpl requestsService;

    @PostMapping("/{sender}/{receiver}")
    public void save(@Valid @PathVariable String sender,@Valid @PathVariable String receiver) {
        requestsService.sendRequest(sender,receiver);
    }
    @GetMapping("find-by-username/{username}")
    public List<Request> findByUsername(@PathVariable String username) {
        return requestsService.findAllByUsername(username);
    }

    @GetMapping("/{id}")
    public Request findById(@PathVariable UUID id) {
        return requestsService.findById(id);
    }
    @PatchMapping("/{id}")
    public Request update(@PathVariable UUID id, @RequestBody Request dto,String status) {
        return requestsService.update(id, dto, status);
    }

}

