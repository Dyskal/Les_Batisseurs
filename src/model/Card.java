package model;

import java.io.Serializable;

/**
 * Card is a class to create and handle a basic game card.
 */
public abstract class Card implements Serializable {
    /**
     * The name of the card.
     */
    private final String name;

    /**
     * The amount of ecus of the card.
     */
    private final int ecus;

    /**
     * The value of stone of the card.
     */
    private final int stone;

    /**
     * The value of wood of the card.
     */
    private final int wood;

    /**
     * The value of knowledge of the card.
     */
    private final int knowledge;

    /**
     * The value of tile of the card.
     */
    private final int tile;

    /**
     * Create a new Card with the given values.
     *
     * @param name the name of the card
     * @param ecus the value of ecus of the card
     * @param stone the value of stone of the card
     * @param wood the value of wood of the card
     * @param knowledge the value of knowledge of the card
     * @param tile the value of tile of the card
     */
    public Card(String name, int ecus, int stone, int wood, int knowledge, int tile) {
        if (name != null && ecus >= 0 && stone >= 0 && wood >= 0 && knowledge >= 0 && tile >= 0) {
            this.name = name;
            this.ecus = ecus;
            this.stone = stone;
            this.wood = wood;
            this.knowledge = knowledge;
            this.tile = tile;
        } else {
            throw new IllegalArgumentException("Card: null or invalid value.");
        }
    }

    /**
     * Get the name of the card.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the value of ecus of the card.
     *
     * @return the ecus
     */
    public int getEcus() {
        return this.ecus;
    }

    /**
     * Get the value of stone of the card.
     *
     * @return the stone
     */
    public int getStone() {
        return this.stone;
    }

    /**
     * Get the value of wood of the card.
     *
     * @return the wood
     */
    public int getWood() {
        return this.wood;
    }

    /**
     * Get the value of knowledge of the card.
     *
     * @return the knowledge
     */
    public int getKnowledge() {
        return this.knowledge;
    }

    /**
     * Get the value of tile of the card.
     *
     * @return the tile
     */
    public int getTile() {
        return this.tile;
    }

    /**
     * Show the card values.
     *
     * @return the representation of the card
     */
    public abstract String toString();
}