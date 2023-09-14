package com.alumni.project.service.chatRoom;

import com.alumni.project.dal.entity.ChatRoom;
import com.alumni.project.dal.entity.Friends;
import com.alumni.project.dal.entity.User;
import com.alumni.project.dal.repository.ChatRoomRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.chatRoom.ChatRoomDto;
import com.alumni.project.service.mapping.MappingServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service


public class ChatRoomServiceImpl implements ChatRoomService{
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MappingServiceImpl mappingService;

    public ChatRoomServiceImpl(UserRepository userRepository, ChatRoomRepository chatRoomRepository, MappingServiceImpl mappingService) {
        this.userRepository = userRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.mappingService = mappingService;
    }

    @Override
    public String save(UUID id1, UUID id2) {
        try {
            var user = userRepository.findById(id1).orElseThrow(RuntimeException::new);
            var user2 = userRepository.findById(id2).orElseThrow(RuntimeException::new);

            if (user != null && user2 != null) {

                ChatRoom newChatRoom = new ChatRoom(user, user2);
                chatRoomRepository.save(newChatRoom);
            }
            return "Success";
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @Override
    public List<ChatRoomDto> findAll() {

        return chatRoomRepository.findAll()
                .stream()
                .map(mappingService::convertToChatRoomDto)
                .collect(Collectors.toList());
    }

    @Override
    public ChatRoomDto findById(UUID id) {

        var optional = chatRoomRepository.findById(id);
        if (optional.isPresent()) {
            return mappingService.convertToChatRoomDto(optional.get());
        }
        throw new RuntimeException("No interest found");
    }


    @Override
    public void delete(UUID id) {
        chatRoomRepository.deleteById(id);
    }

    }


