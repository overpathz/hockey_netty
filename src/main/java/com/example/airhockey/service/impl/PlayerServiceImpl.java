package com.example.airhockey.service.impl;

import com.example.airhockey.model.Player;
import com.example.airhockey.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final Map<String, Player> players = new ConcurrentHashMap<>();
    public void addPlayer(String id, Player player) {
        this.players.put(id, player);
    }

    @Override
    public void addPlayer(Player player) {
        String id = player.getId();
        this.players.put(id, player);
    }

    public void removePlayer(String id) {
        this.players.remove(id);
    }

    public List<Player> getAllPlayers() {
        return this.players.values().stream().toList();
    }

    @Override
    public int getPlayersAmount() {
        return players.size();
    }
}
