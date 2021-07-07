package model;

/**
 * Machine is a class to create and handle a building card.
 */
public class Machine extends Building {
    /**
     * The name of the material given after the construction.
     */
    private final String material;

    /**
     * The amount of material given after the construction.
     */
    private final int materialValue;

    /**
     * Create a new Machine with the given values.
     *
     * @param name the name of the card
     * @param material the material this machine will provide after being constructed
     * @param materialValue the value of the material this machine will provide after being constructed
     * @param score the score of the card
     * @param stone the value of stone of the card
     * @param wood the value of wood of the card
     * @param knowledge the value of knowledge of the card
     * @param tile the value of tile of the card
     */
    public Machine(String name, String material, int materialValue, int score, int stone, int wood, int knowledge, int tile) {
        super(name, 0, score, stone, wood, knowledge, tile);
        if (material != null && materialValue > 0) {
            this.material = material;
            this.materialValue = materialValue;
        } else {
            throw new IllegalArgumentException("Machine: null or invalid value.");
        }
    }

    /**
     * Get the material tnhis machine will provide after being constructed.
     *
     * @return the name of the material
     */
    public String getMaterial() {
        return this.material;
    }

    /**
     * Get the value of the material this machine will provide after being constructed.
     *
     * @return the value of the material
     */
    public int getMaterialValue() {
        return this.materialValue;
    }
}