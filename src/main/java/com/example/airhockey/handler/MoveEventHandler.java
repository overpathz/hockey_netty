package com.example.airhockey.handler;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.example.airhockey.command.MoveCommand;
import com.example.airhockey.event.Event;
import com.example.airhockey.model.Puck;
import com.example.airhockey.sender.WebSocketSender;
import com.example.airhockey.service.PlayerService;
import com.example.airhockey.service.impl.GameServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
@Slf4j
public class MoveEventHandler implements EventHandler<MoveCommand> {

    private final SocketIOServer server;
    private final GameServiceImpl gameService;
    private final PlayerService playerService;
    private final WebSocketSender webSocketSender;
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    public Event getEventType(SocketIOClient client) {
        return Event.MOVE;
    }

    @Override
    public Event getEventType() {
        return Event.MOVE;
    }

    @Override
    public void onEvent(SocketIOClient client, String event, MoveCommand moveCommand) {
        checkCollision(client);
        log.info("Client={} moved on x={}, y={}", client, moveCommand.getX(), moveCommand.getY());
    }

    private void checkCollision(SocketIOClient client) {
        executorService.submit(() -> {
            if (5 == 5) {
                Puck puck = gameService.getGameSession().getPuck();
                for (int i = 0; i < 10; i++) {
                    puck.setX(puck.getX() + i+1);
                    puck.setY(puck.getY() + i+1);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    server.getBroadcastOperations().sendEvent("puck_moved", gameService.getGameSession().toString());
                }
            }
        });
    }

    @Override
    public Class<MoveCommand> getCommandType() {
        return MoveCommand.class;
    }
}
