package com.example.airhockey.service.impl;

import com.example.airhockey.model.GameSession;
import com.example.airhockey.service.GameService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@Data
public class GameServiceImpl implements GameService {
    private GameSession gameSession;
}
