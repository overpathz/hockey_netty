package com.example.airhockey.event;

public enum Event {
    MOVE("move"),
    INIT_GAME("init_game");

    private final String name;

    Event(String move) {
        this.name = move;
    }

    public String getName() {
        return name;
    }
}
