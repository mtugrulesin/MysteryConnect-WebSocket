package com.javatechie.spring.ws.api.dto;

import com.javatechie.spring.ws.api.model.Status;

public class ResponseNotify {

    private String content;

    public Status getType() {
        return type;
    }

    public void setType(Status type) {
        this.type = type;
    }

    private Status type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;

    public ResponseNotify() {
    }

    public ResponseNotify(String content,String userId,Status status) {
        this.content = content;
        this.userId = userId;
        this.type = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
