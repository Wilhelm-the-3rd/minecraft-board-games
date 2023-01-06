package me.wilhelm.boardgames.game.uno;

import fr.mrmicky.fastboard.FastBoard;
import me.wilhelm.boardgames.other.Utility;
import org.bukkit.entity.Player;

public class UnoScoreboard {
    public static void updateScores(Player player) {
        FastBoard board = new FastBoard(player);

        board.updateTitle(Utility.text("&c&lU&9&lN&a&lO"));

        int pCount = Uno.currentGame.getPLAYERS().size();

        board.updateLine(0, "");

        for (int i = 1; pCount>0; i+=3) {

            board.updateLine(i, Utility.text("&e&n" + Uno.currentGame.getPLAYERS().get(pCount-1).getName()));
            board.updateLine(i+1, Utility.text("  &fCards: &6" + Uno.currentGame.getHand(Uno.currentGame.getPLAYERS().get(pCount-1)).size()));
            board.updateLine(i+2, "");

            pCount--;
        }
    }
}
