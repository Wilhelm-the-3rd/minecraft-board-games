package me.wilhelm.boardgames.other;

import com.google.common.base.Objects;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import dev.dbassett.skullcreator.SkullCreator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Utility {
    public static final ItemStack FILLER = newItem(" ", Material.GRAY_STAINED_GLASS_PANE, 1, new ArrayList<>());

    public static String text(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
    public static TextComponent component(String message) {
        return Component.text(text(message));
    }

    public static ItemStack newItem(String name, Material item, Integer amount, ArrayList<String> lore) {
        ItemStack i = new ItemStack(item, amount);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(text(name));
        im.setLore(lore);
        i.setItemMeta(im);
        return i;
    }
    public static ItemStack newBanner(String name, Material bannerColor, ArrayList<Pattern> patterns, Integer amount, ArrayList<String> lore) {
        ItemStack i = new ItemStack(bannerColor, amount);
        BannerMeta im = (BannerMeta) i.getItemMeta();
        im.setDisplayName(text(name));
        im.setLore(lore);
        im.setPatterns(patterns);
        im.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        i.setItemMeta(im);
        return i;
    }
    public static ItemStack newSkull(String name, String owner, Integer amount, ArrayList<String> lore) {
        ItemStack i = new ItemStack(Material.PLAYER_HEAD, amount);
        SkullMeta im = (SkullMeta) i.getItemMeta();
        im.setDisplayName(text(name));
        im.setLore(lore);
        im.setOwningPlayer(Bukkit.getPlayer(owner));
        i.setItemMeta(im);
        return i;
    }
    public static ItemStack newCustomSkull(String name, String link, Integer amount, ArrayList<String> lore) {
        ItemStack i = SkullCreator.itemFromUrl(link);
        i.setAmount(amount);
        SkullMeta im = (SkullMeta) i.getItemMeta();
        im.setDisplayName(text(name));
        im.setLore(lore);
        i.setItemMeta(im);
        return i;
    }
    public static Inventory newInventory(Player p, Integer size, String name, Boolean filler) {
        Inventory inv = Bukkit.createInventory(p, size, text(name));
        if (filler)
            setAllFiller(inv, new ArrayList<>());
        return inv;
    }

    public static void spawnSchematic(File schem, Integer x, Integer y, Integer z) throws IOException {
        com.sk89q.worldedit.world.World adaptedWorld = BukkitAdapter.adapt(Bukkit.getWorld("world"));

        ClipboardFormat format = ClipboardFormats.findByFile(schem);
        ClipboardReader reader = format.getReader(new FileInputStream(schem));
        Clipboard clipboard = reader.read();
        EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(adaptedWorld, -1);
        Operation operation = new ClipboardHolder(clipboard).createPaste(editSession)
                .to(BlockVector3.at(x, y, z)).ignoreAirBlocks(true).build();
        try {
            Operations.complete(operation);
            editSession.flushSession();

        } catch (WorldEditException e) {
            e.printStackTrace();
        }
    }

    public static Boolean checkInventoryClick(String title, ItemStack check, ItemStack i, Player p) {
        if (Objects.equal(p.getOpenInventory().getTitle(), title)) {
            if (check != null) {
                return Objects.equal(check, i);
            }
            return false;
        }
        return false;
    }
    public static Boolean checkBlockClick(Material check, Material m, PlayerInteractEvent e) {
        if (e.hasBlock()) {
            if (check != null) {
                return Objects.equal(check, m);
            }
            return false;
        }
        return false;
    }
    public static Boolean checkClick(ItemStack check, ItemStack i) {
        if (check != null) {
            return Objects.equal(check, i);
        }
        return false;
    }

    public static void setFiller(Inventory inv, int[] slots) {
        for (Integer s : slots) {
            inv.setItem(s, FILLER);
        }
    }
    public static void setFiller(Inventory inv, int slot) {
        inv.setItem(slot, FILLER);
    }
    public static void setAllFiller(Inventory inv, ArrayList<Integer> exclude) {
        for (int i=0; i<inv.getSize(); i++) {
            if (exclude.contains(i))
                continue;
            inv.setItem(i, FILLER);
        }
    }
}
