package com.alumni.project.service.chat;

import com.alumni.project.dal.entity.Chat;
<<<<<<< HEAD
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
=======
import com.alumni.project.dal.entity.Request;
import com.alumni.project.dal.entity.User;
import com.alumni.project.dal.repository.ChatRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.service.request.RequestStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;



//    @Override
//    public void save(String sender, String receiver) {
//        User senderUser = userRepository.findByUsername(sender);
//        User receiverUser = userRepository.findByUsername(receiver);
//
//        if(senderUser != null && receiverUser != null ){
//            Chat newChat = new Chat(senderUser.getUsername(), receiverUser.getUsername());
//            ChatRepository.save(newChat);
//        }
//
//        var chat1 = chatRepository.findById(id).orElseThrow(RuntimeException::new);
//        chat1.setChatRoom(chat.getChatRoom());
//        chat1.setSender(chat.getSender());
//        chat1.setReceiver(chat.getReceiver());
//
//
//        return map(chatRepository.save(id, chat1));
//    }

    @Override
    public void save(UUID id, Chat chat) {
>>>>>>> 6ef8658e2fe6a08ac60418cd7b077e0b96c20368

    }

    @Override
<<<<<<< HEAD
    public List<ChatDto> findAll() {
        return chatRepository.findAll()
                .stream()
                .map(mappingService::convertToChatDto)
=======
    public List<Chat> findAll() {
        return chatRepository.findAll()
                .stream()
                .map(this::map)
>>>>>>> 6ef8658e2fe6a08ac60418cd7b077e0b96c20368
                .collect(Collectors.toList());
    }

    @Override
<<<<<<< HEAD
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
=======
    public Chat findById(UUID id) {
        var optional = chatRepository.findById(id);
        if (optional.isPresent()) {
            return map(optional.get());
        }
        throw new RuntimeException("No interest found");
    }

    @Override
    public List<Chat> findByUser(UUID id) {
        return null;
    }

>>>>>>> 6ef8658e2fe6a08ac60418cd7b077e0b96c20368
    @Override
    public void delete(UUID id) {
        this.chatRepository.deleteById(id);

    }

<<<<<<< HEAD
=======
    @Override
    public Chat update(UUID uuid, Chat chat) {
        return null;
    }
>>>>>>> 6ef8658e2fe6a08ac60418cd7b077e0b96c20368
    private Chat map(Chat chat) {
        var dto = new Chat();
        dto.setChatRoom(chat.getChatRoom());
        dto.setSender(chat.getSender());
        dto.setReceiver(chat.getReceiver());

        return dto;
    }
}
