package com.alumni.project.controller.groupChat;

import com.alumni.project.dto.groupChat.GroupChatDto;
import com.alumni.project.service.groupChat.GroupChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/groupChat")
@RequiredArgsConstructor
public class GroupChatController {

    private final GroupChatService groupChatService;




    @GetMapping
    public List<GroupChatDto> getAllGroupChats() {
        return groupChatService.getAllGroupChats();
    }

    @PostMapping
    public void createGroupChat(@RequestParam("name") String name) {
        groupChatService.createGroupChat(name);
    }

    @PatchMapping("/changeName/{groupChatId}")
    public void changeGroupName(@PathVariable UUID groupChatId,@RequestParam("name") String name) {
        groupChatService.changeGroupName(groupChatId, name);
    }

    @DeleteMapping("/{groupChatId}")
    public void deleteGroupChat(@PathVariable UUID groupChatId) {
        groupChatService.deleteGroupChat(groupChatId);
    }

}
