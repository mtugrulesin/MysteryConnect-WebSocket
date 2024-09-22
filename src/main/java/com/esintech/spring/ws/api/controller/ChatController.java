package com.javatechie.spring.ws.api.controller;

import com.javatechie.spring.ws.api.dto.Message;
import com.javatechie.spring.ws.api.dto.ResponseMessage;
import com.javatechie.spring.ws.api.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.javatechie.spring.ws.api.model.ChatMessage;

import java.security.Principal;

@Controller
public class ChatController {

	@Autowired
	private NotificationService notificationService;

	@MessageMapping("/message")
	@SendTo("/topic/messages")
	public ResponseMessage getMessage(final Message message) throws InterruptedException {
		Thread.sleep(1000);
		notificationService.sendGlobalNotification();
		return new ResponseMessage(message.getMessageContent());
	}

	@MessageMapping("/private-message")
	@SendToUser("/topic/private-messages")
	public ResponseMessage getPrivateMessage(final Message message,
											 final Principal principal) throws InterruptedException {
		Thread.sleep(1000);
		System.out.println(message.getMessageContent()+" Received from "+principal.getName());
		//notificationService.sendPrivateNotification(principal.getName());
		return new ResponseMessage("Sending private message to user " + principal.getName() + ": "
				+ message.getMessageContent()
		);
	}

}
