package me.wilhelm.boardgames.game.fourinarow;

import me.wilhelm.boardgames.Boardgames;
import me.wilhelm.boardgames.game.Game;
import me.wilhelm.boardgames.other.Utility;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class FourInARow implements Game {
    public static FourInARow currentGame;

    private final LinkedList<Player> PLAYERS;
    private HashMap<Integer, TeamColor> spaces = new HashMap<>();

    private Player player1;
    private Player player2;
    private int turn = 2;

    private FourInARow(LinkedList<Player> players) {
        this.PLAYERS = players;
    }

    // Ensure there is no active game before creating one
    public static void newGame(LinkedList<Player> players) {
        if (Objects.equals(Boardgames.activeGame, Boardgames.Game.FIAR)) {
            Boardgames.gameStarted = true;

            currentGame = new FourInARow(players);
            currentGame.initiateGame();
            FIARUtility.clearBoard();
        }
    }

    public void initiateGame() {
        player1 = PLAYERS.get(0);
        player2 = PLAYERS.get(1);

        FIARUtility.countDown(player1, player2);
    }

    public void switchTurn() {
        if (turn == 1)
            turn++;
        else if (turn == 2)
            turn = 1;

        player1.playSound(player1.getLocation(), Sound.BLOCK_ANCIENT_DEBRIS_FALL, 10, 10);
        player2.playSound(player2.getLocation(), Sound.BLOCK_ANCIENT_DEBRIS_FALL, 10, 10);

        FIARUtility.initiateActionBar(player1);
        FIARUtility.initiateActionBar(player2);

        ArrayList<String> menuLore = new ArrayList<>();
        menuLore.add(Utility.text("&7Click to open Four in a Row settings."));
        ItemStack menuItem = Utility.newCustomSkull("&9Four in a Row &7(Right Click)", "http://textures.minecraft.net/texture/e2e1f7f6a7e48d70d9dcacf616d5740bafc22161283999506be6d1944ae7e955", 1, menuLore);

        if (turn == 1) {
            player1.playSound(player1.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 3);
            player1.sendTitle(Utility.text(" "), Utility.text("&6&lIt's your turn!"));

            for (int i=0; i<7; i++) {
                player1.getInventory().setItem(i, FIARUtility.columnSelectItem(i+1));
            }
            player1.getInventory().setItem(7, Utility.FILLER);
            player1.getInventory().setItem(7, menuItem);

            for (int i=0; i<8; i++) {
                player2.getInventory().setItem(i, Utility.FILLER);
            }
            player2.getInventory().setItem(7, menuItem);

        } else {
            player2.playSound(player2.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 3);
            player2.sendTitle(Utility.text(" "), Utility.text("&6&lIt's your turn!"));

            for (int i=0; i<7; i++) {
                player2.getInventory().setItem(i, FIARUtility.columnSelectItem(i+1));
            }
            player2.getInventory().setItem(7, Utility.FILLER);
            player2.getInventory().setItem(7, menuItem);

            for (int i=0; i<8; i++) {
                player1.getInventory().setItem(i, Utility.FILLER);
            }
            player1.getInventory().setItem(7, menuItem);
        }

    }

    public void winGame(Player winner) {
        winner.sendTitle(Utility.text("&6&lYou Won!"), Utility.text(" "));

        if (!winner.equals(player1))
            player1.sendTitle(Utility.text("&c&lYou Lost!"), Utility.text(" "));

        if (!winner.equals(player2))
            player2.sendTitle(Utility.text("&c&lYou Lost!"), Utility.text(" "));

        player1.getInventory().clear();
        player2.getInventory().clear();

        ArrayList<String> roomLore = new ArrayList<>();
        roomLore.add(Utility.text("&7Click to open the room menu."));
        ItemStack roomMenu = Utility.newItem("&dRoom Menu &7(Right Click)", Material.NETHER_STAR, 1, roomLore);

        ArrayList<String> menuLore = new ArrayList<>();
        menuLore.add(Utility.text("&7Click to open Four in a Row settings."));
        ItemStack menuItem = Utility.newCustomSkull("&9Four in a Row &7(Right Click)", "http://textures.minecraft.net/texture/e2e1f7f6a7e48d70d9dcacf616d5740bafc22161283999506be6d1944ae7e955", 1, menuLore);

        player1.getInventory().setItem(8, roomMenu);
        player1.getInventory().setItem(7, menuItem);

        player2.getInventory().setItem(8, roomMenu);
        player2.getInventory().setItem(7, menuItem);

        Boardgames.gameStarted = false;
    }

    // Reset hashmap to contain EMPTY in every slot
    public void resetSpaces() {
        for (int i = 1; i < 42; i++) {
            spaces.put(i, TeamColor.EMPTY);
        }
    }

    // Fetch player from PLAYERS LinkedList
    public Player getPlayer(int playerID) {
        return PLAYERS.get(playerID-1);
    }

    // Fetch turn variable
    public int getTurn() {
        return turn;
    }

    // Fetch specific space from hashmap
    public TeamColor getSpace(int slotID) {
        return spaces.get(slotID);
    }

    // Set specific space from hashmap
    public void setSpace(int slotID, TeamColor team) {
        spaces.put(slotID, team);
    }

    public boolean isEmpty(int x) {
        return Objects.equals(currentGame.getSpace(x), FourInARow.TeamColor.EMPTY);
    }

    public enum TeamColor {
        RED,
        YELLOW,
        EMPTY,
    }
}
