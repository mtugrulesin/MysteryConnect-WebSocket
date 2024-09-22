package com.javatechie.spring.ws.api.service;

import com.javatechie.spring.ws.api.dto.ResponseMessage;
import com.javatechie.spring.ws.api.dto.ResponseNotify;
import com.javatechie.spring.ws.api.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendGlobalNotification() {
        ResponseMessage message = new ResponseMessage("Global Notification");

        messagingTemplate.convertAndSend("/topic/global-notifications", message);
    }

    public void sendPrivateNotification(final String userId,final String userFrom, final Status status) {
        ResponseNotify message = new ResponseNotify("Private Notification",userFrom,status);

        messagingTemplate.convertAndSendToUser(userId,"/topic/private-notifications", message);
    }
}