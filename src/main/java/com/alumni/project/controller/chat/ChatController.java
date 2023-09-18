package com.alumni.project.controller.chat;

import com.alumni.project.dto.chat.ChatDto;
import com.alumni.project.dto.chat.ChatMessageDto;

import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.chat.ChatService;
import com.alumni.project.service.chat.ChatServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/chat")
@RequiredArgsConstructor

public class ChatController {
    private final ChatService chatService;

//    public AuthUserDetail authenticatedUser() {
//        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    }
//
//
//    @PostMapping("/{receiver}/{chatRoomId}")
//    public void save(@Valid @PathVariable UUID receiver,
//                     @Valid @PathVariable UUID chatRoomId, @RequestBody ChatMessageDto chatMessageDto) {
//        chatService.save(authenticatedUser().getId(), receiver, chatRoomId, chatMessageDto.getMessage());
//    }

    @GetMapping("/list/{chatRoomId}")
    public List<ChatDto> getAllChatsByChatRoomId(@PathVariable UUID chatRoomId) {
        return chatService.getAllChatsByChatRoomId(chatRoomId);
    }

    @GetMapping("/{id}")
    public ChatDto findById(@PathVariable UUID id) {
        return chatService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        chatService.delete(id);
    }

}