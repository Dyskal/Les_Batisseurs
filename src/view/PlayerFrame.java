package view;

import controller.PlayerListener;
import model.Mode;
import utils.JColoredButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.metal.MetalComboBoxUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class PlayerFrame extends JFrame {
    private final JComboBox<Mode> nbPlayers;
    private final JTextField player1;
    private final JTextField player2;
    private final JTextField player3;
    private final JTextField player4;
    private final JColoredButton play;
    private final JColoredButton back;

    public PlayerFrame() {
        super("Les Bâtisseurs");
        PlayerListener playerListener = new PlayerListener(this);
        this.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/crown.png"))).getImage());
        this.setMinimumSize(new Dimension(950, 700));
        this.setExtendedState(MAXIMIZED_BOTH);


        Dimension comboSize  = new Dimension(230, 50);
        Dimension textSize   = new Dimension(230, 50);
        Dimension buttonSize = new Dimension(230, 100);
        Dimension upperSize  = new Dimension(0, 40);
        Dimension spacerSize = new Dimension(0, 50);


        Box combo = Box.createVerticalBox();
        JLabel logo = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/logo.png"))));
        logo.setAlignmentX(CENTER_ALIGNMENT);

        nbPlayers = new JComboBox<>(Mode.values());
        nbPlayers.setUI(new MetalComboBoxUI() {
            @Override
            public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                Color t = g.getColor();
                g.setColor(Color.DARK_GRAY);
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
                g.setColor(t);
            }
        });
        nbPlayers.setFont(nbPlayers.getFont().deriveFont(Font.PLAIN, 16));
        nbPlayers.setForeground(Color.WHITE);
        nbPlayers.setBackground(Color.DARK_GRAY);
        nbPlayers.setFocusable(false);
        nbPlayers.setMinimumSize(comboSize);
        nbPlayers.setPreferredSize(comboSize);
        nbPlayers.setMaximumSize(comboSize);
        nbPlayers.setAlignmentX(CENTER_ALIGNMENT);
        nbPlayers.addItemListener(playerListener);

        combo.add(logo);
        combo.add(new Box.Filler(spacerSize, spacerSize, spacerSize));
        combo.add(nbPlayers);

        Box names = Box.createVerticalBox();
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.setOpaque(false);

        player1 = new JTextField();
        player1.setFont(player1.getFont().deriveFont(Font.PLAIN, 16));
        player1.setForeground(Color.WHITE);
        player1.setBackground(Color.DARK_GRAY);
        player1.setMinimumSize(textSize);
        player1.setPreferredSize(textSize);
        player1.setMaximumSize(textSize);

        player2 = new JTextField();
        player2.setFont(player2.getFont().deriveFont(Font.PLAIN, 16));
        player2.setForeground(Color.WHITE);
        player2.setBackground(Color.DARK_GRAY);
        player2.setMinimumSize(textSize);
        player2.setPreferredSize(textSize);
        player2.setMaximumSize(textSize);

        player3 = new JTextField();
        player3.setFont(player3.getFont().deriveFont(Font.PLAIN, 16));
        player3.setForeground(Color.WHITE);
        player3.setBackground(Color.DARK_GRAY);
        player3.setMinimumSize(textSize);
        player3.setPreferredSize(textSize);
        player3.setMaximumSize(textSize);
        player3.setVisible(false);

        player4 = new JTextField();
        player4.setFont(player4.getFont().deriveFont(Font.PLAIN, 16));
        player4.setForeground(Color.WHITE);
        player4.setBackground(Color.DARK_GRAY);
        player4.setMinimumSize(textSize);
        player4.setPreferredSize(textSize);
        player4.setMaximumSize(textSize);
        player4.setVisible(false);

        panel.add(player1);
        panel.add(player2);
        panel.add(player3);
        panel.add(player4);
        names.add(panel);

        Box buttons = Box.createHorizontalBox();
        play = new JColoredButton("Jouer", Color.DARK_GRAY, Color.WHITE);
        play.setFont(play.getFont().deriveFont(Font.PLAIN, 14));
        play.setSize(buttonSize);
        play.setAlignmentX(CENTER_ALIGNMENT);
        play.addActionListener(playerListener);

        back = new JColoredButton("Retour", Color.DARK_GRAY, Color.WHITE);
        back.setFont(back.getFont().deriveFont(Font.PLAIN, 14));
        back.setSize(buttonSize);
        back.setAlignmentX(CENTER_ALIGNMENT);
        back.addActionListener(playerListener);

        buttons.add(play);
        buttons.add(new JLabel("            "));
        buttons.add(back);

        Box body = Box.createVerticalBox();
        body.add(new Box.Filler(upperSize, upperSize, upperSize));
        body.add(combo);
        body.add(new Box.Filler(spacerSize, spacerSize, spacerSize));
        body.add(names);
        body.add(new Box.Filler(spacerSize, spacerSize, spacerSize));
        body.add(buttons);
        body.add(new Box.Filler(spacerSize, spacerSize, spacerSize));

        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    BufferedImage bufimage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/wallpaper_home.jpg")));
                    g.drawImage(bufimage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    System.out.println("paintComponent(): " + e.getMessage());
                }
            }
        });

        this.add(body);
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public JComboBox<Mode> getNbPlayers() {
        return this.nbPlayers;
    }

    public JTextField getPlayer1() {
        return this.player1;
    }

    public JTextField getPlayer2() {
        return this.player2;
    }

    public JTextField getPlayer3() {
        return this.player3;
    }

    public JTextField getPlayer4() {
        return this.player4;
    }

    public JButton getPlay() {
        return this.play;
    }

    public JButton getBack() {
        return this.back;
    }
}