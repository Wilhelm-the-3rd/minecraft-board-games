package me.wilhelm.boardgames.other;

import me.wilhelm.boardgames.Boardgames;
import me.wilhelm.boardgames.general.General;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Invites {
    Boardgames plugin;

    public Invites(Boardgames plugin) {
        this.plugin = plugin;
    }

    public void sendInvite(Player sender, Player recipient, Boardgames.Game gameID) {
        String message;
        if (gameID.equals(Boardgames.Game.FIAR)) {
            message = Utility.text("&7[&f!&7] &7You have been invited to play &9Four in a Row &7by &b" + sender.getName() + "!");
        } else {
            message = "null";
        }

        TextComponent clickableMessage = Component.text("Click ").color(NamedTextColor.GRAY)
                .append(Component.text("here")
                        .color(NamedTextColor.AQUA)
                        .decoration(TextDecoration.UNDERLINED, TextDecoration.State.TRUE).clickEvent(ClickEvent.runCommand("/Game accept " + sender.getName())))
                .append(Component.text(" to accept.").color(NamedTextColor.GRAY));
        recipient.sendMessage(" ");
        recipient.sendMessage(message);
        recipient.sendMessage(clickableMessage);
        recipient.sendMessage(" ");

        sender.closeInventory();
        sender.sendMessage(Utility.text("&7[&f!&7] You invited " + recipient.getName() + " to play!"));
        sender.sendMessage(Utility.text("&7This invite expires in two minutes."));
        General.gameInvites.get(recipient).put(sender, gameID);

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (General.gameInvites.get(recipient).containsKey(sender)) {
                if (General.gameInvites.get(recipient).get(sender).equals(gameID)) {
                    General.gameInvites.get(recipient).remove(sender);
                    sender.sendMessage(Utility.text("&7[&f!&7] &cYour invite to &b" + recipient.getName() + " &chas expired."));
                }
            }
        }, 2400L);
    }

    public void offerRematch(Player sender, Player recipient, Boardgames.Game gameID) {
        String message;

        if (gameID.equals(Boardgames.Game.FIAR)) {
            message = Utility.text("7[&f!&7] &7You just played against " + recipient + " in &9Four in a Row&7! Would you like to play again?");
        } else {
            message = "null";
        }

        TextComponent clickableMessageSender = Component.text("Click ").color(NamedTextColor.GRAY)
                .append(Component.text("here")
                        .color(NamedTextColor.AQUA)
                        .decoration(TextDecoration.UNDERLINED, TextDecoration.State.TRUE).clickEvent(ClickEvent.runCommand("/Room game invite " + sender.getName())))
                .append(Component.text(" to invite them.").color(NamedTextColor.GRAY));

        sender.sendMessage(" ");
        sender.sendMessage(message);
        sender.sendMessage(clickableMessageSender);
        sender.sendMessage(" ");
    }
}
