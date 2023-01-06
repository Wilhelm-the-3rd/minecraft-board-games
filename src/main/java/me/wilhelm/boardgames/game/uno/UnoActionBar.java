package me.wilhelm.boardgames.game.uno;

import me.wilhelm.boardgames.Boardgames;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class UnoActionBar {
    static Boardgames plugin;

    public UnoActionBar(Boardgames plugin) {
        UnoActionBar.plugin = plugin;
    }

    static int taskID;

    public static void createUnoActionBar(Player player) {
        BukkitTask task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {

            player.sendActionBar(Component.text("● ").color(Uno.currentGame.getCardInPlay().getColor().asNamedTextColor())
                    .append(Component.text(Uno.currentGame.getCardInPlay().getColor().name()).color(Uno.currentGame.getCardInPlay().getColor().asNamedTextColor()))
                    .append(Component.text("  ⋆  ").color(NamedTextColor.DARK_GRAY))
                    .append(Component.text("It's " + Uno.currentGame.getCurrentPlayer().getName() + "'s Turn! ●").color(Uno.currentGame.getCardInPlay().getColor().asNamedTextColor())));

            if (!Boardgames.gameStarted)
                Bukkit.getScheduler().cancelTask(taskID);
        }, 0L, 10L);

        taskID = task.getTaskId();
    }
}
