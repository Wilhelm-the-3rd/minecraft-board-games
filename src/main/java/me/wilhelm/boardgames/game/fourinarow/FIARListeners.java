package me.wilhelm.boardgames.game.fourinarow;

import com.google.common.base.Objects;
import me.wilhelm.boardgames.Boardgames;
import me.wilhelm.boardgames.general.General;
import me.wilhelm.boardgames.general.Listeners;
import me.wilhelm.boardgames.other.Utility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FIARListeners implements Listener {

    Boardgames plugin;

    public FIARListeners(Boardgames plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onOppSelectClick(InventoryClickEvent e) {
        if (java.util.Objects.equals(Boardgames.activeGame, Boardgames.Game.FIAR)) {
            e.setCancelled(true);
            if (checkInventoryClickByLore(Utility.text("&8Select an Opponent!"), Utility.text("&01"), e.getCurrentItem().getLore(), (Player) e.getWhoClicked())) {
                plugin.invites.sendInvite((Player) e.getWhoClicked(), General.onlinePlayers.get(1), Boardgames.Game.FIAR);
            }
            if (checkInventoryClickByLore(Utility.text("&8Select an Opponent!"), Utility.text("&02"), e.getCurrentItem().getLore(), (Player) e.getWhoClicked())) {
                plugin.invites.sendInvite((Player) e.getWhoClicked(), General.onlinePlayers.get(2), Boardgames.Game.FIAR);
            }
            if (checkInventoryClickByLore(Utility.text("&8Select an Opponent!"), Utility.text("&03"), e.getCurrentItem().getLore(), (Player) e.getWhoClicked())) {
                plugin.invites.sendInvite((Player) e.getWhoClicked(), General.onlinePlayers.get(3), Boardgames.Game.FIAR);
            }
            if (checkInventoryClickByLore(Utility.text("&8Select an Opponent!"), Utility.text("&04"), e.getCurrentItem().getLore(), (Player) e.getWhoClicked())) {
                plugin.invites.sendInvite((Player) e.getWhoClicked(), General.onlinePlayers.get(4), Boardgames.Game.FIAR);
            }
            if (checkInventoryClickByLore(Utility.text("&8Select an Opponent!"), Utility.text("&05"), e.getCurrentItem().getLore(), (Player) e.getWhoClicked())) {
                plugin.invites.sendInvite((Player) e.getWhoClicked(), General.onlinePlayers.get(5), Boardgames.Game.FIAR);
            }
        }
    }

    @EventHandler
    public void onFIARItemClick(PlayerInteractEvent e) {

        if (!Utility.checkClick(e.getPlayer().getInventory().getItemInMainHand(), Listeners.menuItem))
            return;

        Inventory inv = Utility.newInventory(e.getPlayer(), 45, "&8Four in a Row", false);
        int[] include = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};
        Utility.setFiller(inv, include);

        ArrayList<String> sGLore = new ArrayList<>();
        sGLore.add(" ");
        sGLore.add(Utility.text("&eLeft-Click &7to search for a player in this"));
        sGLore.add(Utility.text("&7room to invite."));
        inv.setItem(22, Utility.newItem("&aStart Game", Material.LIME_TERRACOTTA, 1, sGLore));
    }

    @EventHandler
    public void onFIARMenuClick(InventoryClickEvent e) {
        ArrayList<String> sGLore = new ArrayList<>();
        sGLore.add(" ");
        sGLore.add(Utility.text("&eLeft-Click &7to search for a player in this"));
        sGLore.add(Utility.text("&7room to invite."));
        ItemStack sG = Utility.newItem("&aStart Game", Material.LIME_TERRACOTTA, 1, sGLore);

        if (!Utility.checkInventoryClick("&8Four in a Row", e.getCurrentItem(), sG, (Player) e.getWhoClicked()))
            return;

        if (!Boardgames.gameStarted) {
            plugin.general.oppMenu((Player) e.getWhoClicked(), "Four in a Row");
        } else {
            e.getWhoClicked().sendMessage("&7[&f!&7] &cThere is an active game! Please wait for it to complete.");
            e.getWhoClicked().closeInventory();
        }
    }

    @EventHandler
    public void onFIARGameItemClick(PlayerInteractEvent e) {
        if (Boardgames.Game.FIAR != Boardgames.activeGame)
            return;

        if (!Boardgames.gameStarted)
            return;

        if (e.getAction() == Action.RIGHT_CLICK_AIR)
            return;

        if (!e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.BLUE_CONCRETE))
            return;

        Player p1 = FourInARow.currentGame.getPlayer(1);
        Player p2 = FourInARow.currentGame.getPlayer(2);
        if (e.getPlayer() == p1) {
            plugin.timer.pauseTimer(p1.getName() + "TurnTimer");
            plugin.timer.setTimer(60, p2.getName() + "TurnTimer");
            plugin.timer.startTimer(p2.getName() + "TurnTimer");
        }

        if (e.getPlayer() == p2) {
            plugin.timer.pauseTimer(p2.getName() + "TurnTimer");
            plugin.timer.setTimer(60, p1.getName() + "TurnTimer");
            plugin.timer.startTimer(p1.getName() + "TurnTimer");
        }

        if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("1")) {
            // 1, 8, 15, 22, 29, 36
            if (FourInARow.currentGame.isEmpty(1)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(1, team);
                    FIARUtility.setRedToken(-23, 127, 20);

                    if (FIARUtility.checkWinSlot(1, 1, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(1, team);
                    FIARUtility.setYellowToken(-23, 127, 20);

                    if (FIARUtility.checkWinSlot(1, 1, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(8)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(8, team);
                    FIARUtility.setRedToken(-23, 132, 20);

                    if (FIARUtility.checkWinSlot(8, 1, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(8, team);
                    FIARUtility.setYellowToken(-23, 132, 20);

                    if (FIARUtility.checkWinSlot(8, 1, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(15)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(15, team);
                    FIARUtility.setRedToken(-23, 137, 20);

                    if (FIARUtility.checkWinSlot(15, 1, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(15, team);
                    FIARUtility.setYellowToken(-23, 137, 20);

                    if (FIARUtility.checkWinSlot(15, 1, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(22)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(22, team);
                    FIARUtility.setRedToken(-23, 142, 20);

                    if (FIARUtility.checkWinSlot(22, 1, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(22, team);
                    FIARUtility.setYellowToken(-23, 142, 20);

                    if (FIARUtility.checkWinSlot(22, 1, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(29)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(29, team);
                    FIARUtility.setRedToken(-23, 147, 20);

                    if (FIARUtility.checkWinSlot(29, 1, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(29, team);
                    FIARUtility.setYellowToken(-23, 147, 20);

                    if (FIARUtility.checkWinSlot(29, 1, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(36)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(36, team);
                    FIARUtility.setRedToken(-23, 152, 20);

                    if (FIARUtility.checkWinSlot(36, 1, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(36, team);
                    FIARUtility.setYellowToken(-23, 152, 20);

                    if (FIARUtility.checkWinSlot(36, 1, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            }
        } else if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("2")) {
            // 2, 9, 16, 23, 30, 37
            if (FourInARow.currentGame.isEmpty(2)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(2, team);
                    FIARUtility.setRedToken(-23, 127, 15);

                    if (FIARUtility.checkWinSlot(2, 2, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(2, team);
                    FIARUtility.setYellowToken(-23, 127, 15);

                    if (FIARUtility.checkWinSlot(2, 2, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(9)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(9, team);
                    FIARUtility.setRedToken(-23, 132, 15);

                    if (FIARUtility.checkWinSlot(9, 2, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(9, team);
                    FIARUtility.setYellowToken(-23, 132, 15);

                    if (FIARUtility.checkWinSlot(9, 2, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(16)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(16, team);
                    FIARUtility.setRedToken(-23, 137, 15);

                    if (FIARUtility.checkWinSlot(16, 2, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(16, team);
                    FIARUtility.setYellowToken(-23, 137, 15);

                    if (FIARUtility.checkWinSlot(16, 2, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(23)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(23, team);
                    FIARUtility.setRedToken(-23, 142, 15);

                    if (FIARUtility.checkWinSlot(23, 2, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(23, team);
                    FIARUtility.setYellowToken(-23, 142, 15);

                    if (FIARUtility.checkWinSlot(23, 2, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(30)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(30, team);
                    FIARUtility.setRedToken(-23, 147, 15);

                    if (FIARUtility.checkWinSlot(30, 2, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(30, team);
                    FIARUtility.setYellowToken(-23, 147, 15);

                    if (FIARUtility.checkWinSlot(30, 2, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(37)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(37, team);
                    FIARUtility.setRedToken(-23, 152, 15);

                    if (FIARUtility.checkWinSlot(37, 2, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(37, team);
                    FIARUtility.setYellowToken(-23, 152, 15);

                    if (FIARUtility.checkWinSlot(37, 2, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            }
        } else if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("3")) {
            // 3, 10, 17, 24, 31, 38
            if (FourInARow.currentGame.isEmpty(3)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(3, team);
                    FIARUtility.setRedToken(-23, 127, 10);

                    if (FIARUtility.checkWinSlot(3, 3, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(3, team);
                    FIARUtility.setYellowToken(-23, 127, 10);

                    if (FIARUtility.checkWinSlot(3, 3, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(10)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(10, team);
                    FIARUtility.setRedToken(-23, 132, 10);

                    if (FIARUtility.checkWinSlot(10, 3, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(10, team);
                    FIARUtility.setYellowToken(-23, 132, 10);

                    if (FIARUtility.checkWinSlot(10, 3, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(17)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(17, team);
                    FIARUtility.setRedToken(-23, 137, 10);

                    if (FIARUtility.checkWinSlot(17, 3, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(17, team);
                    FIARUtility.setYellowToken(-23, 137, 10);

                    if (FIARUtility.checkWinSlot(17, 3, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(24)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(24, team);
                    FIARUtility.setRedToken(-23, 142, 10);

                    if (FIARUtility.checkWinSlot(24, 3, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(24, team);
                    FIARUtility.setYellowToken(-23, 142, 10);

                    if (FIARUtility.checkWinSlot(24, 3, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(31)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(31, team);
                    FIARUtility.setRedToken(-23, 147, 10);

                    if (FIARUtility.checkWinSlot(31, 3, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(31, team);
                    FIARUtility.setYellowToken(-23, 147, 10);

                    if (FIARUtility.checkWinSlot(31, 3, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(38)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(38, team);
                    FIARUtility.setRedToken(-23, 152, 10);

                    if (FIARUtility.checkWinSlot(38, 3, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(38, team);
                    FIARUtility.setYellowToken(-23, 152, 10);

                    if (FIARUtility.checkWinSlot(38, 3, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            }
        } else if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("4")) {
            // 4, 11, 18, 25, 32, 39
            if (FourInARow.currentGame.isEmpty(4)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(4, team);
                    FIARUtility.setRedToken(-23, 127, 5);

                    if (FIARUtility.checkWinSlot(4, 4, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(4, team);
                    FIARUtility.setYellowToken(-23, 127, 5);

                    if (FIARUtility.checkWinSlot(4, 4, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(11)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(11, team);
                    FIARUtility.setRedToken(-23, 132, 5);

                    if (FIARUtility.checkWinSlot(11, 4, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(11, team);
                    FIARUtility.setYellowToken(-23, 132, 5);

                    if (FIARUtility.checkWinSlot(11, 4, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(18)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(18, team);
                    FIARUtility.setRedToken(-23, 137, 5);

                    if (FIARUtility.checkWinSlot(18, 4, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(18, team);
                    FIARUtility.setYellowToken(-23, 137, 5);

                    if (FIARUtility.checkWinSlot(18, 4, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(25)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(25, team);
                    FIARUtility.setRedToken(-23, 142, 5);

                    if (FIARUtility.checkWinSlot(25, 4, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(25, team);
                    FIARUtility.setYellowToken(-23, 142, 5);

                    if (FIARUtility.checkWinSlot(25, 4, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(32)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(32, team);
                    FIARUtility.setRedToken(-23, 147, 5);

                    if (FIARUtility.checkWinSlot(32, 4, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(32, team);
                    FIARUtility.setYellowToken(-23, 147, 5);

                    if (FIARUtility.checkWinSlot(32, 4, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(39)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(39, team);
                    FIARUtility.setRedToken(-23, 152, 5);

                    if (FIARUtility.checkWinSlot(39, 4, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(39, team);
                    FIARUtility.setYellowToken(-23, 152, 5);

                    if (FIARUtility.checkWinSlot(39, 4, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            }
        } else if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("5")) {
            // 5, 12, 19, 26, 33, 40
            if (FourInARow.currentGame.isEmpty(5)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(5, team);
                    FIARUtility.setRedToken(-23, 127, 0);

                    if (FIARUtility.checkWinSlot(5, 5, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(5, team);
                    FIARUtility.setYellowToken(-23, 127, 0);

                    if (FIARUtility.checkWinSlot(5, 5, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(12)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(12, team);
                    FIARUtility.setRedToken(-23, 132, 0);

                    if (FIARUtility.checkWinSlot(12, 5, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(12, team);
                    FIARUtility.setYellowToken(-23, 132, 0);

                    if (FIARUtility.checkWinSlot(12, 5, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(19)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(19, team);
                    FIARUtility.setRedToken(-23, 137, 0);

                    if (FIARUtility.checkWinSlot(19, 5, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(19, team);
                    FIARUtility.setYellowToken(-23, 137, 0);

                    if (FIARUtility.checkWinSlot(19, 5, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(26)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(26, team);
                    FIARUtility.setRedToken(-23, 142, 0);

                    if (FIARUtility.checkWinSlot(26, 5, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(26, team);
                    FIARUtility.setYellowToken(-23, 142, 0);

                    if (FIARUtility.checkWinSlot(26, 5, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(33)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(33, team);
                    FIARUtility.setRedToken(-23, 147, 0);

                    if (FIARUtility.checkWinSlot(33, 5, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(33, team);
                    FIARUtility.setYellowToken(-23, 147, 0);

                    if (FIARUtility.checkWinSlot(33, 5, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(40)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(40, team);
                    FIARUtility.setRedToken(-23, 152, 0);

                    if (FIARUtility.checkWinSlot(40, 5, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(40, team);
                    FIARUtility.setYellowToken(-23, 152, 0);

                    if (FIARUtility.checkWinSlot(40, 5, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            }
        } else if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("6")) {
            // 6, 13, 20, 27, 34, 41
            if (FourInARow.currentGame.isEmpty(6)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(6, team);
                    FIARUtility.setRedToken(-23, 127, -5);

                    if (FIARUtility.checkWinSlot(6, 6, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(6, team);
                    FIARUtility.setYellowToken(-23, 127, -5);

                    if (FIARUtility.checkWinSlot(6, 6, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(13)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(13, team);
                    FIARUtility.setRedToken(-23, 132, -5);

                    if (FIARUtility.checkWinSlot(13, 6, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(13, team);
                    FIARUtility.setYellowToken(-23, 132, -5);

                    if (FIARUtility.checkWinSlot(13, 6, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(20)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(20, team);
                    FIARUtility.setRedToken(-23, 137, -5);

                    if (FIARUtility.checkWinSlot(20, 6, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(20, team);
                    FIARUtility.setYellowToken(-23, 137, -5);

                    if (FIARUtility.checkWinSlot(20, 6, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(27)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(27, team);
                    FIARUtility.setRedToken(-23, 142, -5);

                    if (FIARUtility.checkWinSlot(27, 6, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(27, team);
                    FIARUtility.setYellowToken(-23, 142, -5);

                    if (FIARUtility.checkWinSlot(27, 6, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(34)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(34, team);
                    FIARUtility.setRedToken(-23, 147, -5);

                    if (FIARUtility.checkWinSlot(34, 6, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(34, team);
                    FIARUtility.setYellowToken(-23, 147, -5);

                    if (FIARUtility.checkWinSlot(34, 6, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(41)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(41, team);
                    FIARUtility.setRedToken(-23, 152, -5);

                    if (FIARUtility.checkWinSlot(41, 6, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(41, team);
                    FIARUtility.setYellowToken(-23, 152, -5);

                    if (FIARUtility.checkWinSlot(41, 6, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            }
        } else if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("7")) {
            // 7, 14, 21, 28, 35, 42
            if (FourInARow.currentGame.isEmpty(7)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(7, team);
                    FIARUtility.setRedToken(-23, 127, -10);

                    if (FIARUtility.checkWinSlot(7, 7, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(7, team);
                    FIARUtility.setYellowToken(-23, 127, -10);

                    if (FIARUtility.checkWinSlot(7, 7, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(14)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(14, team);
                    FIARUtility.setRedToken(-23, 132, -10);

                    if (FIARUtility.checkWinSlot(14, 7, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(14, team);
                    FIARUtility.setYellowToken(-23, 132, -10);

                    if (FIARUtility.checkWinSlot(14, 7, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(21)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(21, team);
                    FIARUtility.setRedToken(-23, 137, -10);

                    if (FIARUtility.checkWinSlot(21, 7, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(21, team);
                    FIARUtility.setYellowToken(-23, 137, -10);

                    if (FIARUtility.checkWinSlot(21, 7, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(28)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(28, team);
                    FIARUtility.setRedToken(-23, 142, -10);

                    if (FIARUtility.checkWinSlot(28, 7, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(28, team);
                    FIARUtility.setYellowToken(-23, 142, -10);

                    if (FIARUtility.checkWinSlot(28, 7, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(35)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(35, team);
                    FIARUtility.setRedToken(-23, 147, -10);

                    if (FIARUtility.checkWinSlot(35, 7, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(35, team);
                    FIARUtility.setYellowToken(-23, 147, -10);

                    if (FIARUtility.checkWinSlot(35, 7, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            } else if (FourInARow.currentGame.isEmpty(42)) {
                if (e.getPlayer() == p1) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.RED;
                    FourInARow.currentGame.setSpace(42, team);
                    FIARUtility.setRedToken(-23, 152, -10);

                    if (FIARUtility.checkWinSlot(42, 7, team)) {
                        FourInARow.currentGame.winGame(p1);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                } else if (e.getPlayer() == p2) {
                    FourInARow.TeamColor team = FourInARow.TeamColor.YELLOW;
                    FourInARow.currentGame.setSpace(42, team);
                    FIARUtility.setYellowToken(-23, 152, -10);

                    if (FIARUtility.checkWinSlot(42, 7, team)) {
                        FourInARow.currentGame.winGame(p2);
                    } else {
                        FourInARow.currentGame.switchTurn();
                    }
                }
            }
        }
    }

    private Boolean checkInventoryClickByLore(String title, String check, List<String> lore, Player p) {
        if (Objects.equal(p.getOpenInventory().getTitle(), title)) {
            if (lore != null)
                return lore.contains(check);
            return false;
        }
        return false;
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e) {
        if (Boardgames.activeGame.equals(Boardgames.Game.FIAR)) {
            if (e.getPlayer().equals(FourInARow.currentGame.getPlayer(1)) || e.getPlayer().equals(FourInARow.currentGame.getPlayer(2))) {
                e.setCancelled(true);
            }
        }
    }
}
