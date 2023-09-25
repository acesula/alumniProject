package com.alumni.project.service.groupChat;

import com.alumni.project.dto.groupChat.GroupChatDto;

import java.util.List;
import java.util.UUID;

public interface GroupChatService {

    void createGroupChat(String name);

    List<GroupChatDto> getAllGroupChats();


    void deleteGroupChat(UUID groupChatId);

    void changeGroupName(UUID groupChatId, String name);

}
