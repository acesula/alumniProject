package com.alumni.project.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConnectionRequest {

    private String username;
    private String friend;
    private boolean status;
}
