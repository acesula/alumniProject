package com.alumni.project.service.chat;

import com.alumni.project.dal.entity.Chat;

import com.alumni.project.dal.entity.ChatRoom;
import com.alumni.project.dal.repository.ChatRepository;
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
public class ChatServiceImpl implements ChatService{
    private final ChatRepository chatRepository;
    private final ChatRoomService chatRoomService;
    private final UserRepository userRepository;
    private final MappingService mappingService;


    @Override
    public String save(UUID sender, UUID receiver, UUID chatRoomId, String message) {
        try{
            ChatRoom chatRoom = this.chatRoomService.findChatRoomById(chatRoomId);
            if(chatRoom != null){
                Chat chat = new Chat();
                chat.setSender(sender.toString());
                chat.setReceiver(receiver.toString());
                chat.setChatRoom(chatRoom);
                chat.setMessage(message);
                this.chatRepository.save(chat);

                return "Message was sent successfully";

            } else{
                return "Chat room does not exist";
            }
        } catch(Exception e){
            return "Error on sending chat";
        }
    }

    @Override
    public void save(UUID id, Chat chat) {
        var chat1 = chatRepository.findById(id).orElseThrow(RuntimeException::new);
        chat1.setChatRoom(chat.getChatRoom());
        chat1.setSender(chat.getSender());
        chat1.setReceiver(chat.getReceiver());

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
        try {
            List<Chat> chats = chatRepository.findAllByChatRoomId(chatRoomId).stream().toList();
            List<ChatDto> chatList = new ArrayList<>();
            chats.forEach(it -> chatList.add(mappingService.convertToChatDto(it)));
            return chatList;
        } catch (Exception e) {
                return null;
        }

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
