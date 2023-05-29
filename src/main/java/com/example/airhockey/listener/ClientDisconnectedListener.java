package com.example.airhockey.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.example.airhockey.sender.WebSocketSender;
import com.example.airhockey.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClientDisconnectedListener implements DisconnectListener {
    private final PlayerService playerService;
    private final WebSocketSender webSocketSender;

    @Override
    public void onDisconnect(SocketIOClient client) {
        log.info("Socket ID[{}] Disconnected from socket", client.getSessionId().toString());
        String sessionId = client.getSessionId().toString();
        playerService.removePlayer(sessionId);
        log.info("Added player. Players size={}", playerService.getPlayersAmount());
    }
}
