package org.snakeAndLadder;

import org.snakeAndLadder.model.Ladder;
import org.snakeAndLadder.model.Player;
import org.snakeAndLadder.model.Snake;
import org.snakeAndLadder.service.SnakeAndLadderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter the number of snakes");
        Scanner scanner=new Scanner(System.in);
        int noOfSnakes= scanner.nextInt();

        System.out.println("Enter the snakes detail");
        List<Snake> snakes=new ArrayList<>();
        for(int i=0;i<noOfSnakes;i++)
            snakes.add(new Snake(scanner.nextInt(),scanner.nextInt()));

        System.out.println("Enter the number of ladders");
        int noOfLadders=scanner.nextInt();
        List<Ladder> ladders=new ArrayList<>();
        System.out.println("Enter the ladders detail");

        for(int i=0;i<noOfLadders;i++)
            ladders.add(new Ladder(scanner.nextInt(),scanner.nextInt()));

        System.out.println("Enter the number of players");
        int noOfPlayers = scanner.nextInt();
        List<Player> players = new ArrayList<Player>();

        System.out.println("Enter the players detail");
        for (int i = 0; i < noOfPlayers; i++) {
            players.add(new Player(scanner.next()));
        }

        SnakeAndLadderService snakeAndLadderService = new SnakeAndLadderService();
        snakeAndLadderService.setPlayers(players);
        snakeAndLadderService.setSnakes(snakes);
        snakeAndLadderService.setLadder(ladders);

        snakeAndLadderService.startGame();

    }
}