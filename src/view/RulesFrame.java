package view;

import controller.HomeListener;
import utils.JColoredButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class RulesFrame extends JFrame {
    private final JColoredButton back;

    public RulesFrame() {
        super("Les Bâtisseurs");
        HomeListener homeFrame = new HomeListener(this);
        this.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/crown.png"))).getImage());
        this.setMinimumSize(new Dimension(950, 700));
        this.setExtendedState(MAXIMIZED_BOTH);


        Dimension buttonSize = new Dimension(230, 100);


        Box main = Box.createVerticalBox();
        JLabel logo = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/logo.png"))));
        logo.setAlignmentX(CENTER_ALIGNMENT);

        back = new JColoredButton("Retour", Color.DARK_GRAY, Color.WHITE);
        back.setFont(back.getFont().deriveFont(Font.PLAIN, 14));
        back.setSize(buttonSize);
        back.setAlignmentX(CENTER_ALIGNMENT);
        back.addActionListener(homeFrame);

        JTextArea rules = new JTextArea("But du jeu :\n" +
                "Les joueurs doivent accumuler le plus de points de victoire en construisant des bâtiments.\n" +
                "\n" +
                "Matériel :\n" +
                "\n" +
                "42 cartes Ouvriers :\n" +
                "\n" +
                "Les cartes Ouvriers contiennent :\n" +
                "  - le nombre d’écus que le joueur doit payer pour envoyer travailler l'ouvrier\n" +
                "  - Le nombre de ressources \"pierre\" que produit l'ouvrier\n" +
                "  - Le nombre de ressources \"bois\" que produit l'ouvrier\n" +
                "  - Le nombre de ressources \"savoir\" que produit l'ouvrier\n" +
                "  - Le nombre de ressources \"tuile\" que produit l'ouvrier\n" +
                "\n"
                +
                "40 pièces :\n" +
                "  - 25 pièces d’argent\n" +
                "  - 15 pièces d’or\n" +
                "\n" +
                "Les pièces d’argent valent 1 écu et les pièces d’or, 5 écus. Chaque tranche complète de 10 écus rapporte 1 point de victoire en fin de partie.\n" +
                "\n" +
                "42 cartes Bâtiment (dont 8 machines) :\n" +
                "\n" +
                "Les cartes Bâtiment contiennent :\n" +
                "  - Le nombre d’écus que le joueur gagnera une fois le bâtiment terminé\n" +
                "  - Le nombre de points de victoire que le joueur gagnera une fois le bâtiment terminé\n" +
                "  - Le nombre de ressources \"pierre\" nécessaires à la construction du bâtiment\n" +
                "  - Le nombre de ressources \"bois\" nécessaires à la construction du bâtiment\n" +
                "  - Le nombre de ressources \"savoir\" nécessaires à la construction du bâtiment\n" +
                "  - Le nombre de ressources \"tuile\" nécessaires à la construction du bâtiment\n" +
                "\n" +
                "Mise en place :\n" +
                "  1. Les cartes sont mélangées aléatoirement\n" +
                "  2. On assigne à chaque joueur une carte Ouvrier aléatoire de type \"apprenti\"\n" +
                "  3. 5 cartes de chaque tas sont disposées pour être prises par les joueurs\n" +
                "  4. Chaque joueur reçoit 10 écus, soit 5 pièces d’argent et 1 pièce d’or\n" +
                "  5. Un joueur est désigné aléatoirement comme étant le premier joueur\n" +
                "\n" +
                "Tour de jeu :\n" +
                "   Lors de son tour de jeu, un joueur dispose de 3 actions gratuites auxquelles il peut, s’il le souhaite, ajouter une ou plusieurs actions payantes.\n" +
                "   Une action payante coûte 5 Écus.\n" +
                "\n" +
                "Avec ses actions un joueur peut:\n" +
                "  - Ouvrir un chantier\n" +
                "  - Recruter un ouvrier\n" +
                "  - Envoyer travailler un ouvrier\n" +
                "  - Prendre un ou plusieurs écus\n" +
                "\n" +
                "Ouvrir un chantier :\n" +
                "   Ouvrir un Chantier coûte une action.\n" +
                "   Le joueur sélectionne un bâtiment parmi les cinq disponibles. On dit qu’il \"ouvre\" un chantier. Il est possible d’ouvrir plusieurs chantiers en parallèle.\n" +
                "   Le joueur peut répéter l’opération tant qu’il a des actions pour le faire.\n" +
                "\n" +
                "Recruter un ouvrier :\n" +
                "   Recruter un ouvrier coûte une action.\n" +
                "   Le joueur sélectionne un ouvrier parmi les cinq disponibles. On dit qu’il \"recrute un ouvrier. Le joueur peut répéter l’opération tant qu’il a des actions pour le faire.\n" +
                "\n" +
                "Envoyer travailler un ouvrier :\n" +
                "   Envoyer travailler un Ouvrier a un coût variable.\n" +
                "   Le joueur sélectionne un de ses ouvriers puis un de ses bâtiments en chantier. Le joueur doit alors payer le nombre d’écus indiqué sur la carte de l'ouvrier sélectionné.\n" +
                "   Une fois un ouvrier engagé sur un chantier, il ne peut plus être utilisé tant que le chantier n’est pas terminé.\n" +
                "\n" +
                "Coût :\n" +
                "  - Pendant un tour de jeu, envoyer travailler un ouvrier sur un chantier, quel que soit le nombre d’ouvriers déjà présents sur ce chantier, ne coûte qu’une action.\n" +
                "  - Cependant, envoyer travailler un deuxième ouvrier sur le même chantier au cours du même tour coûte 2 actions !\n" +
                "  - Envoyer travailler un troisième ouvrier, au cours du même tour et toujours sur le même chantier, coûte 3 actions, et ainsi de suite.\n" +
                "  - Par contre, envoyer travailler un ouvrier sur un chantier puis envoyer travailler un ouvrier (un autre ou le même si le bâtiment sur lequel il se trouvait est terminé) sur un autre chantier pendant le même tour ne coûte que 2 actions, soit 1 par ouvrier.\n" +
                "\n" +
                "Prendre un ou plusieurs écus :\n" +
                "   Prendre un ou plusieurs Écus a un coût variable.\n" +
                "\n" +
                "Le joueur peut échanger ses actions contre des écus de la réserve :\n" +
                "  - Pour 1 action, il recevra 1 écu\n" +
                "  - Pour 2 actions, il recevra 3 écus\n" +
                "  - Pour 3 actions, il recevra 6 écus\n" +
                "\n" +
                "Attention : chaque tranche complète de 10 Écus rapporte 1 point de victoire en fin de partie.\n" +
                "\n" +
                "Lorsqu’un joueur a effectué toutes ses actions, c’est au joueur suivant de jouer et ainsi de suite...\n" +
                "\n" +
                "Terminer un Bâtiment :\n" +
                "   Terminer un Bâtiment n’est pas une action.\n" +
                "   Lorsque la somme des différentes ressources de tous les ouvriers posés sur un Chantier atteint ou dépasse les besoins du bâtiment dans chaque ressource, celui-ci est terminé.\n" +
                "   Les Ouvriers qui y travaillaient rejoignent alors l’équipe d’Ouvriers du joueur. Ce dernier reçoit alors le nombre d’écus indiqué sur le bâtiment, de même pour les points de victoire.\n" +
                "\n" +
                "Les machines :\n" +
                "   Les machines sont des bâtiments un peu particulier, puisqu’une fois terminées, elles sont considérées comme des ouvriers non rémunérés pour leur travail (leur coût en écus lorsqu’on les pose sur un chantier est de zéro).\n" +
                "   Une machine se construit exactement comme un bâtiment classique et rapporte des points de victoire lorsqu’elle est terminée. Mais, au lieu de disparaître comme un bâtiment classique, elle vient rejoindre l’équipe d’ouvriers du joueur.\n" +
                "\n" +
                "Fin de partie :\n" +
                "   Lorsqu’un joueur, à la fin de son tour, atteint ou dépasse 17 points de victoire (en comptant les bâtiments et les machines, mais pas les écus), il déclenche la fin de partie.\n" +
                "   Seuls les joueurs qui n’ont pas encore joué lors de ce tour peuvent encore le faire, de façon à ce que tout le monde ait joué autant de tours.\n" +
                "   Ainsi, si c’est le premier joueur qui déclenche la fin de partie, tous les autres joueurs doivent encore jouer une fois.\n" +
                "   À l’opposé, si c’est le dernier joueur qui déclenche la fin de partie, le jeu s’arrête à la fin de son tour.\n" +
                "   Celui qui a le plus de points de victoire devient le Premier Bâtisseur du Royaume et est déclaré vainqueur !\n" +
                "   En cas d’égalité, c’est le joueur avec le plus de points de victoire grâce à ses bâtiments qui est le vainqueur. S’il y a encore égalité, c’est celui qui a le plus de pièces qui l’emporte.\n");
        rules.setFont(back.getFont().deriveFont(Font.PLAIN, 14));
        rules.setRows(20);
        rules.setColumns(75);
        rules.setLineWrap(true);
        rules.setWrapStyleWord(true);
        rules.setForeground(Color.WHITE);
        rules.setBackground(Color.DARK_GRAY);
        rules.setEditable(false);
        rules.setAlignmentX(CENTER_ALIGNMENT);
        JScrollPane sp = new JScrollPane(rules);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        main.add(logo);
        main.add(sp);
        main.add(back);

        Box body = Box.createVerticalBox();
        body.add(main);

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

    public JButton getBack() {
        return this.back;
    }
}