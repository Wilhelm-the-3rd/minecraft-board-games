package me.wilhelm.boardgames.game.uno;

import fr.mrmicky.fastboard.FastBoard;
import me.wilhelm.boardgames.Boardgames;
import me.wilhelm.boardgames.game.Game;
import me.wilhelm.boardgames.other.Timer;
import me.wilhelm.boardgames.other.Utility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Uno implements Game {
    public static Uno currentGame;

    private final LinkedList<Player> PLAYERS;
    private HashMap<Player, ArrayList<UnoCard>> hands = new HashMap<>();

    private int turn = 4;
    private UnoCard cardInPlay;
    private boolean reverse = false;

    private Uno(LinkedList<Player> players) {
        this.PLAYERS = players;
    }
    public static void newGame(LinkedList<Player> players) {
        if (Boardgames.activeGame == Boardgames.Game.UNO) {
            Boardgames.gameStarted = true;

            currentGame = new Uno(players);
            currentGame.turn = currentGame.PLAYERS.size();
            currentGame.initiateGame();

            for (Player player : currentGame.PLAYERS) {
                UnoActionBar.createUnoActionBar(player);
                loadTextures(player);
            }
        }
    }

    public boolean getReverse() {
        return reverse;
    }
    public UnoCard getCardInPlay() {
        return cardInPlay;
    }
    public ArrayList<UnoCard> getHand(Player player) {
        return hands.get(player);
    }

    public LinkedList<Player> getPLAYERS() {
        return PLAYERS;
    }
    public Player getCurrentPlayer() {
        return PLAYERS.get(turn-1);
    }
    public Player getNextPlayer() {

        if (!reverse) {
            if (turn >= PLAYERS.size() || turn < 0) {
                return PLAYERS.get(0);
            }
            return PLAYERS.get(turn);
        }
        else {
            if (turn-2 >= PLAYERS.size() || turn-2 < 0) {
                return PLAYERS.get(PLAYERS.size()-1);
            }
            return PLAYERS.get(turn - 2);
        }
    }

    public void setCardInPlay(UnoCard card) {
        cardInPlay = card;

        for (Player player : PLAYERS) {
            player.getInventory().setItemInOffHand(cardInPlay.asItemStack());
        }

    }
    public void setReverse(boolean isReverse) {
        reverse = isReverse;
    }

    public void initiateGame() {
        setCardInPlay(UnoCard.randomCard());

        for (Player player : PLAYERS) {
            ArrayList<UnoCard> cards = new ArrayList<>();
            hands.put(player, cards);

            for (int i=0; i<7; i++)
                addToHand(player, UnoCard.randomCard());

            setPlayerInventory(player);
        }
        switchTurn();
    }
    public void switchTurn() {
        for (Player player : PLAYERS) {
            UnoScoreboard.updateScores(player);
        }

        if (!reverse) {
            if (turn <= currentGame.PLAYERS.size() - 1)
                turn++;
            else
                turn = 1;
        } else {
            if (turn >= 2)
                turn--;
            else
                turn = currentGame.PLAYERS.size();
        }

        boolean anyUsable = false;
        for (UnoCard card : hands.get(getCurrentPlayer())) {
            if (card.isUsable()) {
                anyUsable = true;
                break;
            }
        }

        if (!anyUsable) {
            getCurrentPlayer().sendMessage(Utility.text("&7[&cU&9N&aO&7] &fYou have no usable cards!"));
            getCurrentPlayer().sendMessage(Utility.text("&7[&cU&9N&aO&7] &6+1 &fCard"));
            addToHand(getCurrentPlayer(), UnoCard.randomCard());

            Timer.delayTask(30L, this::switchTurn);
            return;
        }

        getCurrentPlayer().sendTitle(Utility.text(" "), Utility.text("&6&lIt's your turn!"));
    }
    public void winGame(Player winner) {
        ArrayList<String> roomLore = new ArrayList<>();
        roomLore.add(Utility.text("&7Click to open the room menu."));
        ItemStack roomMenu = Utility.newItem("&dRoom Menu &7(Right Click)", Material.NETHER_STAR, 1, roomLore);

        for (Player player : Uno.currentGame.getPLAYERS()) {
            player.getInventory().clear();
            UnoScoreboard.updateScores(player);
            FastBoard board = new FastBoard(player);
            player.setResourcePack("");
            player.getInventory().setItem(8, roomMenu);

            if (player == winner)
                player.sendTitle(Utility.text("&6&lYou Won!"), " ");
            else
                player.sendTitle(Utility.text("&c&l" + winner.getName() + " Won!"), Utility.text("&7Better luck next time..."));
        }

        Boardgames.gameStarted = false;
    }
    public void forceEndGame(String reason) {
        ArrayList<String> roomLore = new ArrayList<>();
        roomLore.add(Utility.text("&7Click to open the room menu."));
        ItemStack roomMenu = Utility.newItem("&dRoom Menu &7(Right Click)", Material.NETHER_STAR, 1, roomLore);

        for (Player player : Uno.currentGame.getPLAYERS()) {
            player.getInventory().clear();
            UnoScoreboard.updateScores(player);
            FastBoard board = new FastBoard(player);
            player.setResourcePack("");
            player.getInventory().setItem(8, roomMenu);

            player.sendTitle(Utility.text("&c&lThe Game Was Forcefully Ended"), Utility.text(reason));
        }

        Boardgames.gameStarted = false;
    }

    public void setPlayerInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setItem(8, new ItemStack(Material.NETHER_STAR));

        for (int i=7; i<=34; i+=9) {
            player.getInventory().setItem(i, Utility.FILLER);
        }

        for (int i=17; i<=35; i+=9) {
            player.getInventory().setItem(i, Utility.FILLER);
        }

        for (int i=0; i<=hands.get(player).size()-1; i++) {
            player.getInventory().addItem(hands.get(player).get(i).asItemStack());
        }

        for (int i=0; i<=(28-hands.get(player).size()); i++) {
            player.getInventory().addItem(UnoListeners.emptyCard());
        }

        player.getInventory().setItemInOffHand(cardInPlay.asItemStack());
    }
    public void addToHand(Player player, UnoCard card) {
        hands.get(player).add(card);
        setPlayerInventory(player);
    }
    public void removeFromHand(Player player, int index) {
        hands.get(player).remove(index);
        setPlayerInventory(player);
    }

    public static void openColorSelection(Player player) {
        Inventory inv = Utility.newInventory(player, 54, "&cU&9N&aO &7| &8Choose a color...", true);

        ArrayList<String> redLore = new ArrayList<>();
        redLore.add(Utility.text(" "));
        redLore.add(Utility.text("&eRight-Click &7to set the color to &c&lRED&f!"));
        for (int i=10; i<=21; i++) {
            inv.setItem(i, Utility.newItem("&cRed", Material.RED_CONCRETE, 1, redLore));

            if (i == 12)
                i = 18;
        }

        ArrayList<String> blueLore = new ArrayList<>();
        blueLore.add(Utility.text(" "));
        blueLore.add(Utility.text("&eRight-Click &7to set the color to &9&lBLUE&f!"));
        for (int i=14; i<=25; i++) {
            inv.setItem(i, Utility.newItem("&9Blue", Material.BLUE_CONCRETE, 1, blueLore));

            if (i == 16)
                i = 22;
        }

        ArrayList<String> greenLore = new ArrayList<>();
        greenLore.add(Utility.text(" "));
        greenLore.add(Utility.text("&eRight-Click &7to set the color to &a&lGREEN&f!"));
        for (int i=28; i<=39; i++) {
            inv.setItem(i, Utility.newItem("&aGreen", Material.GREEN_CONCRETE, 1, greenLore));

            if (i == 30)
                i = 36;
        }

        ArrayList<String> yellowLore = new ArrayList<>();
        yellowLore.add(Utility.text(" "));
        yellowLore.add(Utility.text("&eRight-Click &7to set the color to &e&lYELLOW&f!"));
        for (int i=32; i<=43; i++) {
            inv.setItem(i, Utility.newItem("&eYellow", Material.YELLOW_CONCRETE, 1, yellowLore));

            if (i == 34)
                i = 40;
        }

        player.openInventory(inv);
    }
    public static void loadTextures(Player player) {
        player.setResourcePack("https://www.dropbox.com/s/a3jn457uwayqzoh/Seven.zip?dl=1");
    }
}
