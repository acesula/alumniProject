package com.alumni.project.messaging.controller;

import com.alumni.project.messaging.dto.Message;
import com.alumni.project.messaging.dto.ResponseMessage;
import com.alumni.project.messaging.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/messaging")
@RequiredArgsConstructor
public class MessageController {

    private final NotificationService notificationService;


    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(final Message message) throws InterruptedException {
        Thread.sleep(1000);
        notificationService.sendGlobalNotification();
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()), HtmlUtils.htmlEscape(message.getSender()));
    }

//    @MessageMapping("/private-message")
//    @SendToUser("/topic/private-messages")
//    public ResponseMessage getPrivateMessage(final Message message,
//                                             final Principal principal) throws InterruptedException {
//        Thread.sleep(1000);
//        notificationService.sendPrivateNotification(principal.getName());
//        return new ResponseMessage(HtmlUtils.htmlEscape(
//                "Sending private message to user " + principal.getName() + ": "
//                        + message.getMessageContent())
//        );
//    }

}
