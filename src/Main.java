import model.*;
import view.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 * Entry point of the game.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length > 0) {
            console();
        } else {
            graphic();
        }
    }

    /**
     * Show the console configuration.
     */
    private static void console() {
        Scanner sc = new Scanner(System.in);
        System.out.println("                                                                                                                      _              \n" +
                "  _                 ____    _   _   _                                     __  __                                     / \\             \n" +
                " | |               |  _ \\  / \\ | | (_)                               _   |  \\/  |                                     _              \n" +
                " | |     ___  ___  | |_) | __ _| |_ _ ___ ___  ___ _   _ _ __ ___   (_)  | \\  / | ___  _   _  ___ _ __    ______     / \\   __ _  ___ \n" +
                " | |    / _ \\/ __| |  _ < / _` | __| / __/ __|/ _ \\ | | | '__/ __|       | |\\/| |/ _ \\| | | |/ _ \\ '_ \\  |______|   / _ \\ / _` |/ _ \\\n" +
                " | |___|  __/\\__ \\ | |_) | (_| | |_| \\__ \\__ \\  __/ |_| | |  \\__ \\   _   | |  | | (_) | |_| |  __/ | | |           / ___ \\ (_| |  __/\n" +
                " |______\\___||___/ |____/ \\__,_|\\__|_|___/___/\\___|\\__,_|_|  |___/  (_)  |_|  |_|\\___/ \\__, |\\___|_| |_|          /_/   \\_\\__, |\\___|\n" +
                "                                                                                        __/ |                              __/ |     \n" +
                "                                                                                       |___/                              |___/       ");

        System.out.println("Bienvenue dans Les Bâtisseurs : Moyen-Âge\n\n1 - Jouer\n2 - Charger une partie\n3 - Voir les règles\n4 - Quitter\n");

        int n = 0;
        do {
            try {
                System.out.print("> ");
                n = sc.nextInt();
                switch (n) {
                    case 1:
                        initGame();
                        break;
                    case 2:
                        loadGame();
                        break;
                    case 3:
                        rules();
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Mauvaise valeur, recommencez");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Mauvaise valeur, recommencez");
                sc.next();
            }
        } while (n != 1 && n != 2 && n != 3 && n != 4);
    }

    /**
     * Initialize the console game (mode &amp; players).
     */
    private static void initGame() {
        music();
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Entrez le mode de jeu (HH, AA, HA, HHH, AAA, HHA, HAA, HHHH, AAAA, HHHA, HHAA, HAAA) :");
        Mode mode = null;
        do {
            try {
                System.out.print("> ");
                mode = Mode.valueOf(sc.next().trim());
            } catch (IllegalArgumentException e) {
                System.out.println("Valeur invalide, réessayez");
            }
        } while (mode == null);
        ArrayList<String> names = new ArrayList<>();
        System.out.println("Entrez le nom des joueurs (par ligne) :");
        while (names.size() < String.valueOf(mode).length()) {
            System.out.print("> ");
            names.add(sc.next());
        }
        new Game(names, mode, true);
    }

    /**
     * Load a game with a saved file (format : *.sav).
     * Call graphic(String... data) with one argument.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static void loadGame() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choisissez la sauvegarde à lancer:");
        new File("data/").mkdirs();
        File[] list = new File("data/").listFiles(pathname -> pathname.getName().endsWith(".sav"));
        if (list == null || list.length == 0) {
            System.out.println("Pas de sauvegarde en format .sav dans le dossier data.");
        } else {
            ArrayList<String> saveList = new ArrayList<>();
            for (File file : list) {
                saveList.add(file.getName());
                System.out.println(file.getName());
            }

            System.out.println("Entrez le nom de la sauvegarde à lancer:");
            System.out.print("> ");
            String ret = sc.nextLine();
            if (saveList.contains(ret)) {
                graphic("data/" + ret);
            }
        }
        console();
    }

    /**
     * Print the game rules.
     */
    private static void rules() {
        Scanner sc = new Scanner(System.in);
        System.out.println("But du jeu :\n" +
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
        sc.nextLine();
        console();
    }

    /**
     * Initialize the graphic game.
     * @param data if present, load a graphic game from the console.
     */
    private static void graphic(String... data) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            music();
            if (data.length > 0) {
                new GameFrame(data[0]);
            } else {
                new HomeFrame();
            }
        });
    }

    /**
     * Configure the game music.
     */
    private static void music() {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(Objects.requireNonNull(Main.class.getClassLoader().getResourceAsStream("resources/batisseurs.wav"))));
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            ((FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN)).setValue(-15);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}