package com.alumni.project.service.chat;

import com.alumni.project.dal.entity.Chat;
import com.alumni.project.dal.entity.ChatRoom;
import com.alumni.project.dal.entity.Request;
import com.alumni.project.dal.entity.User;
import com.alumni.project.dal.repository.ChatRepository;
import com.alumni.project.dal.repository.ChatRoomRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.Chat.ChatDto;
import com.alumni.project.dto.Chat.ChatMessageDto;
import com.alumni.project.service.request.RequestStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.alumni.project.service.mapping.MappingServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor

public class ChatServiceImpl implements ChatService{
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final MappingServiceImpl mappingService;
    private final ChatRoomRepository chatRoomRepository;



    @Override
    public String save(String sender, String receiver, UUID chatRoomId, String message) {
        try {
            User senderUser = userRepository.findByUsername(sender);
            User receiverUser = userRepository.findByUsername(receiver);
          Optional<ChatRoom>  chatRoom1 = chatRoomRepository.findById(chatRoomId);

            if(senderUser != null && receiverUser != null && chatRoom1 != null  ){

                Chat newChat = new Chat(sender, receiver, chatRoom1.get(), message);
                chatRepository.save(newChat);
            }

            return "Success: message was sent";
        } catch (Exception e){
            return e.getMessage();
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
    public ChatDto findById(UUID id) {
        var optional = chatRepository.findById(id);
        if (optional.isPresent()) {
            return mappingService.convertToChatDto(optional.get());
        }
        throw new RuntimeException("Education not found");
    }

//    @Override
//    public List<Chat> findByUser(UUID id) {
//        return null;
//    }
//
    @Override
    public void delete(UUID id) {
        this.chatRepository.deleteById(id);

    }

    private Chat map(Chat chat) {
        var dto = new Chat();
        dto.setChatRoom(chat.getChatRoom());
        dto.setSender(chat.getSender());
        dto.setReceiver(chat.getReceiver());

        return dto;
    }
}
