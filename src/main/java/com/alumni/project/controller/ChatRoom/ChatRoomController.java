package com.alumni.project.controller.ChatRoom;
import com.alumni.project.dal.entity.Request;
import com.alumni.project.dto.chatRoom.ChatRoomDto;
import com.alumni.project.dto.user.GetChatRoomDto;
import com.alumni.project.dto.user.UserDto;
import com.alumni.project.dto.user.UserInfoDto;
import com.alumni.project.dto.user.UserRequestDto;
import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.chat.ChatService;
import com.alumni.project.service.chatRoom.ChatRoomServiceImpl;
import com.alumni.project.service.request.RequestServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/ChatRoom")
@RequiredArgsConstructor

public class ChatRoomController {

    private final ChatRoomServiceImpl chatRoomService;

    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    @PostMapping("/{otherUserId}")
    public void save(@Valid @PathVariable UUID otherUserId) {
        chatRoomService.save(authenticatedUser().getId(),otherUserId);
    }

    @GetMapping("/{sender}")
    public List<ChatRoomDto> findAllChatRoom(@PathVariable String sender){
        return chatRoomService.findAll();
    }

    @GetMapping("/listperuser")
    public List<GetChatRoomDto> findAllChatRoomsById(){
        return chatRoomService.findAllChatRoomsById(authenticatedUser().getId());
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
