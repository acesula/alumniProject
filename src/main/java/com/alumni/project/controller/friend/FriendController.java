package com.alumni.project.controller.friend;
import com.alumni.project.dal.entity.Friends;
<<<<<<< HEAD
import com.alumni.project.dto.user.GetFriendsDto;
=======
import com.alumni.project.service.friends.FriendsService;
>>>>>>> 6ef8658e2fe6a08ac60418cd7b077e0b96c20368
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


    @GetMapping("/friend-list/{username}")
    public List<GetFriendsDto> findAllFriendsPerUser(@PathVariable() String username) {
        return friendsService.findAllFriendsPerUser(username);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable UUID id) {
        friendsService.delete(id);
}


    @GetMapping("/{id}")
    public Friends findById(@PathVariable UUID id) {
        return friendsService.findById(id);
    }
}



