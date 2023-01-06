package me.wilhelm.boardgames.general;

import me.wilhelm.boardgames.Boardgames;
import me.wilhelm.boardgames.other.Utility;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class General {
    Boardgames plugin;
    public General(Boardgames plugin) {
        this.plugin = plugin;
    }
    public static HashMap<Integer, Player> onlinePlayers = new HashMap<>();
    public static HashMap<Player, HashMap<Player, Boardgames.Game>> gameInvites = new HashMap<>();

    public void oppMenu(Player p, String game) {
        Inventory inv = Utility.newInventory(p, 27, Utility.text("&8Select an Opponent!"), true);

        ArrayList<String> p1l = new ArrayList<>();
        p1l.add(Utility.text("&7Would you like to invite this player"));
        p1l.add(Utility.text("&7to play " + game + "?"));
        p1l.add(Utility.text("&01"));
        p1l.add(Utility.text("&e(Click to Select)"));
        ItemStack p1 = Utility.newSkull("&b" + onlinePlayers.get(1).getName(), onlinePlayers.get(1).getName(), 1, p1l);

        ArrayList<String> p2l = new ArrayList<>();
        p2l.add(Utility.text("&7Would you like to invite this player"));
        p2l.add(Utility.text("&7to play " + game + "?"));
        p2l.add(Utility.text("&02"));
        p2l.add(Utility.text("&e(Click to Select)"));
        ItemStack p2 = Utility.newSkull("&b" + onlinePlayers.get(2).getName(), onlinePlayers.get(2).getName(), 1, p2l);

        ArrayList<String> p3l = new ArrayList<>();
        p3l.add(Utility.text("&7Would you like to invite this player"));
        p3l.add(Utility.text("&7to play " + game + "?"));
        p3l.add(Utility.text("&03"));
        p3l.add(Utility.text("&e(Click to Select)"));
        ItemStack p3 = Utility.newSkull("&b" + onlinePlayers.get(3).getName(), onlinePlayers.get(3).getName(), 1, p3l);

        ArrayList<String> p4l = new ArrayList<>();
        p4l.add(Utility.text("&7Would you like to invite this player"));
        p4l.add(Utility.text("&7to play " + game + "?"));
        p4l.add(Utility.text("&04"));
        p4l.add(Utility.text("&e(Click to Select)"));
        ItemStack p4 = Utility.newSkull("&b" + onlinePlayers.get(4).getName(), onlinePlayers.get(4).getName(), 1, p4l);

        ArrayList<String> p5l = new ArrayList<>();
        p5l.add(Utility.text("&7Would you like to invite this player"));
        p5l.add(Utility.text("&7to play " + game + "?"));
        p5l.add(Utility.text("&05"));
        p5l.add(Utility.text("&e(Click to Select)"));
        ItemStack p5 = Utility.newSkull("&b" + onlinePlayers.get(5).getName(), onlinePlayers.get(5).getName(), 1, p5l);

        if (Objects.equals(p, onlinePlayers.get(1))) {
            if (onlinePlayers.containsKey(2)) {
                inv.setItem(10, p2);
            }
            if (onlinePlayers.containsKey(3)) {
                inv.setItem(11, p3);
            }
            if (onlinePlayers.containsKey(4)) {
                inv.setItem(12, p4);
            }
            if (onlinePlayers.containsKey(5)) {
                inv.setItem(13, p5);
            }
        } else if (Objects.equals(p, onlinePlayers.get(2))) {
            if (onlinePlayers.containsKey(1)) {
                inv.setItem(10, p1);
            }
            if (onlinePlayers.containsKey(3)) {
                inv.setItem(11, p3);
            }
            if (onlinePlayers.containsKey(4)) {
                inv.setItem(12, p4);
            }
            if (onlinePlayers.containsKey(5)) {
                inv.setItem(13, p5);
            }
        } else if (Objects.equals(p, onlinePlayers.get(3))) {
            if (onlinePlayers.containsKey(1)) {
                inv.setItem(10, p1);
            }
            if (onlinePlayers.containsKey(2)) {
                inv.setItem(11, p2);
            }
            if (onlinePlayers.containsKey(4)) {
                inv.setItem(12, p4);
            }
            if (onlinePlayers.containsKey(5)) {
                inv.setItem(13, p5);
            }
        } else if (Objects.equals(p, onlinePlayers.get(4))) {
            if (onlinePlayers.containsKey(1)) {
                inv.setItem(10, p1);
            }
            if (onlinePlayers.containsKey(2)) {
                inv.setItem(11, p2);
            }
            if (onlinePlayers.containsKey(3)) {
                inv.setItem(12, p3);
            }
            if (onlinePlayers.containsKey(5)) {
                inv.setItem(13, p5);
            }
        } else if (Objects.equals(p, onlinePlayers.get(5))) {
            if (onlinePlayers.containsKey(1)) {
                inv.setItem(10, p1);
            }
            if (onlinePlayers.containsKey(2)) {
                inv.setItem(11, p2);
            }
            if (onlinePlayers.containsKey(3)) {
                inv.setItem(12, p3);
            }
            if (onlinePlayers.containsKey(4)) {
                inv.setItem(13, p4);
            }
        }
        p.openInventory(inv);
    }

    public void roomMenu(Player p) {
        Inventory inv = Utility.newInventory(p, 27, Utility.text("&8Room Menu"), true);

        ArrayList<String> bl = new ArrayList<>();
        bl.add(Utility.text("&7Browse a list of other players'"));
        bl.add(Utility.text("&7rooms that they have made public for anyone to join!"));
        bl.add(Utility.text(" "));
        bl.add(Utility.text("&eRight-Click &7to open"));
        ItemStack b = Utility.newItem("&fBrowse Other Rooms", Material.PAINTING, 1, bl);
        inv.setItem(10, b);

        ArrayList<String> gl = new ArrayList<>();
        gl.add(Utility.text("&7Browse the library of board games"));
        gl.add(Utility.text("&7that we have and play them with your friends!"));
        gl.add(Utility.text(" "));
        gl.add(Utility.text("&eRight-Click &7to open"));
        ItemStack g = Utility.newCustomSkull("&eGames", "http://textures.minecraft.net/texture/8f9233c1247e03e9fd27742737e79e4ccebd225a9b059d596d5cd34e26f2165", 1, gl);
        inv.setItem(13, g);

        ArrayList<String> fl = new ArrayList<>();
        fl.add(Utility.text("&7Invite your friends and manage"));
        fl.add(Utility.text("&7their permissions within your room!"));
        fl.add(Utility.text(" "));
        fl.add(Utility.text("&e&eRight-Click &7to open"));
        ItemStack f = Utility.newItem("&aFriends", Material.SPYGLASS, 1, fl);
        inv.setItem(12, f);

        ArrayList<String> ggl = new ArrayList<>();
        ggl.add(Utility.text("&7Equip fun gadgets that you can use"));
        ggl.add(Utility.text("&7in your room!"));
        ggl.add(Utility.text(" "));
        ggl.add(Utility.text("&eRight-Click &7to open"));
        ItemStack gg = Utility.newItem("&dGadgets", Material.CHEST, 1, ggl);
        inv.setItem(14, gg);

        ArrayList<String> trl = new ArrayList<>();
        trl.add(Utility.text("&7Configure this room's settings!"));
        trl.add(Utility.text(" "));
        trl.add(Utility.text("&eRight-Click &7to open"));
        ItemStack tr = Utility.newItem("&fThis Room", Material.JUKEBOX, 1, trl);
        inv.setItem(16, tr);

        p.openInventory(inv);
    }
    public void gameMenu(Player p) {
        Inventory inv = Utility.newInventory(p, 27, "&8Select a Game", true);

        ArrayList<String> sl = new ArrayList<>();
        sl.add(Utility.text("&7Search for your desired game."));
        sl.add(Utility.text(" "));
        sl.add(Utility.text("&eRight-Click &7to search"));
        ItemStack s = Utility.newItem("&dSearch", Material.OAK_SIGN, 1, sl);

        ArrayList<String> c4l = new ArrayList<>();
        c4l.add(Utility.text("&7Create a pattern of four before your"));
        c4l.add(Utility.text("&7opponent in Four in a Row"));
        c4l.add(Utility.text(" "));
        c4l.add(Utility.text("&eRight-Click &7to select"));
        ItemStack c4 = Utility.newCustomSkull("&bFour in a Row", "http://textures.minecraft.net/texture/e2e1f7f6a7e48d70d9dcacf616d5740bafc22161283999506be6d1944ae7e955", 1, c4l);

        ArrayList<String> ml = new ArrayList<>();
        ml.add(Utility.text("&7Try to be the last standing player"));
        ml.add(Utility.text("&7by making your friends poor!"));
        ml.add(Utility.text(" "));
        ml.add(Utility.text("&e&eRight-Click &7to select"));
        ItemStack m = Utility.newCustomSkull("&bMineopoly", "http://textures.minecraft.net/texture/ac24dd9f250f35cd9160bf46717d7850ad539716eae05bb11037737523bc0dd8", 1, ml);

        ArrayList<String> snl = new ArrayList<>();
        snl.add(Utility.text("&7Start with 7 cards and aim to be"));
        snl.add(Utility.text("&7the first players without any cards!"));
        snl.add(Utility.text(" "));
        snl.add(Utility.text("&e&eRight-Click &7to select"));

        ArrayList<Pattern> snpatterns = new ArrayList<>();
        snpatterns.add(new Pattern(DyeColor.RED, PatternType.SQUARE_BOTTOM_LEFT));
        snpatterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_DOWNLEFT));
        snpatterns.add(new Pattern(DyeColor.RED, PatternType.STRIPE_TOP));
        snpatterns.add(new Pattern(DyeColor.WHITE, PatternType.BORDER));

        ItemStack sn = Utility.newBanner("&cSeven", Material.WHITE_BANNER, snpatterns, 1, snl);

        inv.setItem(10, c4);
        inv.setItem(11, m);
        inv.setItem(12, sn);
        inv.setItem(22, s);
        p.openInventory(inv);
    }
    public void roomInfo(Player p) {
        Inventory inv = Bukkit.createInventory(p, InventoryType.DROPPER, Utility.text("&8Room Info"));

        ArrayList<String> infoLore = new ArrayList<>();
        infoLore.add(Utility.text("&7Nothing here yet..."));
        ItemStack info = Utility.newItem("&bThis Room", Material.DARK_OAK_DOOR, 1, infoLore);

        inv.setItem(0, Utility.FILLER);
        inv.setItem(1, Utility.FILLER);
        inv.setItem(2, Utility.FILLER);
        inv.setItem(3, Utility.FILLER);
        inv.setItem(4, info);
        inv.setItem(5, Utility.FILLER);
        inv.setItem(6, Utility.FILLER);
        inv.setItem(7, Utility.FILLER);
        inv.setItem(8, Utility.FILLER);

        p.openInventory(inv);
    }
}
