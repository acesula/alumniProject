package com.alumni.project.controller.friend;


import com.alumni.project.dto.friends.FriendsDto;
import com.alumni.project.dto.user.GetFriendsDto;

import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.friends.FriendsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/friends")
@RequiredArgsConstructor
public class FriendController {
    private final FriendsService friendsService;

    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping("/{id}")
    public void save(@Valid @PathVariable UUID id) {
        friendsService.save(authenticatedUser().getId(), id);
    }


    @GetMapping
    public List<GetFriendsDto> findAllFriendsPerUser() {
        return friendsService.findAllFriendsPerUser(authenticatedUser().getId());
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        friendsService.delete(id);
    }


    @GetMapping("/{id}")
    public FriendsDto findById(@PathVariable UUID id) {
        return friendsService.findById(id);
    }
}



