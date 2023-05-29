package com.example.airhockey.handler;

import com.corundumstudio.socketio.SocketIOClient;
import com.example.airhockey.command.Command;
import com.example.airhockey.event.Event;

public interface EventHandler<T extends Command> {
    Event getEventType();
    void onEvent(SocketIOClient client, String event, T t);
    Class<T> getCommandType();
}
