//package com.example.airhockey.controller;
//
//import com.corundumstudio.socketio.SocketIOClient;
//import com.corundumstudio.socketio.SocketIOServer;
//import com.corundumstudio.socketio.annotation.OnConnect;
//import com.corundumstudio.socketio.annotation.OnDisconnect;
//import com.corundumstudio.socketio.annotation.OnEvent;
//import com.example.airhockey.model.Table;
//import com.example.airhockey.model.Player;
//import com.example.airhockey.service.impl.GameServiceImpl;
//import lombok.AllArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.stereotype.Controller;
//
//
//@Log4j2
//@Controller
//@AllArgsConstructor
//public class AirHockeyController {
//    private final SocketIOServer server;
//    private final GameServiceImpl gameService;
//
//    @OnConnect
//    public void onConnect(SocketIOClient client) {
//        log.info("Client[{}] connected to server", client.getSessionId().toString());
//        Player player = new Player();
//        player.setId(client.getSessionId().toString());
//        Table table = gameService.addPlayerToTable(player);
//        server.getBroadcastOperations().sendEvent("on_connect", table);
//    }
//
//    @OnDisconnect
//    public void onDisconnect(SocketIOClient client) {
//        log.info("Client[{}] disconnected from server", client.getSessionId().toString());
//        String playerId = client.getSessionId().toString();
//        gameService.removePlayerFromTable(playerId);
//        server.getBroadcastOperations().sendEvent("player_disconnected", playerId);
//    }
//
//    @OnEvent("start_game")
//    public void startGame(SocketIOClient client) {
//        log.info("Client[{}] started the game", client.getSessionId().toString());
//        gameService.startGame();
//        Table table = gameService.getTable();
//        server.getBroadcastOperations().sendEvent("game_started", table);
//    }
//
//    @OnEvent("player_move")
//    public void onPlayerMoveEvent(SocketIOClient client, Table table) {
//
//    }
//}
