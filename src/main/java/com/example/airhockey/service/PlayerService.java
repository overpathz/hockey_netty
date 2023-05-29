package com.example.airhockey.service;

import com.example.airhockey.model.Player;

import java.util.List;

public interface PlayerService {
    void addPlayer(String id, Player player);
    void addPlayer(Player player);

    void removePlayer(String id);

    List<Player> getAllPlayers();

    int getPlayersAmount();
}
