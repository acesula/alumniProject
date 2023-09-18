package com.alumni.project.controller.chatRoom;
import com.alumni.project.dto.chatRoom.ChatRoomDto;
import com.alumni.project.service.chatRoom.ChatRoomServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/ChatRoom")
@RequiredArgsConstructor

public class ChatRoomController {

    private final ChatRoomServiceImpl chatRoomService;



    @PostMapping("/{sender}/{receiver}")
    public void save(@Valid @PathVariable String sender,@Valid @PathVariable String receiver) {
        chatRoomService.save(sender,receiver);
    }

    @GetMapping("/{sender}")
    public List<ChatRoomDto> findAllChatRoom(@PathVariable String sender){
        return chatRoomService.findAll();
    }

    @GetMapping("/all-req/{id}")
    public ChatRoomDto findById(@PathVariable UUID id) {
        return chatRoomService.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable UUID id) {
        chatRoomService.delete(id);
    }

}
