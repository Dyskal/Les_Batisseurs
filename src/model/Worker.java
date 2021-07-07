package model;

/**
 * Worker is a class to create and handle a worker card.
 */
public class Worker extends Card {

    /**
     * Create a new Worker with the given values.
     *
     * @param name the name of the card
     * @param ecus the value of ecus of the card
     * @param stone the value of stone of the card
     * @param wood the value of wood of the card
     * @param knowledge the value of knowledge of the card
     * @param tile the value of tile of the card
     */
    public Worker(String name, int ecus, int stone, int wood, int knowledge, int tile) {
        super(name, ecus, stone, wood, knowledge, tile);
    }

    /**
     * Show the worker values.
     *
     * @return the representation of the card
     */
    public String toString() {
        return String.format("%s (%d|%d/%d/%d/%d)", this.getName(), this.getEcus(), this.getStone(), this.getWood(), this.getKnowledge(), this.getTile());
    }
}