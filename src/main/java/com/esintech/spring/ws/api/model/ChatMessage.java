package com.javatechie.spring.ws.api.model;

import lombok.*;

import javax.swing.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatMessage {

	private String senderId;
	private String receiverId;
	private String message;

	private Status status;
}
