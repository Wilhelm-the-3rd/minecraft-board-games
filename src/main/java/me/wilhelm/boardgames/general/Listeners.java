package me.wilhelm.boardgames.general;

import me.wilhelm.boardgames.Boardgames;
import me.wilhelm.boardgames.other.Utility;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Listeners implements Listener {

    Boardgames plugin;
    public Listeners(Boardgames plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        ArrayList<String> ml = new ArrayList<>();
        ml.add(Utility.text("&7Click to open the room menu."));
        ItemStack m = Utility.newItem("&dRoom Menu &7(Right Click)", Material.NETHER_STAR, 1, ml);

        e.getPlayer().getInventory().setItem(8, m);

        if (!General.onlinePlayers.containsKey(1)) {
            General.onlinePlayers.put(1, e.getPlayer());
        } else if (!General.onlinePlayers.containsKey(2)) {
            General.onlinePlayers.put(2, e.getPlayer());
        } else if (!General.onlinePlayers.containsKey(3)) {
            General.onlinePlayers.put(3, e.getPlayer());
        } else if (!General.onlinePlayers.containsKey(4)) {
            General.onlinePlayers.put(4, e.getPlayer());
        } else if (!General.onlinePlayers.containsKey(5)) {
            General.onlinePlayers.put(5, e.getPlayer());
        } else {
            e.getPlayer().kick(Component.text("Sorry! This room is full!"));
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        if (Objects.equals(General.onlinePlayers.get(1), e.getPlayer())) {
            if (General.onlinePlayers.containsKey(2)) {
                General.onlinePlayers.put(1, General.onlinePlayers.get(2));
            } if (General.onlinePlayers.containsKey(3)) {
                General.onlinePlayers.put(2, General.onlinePlayers.get(3));
            } if (General.onlinePlayers.containsKey(4)) {
                General.onlinePlayers.put(3, General.onlinePlayers.get(4));
            } if (General.onlinePlayers.containsKey(5)) {
                General.onlinePlayers.put(4, General.onlinePlayers.get(5));
            }
            General.onlinePlayers.remove(5);
        } else if (Objects.equals(General.onlinePlayers.get(2), e.getPlayer())) {
            if (General.onlinePlayers.containsKey(3)) {
                General.onlinePlayers.put(2, General.onlinePlayers.get(3));
            } if (General.onlinePlayers.containsKey(4)) {
                General.onlinePlayers.put(3, General.onlinePlayers.get(4));
            } if (General.onlinePlayers.containsKey(5)) {
                General.onlinePlayers.put(4, General.onlinePlayers.get(5));
            }
            General.onlinePlayers.remove(5);
        } else if (Objects.equals(General.onlinePlayers.get(3), e.getPlayer())) {
            if (General.onlinePlayers.containsKey(4)) {
                General.onlinePlayers.put(3, General.onlinePlayers.get(4));
            } if (General.onlinePlayers.containsKey(5)) {
                General.onlinePlayers.put(4, General.onlinePlayers.get(5));
            }
            General.onlinePlayers.remove(5);
        } else if (Objects.equals(General.onlinePlayers.get(4), e.getPlayer())) {
            if (General.onlinePlayers.containsKey(5)) {
                General.onlinePlayers.put(4, General.onlinePlayers.get(5));
            }
            General.onlinePlayers.remove(5);
        } else if (Objects.equals(General.onlinePlayers.get(5), e.getPlayer())) {
            General.onlinePlayers.remove(5);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        ArrayList<String> ml = new ArrayList<>();
        ml.add(Utility.text("&7Click to open the room menu."));
        ItemStack m = Utility.newItem("&dRoom Menu &7(Right Click)", Material.NETHER_STAR, 1, ml);

        if (Utility.checkClick(e.getPlayer().getInventory().getItemInMainHand(), m)) {
           plugin.general.roomMenu(e.getPlayer());
        }
    }

    public static ItemStack menuItem;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(Utility.text("&8Room Menu"))) {
            switch (e.getCurrentItem().getType()) {
                case PLAYER_HEAD:
                    plugin.general.gameMenu((Player) e.getWhoClicked());
                    break;
                case CHEST:
                    break;
                case SPYGLASS:
                    break;
                case JUKEBOX:
                    plugin.general.roomInfo((Player) e.getWhoClicked());
                    break;
                case PAINTING:
                    break;
            }
            e.setCancelled(true);
        }
        if (e.getView().getTitle().equals(Utility.text("&8Room Info"))) {
            e.setCancelled(true);
        }

        if (e.getView().getTitle().equals(Utility.text("&8Select a Game"))) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {
                switch (e.getCurrentItem().getType()) {
                    case PLAYER_HEAD:
                        if (!(Boardgames.activeGame == Boardgames.Game.FIAR)) {
                            e.getWhoClicked().closeInventory();
                            ArrayList<String> ml = new ArrayList<>();
                            ml.add(Utility.text("&7Click to start Four in a Row"));
                            menuItem = Utility.newCustomSkull("&9Four in a Row &7(Right Click)", "http://textures.minecraft.net/texture/e2e1f7f6a7e48d70d9dcacf616d5740bafc22161283999506be6d1944ae7e955", 1, ml);

                            for (Player pp : Bukkit.getOnlinePlayers()) {
                                pp.getInventory().setItem(7, menuItem);
                            }
                            Boardgames.activeGame = Boardgames.Game.FIAR;
                            File file = new File(plugin.getDataFolder().getAbsolutePath() + "/c4wood.schem");
                            try {
                                Utility.spawnSchematic(file, -20, 122, 5);
                            } catch (IOException ee) {
                                ee.printStackTrace();
                                e.getWhoClicked().sendMessage(Utility.text("&7[&f!&7] &cSomething went wrong!"));
                            }
                        } else {
                            e.getWhoClicked().sendMessage(Utility.text("&7[&f!&7] &cThis is already the active game!"));
                        }
                        break;
                    case WHITE_BANNER:
                        /*if (!plugin.getConfig().getString("Game.game").equals("SEVEN")) {
                            e.getWhoClicked().closeInventory();
                            ArrayList<String> snl = new ArrayList<>();
                            snl.add(" ");
                            snl.add(Utils.text("&7Start with 7 cards and aim to be"));
                            snl.add(Utils.text("&7the first players without any cards!"));

                            ArrayList<Pattern> snpatterns = new ArrayList<>();
                            snpatterns.add(new Pattern(DyeColor.RED, PatternType.SQUARE_BOTTOM_LEFT));
                            snpatterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_DOWNLEFT));
                            snpatterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_TOP));
                            snpatterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));

                            ItemStack sn = Utils.newBanner("&cSeven &7(Right Click)", Material.WHITE_BANNER, snpatterns, 1, snl);

                            for (Player pp : Bukkit.getOnlinePlayers()) {
                                pp.getInventory().setItem(7, sn);
                            }
                            plugin.getConfig().set("Game.game", "SEVEN");
                            plugin.saveConfig();
                            plugin.reloadConfig();
                        } else {
                            e.getWhoClicked().sendMessage(Utils.text("&7[&f!&7] &cThis is already the active game!"));
                        }*/
                        break;
                }
            }
        }
    }
}
