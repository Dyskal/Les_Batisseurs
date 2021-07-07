package controller;

import model.AutoPlayer;
import model.Board;
import model.Building;
import model.Worker;
import view.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * The listener of the game frame.
 */
public class GameListener extends WindowAdapter implements ActionListener {
    /**
     * The linked game frame.
     */
    private final GameFrame gameFrame;

    /**
     * Create a listener with a game frame.
     *
     * @param gameFrame the linked game frame
     */
    public GameListener(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    /**
     * Invoked when a button is pressed.
     *
     * @param e the ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameFrame.getBuilding0()) {
            if (!gameFrame.getGame().getCurrent().startBuild(0)) {
                JOptionPane.showMessageDialog(gameFrame, "Erreur, index invalide", gameFrame.getTitle(), JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == gameFrame.getBuilding1()) {
            if (!gameFrame.getGame().getCurrent().startBuild(1)) {
                JOptionPane.showMessageDialog(gameFrame, "Erreur, index invalide", gameFrame.getTitle(), JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == gameFrame.getBuilding2()) {
            if (!gameFrame.getGame().getCurrent().startBuild(2)) {
                JOptionPane.showMessageDialog(gameFrame, "Erreur, index invalide", gameFrame.getTitle(), JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == gameFrame.getBuilding3()) {
            if (!gameFrame.getGame().getCurrent().startBuild(3)) {
                JOptionPane.showMessageDialog(gameFrame, "Erreur, index invalide", gameFrame.getTitle(), JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == gameFrame.getBuilding4()) {
            if (!gameFrame.getGame().getCurrent().startBuild(4)) {
                JOptionPane.showMessageDialog(gameFrame, "Erreur, index invalide", gameFrame.getTitle(), JOptionPane.ERROR_MESSAGE);
            }


        } else if (e.getSource() == gameFrame.getWorker0()) {
            if (!gameFrame.getGame().getCurrent().takeWorker(0)) {
                JOptionPane.showMessageDialog(gameFrame, "Erreur, index invalide", gameFrame.getTitle(), JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == gameFrame.getWorker1()) {
            if (!gameFrame.getGame().getCurrent().takeWorker(1)) {
                JOptionPane.showMessageDialog(gameFrame, "Erreur, index invalide", gameFrame.getTitle(), JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == gameFrame.getWorker2()) {
            if (!gameFrame.getGame().getCurrent().takeWorker(2)) {
                JOptionPane.showMessageDialog(gameFrame, "Erreur, index invalide", gameFrame.getTitle(), JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == gameFrame.getWorker3()) {
            if (!gameFrame.getGame().getCurrent().takeWorker(3)) {
                JOptionPane.showMessageDialog(gameFrame, "Erreur, index invalide", gameFrame.getTitle(), JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == gameFrame.getWorker4()) {
            if (!gameFrame.getGame().getCurrent().takeWorker(4)) {
                JOptionPane.showMessageDialog(gameFrame, "Erreur, index invalide", gameFrame.getTitle(), JOptionPane.ERROR_MESSAGE);
            }


        } else if (gameFrame.getPlayerBuildings().contains((JButton) e.getSource())) {
            Building b = gameFrame.getGame().getCurrent().getBuilding(gameFrame.getPlayerBuildings().indexOf((JButton) e.getSource()));
            Worker w = null;
            try {
                w = (Worker) JOptionPane.showInputDialog(gameFrame, "Entrez l'ouvrier à assigner à ce bâtiment :", gameFrame.getTitle() ,JOptionPane.PLAIN_MESSAGE, null, gameFrame.getGame().getCurrent().getWorkers().toArray(new Worker[0]), gameFrame.getGame().getCurrent().getWorkers().toArray(new Worker[0])[0]);
            } catch (ArrayIndexOutOfBoundsException ignored) {}
            if (w != null) {
                int ret = gameFrame.getGame().getCurrent().assignWorker(w, b);
                if (ret == -1) {
                    JOptionPane.showMessageDialog(gameFrame, "Erreur, index invalide", gameFrame.getTitle(), JOptionPane.ERROR_MESSAGE);
                } else if (ret == 1) {
                    JOptionPane.showMessageDialog(gameFrame, "Vous n'avez pas assez de d'actions ou d'écus", gameFrame.getTitle(), JOptionPane.ERROR_MESSAGE);
                }
                gameFrame.getGame().getCurrent().checkFinished(b);
            }

        } else if (gameFrame.getPlayerInConstruction().contains((JButton) e.getSource())) {
            Building b = gameFrame.getGame().getCurrent().getInConstructionBuilding(gameFrame.getPlayerInConstruction().indexOf((JButton) e.getSource()));
            Worker w = null;
            try {
                w = (Worker) JOptionPane.showInputDialog(gameFrame, "Entrez l'ouvrier à assigner à ce bâtiment :", gameFrame.getTitle(), JOptionPane.PLAIN_MESSAGE, null, gameFrame.getGame().getCurrent().getWorkers().toArray(new Worker[0]), gameFrame.getGame().getCurrent().getWorkers().toArray(new Worker[0])[0]);
            } catch (ArrayIndexOutOfBoundsException ignored) {}
            if (w != null) {
                int ret = gameFrame.getGame().getCurrent().assignWorker(w, b);
                if (ret == -1) {
                    JOptionPane.showMessageDialog(gameFrame, "Erreur, index invalide", gameFrame.getTitle(), JOptionPane.ERROR_MESSAGE);
                } else if (ret == 1) {
                    JOptionPane.showMessageDialog(gameFrame, "Vous n'avez pas assez de d'actions ou d'écus", gameFrame.getTitle(), JOptionPane.ERROR_MESSAGE);
                }
                gameFrame.getGame().getCurrent().checkFinished(b);
            }


        } else if (e.getSource() == gameFrame.getEcusButton()) {
            try {
                int ret = Integer.parseInt(String.valueOf(JOptionPane.showInputDialog(gameFrame, "Entrez le nombre d'actions à échanger contre des écus (1 a -> 1 e / 2 -> 3 / 3 -> 6) :", gameFrame.getTitle() ,JOptionPane.QUESTION_MESSAGE, null, new String[]{"1","2","3"}, "1")));
                if (!gameFrame.getGame().getCurrent().addEcu(ret)) {
                    JOptionPane.showMessageDialog(gameFrame, "Nombre différent de 1, 2 ou 3 ou nombre d'actions nécessaires invalide", gameFrame.getTitle(), JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ignored) {}

        }  else if (e.getSource() == gameFrame.getScoreButton()) {
            if (gameFrame.getGame().getCurrent() instanceof AutoPlayer) {
                System.out.println("AutoPlayer turn");
                autoPlay();
            }
        } else if (e.getSource() == gameFrame.getActionsButton()) {
            try {
                int ret = Integer.parseInt(JOptionPane.showInputDialog(gameFrame, "Entrez le nombre d'actions à prendre en échange d'écus (5 e -> 1 a) :", gameFrame.getTitle() ,JOptionPane.QUESTION_MESSAGE));
                if (!gameFrame.getGame().getCurrent().addAction(ret)) {
                    JOptionPane.showMessageDialog(gameFrame, "Nombre négatif ou nombre d'écus nécessaires invalide", gameFrame.getTitle(), JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ignored) {}
        }

        nextTurn();
    }

    /**
     * Setup the next turn (refresh labels, change player, check end of game).
     */
    private void nextTurn() {
        resetInv();
        refreshButtons();

        if (gameFrame.getGame().getCurrent().getScore() >= 17) {
            gameFrame.dispose();
            gameFrame.getGame().end();
        }

        if (gameFrame.getGame().getCurrent().getActions() <= 0) {
            gameFrame.getGame().getCurrent().initPlayer();
            gameFrame.getGame().changeCurrent();
            resetInv();
            refreshButtons();
            if (gameFrame.getGame().getCurrent() instanceof AutoPlayer) {
                gameFrame.getScoreButton().doClick();
            }
        }
    }

    /**
     * Play a turn for an AutoPlayer.
     */
    private void autoPlay() {
        ((AutoPlayer) gameFrame.getGame().getCurrent()).graphicalPlay(this);
        nextTurn();
    }

    /**
     * Reset the player inventory to refresh the labels.
     */
    private void resetInv() {
        for (int i = 0; i < gameFrame.getPlayerBuildings().size(); i++) {
            gameFrame.getPlayerBuildings().get(i).setVisible(false);
        }
        for (int i = 0; i < gameFrame.getPlayerWorkers().size(); i++) {
            gameFrame.getPlayerWorkers().get(i).setVisible(false);
        }
        for (int i = 0; i < gameFrame.getPlayerInConstruction().size(); i++) {
            gameFrame.getPlayerInConstruction().get(i).setVisible(false);
        }
        gameFrame.getPlayerBuildings().clear();
        gameFrame.getPlayerWorkers().clear();
        gameFrame.getPlayerInConstruction().clear();
    }

    /**
     * Refresh the buttons labels
     */
    public void refreshButtons() {
        gameFrame.getPlayerName().setText(gameFrame.getGame().getCurrent().getName());
        Board board = gameFrame.getGame().getCurrent().getBoard();
        checkAvailable(gameFrame.getBuilding0(), board.getBuildings(), 0);
        checkAvailable(gameFrame.getBuilding1(), board.getBuildings(), 1);
        checkAvailable(gameFrame.getBuilding2(), board.getBuildings(), 2);
        checkAvailable(gameFrame.getBuilding3(), board.getBuildings(), 3);
        checkAvailable(gameFrame.getBuilding4(), board.getBuildings(), 4);

        checkAvailable(gameFrame.getWorker0(), board.getWorkers(), 0);
        checkAvailable(gameFrame.getWorker1(), board.getWorkers(), 1);
        checkAvailable(gameFrame.getWorker2(), board.getWorkers(), 2);
        checkAvailable(gameFrame.getWorker3(), board.getWorkers(), 3);
        checkAvailable(gameFrame.getWorker4(), board.getWorkers(), 4);

        for (int i = 0; i < gameFrame.getGame().getCurrent().getBuildings().size(); i++) {
            try {
                gameFrame.getPlayerBuildings().get(i).setText(gameFrame.getGame().getCurrent().getBuilding(i).toString());
                gameFrame.getPlayerBuildings().get(i).setVisible(true);
            } catch (IndexOutOfBoundsException e) {
                JButton temp = new JButton(gameFrame.getGame().getCurrent().getBuilding(i).toString());
                temp.addActionListener(this);
                gameFrame.getPlayerBuildings().add(temp);
                gameFrame.getBuildingsPlayer().add(temp);
            }
        }

        for (int i = 0; i < gameFrame.getGame().getCurrent().getWorkers().size(); i++) {
            try {
                gameFrame.getPlayerWorkers().get(i).setText(gameFrame.getGame().getCurrent().getWorker(i).toString());
                gameFrame.getPlayerWorkers().get(i).setVisible(true);
            } catch (IndexOutOfBoundsException e) {
                JButton temp = new JButton(gameFrame.getGame().getCurrent().getWorker(i).toString());
                temp.addActionListener(this);
                gameFrame.getPlayerWorkers().add(temp);
                gameFrame.getWorkersPlayer().add(temp);
            }
        }

        for (int i = 0; i < gameFrame.getGame().getCurrent().getInConstruction().size(); i++) {
            try {
                gameFrame.getPlayerInConstruction().get(i).setText(gameFrame.getGame().getCurrent().getInConstructionBuilding(i).toString() + " | " + gameFrame.getGame().getCurrent().getInConstructionBuilding(i).remainingResources());
                gameFrame.getPlayerInConstruction().get(i).setVisible(true);
            } catch (IndexOutOfBoundsException e) {
                JButton temp = new JButton(gameFrame.getGame().getCurrent().getInConstructionBuilding(i).toString() + " | " + gameFrame.getGame().getCurrent().getInConstructionBuilding(i).remainingResources());
                temp.addActionListener(this);
                gameFrame.getPlayerInConstruction().add(temp);
                gameFrame.getInConstructionPlayer().add(temp);
            }
        }

        gameFrame.getEcusButton().setText(String.valueOf(gameFrame.getGame().getCurrent().getEcus()));
        gameFrame.getScoreButton().setText(gameFrame.getGame().getCurrent().getScore() + "/17");
        gameFrame.getActionsButton().setText("Actions restantes : " + gameFrame.getGame().getCurrent().getActions());
    }

    /**
     * Check if the JButton of a card of the board is still available.
     * If valid, change the text, if not hide it.
     *
     * @param b the JButton of the frame
     * @param list the worker/building list of the board
     * @param index the index of the card
     */
    private void checkAvailable(JButton b, ArrayList<?> list, int index) {
        try {
            b.setText(list.get(index).toString());
        } catch (IndexOutOfBoundsException e) {
            b.setVisible(false);
        }
    }

    /**
     * Invoked when closing the window.
     *
     * @param e the ActionEvent
     */
    @Override
    public void windowClosing(WindowEvent e) {
        int ret = JOptionPane.showConfirmDialog(gameFrame, "Voulez vous sauvegarder la partie ?", gameFrame.getTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (ret == JOptionPane.YES_OPTION) {
            gameFrame.getGame().writeObject();
        }
    }
}