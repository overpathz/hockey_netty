package com.example.airhockey.sender;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebSocketSender {

    private final SocketIOServer server;

    public void broadcastMessage(String eventName, Object message) {
        server.getBroadcastOperations().sendEvent(eventName, message);
    }

    public void sendMessage(String eventName, SocketIOClient senderClient, String message) {
        senderClient.sendEvent(eventName, message);
    }
}