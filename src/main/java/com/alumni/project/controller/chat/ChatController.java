package com.alumni.project.controller.chat;

import com.alumni.project.dto.chat.ChatDto;
import com.alumni.project.dto.chat.ChatMessageDto;
import com.alumni.project.service.chat.ChatServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/chat")
@RequiredArgsConstructor

public class ChatController {
    private final ChatServiceImpl chatService;



    @PostMapping("/{sender}/{receiver}/{chatRoomId}")
    public void save(@Valid @PathVariable String sender,@Valid @PathVariable String receiver,
                     @Valid @PathVariable UUID chatRoomId, @RequestBody ChatMessageDto chatMessageDto) {
        chatService.save(sender,receiver, chatRoomId, chatMessageDto.getMessage());
    }
    @GetMapping
    public List<ChatDto> findAll() {
        return chatService.findAll();
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
