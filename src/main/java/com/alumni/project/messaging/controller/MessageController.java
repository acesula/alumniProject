package com.alumni.project.messaging.controller;

import com.alumni.project.messaging.dto.ResponseMessage;
import com.alumni.project.messaging.model.Message;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("api/v1/messaging")
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/public-message")
    @SendTo("/chatroom/public")
    private Message recievePublicMessage(@Payload Message message) {
        return message;
    }

    @MessageMapping("/private-message")
    private Message recievePrivateMessage(@Payload Message message) {
        simpMessagingTemplate.convertAndSendToUser(message.getReceiver(), "/private", message);
        return message;
    }
    //shtesngavidjoja
//    @MessageMapping("/public-message")
//    @SendTo("/topic/message")
//    public ResponseMessage getMessage(final Message message) throws interruptedException{
//
//
//    }

}
