package com.alumni.project.controller.chatRoom;
import com.alumni.project.dto.chatRoom.ChatRoomDto;
import com.alumni.project.dto.chatRoom.GetChatRoomDto;
import com.alumni.project.service.chatRoom.ChatRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/chatRoom")
@RequiredArgsConstructor
@Transactional
public class ChatRoomController {

    private final ChatRoomService chatRoomService;




    @PostMapping("/{userId}")
    public void save(@Valid @PathVariable UUID userId) {
        chatRoomService.save(userId);
    }

    @GetMapping
    public List<ChatRoomDto> findAllChatRooms(){
        return chatRoomService.findAll();
    }

    @GetMapping("/chatRoomsOfUser")
    public List<GetChatRoomDto> findAllChatRoomsById(){
        return chatRoomService.findAllChatRoomsById();
    }

    @GetMapping("/chatRoom/{id}")
    public ChatRoomDto findById(@PathVariable UUID id) {
        return chatRoomService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        chatRoomService.delete(id);
    }

}
