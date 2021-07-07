package controller;

import view.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * The listener of the home frame and the rules frame.
 */
public class HomeListener implements ActionListener {
    /**
     * The linked home frame.
     */
    private HomeFrame homeFrame = null;

    /**
     * The linked rules frame.
     */
    private RulesFrame rulesFrame = null;

    /**
     * Create a listener with a home frame.
     *
     * @param homeFrame the linked home frame
     */
    public HomeListener(HomeFrame homeFrame) {
        this.homeFrame = homeFrame;
    }

    /**
     * Create a listener with a rules frame.
     *
     * @param rulesFrame the linked rules frame
     */
    public HomeListener(RulesFrame rulesFrame) {
        this.rulesFrame = rulesFrame;
    }

    /**
     * Invoked when a button is pressed.
     *
     * @param e the ActionEvent
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void actionPerformed(ActionEvent e) {
        if (homeFrame != null) {
            if (e.getSource() == homeFrame.getPlay()) {
                PlayerFrame frame = new PlayerFrame();
                frame.setExtendedState(homeFrame.getExtendedState());
                frame.setSize(homeFrame.getSize());
                frame.setLocationRelativeTo(homeFrame);
                homeFrame.dispose();
            } else if (e.getSource() == homeFrame.getLoad()) {
                UIManager.put("FileChooser.readOnly", Boolean.TRUE);
                new File("data").mkdirs();
                JFileChooser file = new JFileChooser("data/");
                file.setFileFilter(new FileNameExtensionFilter("Fichiers de sauvegarde", "sav"));
                int res = file.showOpenDialog(null);
                if (res == JFileChooser.APPROVE_OPTION) {
                    GameFrame frame = new GameFrame(file.getSelectedFile().getAbsolutePath());
                    frame.setExtendedState(homeFrame.getExtendedState());
                    frame.setSize(homeFrame.getSize());
                    frame.setLocationRelativeTo(homeFrame);
                    homeFrame.dispose();
                }
            } else if (e.getSource() == homeFrame.getRules()) {
                RulesFrame frame = new RulesFrame();
                frame.setExtendedState(homeFrame.getExtendedState());
                frame.setSize(homeFrame.getSize());
                frame.setLocationRelativeTo(homeFrame);
                homeFrame.dispose();
            }
        } else if (rulesFrame != null) {
            if (e.getSource() == rulesFrame.getBack()) {
                HomeFrame frame = new HomeFrame();
                frame.setExtendedState(rulesFrame.getExtendedState());
                frame.setSize(rulesFrame.getSize());
                frame.setLocationRelativeTo(rulesFrame);
                rulesFrame.dispose();
            }
        }
    }
}