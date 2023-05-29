package com.example.airhockey.model;

import lombok.Data;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Data
public class GameSession {
    private Player firstPlayer;
    private Player secondPlayer;
    private Puck puck = new Puck();
    private GameStatus status;
    private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);
}
