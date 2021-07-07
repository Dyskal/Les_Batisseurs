package controller;

import model.Mode;
import view.GameFrame;
import view.HomeFrame;
import view.PlayerFrame;

import java.awt.event.*;
import java.util.ArrayList;

/**
 * The listener of the Player frame.
 */
public class PlayerListener implements ActionListener, ItemListener {
    /**
     * The linked player frame.
     */
    private final PlayerFrame playerFrame;

    /**
     * Create a listener with a player frame.
     *
     * @param playerFrame the linked
     */
    public PlayerListener(PlayerFrame playerFrame) {
        this.playerFrame = playerFrame;
    }

    /**
     * Invoked when a button is pressed.
     *
     * @param e the ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playerFrame.getPlay()) {
            Mode mode = (Mode) playerFrame.getNbPlayers().getSelectedItem();
            ArrayList<String> names = new ArrayList<>();
            names.add(playerFrame.getPlayer1().getText());
            names.add(playerFrame.getPlayer2().getText());
            if (playerFrame.getPlayer3().isVisible()) {
                names.add(playerFrame.getPlayer3().getText());
            }
            if (playerFrame.getPlayer4().isVisible()) {
                names.add(playerFrame.getPlayer4().getText());
            }

            GameFrame frame = new GameFrame(names, mode);
            frame.setExtendedState(playerFrame.getExtendedState());
            frame.setSize(playerFrame.getSize());
            frame.setLocationRelativeTo(playerFrame);
            playerFrame.dispose();
        } else if (e.getSource() == playerFrame.getBack()) {
            HomeFrame frame = new HomeFrame();
            frame.setExtendedState(playerFrame.getExtendedState());
            frame.setSize(playerFrame.getSize());
            frame.setLocationRelativeTo(playerFrame);
            playerFrame.dispose();
        }
    }

    /**
     * Invoked when the combobox value is changed.
     *
     * @param e the ActionEvent
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            Mode mode = (Mode) playerFrame.getNbPlayers().getSelectedItem();
            switch (String.valueOf(mode).length()) {
                case 2:
                    playerFrame.getPlayer3().setVisible(false);
                    playerFrame.getPlayer4().setVisible(false);
                    break;
                case 3:
                    playerFrame.getPlayer3().setVisible(true);
                    playerFrame.getPlayer4().setVisible(false);
                    break;
                case 4:
                    playerFrame.getPlayer3().setVisible(true);
                    playerFrame.getPlayer4().setVisible(true);
                    break;
            }
        }
    }
}