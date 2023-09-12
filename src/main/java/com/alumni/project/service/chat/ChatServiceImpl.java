package com.alumni.project.service.chat;

import com.alumni.project.dal.entity.Chat;
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

    }

    @Override
    public List<Chat> findAll() {
        return chatRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
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

    @Override
    public void delete(UUID id) {
        this.chatRepository.deleteById(id);

    }

    @Override
    public Chat update(UUID uuid, Chat chat) {
        return null;
    }
    private Chat map(Chat chat) {
        var dto = new Chat();
        dto.setChatRoom(chat.getChatRoom());
        dto.setSender(chat.getSender());
        dto.setReceiver(chat.getReceiver());

        return dto;
    }
}
