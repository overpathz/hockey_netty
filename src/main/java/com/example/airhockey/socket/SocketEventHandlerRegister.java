package com.example.airhockey.socket;


import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.example.airhockey.command.Command;
import com.example.airhockey.handler.EventHandler;
import com.example.airhockey.util.EventHandlerRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class SocketEventHandlerRegister {

    private final SocketIOServer server;
    private final ConnectListener connectListener;
    private final DisconnectListener disconnectListener;
    private final EventHandlerRegistry eventHandlerRegistry;

    @PostConstruct
    public void init() {
        registerListeners(server);
    }

    private void registerListeners(SocketIOServer server) {
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        registerEventHandlers();
    }

    private void registerEventHandlers() {
        for (String commandType : eventHandlerRegistry.getHandlerTypes()) {
            EventHandler handler = eventHandlerRegistry.getHandler(commandType);
            server.addEventListener(commandType, handler.getCommandType(),
                    (client, data, ackSender) -> handler.onEvent(client, commandType, (Command) data));
        }
    }

    private ConnectListener onConnected() {
        return connectListener;
    }

    private DisconnectListener onDisconnected() {
        return disconnectListener;
    }

}
