package com.example.airhockey.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Puck {
    private double x;
    private double y;
    private double velocityX;
    private double velocityY;

    public Puck() {
        this.x = 50;
        this.y = 50;
        this.velocityX = 0;
        this.velocityY = 0;
    }
}
