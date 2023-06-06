package org.snakeAndLadder.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class SnakeAndLadderBoard {
    private int size;
    @Setter
    private List<Snake> snakesList;
    @Setter
    private List<Ladder> ladderList;
    @Setter
    private Map<String,Integer> playerPieces;
    public SnakeAndLadderBoard(int size)
    {
        this.size=size;
        this.snakesList=new ArrayList<>();
        this.ladderList=new ArrayList<>();
        this.playerPieces=new HashMap<>();
    }
}
