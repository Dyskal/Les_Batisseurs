package model;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * HumanPlayer is a class used to create and handle human players for the game.
 */
public class HumanPlayer extends Player {

    /**
     * Create an HumanPlayer with a given name, workers list, buildings list and board.
     *
     * @param name      the name of the player
     * @param board     the board associated to the player
     */
    public HumanPlayer(String name, Board board) {
        super(name, board);
    }

    /**
     * Play a turn.
     */
    public void play() {
        initPlayer();
        Scanner sc = new Scanner(System.in);
        actionsText();
        do {
            System.out.println("Que voulez vous faire ? (1/2/3/4/5/6)");
            try {
                System.out.print("> ");
                int val = sc.nextInt();
                switch (val) {
                    case 1:
                        System.out.println("Entrez l'indice du bâtiment à prendre :");
                        System.out.print("> ");
                        int i = sc.nextInt();
                        if (!startBuild(i)) {
                            System.out.println("Index invalide, réessayez");
                        }

                        actionsText(1);
                        break;

                    case 2:
                        System.out.println("Entre l'indice de l'ouvrier à prendre :");
                        System.out.print("> ");
                        i = sc.nextInt();
                        if (!takeWorker(i)) {
                            System.out.println("Index invalide, réessayez");
                        }

                        actionsText(1);
                        break;

                    case 3:
                        System.out.println("Choisissez le batiment sur lequel envoyer l'ouvrier puis quel ouvrier envoyer :");
                        System.out.println("Choix dans la liste des bâtiments pas encore commencés (1) ou des bâtiments en construction (2) :");
                        System.out.print("> ");
                        int c = sc.nextInt();
                        int b;
                        int w;
                        if (c == 1) {
                            System.out.println("Entrez l'indice du bâtiment à choisir :");
                            System.out.print("> ");
                            b = sc.nextInt();
                            System.out.println("Entre l'indice de l'ouvrier à choisir :");
                            System.out.print("> ");
                            w = sc.nextInt();
                            Building building = this.getBuilding(b);
                            int ret = assignWorker(this.getWorker(w), this.getBuilding(b));
                            if (ret == -1) {
                                System.out.println("Index invalide, réessayez");
                            } else if (ret == 1) {
                                System.out.println("Vous n'avez pas assez de d'actions ou d'écus");
                            } else {
                                checkFinished(building);
                            }
                        } else if (c == 2) {
                            System.out.println("Entrez l'indice du bâtiment en construction à choisir :");
                            System.out.print("> ");
                            b = sc.nextInt();
                            System.out.println("Entre l'indice de l'ouvrier à choisir :");
                            System.out.print("> ");
                            w = sc.nextInt();
                            Building building = this.getInConstructionBuilding(b);
                            int ret = assignWorker(this.getWorker(w), this.getInConstructionBuilding(b));
                            if (ret == -1) {
                                System.out.println("Index invalide, réessayez");
                            } else if (ret == 1) {
                                System.out.println("Vous n'avez pas assez de d'actions ou d'écus");
                            } else {
                                checkFinished(building);
                            }
                        } else {
                            System.out.println("Index invalide, réessayez");
                        }

                        actionsText(1);
                        break;

                    case 4:
                        System.out.println("Entrez le nombre d'actions à échanger contre des écus (1 a -> 1 e / 2 -> 3 / 3 -> 6) :");
                        System.out.print("> ");
                        i = sc.nextInt();
                        if (!addEcu(i)) {
                            System.out.println("Nombre différent de 1, 2 ou 3 ou nombre d'actions nécessaires invalide");
                        }

                        actionsText(1);
                        break;

                    case 5:
                        System.out.println("Entrez le nombre d'actions à prendre en échange d'écus (5 e -> 1 a) :");
                        System.out.print("> ");
                        i = sc.nextInt();
                        if (!addAction(i)) {
                            System.out.println("Nombre négatif ou nombre d'écus nécessaires invalide");
                        }

                        actionsText(1);
                        break;

                    case 6:
                        System.out.println("Voulez vous vraiment sauvegarder et quitter la partie ? (0 pour sauvegarder et quitter)");
                        System.out.print("> ");
                        i = sc.nextInt();
                        if (i == 0) {
                            this.setActions(Integer.MIN_VALUE);
                        }
                        break;

                    default:
                        System.out.println("Mauvaise valeur, recommencez");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Mauvaise valeur, recommencez");
                sc.next();
            }
        } while (getActions() > 0);
    }
}