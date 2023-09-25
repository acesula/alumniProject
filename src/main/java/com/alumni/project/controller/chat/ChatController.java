package com.alumni.project.controller.chat;

import com.alumni.project.dto.chat.ChatDto;
import com.alumni.project.dto.chat.ChatMessageDto;

import com.alumni.project.service.chat.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/chat")
@RequiredArgsConstructor

public class ChatController {
    private final ChatService chatService;




    @PostMapping("/{chatRoomId}")
    public void saveByChatRoomId(@Valid @PathVariable UUID chatRoomId, @RequestBody ChatMessageDto chatMessageDto) {
        chatService.saveByChatRoomId(chatRoomId, chatMessageDto.getMessage());
    }

    @PostMapping("/groupChat/{groupChatId}")
    public void saveByGroupChatId(@Valid @PathVariable UUID groupChatId, @RequestBody ChatMessageDto chatMessageDto) {
        chatService.saveByGroupChatId(groupChatId, chatMessageDto.getMessage());
    }

    @GetMapping("/{chatRoomId}")
    public List<ChatDto> getAllChatsByChatRoomId(@PathVariable UUID chatRoomId) {
        return chatService.getAllChatsByChatRoomId(chatRoomId);
    }

    @GetMapping("/groupChat/{groupChatId}")
    public List<ChatDto> getAllChatsByGroupChatId(@PathVariable UUID groupChatId) {
        return chatService.getAllChatsByGroupChatId(groupChatId);
    }

    @GetMapping("/findChat/{id}")
    public ChatDto findById(@PathVariable UUID id) {
        return chatService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        chatService.delete(id);
    }

}