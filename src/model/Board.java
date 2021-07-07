package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Board is a class used to create and manage the game's board.
 */
public class Board implements Serializable {
    /**
     * The workers of the board.
     */
    private final ArrayList<Worker> workers;

    /**
     * The buildings of the board.
     */
    private final ArrayList<Building> buildings;

    /**
     * Create a new Board.
     *
     * @param workers the workers
     * @param buildings the buildings
     */
    public Board(ArrayList<Worker> workers, ArrayList<Building> buildings) {
        this.workers = workers;
        this.buildings = buildings;
    }

    /**
     * Get the workers list.
     *
     * @return the workers list
     */
    public ArrayList<Worker> getWorkers() {
        return this.workers;
    }

    /**
     * Get the buildings list.
     *
     * @return the buildings list
     */
    public ArrayList<Building> getBuildings() {
        return this.buildings;
    }

    /**
     * Show the board.
     *
     * @return the representation of the board
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Bâtiments disponibles : (gain écus/points|pierre/bois/savoir/tuiles)").append("\n");
        for (int i = 0; i < this.buildings.size() && i < 5; i++) {
            if (i % 3 == 0 && i != 0) {
                builder.append("\n");
            }
            builder.append(i).append(" - ").append(this.buildings.get(i)).append("\t\t");
        }
        builder.append("\n");

        builder.append("\n").append("Ouvriers disponibles : (coût écus|pierre/bois/savoir/tuiles)").append("\n");

        for (int i = 0; i < this.workers.size() && i < 5; i++) {
            if (i % 3 == 0 && i != 0) {
                builder.append("\n");
            }
            builder.append(i).append(" - ").append(this.workers.get(i)).append("\t\t");
        }
        builder.append("\n");

        builder.append("────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────");
        return builder.toString();
    }
}