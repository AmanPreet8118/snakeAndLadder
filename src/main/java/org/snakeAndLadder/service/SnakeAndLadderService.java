package org.snakeAndLadder.service;

import org.snakeAndLadder.model.Ladder;
import org.snakeAndLadder.model.Player;
import org.snakeAndLadder.model.Snake;
import org.snakeAndLadder.model.SnakeAndLadderBoard;

import java.util.*;

public class SnakeAndLadderService {
    private SnakeAndLadderBoard snakeAndLadderBoard;
    private int intialNumberOfPlayers;
    private int noOfDices;
    private Queue<Player> players;
    private boolean isGameCompleted;

    private static final int DEFAULT_BOARD_SIZE = 100;
    private static final int DEFAULT_NO_OF_DICES = 1;

    public SnakeAndLadderService() {
        this(SnakeAndLadderService.DEFAULT_BOARD_SIZE);
    }

    public SnakeAndLadderService(int boardSize) {
        this.snakeAndLadderBoard = new SnakeAndLadderBoard(boardSize);
        this.players = new LinkedList<Player>();
        this.noOfDices = SnakeAndLadderService.DEFAULT_NO_OF_DICES;
    }

//                             INITIALIZING BOARD    //
    public void setPlayers(List<Player> players) {
        this.players = new LinkedList<>();
        this.intialNumberOfPlayers = players.size();
        Map<String, Integer> playerPieces = new HashMap<>();
        for (Player player : players) {
            this.players.add(player);
            playerPieces.put(player.getId(), 0);
        }
        snakeAndLadderBoard.setPlayerPieces(playerPieces);
    }

    public void setSnakes(List<Snake> snakes) {
        snakeAndLadderBoard.setSnakesList(snakes);
    }

    public void setLadder(List<Ladder> ladders)
    {
        snakeAndLadderBoard.setLadderList(ladders);
    }
                // Business Logic
    private int getNewPositionAfterSnakeOrLadder(int newPosition)
    {
        int prevPosition;
        do {
            prevPosition = newPosition;
            for (Snake snake : snakeAndLadderBoard.getSnakesList()) {
                if (snake.getStart() == newPosition)
                    newPosition = snake.getEnd();
            }
            for (Ladder ladder : snakeAndLadderBoard.getLadderList())
            {
                if(ladder.getStart()== newPosition)
                    newPosition=ladder.getEnd();
            }
        }
        while(prevPosition != newPosition);

        return newPosition;
    }

    private void movePlayer(Player player, int positions)
    {
        int oldPosition=snakeAndLadderBoard.getPlayerPieces().get(player.getId());
        int newPosition=oldPosition+positions;

        int boardSize=snakeAndLadderBoard.getSize();

        if(newPosition > boardSize)
        {
            newPosition=oldPosition;
        }
        else
            newPosition=getNewPositionAfterSnakeOrLadder(newPosition);

        snakeAndLadderBoard.getPlayerPieces().put(player.getId(),newPosition);

        System.out.println(player.getName() + " rolled a " + positions + " and moved from " + oldPosition +" to " + newPosition);
    }

    private int getValueAfterDiceRoll()
    {
        return DiceService.roll();
    }

    private boolean hasPlayerWon(Player player)
    {
        int playerPosition=snakeAndLadderBoard.getPlayerPieces().get(player.getId());
        int winningPosition= snakeAndLadderBoard.getSize();
        return playerPosition==winningPosition;
    }
    private boolean isGameCompleted()
    {
        int currentNumberOfPlayers=players.size();
        return currentNumberOfPlayers<intialNumberOfPlayers;
    }
    public void startGame()
    {
        while(!isGameCompleted())
        {
            int totalDiceValue=getValueAfterDiceRoll();
            Player currentPlayer=players.poll();

            movePlayer(currentPlayer,totalDiceValue);

            if(hasPlayerWon(currentPlayer))
            {
                System.out.println(currentPlayer.getName() + " wins the game");
                snakeAndLadderBoard.getPlayerPieces().remove(currentPlayer.getId());
            }
            else
                players.add(currentPlayer);
        }
    }
}
