package me.wilhelm.boardgames;

import me.wilhelm.boardgames.command.game.GameCommand;
import me.wilhelm.boardgames.command.game.GameTabCompleter;
import me.wilhelm.boardgames.command.room.RoomCommand;
import me.wilhelm.boardgames.command.room.RoomTabCompleter;
import me.wilhelm.boardgames.game.fourinarow.FIARUtility;
import me.wilhelm.boardgames.game.fourinarow.FIARListeners;
import me.wilhelm.boardgames.general.General;
import me.wilhelm.boardgames.game.uno.UnoActionBar;
import me.wilhelm.boardgames.game.uno.UnoListeners;
import me.wilhelm.boardgames.other.Invites;
import me.wilhelm.boardgames.other.Timer;
import me.wilhelm.boardgames.general.Listeners;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Boardgames extends JavaPlugin {

    public static Game activeGame = Game.NONE;
    public static boolean gameStarted = false;

    public Timer timer;
    public Invites invites;
    public General general;
    public FIARUtility fiarUtility;
    public UnoActionBar unoActionBar;

    @Override
    public void onEnable() {
        timer = new Timer(this);
        invites = new Invites(this);
        general = new General(this);
        fiarUtility = new FIARUtility(this);
        unoActionBar = new UnoActionBar(this);

        getLogger().info("Loading config...");
        getLogger().info("Config has been loaded successfully.");
        getLogger().info("Registering listeners...");
        registerListeners();
        getLogger().info("Listeners successfully registered.");
        getLogger().info("Registering commands...");
        registerCommands();
        getLogger().info("Commands successfully registered.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new FIARListeners(this), this);
        getServer().getPluginManager().registerEvents(new Listeners(this), this);
        getServer().getPluginManager().registerEvents(new UnoListeners(this), this);
    }

    private void registerCommands() {
        PluginCommand roomCommand = getCommand("room");
        if (roomCommand != null) {
            roomCommand.setExecutor(new RoomCommand(this));
            roomCommand.setTabCompleter(new RoomTabCompleter());
        }

        PluginCommand gameCommand = getCommand("game");
        if (gameCommand != null) {
            gameCommand.setExecutor(new GameCommand(this));
            gameCommand.setTabCompleter(new GameTabCompleter());
        }
    }

    public enum Game {
        FIAR,
        UNO,
        TICTACTOE,
        NONE,
    }
}
