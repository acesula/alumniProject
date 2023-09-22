package com.alumni.project.service.chatRoom;

import com.alumni.project.dal.entity.ChatRoom;
import com.alumni.project.dal.entity.Friends;
import com.alumni.project.dal.repository.ChatRoomRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.chatRoom.ChatRoomDto;
import com.alumni.project.dto.user.GetChatRoomDto;
import com.alumni.project.service.mapping.MappingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService{
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final MappingServiceImpl mappingService;


    @Override
    public String save(UUID id1, UUID id2) {
        try {
            var user = userRepository.findById(id1).orElseThrow(RuntimeException::new);
            var user2 = userRepository.findById(id2).orElseThrow(RuntimeException::new);


            if (user != null && user2 != null) {
                if (doesChatRoomExist(user.getId(), user2.getId())) {
                    return "ChatRoom Exist";
                }

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
        throw new RuntimeException("No chat found");
    }


    @Override
    public void delete(UUID id) {
       var chatRoom = chatRoomRepository.findById(id).orElseThrow(RuntimeException::new);

        chatRoomRepository.delete(chatRoom);
    }

    @Override
    public List<GetChatRoomDto> findAllChatRoomsById(UUID id) {
        try {
            List<GetChatRoomDto> result = chatRoomRepository.findAllChatRoomsById(id).stream().toList();
            if (!result.isEmpty()) {
                return result;
            }
        } catch (Exception e) {
            System.out.println("----error in getting user info" + e.getMessage());
        }
        return null;
    }


    public ChatRoom findChatRoomById(UUID id){
        try{
            return this.chatRoomRepository.findById(id).get();
        } catch(Exception e){
            return null;
        }

    }

    public boolean doesChatRoomExist(UUID user1, UUID friend1) {

        List<ChatRoom> totalListOfChatRoom = this.chatRoomRepository.findAll();
        Optional<ChatRoom> chatRoom = totalListOfChatRoom.stream().filter(chatRoomItem ->
                (chatRoomItem.getUser1().getId() == user1 && chatRoomItem.getUser2().getId() == friend1) ||
                        (chatRoomItem.getUser1().getId() == friend1 && chatRoomItem.getUser2().getId() == user1)
        ).findFirst();
        if (chatRoom.isEmpty()) return false;
        return true;
    }

}


