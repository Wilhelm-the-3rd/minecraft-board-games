package me.wilhelm.boardgames.game;

import org.bukkit.entity.Player;

public interface Game {
    void initiateGame();
    void switchTurn();
    void winGame(Player winner);
}
