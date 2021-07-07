package view;

import controller.HomeListener;
import utils.JColoredButton;

import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class HomeFrame extends JFrame {
    private final JColoredButton play;
    private final JColoredButton load;
    private final JColoredButton rules;

    public HomeFrame() {
        super("Les Bâtisseurs");
        HomeListener homeFrame = new HomeListener(this);
        this.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/crown.png"))).getImage());
        this.setMinimumSize(new Dimension(950, 700));
        this.setExtendedState(MAXIMIZED_BOTH);


        Dimension buttonSize = new Dimension(230, 100);
        Dimension upperSize  = new Dimension(0, 40);
        Dimension spacerSize = new Dimension(0, 50);


        Box buttons = Box.createVerticalBox();
        JLabel logo = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/logo.png"))));
        logo.setAlignmentX(CENTER_ALIGNMENT);

        play = new JColoredButton("Jouer", Color.DARK_GRAY, Color.WHITE);
        play.setFont(play.getFont().deriveFont(Font.PLAIN, 14));
        play.setSize(buttonSize);
        play.setAlignmentX(CENTER_ALIGNMENT);
        play.addActionListener(homeFrame);

        load = new JColoredButton("Charger", Color.DARK_GRAY, Color.WHITE);
        load.setFont(play.getFont().deriveFont(Font.PLAIN, 14));
        load.setSize(buttonSize);
        load.setAlignmentX(CENTER_ALIGNMENT);
        load.addActionListener(homeFrame);

        rules = new JColoredButton("Règles", Color.DARK_GRAY, Color.WHITE);
        rules.setFont(play.getFont().deriveFont(Font.PLAIN, 14));
        rules.setSize(buttonSize);
        rules.setAlignmentX(CENTER_ALIGNMENT);
        rules.addActionListener(homeFrame);

        buttons.add(logo);
        buttons.add(new Box.Filler(spacerSize, spacerSize, spacerSize));
        buttons.add(play);
        buttons.add(new Box.Filler(spacerSize, spacerSize, spacerSize));
        buttons.add(load);
        buttons.add(new Box.Filler(spacerSize, spacerSize, spacerSize));
        buttons.add(rules);

        Box body = Box.createVerticalBox();
        body.add(new Box.Filler(upperSize, upperSize, upperSize));
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

    public JButton getPlay() {
        return this.play;
    }

    public JButton getLoad() {
        return this.load;
    }

    public JButton getRules() {
        return this.rules;
    }
}