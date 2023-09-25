package com.alumni.project.messaging.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendNotification() {
//        ResponseMessage message = new ResponseMessage("Global Notification");

//        messagingTemplate.convertAndSend("/topic/global-notifications", message);
    }


}
