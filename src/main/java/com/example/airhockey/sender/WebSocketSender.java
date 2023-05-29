package com.example.airhockey.sender;

import com.corundumstudio.socketio.SocketIOClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WebSocketSender {
    public void sendMessage(String eventName, SocketIOClient senderClient, String message) {
        senderClient.sendEvent(eventName, message);
    }
}