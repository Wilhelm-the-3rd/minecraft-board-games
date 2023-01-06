package me.wilhelm.boardgames.game.uno;

import me.wilhelm.boardgames.other.Utility;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.ItemTagType;

import java.util.ArrayList;
import java.util.Objects;

import static me.wilhelm.boardgames.game.uno.UnoListeners.plugin;

public class UnoCard {
    private final CardColor CARD_COLOR;
    private final CardFace CARD_FACE;

    public UnoCard(CardColor color, CardFace face) {
        this.CARD_COLOR = color;
        this.CARD_FACE = face;
    }

    public UnoCard(Material material) {
        switch (material) {
            case WHITE_DYE:
                this.CARD_COLOR = CardColor.RED;
                this.CARD_FACE = CardFace.ZERO;
                break;
            case MAGENTA_DYE:
                this.CARD_COLOR = CardColor.RED;
                this.CARD_FACE = CardFace.ONE;
                break;
            case SCUTE:
                this.CARD_COLOR = CardColor.RED;
                this.CARD_FACE = CardFace.TWO;
                break;
            case LIGHT_BLUE_DYE:
                this.CARD_COLOR = CardColor.RED;
                this.CARD_FACE = CardFace.THREE;
                break;
            case LIME_DYE:
                this.CARD_COLOR = CardColor.RED;
                this.CARD_FACE = CardFace.FOUR;
                break;
            case YELLOW_DYE:
                this.CARD_COLOR = CardColor.RED;
                this.CARD_FACE = CardFace.FIVE;
                break;
            case PINK_DYE:
                this.CARD_COLOR = CardColor.RED;
                this.CARD_FACE = CardFace.SIX;
                break;
            case GRAY_DYE:
                this.CARD_COLOR = CardColor.RED;
                this.CARD_FACE = CardFace.SEVEN;
                break;
            case LIGHT_GRAY_DYE:
                this.CARD_COLOR = CardColor.RED;
                this.CARD_FACE = CardFace.EIGHT;
                break;
            case ORANGE_DYE:
                this.CARD_COLOR = CardColor.RED;
                this.CARD_FACE = CardFace.NINE;
                break;
            case CYAN_DYE:
                this.CARD_COLOR = CardColor.RED;
                this.CARD_FACE = CardFace.REVERSE;
                break;
            case BLUE_DYE:
                this.CARD_COLOR = CardColor.RED;
                this.CARD_FACE = CardFace.PLUS_TWO;
                break;

            case WHITE_TULIP:
                this.CARD_COLOR = CardColor.YELLOW;
                this.CARD_FACE = CardFace.ZERO;
                break;
            case POPPY:
                this.CARD_COLOR = CardColor.YELLOW;
                this.CARD_FACE = CardFace.ONE;
                break;
            case ALLIUM:
                this.CARD_COLOR = CardColor.YELLOW;
                this.CARD_FACE = CardFace.TWO;
                break;
            case BLUE_ORCHID:
                this.CARD_COLOR = CardColor.YELLOW;
                this.CARD_FACE = CardFace.THREE;
                break;
            case DANDELION:
                this.CARD_COLOR = CardColor.YELLOW;
                this.CARD_FACE = CardFace.FOUR;
                break;
            case RED_TULIP:
                this.CARD_COLOR = CardColor.YELLOW;
                this.CARD_FACE = CardFace.FIVE;
                break;
            case PINK_TULIP:
                this.CARD_COLOR = CardColor.YELLOW;
                this.CARD_FACE = CardFace.SIX;
                break;
            case LILY_OF_THE_VALLEY:
                this.CARD_COLOR = CardColor.YELLOW;
                this.CARD_FACE = CardFace.SEVEN;
                break;
            case AZURE_BLUET:
                this.CARD_COLOR = CardColor.YELLOW;
                this.CARD_FACE = CardFace.EIGHT;
                break;
            case OXEYE_DAISY:
                this.CARD_COLOR = CardColor.YELLOW;
                this.CARD_FACE = CardFace.NINE;
                break;
            case WITHER_ROSE:
                this.CARD_COLOR = CardColor.YELLOW;
                this.CARD_FACE = CardFace.REVERSE;
                break;
            case CORNFLOWER:
                this.CARD_COLOR = CardColor.YELLOW;
                this.CARD_FACE = CardFace.PLUS_TWO;
                break;

            case TUBE_CORAL:
                this.CARD_COLOR = CardColor.BLUE;
                this.CARD_FACE = CardFace.ZERO;
                break;
            case BRAIN_CORAL:
                this.CARD_COLOR = CardColor.BLUE;
                this.CARD_FACE = CardFace.ONE;
                break;
            case BUBBLE_CORAL:
                this.CARD_COLOR = CardColor.BLUE;
                this.CARD_FACE = CardFace.TWO;
                break;
            case FIRE_CORAL:
                this.CARD_COLOR = CardColor.BLUE;
                this.CARD_FACE = CardFace.THREE;
                break;
            case HORN_CORAL:
                this.CARD_COLOR = CardColor.BLUE;
                this.CARD_FACE = CardFace.FOUR;
                break;
            case DEAD_BRAIN_CORAL:
                this.CARD_COLOR = CardColor.BLUE;
                this.CARD_FACE = CardFace.FIVE;
                break;
            case DEAD_BUBBLE_CORAL:
                this.CARD_COLOR = CardColor.BLUE;
                this.CARD_FACE = CardFace.SIX;
                break;
            case DEAD_FIRE_CORAL:
                this.CARD_COLOR = CardColor.BLUE;
                this.CARD_FACE = CardFace.SEVEN;
                break;
            case DEAD_HORN_CORAL:
                this.CARD_COLOR = CardColor.BLUE;
                this.CARD_FACE = CardFace.EIGHT;
                break;
            case HORN_CORAL_FAN:
                this.CARD_COLOR = CardColor.BLUE;
                this.CARD_FACE = CardFace.NINE;
                break;
            case BRAIN_CORAL_FAN:
                this.CARD_COLOR = CardColor.BLUE;
                this.CARD_FACE = CardFace.REVERSE;
                break;
            case TUBE_CORAL_FAN:
                this.CARD_COLOR = CardColor.BLUE;
                this.CARD_FACE = CardFace.PLUS_TWO;
                break;

            case CANDLE:
                this.CARD_COLOR = CardColor.GREEN;
                this.CARD_FACE = CardFace.ZERO;
                break;
            case WHITE_CANDLE:
                this.CARD_COLOR = CardColor.GREEN;
                this.CARD_FACE = CardFace.ONE;
                break;
            case ORANGE_CANDLE:
                this.CARD_COLOR = CardColor.GREEN;
                this.CARD_FACE = CardFace.TWO;
                break;
            case MAGENTA_CANDLE:
                this.CARD_COLOR = CardColor.GREEN;
                this.CARD_FACE = CardFace.THREE;
                break;
            case LIGHT_BLUE_CANDLE:
                this.CARD_COLOR = CardColor.GREEN;
                this.CARD_FACE = CardFace.FOUR;
                break;
            case YELLOW_CANDLE:
                this.CARD_COLOR = CardColor.GREEN;
                this.CARD_FACE = CardFace.FIVE;
                break;
            case LIME_CANDLE:
                this.CARD_COLOR = CardColor.GREEN;
                this.CARD_FACE = CardFace.SIX;
                break;
            case PINK_CANDLE:
                this.CARD_COLOR = CardColor.GREEN;
                this.CARD_FACE = CardFace.SEVEN;
                break;
            case GRAY_CANDLE:
                this.CARD_COLOR = CardColor.GREEN;
                this.CARD_FACE = CardFace.EIGHT;
                break;
            case LIGHT_GRAY_CANDLE:
                this.CARD_COLOR = CardColor.GREEN;
                this.CARD_FACE = CardFace.NINE;
                break;
            case CYAN_CANDLE:
                this.CARD_COLOR = CardColor.GREEN;
                this.CARD_FACE = CardFace.REVERSE;
                break;
            case PURPLE_CANDLE:
                this.CARD_COLOR = CardColor.GREEN;
                this.CARD_FACE = CardFace.PLUS_TWO;
                break;

            case RED_MUSHROOM:
                this.CARD_FACE = CardFace.WILD;
                this.CARD_COLOR = CardColor.RED;
                break;
            default:
                this.CARD_FACE = CardFace.PLUS_FOUR;
                this.CARD_COLOR = CardColor.RED;
                break;

        }
    }

    public CardColor getColor() {
        return CARD_COLOR;
    }
    public CardFace getFace() {
        return CARD_FACE;
    }

    public Material asMaterial() {
        switch (CARD_FACE) {
            case ZERO:
                switch (CARD_COLOR) {
                    case RED:
                        return Material.WHITE_DYE;
                    case BLUE:
                        return Material.TUBE_CORAL;
                    case GREEN:
                        return Material.CANDLE;
                    case YELLOW:
                        return Material.WHITE_TULIP;
                }
            case ONE:
                switch (CARD_COLOR) {
                    case RED:
                        return Material.MAGENTA_DYE;
                    case BLUE:
                        return Material.BRAIN_CORAL;
                    case GREEN:
                        return Material.WHITE_CANDLE;
                    case YELLOW:
                        return Material.POPPY;
                }
            case TWO:
                switch (CARD_COLOR) {
                    case RED:
                       return Material.SCUTE;
                    case BLUE:
                        return Material.BUBBLE_CORAL;
                    case GREEN:
                        return Material.ORANGE_CANDLE;
                    case YELLOW:
                        return Material.ALLIUM;
                }
            case THREE:
                switch (CARD_COLOR) {
                    case RED:
                        return Material.LIGHT_BLUE_DYE;
                    case BLUE:
                        return Material.FIRE_CORAL;
                    case GREEN:
                        return Material.MAGENTA_CANDLE;
                    case YELLOW:
                        return Material.BLUE_ORCHID;
                }
            case FOUR:
                switch (CARD_COLOR) {
                    case RED:
                        return Material.LIME_DYE;
                    case BLUE:
                        return Material.HORN_CORAL;
                    case GREEN:
                        return Material.LIGHT_BLUE_CANDLE;
                    case YELLOW:
                        return Material.DANDELION;
                }
            case FIVE:
                switch (CARD_COLOR) {
                    case RED:
                        return Material.YELLOW_DYE;
                    case BLUE:
                        return Material.DEAD_BRAIN_CORAL;
                    case GREEN:
                        return Material.YELLOW_CANDLE;
                    case YELLOW:
                        return Material.RED_TULIP;
                }
            case SIX:
                switch (CARD_COLOR) {
                    case RED:
                        return Material.PINK_DYE;
                    case BLUE:
                        return Material.DEAD_BUBBLE_CORAL;
                    case GREEN:
                        return Material.LIME_CANDLE;
                    case YELLOW:
                        return Material.PINK_TULIP;
                }
            case SEVEN:
                switch (CARD_COLOR) {
                    case RED:
                        return Material.GRAY_DYE;
                    case BLUE:
                        return Material.DEAD_FIRE_CORAL;
                    case GREEN:
                        return Material.PINK_CANDLE;
                    case YELLOW:
                        return Material.LILY_OF_THE_VALLEY;
                }
            case EIGHT:
                switch (CARD_COLOR) {
                    case RED:
                        return Material.LIGHT_GRAY_DYE;
                    case BLUE:
                        return Material.DEAD_HORN_CORAL;
                    case GREEN:
                        return Material.GRAY_CANDLE;
                    case YELLOW:
                        return Material.AZURE_BLUET;
                }
            case NINE:
                switch (CARD_COLOR) {
                    case RED:
                        return Material.ORANGE_DYE;
                    case BLUE:
                        return Material.HORN_CORAL_FAN;
                    case GREEN:
                        return Material.LIGHT_GRAY_CANDLE;
                    case YELLOW:
                        return Material.OXEYE_DAISY;
                }
            case REVERSE:
                switch (CARD_COLOR) {
                    case RED:
                        return Material.CYAN_DYE;
                    case BLUE:
                        return Material.BRAIN_CORAL_FAN;
                    case GREEN:
                        return Material.CYAN_CANDLE;
                    case YELLOW:
                        return Material.WITHER_ROSE;
                }
            case PLUS_TWO:
                switch (CARD_COLOR) {
                    case RED:
                        return Material.BLUE_DYE;
                    case BLUE:
                        return Material.TUBE_CORAL_FAN;
                    case GREEN:
                        return Material.PURPLE_CANDLE;
                    case YELLOW:
                        return Material.CORNFLOWER;
                }
            case PLUS_FOUR:
                return Material.BROWN_MUSHROOM;
            case WILD:
                return Material.RED_MUSHROOM;
        }
        return Material.AIR;
    }
    public ItemStack asItemStack() {
        String name;

        switch (this.asMaterial()) {
            case WHITE_DYE:
                name = "&cRed 0 &fCard";
                break;
            case MAGENTA_DYE:
                name = "&cRed 1 &fCard";
                break;
            case SCUTE:
                name = "&cRed 2 &fCard";
                break;
            case LIGHT_BLUE_DYE:
                name = "&cRed 3 &fCard";
                break;
            case LIME_DYE:
                name = "&cRed 4 &fCard";
                break;
            case YELLOW_DYE:
                name = "&cRed 5 &fCard";
                break;
            case PINK_DYE:
                name = "&cRed 6 &fCard";
                break;
            case GRAY_DYE:
                name = "&cRed 7 &fCard";
                break;
            case LIGHT_GRAY_DYE:
                name = "&cRed 8 &fCard";
                break;
            case ORANGE_DYE:
                name = "&cRed 9 &fCard";
                break;
            case CYAN_DYE:
                name = "&cRed Reverse &fCard";
                break;
            case BLUE_DYE:
                name = "&cRed +2 &fCard";
                break;

            case WHITE_TULIP:
                name = "&eYellow 0 &fCard";
                break;
            case POPPY:
                name = "&eYellow 1 &fCard";
                break;
            case ALLIUM:
                name = "&eYellow 2 &fCard";
                break;
            case BLUE_ORCHID:
                name = "&eYellow 3 &fCard";
                break;
            case DANDELION:
                name = "&eYellow 4 &fCard";
                break;
            case RED_TULIP:
                name = "&eYellow 5 &fCard";
                break;
            case PINK_TULIP:
                name = "&eYellow 6 &fCard";
                break;
            case LILY_OF_THE_VALLEY:
                name = "&eYellow 7 &fCard";
                break;
            case AZURE_BLUET:
                name = "&eYellow 8 &fCard";
                break;
            case OXEYE_DAISY:
                name = "&eYellow 9 &fCard";
                break;
            case WITHER_ROSE:
                name = "&eYellow Reverse &fCard";
                break;
            case CORNFLOWER:
                name = "&eYellow +2 &fCard";
                break;

            case TUBE_CORAL:
                name = "&9Blue 0 &fCard";
                break;
            case BRAIN_CORAL:
                name = "&9Blue 1 &fCard";
                break;
            case BUBBLE_CORAL:
                name = "&9Blue 2 &fCard";
                break;
            case FIRE_CORAL:
                name = "&9Blue 3 &fCard";
                break;
            case HORN_CORAL:
                name = "&9Blue 4 &fCard";
                break;
            case DEAD_BRAIN_CORAL:
                name = "&9Blue 5 &fCard";
                break;
            case DEAD_BUBBLE_CORAL:
                name = "&9Blue 6 &fCard";
                break;
            case DEAD_FIRE_CORAL:
                name = "&9Blue 7 &fCard";
                break;
            case DEAD_HORN_CORAL:
                name = "&9Blue 8 &fCard";
                break;
            case HORN_CORAL_FAN:
                name = "&9Blue 9 &fCard";
                break;
            case BRAIN_CORAL_FAN:
                name = "&9Blue Reverse &fCard";
                break;
            case TUBE_CORAL_FAN:
                name = "&9Blue +2 &fCard";
                break;

            case CANDLE:
                name = "&aGreen 0 &fCard";
                break;
            case WHITE_CANDLE:
                name = "&aGreen 1 &fCard";
                break;
            case ORANGE_CANDLE:
                name = "&aGreen 2 &fCard";
                break;
            case MAGENTA_CANDLE:
                name = "&aGreen 3 &fCard";
                break;
            case LIGHT_BLUE_CANDLE:
                name = "&aGreen 4 &fCard";
                break;
            case YELLOW_CANDLE:
                name = "&aGreen 5 &fCard";
                break;
            case LIME_CANDLE:
                name = "&aGreen 6 &fCard";
                break;
            case PINK_CANDLE:
                name = "&aGreen 7 &fCard";
                break;
            case GRAY_CANDLE:
                name = "&aGreen 8 &fCard";
                break;
            case LIGHT_GRAY_CANDLE:
                name = "&aGreen 9 &fCard";
                break;
            case CYAN_CANDLE:
                name = "&aGreen Reverse &fCard";
                break;
            case PURPLE_CANDLE:
                name = "&aGreen +2 &fCard";
                break;

            case RED_MUSHROOM:
                name = "&cW&ei&9l&ad &fCard";
                break;
            default:
                name = "&cP&el&9u&as &cF&eo&9u&ar &fCard";
                break;
        }
        ItemStack item = Utility.newItem(name, this.asMaterial(), 1, new ArrayList<>());
        ItemMeta meta = item.getItemMeta();
        meta.getCustomTagContainer().setCustomTag(new NamespacedKey(plugin, "id"), ItemTagType.DOUBLE, Math.random());
        item.setItemMeta(meta);
        return item;
    }

    public boolean isUsable() {
        if (this.CARD_FACE.equals(CardFace.PLUS_FOUR) || this.CARD_FACE.equals(CardFace.WILD))
            return true;
        if (this.CARD_FACE.equals(Uno.currentGame.getCardInPlay().getFace())) {
            return true;
        }
        return this.CARD_COLOR.equals(Uno.currentGame.getCardInPlay().getColor());
    }

    public static UnoCard randomCard() {
        return new UnoCard(CardColor.values()[(int) Math.floor(Math.random()*(3+1)+0)], CardFace.values()[(int) Math.floor(Math.random()*(13+1)+0)]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnoCard card = (UnoCard) o;
        return CARD_COLOR == card.CARD_COLOR && CARD_FACE == card.CARD_FACE;
    }

    @Override
    public int hashCode() {
        return Objects.hash(CARD_COLOR, CARD_FACE);
    }

    public enum CardColor {
        RED(NamedTextColor.RED),
        YELLOW(NamedTextColor.YELLOW),
        GREEN(NamedTextColor.GREEN),
        BLUE(NamedTextColor.BLUE),
        ;

        final NamedTextColor COLOR;

        CardColor(NamedTextColor textColor) {
            COLOR = textColor;
        }

        NamedTextColor asNamedTextColor() {
            return COLOR;
        }
    }

    public enum CardFace {
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        REVERSE,
        PLUS_TWO,
        PLUS_FOUR,
        WILD,
    }
}
