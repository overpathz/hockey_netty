package com.example.airhockey.handler;

import com.corundumstudio.socketio.SocketIOClient;
import com.example.airhockey.command.MoveCommand;
import com.example.airhockey.event.Event;
import com.example.airhockey.model.Puck;
import com.example.airhockey.queue.PuckMovingQueueProcessor;
import com.example.airhockey.sender.WebSocketSender;
import com.example.airhockey.service.PlayerService;
import com.example.airhockey.service.impl.GameServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
@Slf4j
public class MoveEventHandler implements EventHandler<MoveCommand> {
    private final GameServiceImpl gameService;
    private final PlayerService playerService;
    private final WebSocketSender webSocketSender;
    private final PuckMovingQueueProcessor puckMovingQueueProcessor;
    private final Random random = new Random();

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
        if (5 == 5) {
            log.info("Collision detected with puck");
            Runnable runnable = getRunnable();
            puckMovingQueueProcessor.assignTask(runnable);
        }
    }

    private Runnable getRunnable() {
        return () -> {
            log.info("Running_");
            Puck puck = gameService.getGameSession().getPuck();
            int i1 = random.nextInt(5);
            int i2 = new Random().nextInt(50);
            log.info("index1={}, index2={}", i1, i2);
            for (int i = i1; i < i2; i++) {
                if (i % 2 == 0) {
                    log.info("Collision puck with other object detected");
                    puckMovingQueueProcessor.assignTask(getRunnable());
                    return;
                }
                puck.setX(puck.getX() + i+1);
                puck.setY(puck.getY() + i+1);
                log.info("New puck data={}", puck);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("broadcasting puck coordinates");
                webSocketSender.broadcastMessage("puck_moved", gameService.getGameSession().toString());
            }

        };
    }

    private void formRunnableTask(Object data) {
        ChannelHandlerContext context = null;
    }

    @Override
    public Class<MoveCommand> getCommandType() {
        return MoveCommand.class;
    }
}
