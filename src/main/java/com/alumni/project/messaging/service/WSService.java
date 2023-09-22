package com.alumni.project.messaging.service;

import com.alumni.project.messaging.dto.Message;
import com.alumni.project.messaging.dto.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WSService {

    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationService notificationService;

//    public void notifyFrontend(final String message) {
//        ResponseMessage response = new ResponseMessage(message);
//        notificationService.sendGlobalNotification();
//
//        messagingTemplate.convertAndSend("/topic/messages", response);
//    }

    public void notifyUser(final Message message) {

        ResponseMessage response = new ResponseMessage(message.getSender(), message.getMessageContent());
//        notificationService.sendGlobalNotification();
        messagingTemplate.convertAndSend("/topic/"+message.getTopic(), response);
    }
}
