package com.alumni.project.service.chat;

import com.alumni.project.dal.entity.Chat;

import com.alumni.project.dal.entity.ChatRoom;
import com.alumni.project.dal.repository.ChatRepository;
import com.alumni.project.dal.repository.GroupChatRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.chat.ChatDto;
import com.alumni.project.service.chatRoom.ChatRoomService;
import com.alumni.project.service.mapping.MappingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final ChatRoomService chatRoomService;
    private final MappingService mappingService;
    private final GroupChatRepository groupChatRepository;


    @Override
    public void saveByChatRoomId(String sender, UUID chatRoomId, String message) {
        ChatRoom chatRoom = this.chatRoomService.findChatRoomById(chatRoomId);
        if (chatRoom != null) {
            Chat chat = new Chat();
            chat.setChatRoom(chatRoom);
            chat.setSender(sender);
            chat.setMessage(message);
            chatRepository.save(chat);
        }
    }

    public void saveByGroupChatId(String sender, UUID groupChatId, String message) {
        var groupChat = groupChatRepository.findById(groupChatId).orElseThrow(RuntimeException::new);
        if (groupChat != null) {
            var chat = new Chat();
            chat.setGroupChat(groupChat);
            chat.setSender(sender);
            chat.setMessage(message);
            chatRepository.save(chat);
        }
    }
    @Override
    public List<ChatDto> findAll() {
        return chatRepository.findAll()
                .stream()
                .map(mappingService::convertToChatDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatDto> getAllChatsByChatRoomId(UUID chatRoomId) {
        return chatRepository.findByChatRoom_Id(chatRoomId)
                .stream()
                .map(mappingService::convertToChatDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<ChatDto> getAllChatsByGroupChatId(UUID groupChatId) {
        return chatRepository.findByGroupChat_Id(groupChatId)
                .stream()
                .map(mappingService::convertToChatDto)
                .collect(Collectors.toList());
    }

    @Override
    public ChatDto findById(UUID id) {
        var optional = chatRepository.findById(id);
        if (optional.isPresent()) {
            return mappingService.convertToChatDto(optional.get());
        }
        throw new RuntimeException("Education not found");
    }


    @Override
    public void delete(UUID id) {
        chatRepository.deleteById(id);

    }


}
