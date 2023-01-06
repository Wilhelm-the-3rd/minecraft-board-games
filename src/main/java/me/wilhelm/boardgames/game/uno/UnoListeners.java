package me.wilhelm.boardgames.game.uno;

import me.wilhelm.boardgames.Boardgames;
import me.wilhelm.boardgames.other.Utility;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.ItemTagType;

import java.util.ArrayList;

public class UnoListeners implements Listener {
    static Boardgames plugin;

    public UnoListeners(Boardgames plugin) {
        UnoListeners.plugin = plugin;
    }

    @EventHandler
    public void onCardClick(PlayerInteractEvent e) {
        e.setCancelled(true);
        Player player = e.getPlayer();

        ItemStack clickedItem = e.getPlayer().getInventory().getItemInMainHand();
        Material material = clickedItem.getType();

        UnoCard card = new UnoCard(material);

        if (!checks(player))
            return;
        // Check that card is usable
        if (!card.isUsable())
            return;
        // If card is plus four check if material is brown mushroom
        if (card.getFace() == UnoCard.CardFace.PLUS_FOUR && material != Material.BROWN_MUSHROOM)
            return;

        Uno.currentGame.setCardInPlay(card);
        Uno.currentGame.removeFromHand(player, player.getInventory().getHeldItemSlot());

        if (Uno.currentGame.getHand(player).size() == 0) {
            Uno.currentGame.winGame(player);
            return;
        }

        // Check for special cards
        switch (card.getFace()) {
            case REVERSE:
                Uno.currentGame.setReverse(!Uno.currentGame.getReverse());

                // Broadcast to all players
                for (Player p : Uno.currentGame.getPLAYERS())
                    p.sendMessage(Utility.text("&7[&cU&9N&aO&7] &f" + player.getName() + " has used a reverse card! Reversing the order of play..."));

                Uno.currentGame.switchTurn();
                return;
            case WILD:
                Uno.openColorSelection(player);

                // Broadcast to all players
                for (Player p : Uno.currentGame.getPLAYERS())
                    p.sendMessage(Utility.text("&7[&cW&9I&aL&eD &cC&9A&aR&eD&7] &f" + player.getName() + " has used a wild card! Waiting for their color choice..."));

                return;
            case PLUS_TWO:

                for (int i=0; i<=1; i++)
                    Uno.currentGame.addToHand(Uno.currentGame.getNextPlayer(), UnoCard.randomCard());

                Uno.currentGame.getNextPlayer().sendMessage(Utility.text("&7[&cU&9N&aO&7] &f" + player.getName() + " has used a plus two card on you!"));
                Uno.currentGame.getNextPlayer().sendMessage(Utility.text("&7[&cU&9N&aO&7] &6+2 &fCards"));

                Uno.currentGame.switchTurn();
                return;
            case PLUS_FOUR:
                Uno.openColorSelection(player);

                for (int i=0; i<=3; i++)
                    Uno.currentGame.addToHand(Uno.currentGame.getNextPlayer(), UnoCard.randomCard());

                // Broadcast to all players
                for (Player p : Uno.currentGame.getPLAYERS()) {
                    p.sendMessage(Utility.text("&7[&cP&9L&aU&eS &cF&9O&aU&eR&7] &f" + player.getName() + " has used a plus four card! Adding four cards to " + Uno.currentGame.getNextPlayer().getName() + "'s hand..."));
                    p.sendMessage(Utility.text("&7[&cP&9L&aU&eS &cF&9O&aU&eR&7] &fWaiting for their color choice..."));
                }

                Uno.currentGame.getNextPlayer().sendMessage(Utility.text("&7[&cU&9N&aO&7] &f" + player.getName() + " has used a plus four card on you!"));
                Uno.currentGame.getNextPlayer().sendMessage(Utility.text("&7[&cU&9N&aO&7] &6+4 &fCards"));

                return;
        }

        Uno.currentGame.switchTurn();
    }

    @EventHandler
    public void onCardInventoryClick(InventoryClickEvent e) {
        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();

        ItemStack clickedItem = e.getCurrentItem();
        Material material;

        UnoCard card;

        if (!checks(player))
            return;
        // Check that current item is not null
        if (clickedItem == null)
            return;

        material = clickedItem.getType();
        card = new UnoCard(material);

        // Check that card is usable
        if (!card.isUsable())
            return;
        // If card is plus four check if material is brown mushroom
        if (card.getFace() == UnoCard.CardFace.PLUS_FOUR && material != Material.BROWN_MUSHROOM)
            return;

        Uno.currentGame.setCardInPlay(card);

        int slot = e.getSlot();
        if (slot >= 27)
            slot = slot-6;
        if (slot >= 18)
            slot = slot-4;
        if (slot >= 9)
            slot = slot-2;

        Uno.currentGame.removeFromHand(player, slot);

        if (Uno.currentGame.getHand(player).size() == 0) {
            Uno.currentGame.winGame(player);
            return;
        }

        // Check for special cards
        switch (card.getFace()) {
            case REVERSE:
                Uno.currentGame.setReverse(!Uno.currentGame.getReverse());

                // Broadcast to all players
                for (Player p : Uno.currentGame.getPLAYERS())
                    p.sendMessage(Utility.text("&7[&cU&9N&aO&7] &f" + player.getName() + " has used a reverse card! Reversing the order of play..."));

                Uno.currentGame.switchTurn();
                return;
            case WILD:
                Uno.openColorSelection(player);

                // Broadcast to all players
                for (Player p : Uno.currentGame.getPLAYERS())
                    p.sendMessage(Utility.text("&7[&cW&9I&aL&eD &cC&9A&aR&eD&7] &f" + player.getName() + " has used a wild card! Waiting for their color choice..."));

                return;
            case PLUS_TWO:

                for (int i=0; i<=1; i++)
                    Uno.currentGame.addToHand(Uno.currentGame.getNextPlayer(), UnoCard.randomCard());

                Uno.currentGame.getNextPlayer().sendMessage(Utility.text("&7[&cU&9N&aO&7] &f" + player.getName() + " has used a plus two card on you!"));
                Uno.currentGame.getNextPlayer().sendMessage(Utility.text("&7[&cU&9N&aO&7] &6+2 &fCards"));

                Uno.currentGame.switchTurn();
                return;
            case PLUS_FOUR:
                Uno.openColorSelection(player);

                for (int i=0; i<=3; i++)
                    Uno.currentGame.addToHand(Uno.currentGame.getNextPlayer(), UnoCard.randomCard());

                // Broadcast to all players
                for (Player p : Uno.currentGame.getPLAYERS()) {
                    p.sendMessage(Utility.text("&7[&cP&9L&aU&eS &cF&9O&aU&eR&7] &f" + player.getName() + " has used a plus four card! Adding four cards to " + Uno.currentGame.getNextPlayer().getName() + "'s hand..."));
                    p.sendMessage(Utility.text("&7[&cP&9L&aU&eS &cF&9O&aU&eR&7] &fWaiting for their color choice..."));
                }

                Uno.currentGame.getNextPlayer().sendMessage(Utility.text("&7[&cU&9N&aO&7] &f" + player.getName() + " has used a plus four card on you!"));
                Uno.currentGame.getNextPlayer().sendMessage(Utility.text("&7[&cU&9N&aO&7] &6+4 &fCards"));

                return;
        }

        Uno.currentGame.switchTurn();
    }

    @EventHandler
    public void onWildInventoryClick(InventoryClickEvent e) {
        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();

        ItemStack clickedItem = e.getCurrentItem();
        Material material;

        if (!checks(player))
            return;

        // Check for correct inventory
        if (!e.getView().getTitle().equalsIgnoreCase(Utility.text("&cU&9N&aO &7| &8Choose a color...")))
            return;

        // Check clicked item is not null
        if (clickedItem == null)
            return;

        material = clickedItem.getType();

        if (material != Material.BLUE_CONCRETE && material != Material.RED_CONCRETE && material != Material.GREEN_CONCRETE && material != Material.YELLOW_CONCRETE)
            return;

        String newColor = "";

        switch (material) {
            case BLUE_CONCRETE:
                Uno.currentGame.setCardInPlay(new UnoCard(UnoCard.CardColor.BLUE, UnoCard.CardFace.WILD));
                newColor = "blue";
                player.closeInventory();
                break;
            case RED_CONCRETE:
                Uno.currentGame.setCardInPlay(new UnoCard(UnoCard.CardColor.RED, UnoCard.CardFace.WILD));
                newColor = "red";
                player.closeInventory();
                break;
            case GREEN_CONCRETE:
                Uno.currentGame.setCardInPlay(new UnoCard(UnoCard.CardColor.GREEN, UnoCard.CardFace.WILD));
                newColor = "green";
                player.closeInventory();
                break;
            case YELLOW_CONCRETE:
                Uno.currentGame.setCardInPlay(new UnoCard(UnoCard.CardColor.YELLOW, UnoCard.CardFace.WILD));
                newColor = "yellow";
                player.closeInventory();
                break;
        }

        for (Player p : Uno.currentGame.getPLAYERS())
            p.sendMessage(Utility.text("&7[&cU&9N&aO&7] &f" + player.getName() + " has chosen " + newColor + "!"));

        Uno.currentGame.switchTurn();
    }

    @EventHandler
    public void onWildInventoryClose(InventoryCloseEvent e) {
        if (!checks((Player) e.getPlayer()))
            return;

        if (!e.getView().getTitle().equalsIgnoreCase(Utility.text("&cU&9N&aO &7| &8Choose a color...")))
            return;

        if (e.getReason() == InventoryCloseEvent.Reason.PLUGIN)
            return;

        Uno.openColorSelection((Player) e.getPlayer());
    }

    public boolean checks(Player current) {
        // Check selected game is UNO
        if (Boardgames.activeGame != Boardgames.Game.UNO)
            return false;
        // Check that game has started
        if (!Boardgames.gameStarted)
            return false;
        // Check that the player whose turn it is, is the one who clicked
        return current.equals(Uno.currentGame.getCurrentPlayer());
    }

    public static ItemStack emptyCard() {
        ItemStack empty = Utility.newItem("No Card", Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1, new ArrayList<>());
        ItemMeta meta = empty.getItemMeta();
        meta.getCustomTagContainer().setCustomTag(new NamespacedKey(plugin, "id"), ItemTagType.DOUBLE, Math.random());
        empty.setItemMeta(meta);
        return empty;
    }
}
