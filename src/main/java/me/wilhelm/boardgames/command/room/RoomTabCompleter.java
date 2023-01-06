package me.wilhelm.boardgames.command.room;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RoomTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("room")) {
            if (args.length == 1) {
                List<String> pa = new ArrayList<>();
                pa.add("reload");
                return pa;
            }
            /*else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("game")) {
                    List<String> pa = new ArrayList<>();
                    pa.add("invite");
                    pa.add("accept");
                    pa.add("decline");
                    pa.add("set");
                    return pa;
                }
                return null;
            } else if (args.length == 3) {
                if (args[1].equalsIgnoreCase("invite")) {
                    List<String> pa = new ArrayList<>();
                    for (Player op : Bukkit.getOnlinePlayers()) {
                        pa.add(op.getName());
                    }
                    return pa;
                } else if (args[1].equalsIgnoreCase("accept")) {
                    List<String> pa = new ArrayList<>();
                    for (Player op : Bukkit.getOnlinePlayers()) {
                        pa.add(op.getName());
                    }
                    return pa;
                } else if (args[1].equalsIgnoreCase("decline")) {
                    List<String> pa = new ArrayList<>();
                    for (Player op : Bukkit.getOnlinePlayers()) {
                        pa.add(op.getName());
                    }
                    return pa;
                } else if (args[1].equalsIgnoreCase("set")) {
                    List<String> pa = new ArrayList<>();
                    pa.add("FourInARow");
                    pa.add("None");
                    return pa;
                }
                return null;
            } else if (args.length == 4) {
                if (args[1].equalsIgnoreCase("invite")) {
                    List<String> pa = new ArrayList<>();
                    pa.add("FourInARow");
                    return pa;
                } else if (args[1].equalsIgnoreCase("accept")) {
                    List<String> pa = new ArrayList<>();
                    pa.add("FourInARow");
                    return pa;
                } else if (args[1].equalsIgnoreCase("decline")) {
                    List<String> pa = new ArrayList<>();
                    pa.add("FourInARow");
                    return pa;
                }
                return null;
            }
            return null;*/
        }
        return null;
    }
}
