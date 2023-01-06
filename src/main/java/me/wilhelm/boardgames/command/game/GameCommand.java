package me.wilhelm.boardgames.command.game;

import me.wilhelm.boardgames.Boardgames;
import me.wilhelm.boardgames.game.fourinarow.FourInARow;
import me.wilhelm.boardgames.general.General;
import me.wilhelm.boardgames.game.uno.Uno;
import me.wilhelm.boardgames.other.Utility;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class GameCommand implements CommandExecutor {

    Boardgames plugin;

    public GameCommand(Boardgames plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!command.getName().equalsIgnoreCase("game"))
            return false;

        if (args.length == 0) {
            sender.sendMessage(Utility.text("&7[&f!&7] &fThe active game is set as &6" + Boardgames.activeGame.name()));
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("start")) {
                if (!Boardgames.gameStarted) {
                    if (Boardgames.activeGame == Boardgames.Game.UNO) {
                        sender.sendMessage(Utility.text("&7[&f!&7] &7Starting &6UNO&7..."));
                        Uno.newGame(new LinkedList<>(Bukkit.getOnlinePlayers()));
                        return true;
                    }
                    sender.sendMessage(Utility.text("&7[&f!&7] &7The current game has not been configured for this command. Please check back later."));
                }
                sender.sendMessage(Utility.text("&7[&f!&7] &7There is already an instance of the game &6" + Boardgames.activeGame.name() + " &7running!"));
                return true;
            }
            if (args[0].equalsIgnoreCase("end")) {
                if (Boardgames.gameStarted) {
                    if (Boardgames.activeGame == Boardgames.Game.UNO) {
                        Uno.currentGame.forceEndGame("&7Ended by an Administrator");
                        plugin.getLogger().info("The current instance of the game UNO was ended by " + sender.getName());
                        return true;
                    }
                    sender.sendMessage(Utility.text("&7[&f!&7] &7The current game has not been configured for this command. Please check back later."));
                }
                sender.sendMessage(Utility.text("&7[&f!&7] &7There is no active game right now."));
                return true;
            }
        }

        if (args.length == 2) {
            switch (args[0].toLowerCase()) {
                case "invite":
                    if (Bukkit.getPlayer(args[1]) == null) {
                        sender.sendMessage(Utility.text("&7[&f!&7] &cThat player is not online or doesn't exist!"));
                        return true;
                    }

                    if (!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[1]))) {
                        sender.sendMessage(Utility.text("&7[&f!&7] &cThat player is not online or doesn't exist!"));
                        return true;
                    }

                    if (Boardgames.activeGame == Boardgames.Game.FIAR) {
                        if (!General.gameInvites.containsKey(Bukkit.getPlayer(args[1]))) {
                            General.gameInvites.put(Bukkit.getPlayer(args[1]), new HashMap<>());
                        }
                        if (!General.gameInvites.get(Bukkit.getPlayer(args[1])).containsKey((Player) sender)) {
                            plugin.invites.sendInvite((Player) sender, Bukkit.getPlayer(args[1]), Boardgames.Game.FIAR);
                            return true;
                        }
                        sender.sendMessage(Utility.text("&7[&f!&7] &cYou've already invited that player!"));
                        return true;
                    }
                case "accept":
                    if (Bukkit.getPlayer(args[1]) == null) {
                        sender.sendMessage(Utility.text("&7[&f!&7] &cThat player is not online or doesn't exist!"));
                        return true;
                    }

                    if (!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[1]))) {
                        sender.sendMessage(Utility.text("&7[&f!&7] &cThat player is not online or doesn't exist!"));
                        return true;
                    }

                    if (!General.gameInvites.get((Player) sender).containsKey(Bukkit.getPlayer(args[1]))) {
                        sender.sendMessage(Utility.text("&7[&f!&7] &cYou have no incoming invites from that player!"));
                        return true;
                    }

                    if (Boardgames.activeGame == Boardgames.Game.FIAR) {
                        LinkedList<Player> players = new LinkedList<>();
                        players.add(Bukkit.getPlayer(args[1]));
                        players.add((Player) sender);

                        FourInARow.newGame(players);

                        General.gameInvites.get((Player) sender).remove(Bukkit.getPlayer(args[1]));
                        return true;
                    }
                case "set":
                    if (!Arrays.asList(Boardgames.Game.values()).contains(Boardgames.Game.valueOf(args[1]))) {
                        sender.sendMessage(Utility.text("&7[&f!&7] &cThat game doesn't exist!"));
                        return true;
                    }
                    Boardgames.activeGame = Boardgames.Game.valueOf(args[1]);
                    sender.sendMessage(Utility.text("&7[&f!&7] &7Active game set to " + Boardgames.activeGame.name()));
                    return true;
            }
        }
        return true;
    }
}
