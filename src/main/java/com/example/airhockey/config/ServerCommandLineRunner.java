package com.example.airhockey.config;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServerCommandLineRunner implements CommandLineRunner {
    private final SocketIOServer server;

    @Override
    public void run(String... args) throws Exception {
        server.start();
    }
}
