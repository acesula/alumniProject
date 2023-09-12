package com.alumni.project.controller.request;

import com.alumni.project.dal.entity.Request;
import com.alumni.project.dto.user.UserInfoDto;
import com.alumni.project.dto.user.UserRequestDto;
import com.alumni.project.service.request.RequestServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
//    @GetMapping("/{username}")
//    public List<Request> findAll(@PathVariable String username) {
//        return requestsService.findAll();
//    }
@GetMapping("/all-req/{username}")
public List<UserRequestDto> findAllByUsername(@PathVariable String username) {
    return requestsService.findAllByUsername(username);
}

    @GetMapping("/{id}")
    public Request findById(@PathVariable UUID id) {
        return requestsService.findById(id);
    }
    @PatchMapping("/update/{id}")
    public Request update(@PathVariable UUID id, @RequestBody Request dto,String status) {
        return requestsService.update(id, dto, status);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id ) {
        requestsService.delete(id);
    }

}

