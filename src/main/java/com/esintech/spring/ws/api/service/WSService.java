package com.javatechie.spring.ws.api.service;

import com.javatechie.spring.ws.api.dto.ResponseMessage;
import com.javatechie.spring.ws.api.dto.ResponseNotify;
import com.javatechie.spring.ws.api.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WSService {
    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationService notificationService;

    @Autowired
    public WSService(SimpMessagingTemplate messagingTemplate, NotificationService notificationService) {
        this.messagingTemplate = messagingTemplate;
        this.notificationService = notificationService;
    }

    public void notifyFrontend(final String message) {
        ResponseMessage response = new ResponseMessage(message);
        notificationService.sendGlobalNotification();

        messagingTemplate.convertAndSend("/topic/messages", response);
    }

    public void notifyUser(final String receiverId, final String senderId, final String message, final Status status) {
        ResponseNotify response = new ResponseNotify(message,senderId,status);

        notificationService.sendPrivateNotification(receiverId,senderId,status);
        messagingTemplate.convertAndSendToUser(receiverId, "/topic/private-messages", response);
    }
}
