package com.alumni.project.service.groupChat;

import com.alumni.project.dal.entity.GroupChat;
import com.alumni.project.dal.repository.GroupChatRepository;
import com.alumni.project.dal.repository.UserRepository;
import com.alumni.project.dto.groupChat.GroupChatDto;
import com.alumni.project.security.model.AuthUserDetail;
import com.alumni.project.service.mapping.MappingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupChatServiceImpl implements GroupChatService{

    private final GroupChatRepository groupChatRepository;
    private final MappingServiceImpl mappingService;
    private final UserRepository userRepository;

    public AuthUserDetail authenticatedUser() {
        return (AuthUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    @Transactional
    public void createGroupChat(String name) {
        var user = userRepository.findById(authenticatedUser().getId()).orElseThrow(RuntimeException::new);
        var groupChat = new GroupChat();
        groupChat.setName(name);
        groupChat.setAdmin(user.getUsername());
        groupChat.setUser(user);
        groupChatRepository.save(groupChat);

    }

    @Override
    public List<GroupChatDto> getAllGroupChats() {
        return groupChatRepository.findAll()
                .stream()
                .map(mappingService::convertToGroupChatDto)
                .toList();
    }


    @Override
    public void deleteGroupChat(UUID groupChatId) {
        groupChatRepository.deleteById(groupChatId);
    }

    @Override
    public void changeGroupName(UUID groupChatId, String name) {
        var groupChat = groupChatRepository.findById(groupChatId).orElseThrow(RuntimeException::new);
        groupChat.setName(name);
        groupChatRepository.save(groupChat);
    }
}
