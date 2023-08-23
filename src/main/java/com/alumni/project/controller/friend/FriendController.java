package com.alumni.project.controller.friend;
import com.alumni.project.dal.entity.Friends;
import com.alumni.project.service.friends.FriendsService;
import com.alumni.project.service.friends.FriendsServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/friends")
@RequiredArgsConstructor
public class FriendController {
    private final FriendsService friendsService;

    @PostMapping("/{sender}/{receiver}")
    public void save(@Valid @PathVariable String sender,@Valid @PathVariable String receiver) {
        friendsService.save(sender,receiver);
    }
    @GetMapping
    public List<Friends> findAll() {
        return friendsService.findAll();
    }
    @GetMapping("/{id}")
    public Friends findById(@PathVariable UUID id) {
        return friendsService.findById(id);
    }
}
