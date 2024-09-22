package com.javatechie.spring.ws.api.controller;

import com.javatechie.spring.ws.api.dto.Message;
import com.javatechie.spring.ws.api.model.Status;
import com.javatechie.spring.ws.api.service.WSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WSController {

    @Autowired
    private WSService service;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody final Message message) {
        service.notifyFrontend(message.getMessageContent());
    }

    @PostMapping("/send-private-message/{receiverId}/{senderId}/{type}")
    public void sendPrivateMessage(@PathVariable final String receiverId,@PathVariable final String senderId,@PathVariable final String type,
                                   @RequestBody final Message message) {
        System.out.println("receiver : "+receiverId+" Message : "+message.getMessageContent()+" type: "+type);


        service.notifyUser(receiverId,senderId, message.getMessageContent(), Status.valueOf(type));
    }

}
