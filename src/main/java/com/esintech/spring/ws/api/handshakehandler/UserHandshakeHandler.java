package com.javatechie.spring.ws.api.handshakehandler;

import com.sun.security.auth.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class UserHandshakeHandler extends DefaultHandshakeHandler {
    private final Logger LOG = LoggerFactory.getLogger(UserHandshakeHandler.class);

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        final String randomId = Objects.requireNonNull(request.getHeaders().get("userId")).get(0);
        LOG.info("User with ID '{}' opened the page " + randomId);

            //LOG.info("User with ID '{}' opened the page " + request.getHeaders().get("userid"));

        return new UserPrincipal(randomId);
    }
}
