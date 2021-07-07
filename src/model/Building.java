package model;

import java.util.ArrayList;

/**
 * Building is a class to create and handle a building card.
 */
public class Building extends Card {
    /**
     * The score of the building
     */
    private final int score;

    /**
     * The workers assigned to the building.
     */
    private final ArrayList<Worker> assigned;

    /**
     * Create a new Building with the given values.
     *
     * @param name the name of the card
     * @param ecus the value of ecus of the card
     * @param score the score of the card
     * @param stone the value of stone of the card
     * @param wood the value of wood of the card
     * @param knowledge the value of knowledge of the card
     * @param tile the value of tile of the card
     */
    public Building(String name, int ecus, int score, int stone, int wood, int knowledge, int tile) {
        super(name, ecus, stone, wood, knowledge, tile);
        if (score >= 0) {
            this.score = score;
        } else {
            throw new IllegalArgumentException("Card: null or invalid value.");
        }
        this.assigned = new ArrayList<>();
    }

    /**
     * Check if this building is constructed by comparing the values of the assigned workers to the values of the building.
     *
     * @return true if the building is constructed
     */
    public boolean isConstructed() {
        boolean assiStone = this.assigned.stream().mapToInt(Worker::getStone).sum() >= this.getStone();
        boolean assiWood = this.assigned.stream().mapToInt(Worker::getWood).sum() >= this.getWood();
        boolean assiKnowledge = this.assigned.stream().mapToInt(Worker::getKnowledge).sum() >= this.getKnowledge();
        boolean assiTile = this.assigned.stream().mapToInt(Worker::getTile).sum() >= this.getTile();

        return assiStone && assiWood && assiKnowledge && assiTile;
    }

    /**
     * Display the remaining resources for the construction.
     *
     * @return a string with the remaining resources
     */
    public String remainingResources() {
        int assiStone = Math.max(0, this.getStone() - this.assigned.stream().mapToInt(Worker::getStone).sum());
        int assiWood = Math.max(0, this.getWood() - this.assigned.stream().mapToInt(Worker::getWood).sum());
        int assiKnowledge = Math.max(0, this.getKnowledge() - this.assigned.stream().mapToInt(Worker::getKnowledge).sum());
        int assiTile = Math.max(0, this.getTile() - this.assigned.stream().mapToInt(Worker::getTile).sum());
        return String.format("(X/X|%s/%s/%s/%s)", assiStone, assiWood, assiKnowledge, assiTile);
    }

    /**
     * Assign a worker to this building.
     *
     * @param worker the worker
     */
    public void assignWorker(Worker worker) {
        this.assigned.add(worker);
    }

    /**
     * Get the score of the building.
     *
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Get the assigned workers list.
     *
     * @return the assigned workers list
     */
    public ArrayList<Worker> getAssigned() {
        return this.assigned;
    }

    /**
     * Show the building values.
     *
     * @return the representation of the card
     */
    public String toString() {
        return String.format("%s (%d/%d|%d/%d/%d/%d)", this.getName(), this.getEcus(), this.getScore(), this.getStone(), this.getWood(), this.getKnowledge(), this.getTile());
    }
}