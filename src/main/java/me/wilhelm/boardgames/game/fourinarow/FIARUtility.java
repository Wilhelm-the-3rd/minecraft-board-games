package me.wilhelm.boardgames.game.fourinarow;

import me.wilhelm.boardgames.Boardgames;
import me.wilhelm.boardgames.other.Utility;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FIARUtility {

    static Boardgames plugin;

    public FIARUtility(Boardgames plugin) {
        FIARUtility.plugin = plugin;
    }

    // Set all physical blocks in a token to red concrete
    public static void setRedToken(int x, int y, int z) {
        Bukkit.getWorld("world").getBlockAt(x, y, z).setType(Material.RED_CONCRETE);
        Bukkit.getWorld("world").getBlockAt(x, y, z - 1).setType(Material.RED_CONCRETE);

        Bukkit.getWorld("world").getBlockAt(x, y+1, z + 1).setType(Material.RED_CONCRETE);
        Bukkit.getWorld("world").getBlockAt(x, y+1, z).setType(Material.RED_CONCRETE);
        Bukkit.getWorld("world").getBlockAt(x, y+1, z - 1).setType(Material.RED_CONCRETE);
        Bukkit.getWorld("world").getBlockAt(x, y+1, z - 2).setType(Material.RED_CONCRETE);

        Bukkit.getWorld("world").getBlockAt(x, y+2, z + 1).setType(Material.RED_CONCRETE);
        Bukkit.getWorld("world").getBlockAt(x, y+2, z).setType(Material.RED_CONCRETE);
        Bukkit.getWorld("world").getBlockAt(x, y+2, z - 1).setType(Material.RED_CONCRETE);
        Bukkit.getWorld("world").getBlockAt(x, y+2, z - 2).setType(Material.RED_CONCRETE);

        Bukkit.getWorld("world").getBlockAt(x, y+3, z).setType(Material.RED_CONCRETE);
        Bukkit.getWorld("world").getBlockAt(x, y+3, z - 1).setType(Material.RED_CONCRETE);
    }

    // Set all physical blocks in a token to yellow concrete
    public static void setYellowToken(int x, int y, int z) {
        Bukkit.getWorld("world").getBlockAt(x, y, z).setType(Material.YELLOW_CONCRETE);
        Bukkit.getWorld("world").getBlockAt(x, y, z - 1).setType(Material.YELLOW_CONCRETE);

        Bukkit.getWorld("world").getBlockAt(x, y+1, z + 1).setType(Material.YELLOW_CONCRETE);
        Bukkit.getWorld("world").getBlockAt(x, y+1, z).setType(Material.YELLOW_CONCRETE);
        Bukkit.getWorld("world").getBlockAt(x, y+1, z - 1).setType(Material.YELLOW_CONCRETE);
        Bukkit.getWorld("world").getBlockAt(x, y+1, z - 2).setType(Material.YELLOW_CONCRETE);

        Bukkit.getWorld("world").getBlockAt(x, y+2, z + 1).setType(Material.YELLOW_CONCRETE);
        Bukkit.getWorld("world").getBlockAt(x, y+2, z).setType(Material.YELLOW_CONCRETE);
        Bukkit.getWorld("world").getBlockAt(x, y+2, z - 1).setType(Material.YELLOW_CONCRETE);
        Bukkit.getWorld("world").getBlockAt(x, y+2, z - 2).setType(Material.YELLOW_CONCRETE);

        Bukkit.getWorld("world").getBlockAt(x, y+3, z).setType(Material.YELLOW_CONCRETE);
        Bukkit.getWorld("world").getBlockAt(x, y+3, z - 1).setType(Material.YELLOW_CONCRETE);
    }

    // Set all physical blocks in a token to air
    public static void clearToken(int x, int y, int z) {
        Bukkit.getWorld("world").getBlockAt(x, y, z).setType(Material.AIR);
        Bukkit.getWorld("world").getBlockAt(x, y, z - 1).setType(Material.AIR);

        Bukkit.getWorld("world").getBlockAt(x, y+1, z + 1).setType(Material.AIR);
        Bukkit.getWorld("world").getBlockAt(x, y+1, z).setType(Material.AIR);
        Bukkit.getWorld("world").getBlockAt(x, y+1, z - 1).setType(Material.AIR);
        Bukkit.getWorld("world").getBlockAt(x, y+1, z - 2).setType(Material.AIR);

        Bukkit.getWorld("world").getBlockAt(x, y+2, z + 1).setType(Material.AIR);
        Bukkit.getWorld("world").getBlockAt(x, y+2, z).setType(Material.AIR);
        Bukkit.getWorld("world").getBlockAt(x, y+2, z - 1).setType(Material.AIR);
        Bukkit.getWorld("world").getBlockAt(x, y+2, z - 2).setType(Material.AIR);

        Bukkit.getWorld("world").getBlockAt(x, y+3, z).setType(Material.AIR);
        Bukkit.getWorld("world").getBlockAt(x, y+3, z - 1).setType(Material.AIR);
    }

    // Clear the board physically and within the Hashmap
    public static void clearBoard() {
        FourInARow.currentGame.resetSpaces();

        int x = -23;
        int y = 127;
        int z = 20;

        for (int i=0; i<=25; i = i+5) {
            for (int j=0; j<=30; j = j+5) {
                clearToken(x, y + i, z - j);
            }
        }
        /*
        // Column 2
        clearToken(x,y,z-5);
        clearToken(x,y+5,z-5);
        clearToken(x,y+10,z-5);
        clearToken(x,y+15,z-5);
        clearToken(x,y+20,z-5);
        clearToken(x,y+25,z-5);

        // Column 3
        clearToken(x,y,z-10);
        clearToken(x,y+5,z-10);
        clearToken(x,y+10,z-10);
        clearToken(x,y+15,z-10);
        clearToken(x,y+20,z-10);
        clearToken(x,y+25,z-10);

        // Column 4
        clearToken(x,y,z-15);
        clearToken(x,y+5,z-15);
        clearToken(x,y+10,z-15);
        clearToken(x,y+15,z-15);
        clearToken(x,y+20,z-15);
        clearToken(x,y+25,z-15);

        // Column 5
        clearToken(x,y,z-20);
        clearToken(x,y+5,z-20);
        clearToken(x,y+10,z-20);
        clearToken(x,y+15,z-20);
        clearToken(x,y+20,z-20);
        clearToken(x,y+25,z-20);

        // Column 6
        clearToken(x,y,z-25);
        clearToken(x,y+5,z-25);
        clearToken(x,y+10,z-25);
        clearToken(x,y+15,z-25);
        clearToken(x,y+20,z-25);
        clearToken(x,y+25,z-25);

        // Column 7
        clearToken(x,y,z-30);
        clearToken(x,y+5,z-30);
        clearToken(x,y+10,z-30);
        clearToken(x,y+15,z-30);
        clearToken(x,y+20,z-30);
        clearToken(x,y+25,z-30); */
    }

    public static ItemStack columnSelectItem(int columnNumber) {
        ArrayList<String> rl = new ArrayList<>();
        rl.add(Utility.text("&7Right click with this item in"));
        rl.add(Utility.text("&7your hand to place a piece in the"));
        rl.add(Utility.text("&7corresponding column."));
        return Utility.newItem("&9Column " + columnNumber + " &7(Right Click)", Material.BLUE_CONCRETE, 1, rl);
    }

    // Check win for a specific combination
    public static boolean checkWin(Integer a, Integer b, Integer c, FourInARow.TeamColor team) {
        if (!(!(a <= 0) && !(a >= 42)))
            return false;
        if (!(!(b <= 0) && !(b >= 42)))
            return false;
        if (!(!(c <= 0) && !(c >= 42)))
            return false;

        return Objects.equals(FourInARow.currentGame.getSpace(a), team) && Objects.equals(FourInARow.currentGame.getSpace(b), team) && Objects.equals(FourInARow.currentGame.getSpace(c), team);
    }

    // Check every possible win combination
    public static boolean checkWinSlot(Integer x, Integer column, FourInARow.TeamColor team) {
        List<Integer> c1 = Arrays.asList(1, 8, 15, 22, 29, 36);
        List<Integer> c2 = Arrays.asList(2, 9, 16, 23, 30, 37);
        List<Integer> c3 = Arrays.asList(3, 10, 17, 24, 31, 38);
        List<Integer> c4 = Arrays.asList(4, 11, 18, 25, 32, 39);
        List<Integer> c5 = Arrays.asList(5, 12, 19, 26, 33, 40);
        List<Integer> c6 = Arrays.asList(6, 13, 20, 27, 34, 41);
        List<Integer> c7 = Arrays.asList(7, 14, 21, 28, 35, 42);

        // Right
        if (checkWin(x + 1, x + 2, x + 3, team)) {
            if (column == 1 && c4.contains(x + 3)) {
                return true;
            } else if (column == 2 && c5.contains(x + 3)) {
                return true;
            } else if (column == 3 && c6.contains(x + 3)) {
                return true;
            } else return column == 4 && c7.contains(x + 3);
        }
        // Right x2
        else if (checkWin(x - 1, x + 1, x + 2, team)) {
            if (column == 2 && c4.contains(x + 2)) {
                return true;
            } else if (column == 3 && c5.contains(x + 2)) {
                return true;
            } else if (column == 4 && c6.contains(x + 2)) {
                return true;
            } else return column == 5 && c7.contains(x + 2);
        }
        // Bottom Right
        else if (checkWin(x - 6, x - 12, x - 18, team)) {
            if (column == 1 && c4.contains(x - 18)) {
                return true;
            } else if (column == 2 && c5.contains(x - 18)) {
                return true;
            } else if (column == 3 && c6.contains(x - 18)) {
                return true;
            } else return column == 4 && c7.contains(x - 18);
        }
        // Bottom Right x2
        else if (checkWin(x + 6, x - 6, x - 12, team)) {
            if (column == 2 && c4.contains(x - 12)) {
                return true;
            } else if (column == 3 && c5.contains(x - 12)) {
                return true;
            } else if (column == 4 && c6.contains(x - 12)) {
                return true;
            } else return column == 5 && c7.contains(x - 12);
        }
        // Down
        else if (checkWin(x - 7, x - 14, x - 21, team)) {
            if (column == 1 && c1.contains(x - 21)) {
                return true;
            } else if (column == 2 && c2.contains(x - 21)) {
                return true;
            } else if (column == 3 && c3.contains(x - 21)) {
                return true;
            } else if (column == 4 && c4.contains(x - 21)) {
                return true;
            } else if (column == 5 && c5.contains(x - 21)) {
                return true;
            } else if (column == 6 && c6.contains(x - 21)) {
                return true;
            } else return column == 7 && c7.contains(x - 21);
        }
        // Bottom Left
        else if (checkWin(x - 8, x - 16, x - 24, team)) {
            if (column == 4 && c1.contains(x - 24)) {
                return true;
            } else if (column == 5 && c2.contains(x - 24)) {
                return true;
            } else if (column == 6 && c3.contains(x - 24)) {
                return true;
            } else return column == 7 && c4.contains(x - 24);
        }
        // Bottom Left x2
        else if (checkWin(x + 8, x - 8, x - 16, team)) {
            if (column == 3 && c1.contains(x - 16)) {
                return true;
            } else if (column == 4 && c2.contains(x - 16)) {
                return true;
            } else if (column == 5 && c3.contains(x - 16)) {
                return true;
            } else return column == 6 && c4.contains(x - 16);
        }
        // Left
        else if (checkWin(x - 1, x - 2, x - 3, team)) {
            if (column == 4 && c1.contains(x - 3)) {
                return true;
            } else if (column == 5 && c2.contains(x - 3)) {
                return true;
            } else if (column == 6 && c3.contains(x - 3)) {
                return true;
            } else return column == 7 && c4.contains(x - 3);
        }
        // Left x2
        else if (checkWin(x + 1, x - 1, x - 2, team)) {
            if (column == 3 && c1.contains(x - 2)) {
                return true;
            } else if (column == 4 && c2.contains(x - 2)) {
                return true;
            } else if (column == 5 && c3.contains(x - 2)) {
                return true;
            } else return column == 6 && c4.contains(x - 2);
        }
        // Top Left
        else if (checkWin(x + 6, x + 12, x + 18, team)) {
            if (column == 4 && c1.contains(x + 18)) {
                return true;
            } else if (column == 5 && c2.contains(x + 18)) {
                return true;
            } else if (column == 6 && c3.contains(x + 18)) {
                return true;
            } else return column == 7 && c4.contains(x + 18);
        }
        // Top Left x2
        else if (checkWin(x - 6, x + 6, x + 12, team)) {
            if (column == 3 && c1.contains(x + 12)) {
                return true;
            } else if (column == 4 && c2.contains(x + 12)) {
                return true;
            } else if (column == 5 && c3.contains(x + 12)) {
                return true;
            } else return column == 6 && c4.contains(x + 12);
        }
        // Top Right
        else if (checkWin(x + 8, x + 16, x + 24, team)) {
            if (column == 1 && c4.contains(x + 24)) {
                return true;
            } else if (column == 2 && c5.contains(x + 24)) {
                return true;
            } else if (column == 3 && c6.contains(x + 24)) {
                return true;
            } else return column == 4 && c7.contains(x + 24);
        }
        // Top Right x2
        else if (checkWin(x - 8, x + 8, x + 16, team)) {
            if (column == 2 && c4.contains(x + 16)) {
                return true;
            } else if (column == 3 && c5.contains(x + 16)) {
                return true;
            } else if (column == 4 && c6.contains(x + 16)) {
                return true;
            } else return column == 5 && c7.contains(x + 16);
        } else {
            return false;
        }
    }
    static int taskID;
    public static void initiateActionBar(Player p) {
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {

            if (FourInARow.currentGame.getTurn() == 1) {
                p.sendActionBar(Component.text("● ").color(NamedTextColor.RED)
                        .append(Component.text("1:00").color(NamedTextColor.GRAY))
                        .append(Component.text("  ⋆  ").color(NamedTextColor.DARK_GRAY))
                        .append(Component.text("It's Red's Turn! ●").color(NamedTextColor.RED)));
            } else if (FourInARow.currentGame.getTurn() == 2) {
                p.sendActionBar(Component.text("● ").color(NamedTextColor.YELLOW)
                        .append(Component.text("1:00").color(NamedTextColor.GRAY))
                        .append(Component.text("  ⋆  ").color(NamedTextColor.DARK_GRAY))
                        .append(Component.text("It's Yellow's Turn! ●").color(NamedTextColor.YELLOW)));
            }

            if (!Boardgames.gameStarted) {
                Bukkit.getScheduler().cancelTask(taskID);
                plugin.timer.stopTimer(FourInARow.currentGame.getPlayer(2).getName() + "TurnTimer");
                plugin.timer.stopTimer(FourInARow.currentGame.getPlayer(1).getName() + "TurnTimer");
            }
        }, 0L, 10L);

        taskID = task.getTaskId();
    }

    // Countdown for player screen before game
    public static void countDown(Player p1, Player p2) {
        BukkitScheduler scheduler = plugin.getServer().getScheduler();

        p1.sendTitle(Utility.text("5"), Utility.text("&6The game starts in..."));
        p2.sendTitle(Utility.text("5"), Utility.text("&6The game starts in..."));

        p1.playSound(p1.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 10, 10);
        p2.playSound(p2.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 10, 10);

        scheduler.runTaskLater(plugin, () -> {
            p1.sendTitle(Utility.text("4"), Utility.text("&6The game starts in..."));
            p2.sendTitle(Utility.text("4"), Utility.text("&6The game starts in..."));

            p1.playSound(p1.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 10, 10);
            p2.playSound(p2.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 10, 10);
        }, 20L);

        scheduler.runTaskLater(plugin, () -> {
            p1.sendTitle(Utility.text("3"), Utility.text("&6The game starts in..."));
            p2.sendTitle(Utility.text("3"), Utility.text("&6The game starts in..."));

            p1.playSound(p1.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 10, 10);
            p2.playSound(p2.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 10, 10);
        }, 40L);

        scheduler.runTaskLater(plugin, () -> {
            p1.sendTitle(Utility.text("2"), Utility.text("&6The game starts in..."));
            p2.sendTitle(Utility.text("2"), Utility.text("&6The game starts in..."));

            p1.playSound(p1.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 10, 10);
            p2.playSound(p2.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 10, 10);
        }, 60L);

        scheduler.runTaskLater(plugin, () -> {
            p1.sendTitle(Utility.text("1"), Utility.text("&6The game starts in..."));
            p2.sendTitle(Utility.text("1"), Utility.text("&6The game starts in..."));

            p1.playSound(p1.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 10, 10);
            p2.playSound(p2.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 10, 10);
            FourInARow.currentGame.switchTurn();
        }, 80L);
    }
}
