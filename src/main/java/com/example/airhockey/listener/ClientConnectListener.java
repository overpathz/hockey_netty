package com.example.airhockey.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.example.airhockey.event.Event;
import com.example.airhockey.model.GameSession;
import com.example.airhockey.model.Player;
import com.example.airhockey.sender.WebSocketSender;
import com.example.airhockey.service.PlayerService;
import com.example.airhockey.service.impl.GameServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClientConnectListener implements ConnectListener {

    private final PlayerService playerService;
    private final WebSocketSender webSocketSender;
    private final GameServiceImpl gameService;

    @Override
    public void onConnect(SocketIOClient client) {
        log.info("Socket ID[{}]  Connected to socket", client.getSessionId().toString());
        GameSession gameSession = gameService.getGameSession();
        if (gameSession == null) {
            gameService.setGameSession(new GameSession());
        }
        Player player = new Player();
        String sessionId = client.getSessionId().toString();
        player.setId(sessionId);
        playerService.addPlayer(player);
        gameService.getGameSession().setFirstPlayer(player);
        log.info("Added player. Players size={}", playerService.getPlayersAmount());
        webSocketSender.sendMessage(Event.INIT_GAME.getName(), client, "100x100, 100, 100");
    }
}
