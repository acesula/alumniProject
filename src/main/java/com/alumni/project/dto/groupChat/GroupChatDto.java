package com.alumni.project.dto.groupChat;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GroupChatDto {
    private UUID id;
    private String admin;
    private String name;
    private UUID userId;
}
