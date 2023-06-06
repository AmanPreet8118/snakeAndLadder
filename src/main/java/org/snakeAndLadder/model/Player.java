package org.snakeAndLadder.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Player {
    private String name;
    private String id;

    public Player(String name)
    {
        this.name=name;
        this.id= UUID.randomUUID().toString();
    }
}
