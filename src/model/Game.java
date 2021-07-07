package model;

import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Game is a class used to create and handle the functioning of the game.
 */
public class Game implements Serializable {
    /**
     * The graphic mode : true for console, false for graphic.
     */
    private boolean console;

    /**
     * The list of players.
     */
    private ArrayList<Player> players;

    /**
     * The current player.
     */
    private Player current;

    /**
     * The list of all workers card.
     */
    private ArrayList<Worker> workers;

    /**
     * The list of all buildings card.
     */
    private ArrayList<Building> buildings;

    /**
     * The board of the game.
     */
    private Board board;

    /**
     * Create a Game with the given player names, mode and display mode.
     *
     * @param names the list of the player names
     * @param mode the mode of the game
     * @param console the display mode
     */
    public Game(ArrayList<String> names, Mode mode, boolean console) {
        if (mode == null) {
            throw new IllegalArgumentException("Game: null parameter");
        }

        createWorkers();
        createBuildings();
        if (this.workers.isEmpty() || this.buildings.isEmpty()) {
            throw new IllegalArgumentException("Game: problem on csv file, probably empty");
        }

        this.board = new Board(this.workers, this.buildings);
        createPlayers(names, mode);
        distributeApprentice();

        this.console = console;
        if (console) {
            start();
        }
    }

    /**
     * Restore a previously saved game.
     *
     * @param save the path of the save game
     */
    public Game(String save) {
        readObject(save);
        if (this.console) {
            start();
        }
    }

    /**
     * Create the players.
     *
     * @param names the names of the players
     * @param mode the mode of the game
     */
    public void createPlayers(ArrayList<String> names, Mode mode) {
        if (names == null) {
            names = new ArrayList<>();
        }
        this.players = new ArrayList<>();
        for (int i = 0; i < String.valueOf(mode).length(); i++) {
            if (names.size() >= i + 1 && names.get(i).isEmpty()) {
                names.set(i, "Joueur");
            } else {
                names.add("Joueur");
            }
        }

        switch (mode) {
            case HH:
                this.players.add(new HumanPlayer(names.get(0), this.board));
                this.players.add(new HumanPlayer(names.get(1), this.board));
                break;
            case AA:
                this.players.add(new AutoPlayer(names.get(0), this.board));
                this.players.add(new AutoPlayer(names.get(1), this.board));
                break;
            case HA:
                this.players.add(new HumanPlayer(names.get(0), this.board));
                this.players.add(new AutoPlayer(names.get(1), this.board));
                break;
            case HHH:
                this.players.add(new HumanPlayer(names.get(0), this.board));
                this.players.add(new HumanPlayer(names.get(1), this.board));
                this.players.add(new HumanPlayer(names.get(2), this.board));
                break;
            case AAA:
                this.players.add(new AutoPlayer(names.get(0), this.board));
                this.players.add(new AutoPlayer(names.get(1), this.board));
                this.players.add(new AutoPlayer(names.get(2), this.board));
                break;
            case HHA:
                this.players.add(new HumanPlayer(names.get(0), this.board));
                this.players.add(new HumanPlayer(names.get(1), this.board));
                this.players.add(new AutoPlayer(names.get(2), this.board));
                break;
            case HAA:
                this.players.add(new HumanPlayer(names.get(0), this.board));
                this.players.add(new AutoPlayer(names.get(1), this.board));
                this.players.add(new AutoPlayer(names.get(2), this.board));
                break;
            case HHHH:
                this.players.add(new HumanPlayer(names.get(0), this.board));
                this.players.add(new HumanPlayer(names.get(1), this.board));
                this.players.add(new HumanPlayer(names.get(2), this.board));
                this.players.add(new HumanPlayer(names.get(3), this.board));
                break;
            case AAAA:
                this.players.add(new AutoPlayer(names.get(0), this.board));
                this.players.add(new AutoPlayer(names.get(1), this.board));
                this.players.add(new AutoPlayer(names.get(2), this.board));
                this.players.add(new AutoPlayer(names.get(3), this.board));
                break;
            case HHHA:
                this.players.add(new HumanPlayer(names.get(0), this.board));
                this.players.add(new HumanPlayer(names.get(1), this.board));
                this.players.add(new HumanPlayer(names.get(2), this.board));
                this.players.add(new AutoPlayer(names.get(3), this.board));
                break;
            case HHAA:
                this.players.add(new HumanPlayer(names.get(0), this.board));
                this.players.add(new HumanPlayer(names.get(1), this.board));
                this.players.add(new AutoPlayer(names.get(2), this.board));
                this.players.add(new AutoPlayer(names.get(3), this.board));
                break;
            case HAAA:
                this.players.add(new HumanPlayer(names.get(0), this.board));
                this.players.add(new AutoPlayer(names.get(1), this.board));
                this.players.add(new AutoPlayer(names.get(2), this.board));
                this.players.add(new AutoPlayer(names.get(3), this.board));
                break;
        }

        this.current = this.players.get(new Random().nextInt(this.players.size()));
    }

    /**
     * Create the workers from a data file.
     */
    public void createWorkers() {
        this.workers = new ArrayList<>();
        Scanner sc = new Scanner(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("resources/ouvriers.csv")));
        while (sc.hasNextLine()) {
            Scanner line = new Scanner(sc.nextLine()).useDelimiter(";");
            this.workers.add(new Worker(line.next(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt()));
        }
        Collections.shuffle(this.workers);
    }

    /**
     * Create the buildings from a data file.
     */
    public void createBuildings() {
        this.buildings = new ArrayList<>();
        Scanner sc = new Scanner(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("resources/batiments.csv")));
        while (sc.hasNextLine()) {
            Scanner line = new Scanner(sc.nextLine()).useDelimiter(";");
            this.buildings.add(new Building(line.next(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt()));
        }
        createMachines();
        Collections.shuffle(this.buildings);
    }

    /**
     * Create the machines from a data file.
     */
    public void createMachines() {
        Scanner sc = new Scanner(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("resources/machines.csv")));
        while (sc.hasNextLine()) {
            Scanner line = new Scanner(sc.nextLine()).useDelimiter(";");
            String name = line.next();
            String material = null;
            int materialValue = -1;
            for (int i = 0; i < 4; i++) {
                int temp = line.nextInt();
                if (temp > 0) {
                    switch (i) {
                        case 0:
                            material = "stone";
                            break;
                        case 1:
                            material = "wood";
                            break;
                        case 2:
                            material = "knowledge";
                            break;
                        case 3:
                            material = "tile";
                            break;
                    }
                    materialValue = temp;
                }
            }
            if (material == null) {
                throw new IllegalArgumentException("createMachines: machine & machineValue invalid, csv file is invalid.");
            }
            this.buildings.add(new Machine(name, material, materialValue, line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt()));
        }
    }

    /**
     * Distribute one apprentice to each player.
     */
    public void distributeApprentice() {
        for (Player player : this.players) {
            Worker apprentice = this.workers.stream().filter(w -> w.getName().equals("Apprenti")).findFirst().orElse(null);
            if (apprentice == null || !this.workers.remove(apprentice)) {
                throw new IllegalArgumentException("distributeApprentice: apprentice is null, csv file is invalid.");
            }
            player.getWorkers().add(apprentice);
        }
    }

    /**
     * Start the game if in console mode.
     */
    public void start() {
        while (this.console && this.current.getScore() < 17) {
            for (int i = 0; i < this.players.size(); i++) {
                System.out.println("Au tour de " + this.current.getName());
                this.current.play();
                if (this.current.getActions() == Integer.MIN_VALUE) {
                    writeObject();
                    System.exit(0);
                }
                changeCurrent();

                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
            }
        }
        end();
    }

    /**
     * Display the end of the game.
     */
    public void end() {
        if (this.players.stream().max(Comparator.comparing(Player::calculateScore)).isPresent()) {
            this.current = this.players.stream().max(Comparator.comparing(Player::calculateScore)).get();
            if (this.console) {
                System.out.println(this.current.getName() + " a gagné avec " + this.current.calculateScore() + " points !");
                System.out.println("Voici le score de tout les joueurs :");
                for (Player player : this.players) {
                    System.out.println(player.getName() + " : " + player.calculateScore() + " points.");
                }
            } else {
                JOptionPane.showMessageDialog(null, this.current.getName() + " a gagné avec " + this.current.calculateScore() + " points !", "Les Bâtisseurs", JOptionPane.PLAIN_MESSAGE);
                StringBuilder ret = new StringBuilder();
                ret.append("Voici le score de tout les joueurs :").append("\n");
                for (Player player : this.players) {
                    ret.append(player.getName()).append(" : ").append(player.calculateScore()).append(" points.").append("\n");
                }
                JOptionPane.showMessageDialog(null, ret.toString(), "Les Bâtisseurs", JOptionPane.PLAIN_MESSAGE);
            }
        }
        System.exit(0);
    }

    /**
     * Get the current player.
     *
     * @return the current player
     */
    public Player getCurrent() {
        return this.current;
    }

    /**
     * Change the current player.
     */
    public void changeCurrent() {
        for (int i = 0; i < this.players.size(); i++) {
            if (this.current == this.players.get(i)) {
                if (i + 1 >= this.players.size()) {
                    i = -1;
                }
                this.current = this.players.get(i + 1);
                break;
            }
        }
    }

    /**
     * Save the game.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void writeObject() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
        String date = LocalDateTime.now().format(formatter);

        new File("data/").mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/save_" + date + ".sav"))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load the game.
     *
     * @param filename the file name to load
     */
    public void readObject(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Game game = (Game) ois.readObject();
            this.console = game.console;
            this.players = game.players;
            this.current = game.current;
            this.workers = game.workers;
            this.buildings = game.buildings;
            this.board = game.board;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}