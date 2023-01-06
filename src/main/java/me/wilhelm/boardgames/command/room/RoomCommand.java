package me.wilhelm.boardgames.command.room;

import me.wilhelm.boardgames.Boardgames;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RoomCommand implements CommandExecutor {
    Boardgames plugin;
    public RoomCommand(Boardgames plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("Room")) {
            if (args.length == 0) {
                plugin.general.roomInfo((Player) sender);
            }

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    plugin.reloadConfig();
                    return true;
                }
                return false;
            }
            /*if (args.length == 3) {
                if (args[0].equalsIgnoreCase("game")) {
                    switch (args[1].toLowerCase()) {
                        case "invite":
                            if (Bukkit.getPlayer(args[2]) != null) {
                                if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[2]))) {
                                    if (Boardgames.activeGame == Boardgames.Game.FIAR) {
                                        if (!General.gameInvites.containsKey(Bukkit.getPlayer(args[2]))) {
                                            General.gameInvites.put(Bukkit.getPlayer(args[2]), new HashMap<>());
                                        }
                                        if (!General.gameInvites.get(Bukkit.getPlayer(args[2])).containsKey((Player) sender)) {
                                            plugin.invites.sendInvite((Player) sender, Bukkit.getPlayer(args[2]), Boardgames.Game.FIAR);
                                            return true;
                                        }
                                        sender.sendMessage(Utils.text("&7[&f!&7] &cYou've already invited that player!"));
                                        return true;
                                    }
                                }
                                sender.sendMessage(Utils.text("&7[&f!&7] &cThat player is not online or doesn't exist!"));
                                return true;
                            }
                            sender.sendMessage(Utils.text("&7[&f!&7] &cThat player is not online or doesn't exist!"));
                            return true;
                        case "accept":
                            if (Bukkit.getPlayer(args[2]) != null) {
                                if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[2]))) {
                                    if (General.gameInvites.get((Player) sender).containsKey(Bukkit.getPlayer(args[2]))) {
                                        if (Boardgames.activeGame == Boardgames.Game.FIAR) {
                                            LinkedList<Player> players = new LinkedList<>();
                                            players.add(Bukkit.getPlayer(args[2]));
                                            players.add((Player) sender);

                                            FourInARow.newGame(players);

                                            General.gameInvites.get((Player) sender).remove(Bukkit.getPlayer(args[2]));
                                            return true;
                                        }
                                    }
                                    sender.sendMessage(Utils.text("&7[&f!&7] &cYou have no incoming invites from that player!"));
                                    return true;
                                }
                                sender.sendMessage(Utils.text("&7[&f!&7] &cThat player is not online or doesn't exist!"));
                                return true;
                            }
                            sender.sendMessage(Utils.text("&7[&f!&7] &cThat player is not online or doesn't exist!"));
                            return true;
                        case "decline":
                            sender.sendMessage(Utils.text("&7[&f!&7] &cThis command doesn't work and I don't want to make it work!"));
                            Boardgames.activeGame = Boardgames.Game.UNO;
                            Uno.newGame(new LinkedList<>(Bukkit.getOnlinePlayers()));
                            return true;
                        case "set":
                            sender.sendMessage(Utils.text("&7[&f!&7] &cThis command doesn't work and I don't want to make it work!"));
                        case "start":

                        default:
                            return false;
                    }
                }
                return false;
            }
            return false;*/
        }
        return false;
    }
}
