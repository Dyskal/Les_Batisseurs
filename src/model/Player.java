package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Player is a class used to create and handle players for the game.
 */
public abstract class Player implements Serializable {
    /**
     * The name of the player.
     */
    private final String name;

    /**
     * The amount of ecus of the player
     */
    private int ecus;

    /**
     * The score of the player.
     */
    private int score;

    /**
     * The number of actions of the player.
     */
    private int actions;

    /**
     * The workers of the player.
     */
    private final ArrayList<Worker> workers;

    /**
     * The buildings of the player.
     */
    private final ArrayList<Building> buildings;

    /**
     * The number of assigned worker to a building in this turn.
     */
    private final HashMap<Building, Integer> assigned;

    /**
     * The buildings of the players that are in construction.
     */
    private final ArrayList<Building> inConstruction;

    /**
     * The board linked to the player.
     */
    private final Board board;

    /**
     * Create a Player with a given name, workers list, buildings list and board.
     *
     * @param name the name of the player
     * @param board the board associed to the player
     */
    public Player(String name, Board board) {
        this.name = name;
        this.ecus = 10;
        this.score = 0;
        this.actions = 3;
        this.workers = new ArrayList<>();
        this.buildings = new ArrayList<>();
        this.assigned = new HashMap<>();
        this.inConstruction = new ArrayList<>();
        this.board = board;
    }

    /**
     * Initialize the actions and reset the assigned list each new turn.
     */
    public void initPlayer() {
        this.actions = 3;
        this.assigned.clear();
    }

    /**
     * Play a turn.
     */
    public abstract void play();

    /**
     * Take a building on the board.
     *
     * @param index the index
     * @return true if the action is successful
     */
    public boolean startBuild(int index) {
        if (this.actions > 0 && index >= 0 && index < 5) {
            try {
                this.buildings.add(this.board.getBuildings().remove(index));
                this.actions--;
                return true;
            } catch (IndexOutOfBoundsException ignored) {}
            //Moins de 5 cartes sur le plateau
        }
        return false;
    }

    /**
     * Take a worker on the board.
     *
     * @param index the index
     * @return true if the action is successful
     */
    public boolean takeWorker(int index) {
        if (this.actions > 0 && index >= 0 && index < 5) {
            try {
                this.workers.add(this.board.getWorkers().remove(index));
                this.actions--;
                return true;
            } catch (IndexOutOfBoundsException ignored) {}
            //Moins de 5 cartes sur le plateau
        }
        return false;
    }

    /**
     * Assign a worker to a building.
     *
     * @param worker the worker
     * @param building the building
     * @return -1 if there is an index error, 1 if there is a problem with the actions or the ecus, 0 if the action is successful
     */
    public int assignWorker(Worker worker, Building building) {
        int ret = -1;
        if (worker != null && building != null) {
            if (this.workers.contains(worker) && (this.buildings.contains(building) || this.inConstruction.contains(building))) {
                int actionsNeeded = this.assigned.computeIfAbsent(building, v -> 1);
                if (this.actions >= actionsNeeded && this.ecus >= worker.getEcus()) {
                    building.assignWorker(worker);
                    this.ecus -= worker.getEcus();
                    this.actions -= actionsNeeded;

                    this.workers.remove(worker);
                    this.inConstruction.remove(building);
                    this.buildings.remove(building);

                    this.inConstruction.add(building);

                    this.assigned.put(building, actionsNeeded + 1);
                    ret = 0;
                } else {
                    ret = 1;
                }
            }
        }
        return ret;
    }

    /**
     * Check if a building is finished.
     *
     * @param building the building
     */
    public void checkFinished(Building building) {
        if (building != null && this.inConstruction.contains(building)) {
            boolean isFinished = building.isConstructed();

            if (isFinished) {
                this.ecus += building.getEcus();
                this.score += building.getScore();
                this.inConstruction.remove(building);
                this.workers.addAll(building.getAssigned());
            }

            if (isFinished && building instanceof Machine) {
                Machine machine = (Machine) building;

                int stone = 0;
                int wood = 0;
                int knowledge = 0;
                int tile = 0;
                switch (machine.getMaterial()) {
                    case "stone":
                        stone = machine.getMaterialValue();
                        break;
                    case "wood":
                        wood = machine.getMaterialValue();
                        break;
                    case "knowledge":
                        knowledge = machine.getMaterialValue();
                        break;
                    case "tile":
                        tile = machine.getMaterialValue();
                        break;
                    default:
                        throw new IllegalArgumentException(machine.getMaterial() + "is not valid.");
                }

                Worker worker = new Worker(machine.getName(), 0, stone, wood, knowledge, tile);
                this.workers.add(worker);
            }
        } else {
            System.out.println("checkFinished: null parameter or building not in construction list");
        }
    }

    /**
     * Add up to 6 ecus by exchanging actions.
     *
     * @param number the number of actions to exchange
     * @return true if the action is successful
     */
    public boolean addEcu(int number) {
        int nbEcus;
        switch (number) {
            case 1:
                nbEcus = 1;
                break;

            case 2:
                nbEcus = 3;
                break;

            case 3:
                nbEcus = 6;
                break;

            default:
                nbEcus = -1;
                break;
        }

        if (nbEcus >= 1 && this.actions >= number) {
            this.ecus += nbEcus;
            this.actions -= number;
            return true;
        }
        return false;
    }

    /**
     * Add one or many action by exchanging ecus.
     *
     * @param number the number of actions to add
     * @return true if the action is successful
     */
    public boolean addAction(int number) {
        if (number > 0 && this.ecus >= 5 * number) {
            this.ecus -= 5 * number;
            this.actions += number;
            return true;
        }
        return false;
    }

    /**
     * Get the name of the player.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the ecus of the player.
     *
     * @return the ecus
     */
    public int getEcus() {
        return this.ecus;
    }

    /**
     * Get the score of the player.
     *
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Calculate the final score of the player.
     *
     * @return the score
     */
    public int calculateScore() {
        return this.score + (this.ecus/10);
    }

    /**
     * Get the number of actions of the player.
     *
     * @return the number of actions
     */
    public int getActions() {
        return this.actions;
    }

    /**
     * Set the number of actions of the player to an invalid value for save purposes.
     *
     * @param actions the number of actions
     */
    public void setActions(int actions) {
        this.actions = actions == Integer.MIN_VALUE ? actions : this.actions;
    }

    /**
     * Get a worker of the list.
     *
     * @param index the index
     * @return a worker
     */
    public Worker getWorker(int index) {
        return index >= 0 && index < this.workers.size() ? this.workers.get(index) : null;
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
     * Get a building of the list.
     *
     * @param index the index
     * @return a building
     */
    public Building getBuilding(int index) {
        return ((index >= 0) && (index < this.buildings.size())) ? this.buildings.get(index) : null;
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
     * Get a building of the construction list.
     *
     * @param index the index
     * @return a building
     */
    public Building getInConstructionBuilding(int index) {
        return ((index >= 0) && (index < this.inConstruction.size())) ? this.inConstruction.get(index) : null;
    }

    /**
     * Get the buildings in construction list.
     *
     * @return the buildings in construction list
     */
    public ArrayList<Building> getInConstruction() {
        return this.inConstruction;
    }

    /**
     * Get the board of the player.
     *
     * @return the board
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Show the player inventory.
     *
     * @return the representation of the player
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Vos bâtiments : (gain écus/points|pierre/bois/savoir/tuiles)").append("\n");
        for (int i = 0; i < this.buildings.size(); i++) {
            if (i % 3 == 0 && i != 0) {
                builder.append("\n");
            }

            builder.append(i).append(" - ").append(this.buildings.get(i)).append("\t\t");
        }
        builder.append("\n");

        builder.append("\n").append("Vos ouvriers libres : (coût écus|pierre/bois/savoir/tuiles)").append("\n");
        for (int i = 0; i < this.workers.size(); i++) {
            if (i % 3 == 0 && i != 0) {
                builder.append("\n");
            }

            builder.append(i).append(" - ").append(this.workers.get(i)).append("\t\t");
        }
        builder.append("\n");

        builder.append("\n").append("Vos bâtiments en cours de construction : (gain écus/points|pierre/bois/savoir/tuiles | ressources restantes)").append("\n");
        for (int i = 0; i < this.inConstruction.size(); i++) {
            if (i % 3 == 0 && i != 0) {
                builder.append("\n");
            }

            builder.append(i).append(" - ").append(this.inConstruction.get(i)).append(" | ").append(this.inConstruction.get(i).remainingResources()).append("\t\t");
        }
        builder.append("\n");

        builder.append("\n").append("Vos écus, vos actions disponibles et vos points :").append("\n");
        builder.append("Écus : ").append(this.ecus).append("\t Actions : ").append(this.actions).append("\t Score : ").append(this.score).append("\n");
        return builder.toString();
    }

    /**
     * Print the text representation of the player available actions.
     *
     * @param a if present, jump 6 lines (for esthetic purposes)
     */
    public void actionsText(int... a) {
        if (a.length > 0) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }

        System.out.println(this.getBoard());
        System.out.println(this);
        System.out.println("Actions disponibles :");
        System.out.println("1 - Ouvrir un chantier                2 - Recruter un ouvrier                  3 - Envoyer travailler un ouvrier");
        System.out.println("4 - Prendre des écus                  5 - Prendre des actions                  6 - Sauvegarder et quitter");
        System.out.println();
    }
}