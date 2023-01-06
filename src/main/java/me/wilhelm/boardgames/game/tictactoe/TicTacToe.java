package me.wilhelm.boardgames.game.tictactoe;

import me.wilhelm.boardgames.Boardgames;
import me.wilhelm.boardgames.game.Game;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.LinkedList;

public class TicTacToe implements Game {
    public static TicTacToe currentGame;

    private HashMap<Integer, Mark> spaces = new HashMap<>();
    private final LinkedList<Player> PLAYERS;

    private int turn = 2;

    private TicTacToe(LinkedList<Player> players) {
        PLAYERS = players;
    }
    public static void newGame(LinkedList<Player> players) {
        if (Boardgames.activeGame == Boardgames.Game.TICTACTOE) {
            Boardgames.gameStarted = true;

            currentGame = new TicTacToe(players);
        }
    }

    public void initiateGame() {

        for (int i = 1; i<10; i++)
            spaces.put(i, Mark.NONE);

    }

    public void switchTurn() {
        if (turn == 2)
            turn = 1;
        if (turn == 1)
            turn = 2;


    }

    public void winGame(Player winner) {

    }


    public enum Mark {
        X,
        O,
        NONE,
    }
}
