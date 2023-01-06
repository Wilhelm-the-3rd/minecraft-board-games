package me.wilhelm.boardgames.command.game;

import me.wilhelm.boardgames.Boardgames;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GameTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!command.getName().equalsIgnoreCase("game"))
            return null;

        if (args.length == 1) {
            List<String> params = new ArrayList<>();
            params.add("start");
            params.add("invite");
            params.add("accept");
            params.add("set");
            return params;
        }

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("accept")) {
                List<String> params = new ArrayList<>();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    params.add(player.getName());
                }
                return params;
            }
            if (args[0].equalsIgnoreCase("set")) {
                List<String> params = new ArrayList<>();
                for (Boardgames.Game game : Boardgames.Game.values()) {
                    params.add(game.name());
                }
                return params;
            }
        }
        return null;
    }
}
