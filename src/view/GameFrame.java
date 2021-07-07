package view;

import controller.GameListener;
import model.*;
import utils.WrapLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GameFrame extends JFrame {
    private final Game game;
    private JLabel playerName;
    private JButton building0;
    private JButton building1;
    private JButton building2;
    private JButton building3;
    private JButton building4;
    private JButton worker0;
    private JButton worker1;
    private JButton worker2;
    private JButton worker3;
    private JButton worker4;
    private JPanel buildingsPlayer;
    private ArrayList<JButton> playerBuildings;
    private JPanel workersPlayer;
    private ArrayList<JButton> playerWorkers;
    private JPanel inConstructionPlayer;
    private ArrayList<JButton> playerInConstruction;
    private JButton ecusButton;
    private JButton scoreButton;
    private JButton actionsButton;

    public GameFrame(ArrayList<String> names, Mode mode) {
        this.game = new Game(names, mode, false);
        new GameFrame(game);
    }
    public GameFrame(String fileName) {
        this.game = new Game(fileName);
        new GameFrame(game);
    }

    private GameFrame(Game newGame) {
        super("Les Bâtisseurs");
        this.game = newGame;
        GameListener gameListener = new GameListener(this);
        this.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/crown.png"))).getImage());
        this.setMinimumSize(new Dimension(950, 700));
        this.setExtendedState(MAXIMIZED_BOTH);


        Box board = Box.createVerticalBox();
        playerName = new JLabel(this.game.getCurrent().getName());

        JPanel buildingsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        buildingsPanel.setOpaque(false);
        building0 = new JButton(this.game.getCurrent().getBoard().getBuildings().get(0).toString());
        building1 = new JButton(this.game.getCurrent().getBoard().getBuildings().get(1).toString());
        building2 = new JButton(this.game.getCurrent().getBoard().getBuildings().get(2).toString());
        building3 = new JButton(this.game.getCurrent().getBoard().getBuildings().get(3).toString());
        building4 = new JButton(this.game.getCurrent().getBoard().getBuildings().get(4).toString());
        building0.addActionListener(gameListener);
        building1.addActionListener(gameListener);
        building2.addActionListener(gameListener);
        building3.addActionListener(gameListener);
        building4.addActionListener(gameListener);

        buildingsPanel.add(building0);
        buildingsPanel.add(building1);
        buildingsPanel.add(building2);
        buildingsPanel.add(building3);
        buildingsPanel.add(building4);

        JPanel workersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        workersPanel.setOpaque(false);
        worker0 = new JButton(this.game.getCurrent().getBoard().getWorkers().get(0).toString());
        worker1 = new JButton(this.game.getCurrent().getBoard().getWorkers().get(1).toString());
        worker2 = new JButton(this.game.getCurrent().getBoard().getWorkers().get(2).toString());
        worker3 = new JButton(this.game.getCurrent().getBoard().getWorkers().get(3).toString());
        worker4 = new JButton(this.game.getCurrent().getBoard().getWorkers().get(4).toString());
        worker0.addActionListener(gameListener);
        worker1.addActionListener(gameListener);
        worker2.addActionListener(gameListener);
        worker3.addActionListener(gameListener);
        worker4.addActionListener(gameListener);

        workersPanel.add(worker0);
        workersPanel.add(worker1);
        workersPanel.add(worker2);
        workersPanel.add(worker3);
        workersPanel.add(worker4);

        board.add(playerName);
        board.add(buildingsPanel);
        board.add(workersPanel);
        board.setBorder(BorderFactory.createMatteBorder(-1, -1, 1, -1, Color.BLACK));

        Box player = Box.createVerticalBox();
        buildingsPlayer = new JPanel(new WrapLayout(FlowLayout.CENTER, 5, 5));
        buildingsPlayer.setOpaque(false);
        playerBuildings = new ArrayList<>();
        for (int i = 0; i < this.game.getCurrent().getBuildings().size(); i++) {
            JButton temp = new JButton(this.game.getCurrent().getBuilding(i).toString());
            temp.addActionListener(gameListener);
            playerBuildings.add(temp);
            buildingsPlayer.add(temp);
        }

        workersPlayer = new JPanel(new WrapLayout(FlowLayout.CENTER, 5, 5));
        workersPlayer.setOpaque(false);
        playerWorkers = new ArrayList<>();
        for (int i = 0; i < this.game.getCurrent().getWorkers().size(); i++) {
            JButton temp = new JButton(this.game.getCurrent().getWorker(i).toString());
            temp.addActionListener(gameListener);
            playerWorkers.add(temp);
            workersPlayer.add(temp);
        }

        inConstructionPlayer = new JPanel(new WrapLayout(FlowLayout.CENTER, 5, 5));
        inConstructionPlayer.setOpaque(false);
        playerInConstruction = new ArrayList<>();
        for (int i = 0; i < this.game.getCurrent().getInConstruction().size(); i++) {
            JButton temp = new JButton(this.game.getCurrent().getInConstructionBuilding(i).toString() + " | " + this.game.getCurrent().getInConstructionBuilding(i).remainingResources());
            temp.addActionListener(gameListener);
            playerInConstruction.add(temp);
            inConstructionPlayer.add(temp);
        }

        player.add(buildingsPlayer);
        player.add(workersPlayer);
        player.add(inConstructionPlayer);

        Box buttons = Box.createHorizontalBox();
        ecusButton = new JButton(String.valueOf(game.getCurrent().getEcus()), new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/ecus.png"))));
        ecusButton.addActionListener(gameListener);
        scoreButton = new JButton(game.getCurrent().getScore() + "/17", new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/crown.png"))));
        scoreButton.addActionListener(gameListener);
        actionsButton = new JButton("Actions restantes : " + game.getCurrent().getActions());
        actionsButton.addActionListener(gameListener);

        buttons.add(ecusButton);
        buttons.add(scoreButton);
        buttons.add(actionsButton);

        Box body = Box.createVerticalBox();
        body.add(board);
        body.add(player);
        body.add(buttons);

        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage bufimage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/wallpaper_game.png")));
                    g.drawImage(bufimage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    System.out.println("paintComponent(): " + e.getMessage());
                }
            }
        });

        this.add(body);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addWindowListener(gameListener);
        this.setVisible(true);

        if (this.game.getCurrent() instanceof AutoPlayer) {
            scoreButton.doClick(10);
        }
    }

    public JLabel getPlayerName() {
        return this.playerName;
    }

    public JButton getBuilding0() {
        return this.building0;
    }

    public JButton getBuilding1() {
        return this.building1;
    }

    public JButton getBuilding2() {
        return this.building2;
    }

    public JButton getBuilding3() {
        return this.building3;
    }

    public JButton getBuilding4() {
        return this.building4;
    }

    public JButton getWorker0() {
        return this.worker0;
    }

    public JButton getWorker1() {
        return this.worker1;
    }

    public JButton getWorker2() {
        return this.worker2;
    }

    public JButton getWorker3() {
        return this.worker3;
    }

    public JButton getWorker4() {
        return this.worker4;
    }

    public ArrayList<JButton> getPlayerBuildings() {
        return this.playerBuildings;
    }

    public ArrayList<JButton> getPlayerWorkers() {
        return this.playerWorkers;
    }

    public ArrayList<JButton> getPlayerInConstruction() {
        return this.playerInConstruction;
    }

    public JButton getEcusButton() {
        return this.ecusButton;
    }

    public JButton getScoreButton() {
        return this.scoreButton;
    }

    public JButton getActionsButton() {
        return this.actionsButton;
    }

    public Game getGame() {
        return this.game;
    }

    public JPanel getBuildingsPlayer() {
        return this.buildingsPlayer;
    }

    public JPanel getWorkersPlayer() {
        return this.workersPlayer;
    }

    public JPanel getInConstructionPlayer() {
        return this.inConstructionPlayer;
    }
}