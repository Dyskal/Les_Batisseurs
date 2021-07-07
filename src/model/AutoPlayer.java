package model;

import controller.GameListener;
import java.util.Random;

/**
 * AutoPlayer is a class used to create and handle automatic players for the game.
 */
public class AutoPlayer extends Player {

    /**
     * Create an AutoPlayer with a given name, workers list, buildings list and board.
     *
     * @param name      the name of the player
     * @param board     the board associated to the player
     */
    public AutoPlayer(String name, Board board) {
        super(name, board);
    }

    /**
     * Play a turn.
     */
    public void play() {
        initPlayer();
        Random random = new Random();
        do {
            actionsText();
            int val = random.nextInt(6 - 1) + 1;
            switch (val) {
                case 1:
                    startBuild(random.nextInt(5));

                    actionsText(1);
                    break;

                case 2:
                    takeWorker(random.nextInt(5));

                    actionsText(1);
                    break;

                case 3:
                    boolean inConst;
                    if (!this.getWorkers().isEmpty() && !this.getInConstruction().isEmpty()) {
                        inConst = true;
                    } else if (!this.getWorkers().isEmpty() && !this.getBuildings().isEmpty()) {
                        inConst = false;
                    } else {
                        break;
                    }

                    int w = random.nextInt(this.getWorkers().size());
                    Building building;
                    if (inConst) {
                        building = this.getInConstructionBuilding(0);
                        assignWorker(this.getWorker(w), this.getInConstructionBuilding(0));
                    } else {
                        building = this.getBuilding(0);
                        assignWorker(this.getWorker(w), this.getBuilding(0));
                    }
                    checkFinished(building);

                    actionsText(1);
                    break;

                case 4:
                    addEcu(1);

                    actionsText(1);
                    break;

                case 5:
                    addAction(1);

                    actionsText(1);
                    break;
            }
        } while (getActions() > 0);
    }

    /**
     * Play a turn for the graphic version
     * @param listener the listener of the window
     */
    public void graphicalPlay(GameListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("graphicalPlay: null listener");
        }
        initPlayer();
        Random random = new Random();
        do {
            listener.refreshButtons();
            int val = random.nextInt(6 - 1) + 1;
            switch (val) {
                case 1:
                    startBuild(random.nextInt(5));

                    listener.refreshButtons();
                    break;

                case 2:
                    takeWorker(random.nextInt(5));

                    listener.refreshButtons();
                    break;

                case 3:
                    boolean inConst;
                    if (!this.getWorkers().isEmpty() && !this.getInConstruction().isEmpty()) {
                        inConst = true;
                    } else if (!this.getWorkers().isEmpty() && !this.getBuildings().isEmpty()) {
                        inConst = false;
                    } else {
                        break;
                    }

                    int w = random.nextInt(this.getWorkers().size());
                    Building building;
                    if (inConst) {
                        building = this.getInConstructionBuilding(0);
                        assignWorker(this.getWorker(w), this.getInConstructionBuilding(0));
                    } else {
                        building = this.getBuilding(0);
                        assignWorker(this.getWorker(w), this.getBuilding(0));
                    }
                    checkFinished(building);

                    listener.refreshButtons();
                    break;

                case 4:
                    addEcu(1);

                    listener.refreshButtons();
                    break;

                case 5:
                    addAction(1);

                    listener.refreshButtons();
                    break;
            }
        } while (getActions() > 0);
    }
}