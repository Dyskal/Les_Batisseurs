package test;

import model.Board;
import model.Building;
import model.HumanPlayer;
import model.Worker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class HumanPlayerTest {

    HumanPlayer p;
    Board d;
    Worker w;
    Building b;

    @Before
    public void setUp() {
        w = new Worker("Constructeur", 0, 5, 4, 4, 4);
        b = new Building("Batiment", 20, 8, 5, 4, 4, 4);
        d = new Board(new ArrayList<>(Collections.singletonList(w)), new ArrayList<>(Collections.singletonList(b)));
        p = new HumanPlayer("Joueur 1", d);
    }

    @After
    public void tearDown() {
        p = null;
    }

    @Test
    public void testName() {
        assertEquals("Joueur 1", p.getName());
    }

    @Test
    public void testEcus() {
        assertEquals(10, p.getEcus());
    }

    @Test
    public void testScore() {
        assertEquals(0, p.getScore());
    }

    @Test
    public void testCalculateScore() {
        assertEquals(1, p.calculateScore());
    }

    @Test
    public void testActions() {
        assertEquals(3, p.getActions());
    }

    @Test
    public void testWorker() {
        Worker w = p.getBoard().getWorkers().get(0);
        p.getWorkers().add(p.getBoard().getWorkers().get(0));
        assertEquals(w, p.getWorker(0));
    }

    @Test
    public void testWorkers() {
        assertNotNull(p.getWorkers());
        assertEquals(new ArrayList<>(), p.getWorkers());

        ArrayList<Worker> w = new ArrayList<>(p.getBoard().getWorkers());
        p.getWorkers().add(p.getBoard().getWorkers().get(0));

        assertEquals(w, p.getWorkers());
    }

    @Test
    public void testBuilding() {
        Building b = p.getBoard().getBuildings().get(0);
        p.getBuildings().add(p.getBoard().getBuildings().get(0));
        assertEquals(b, p.getBuilding(0));
    }

    @Test
    public void testBuildings() {
        assertNotNull(p.getBuildings());
        assertEquals(new ArrayList<>(), p.getBuildings());

        ArrayList<Building> b = new ArrayList<>(p.getBoard().getBuildings());
        p.getBuildings().add(p.getBoard().getBuildings().get(0));

        assertEquals(b, p.getBuildings());
    }

    @Test
    public void testInConstructionBuilding() {
        Building b = p.getBoard().getBuildings().get(0);
        p.getInConstruction().add(p.getBoard().getBuildings().get(0));
        assertEquals(b, p.getInConstructionBuilding(0));
    }

    @Test
    public void testInConstruction() {
        assertNotNull(p.getInConstruction());
        assertEquals(new ArrayList<>(), p.getInConstruction());

        ArrayList<Building> b = new ArrayList<>(p.getBoard().getBuildings());
        p.getInConstruction().add(p.getBoard().getBuildings().get(0));

        assertEquals(b, p.getInConstruction());
    }

    @Test
    public void testBoard() {
        assertEquals(d, p.getBoard());
    }

    @Test
    public void testTakeWorker() {
        assertEquals(new ArrayList<>(), p.getWorkers());
        assertTrue(p.takeWorker(0));
        assertEquals(new ArrayList<>(Collections.singletonList(w)), p.getWorkers());
        assertEquals(new ArrayList<>(), p.getBoard().getWorkers());
    }

    @Test
    public void testStartBuild() {
        assertEquals(new ArrayList<>(), p.getBuildings());
        assertTrue(p.startBuild(0));
        assertEquals(new ArrayList<>(Collections.singletonList(b)), p.getBuildings());
        assertEquals(new ArrayList<>(), p.getBoard().getBuildings());
    }

    @Test
    public void testAssignWorker() {
        p.getWorkers().add(w);
        p.getBuildings().add(b);
        assertEquals(new ArrayList<>(Collections.singletonList(w)), p.getWorkers());
        assertEquals(0, p.assignWorker(w, b));
        assertEquals(new ArrayList<>(), p.getWorkers());
    }

    @Test
    public void testAddAction() {
        assertEquals(3, p.getActions());
        assertEquals(10, p.getEcus());
        assertTrue(p.addAction(2));
        assertEquals(5, p.getActions());
        assertEquals(0, p.getEcus());
    }

    @Test
    public void testAddEcu() {
        assertEquals(3, p.getActions());
        assertEquals(10, p.getEcus());
        assertTrue(p.addEcu(2));
        assertEquals(1, p.getActions());
        assertEquals(13, p.getEcus());
    }

    @Test
    public void testToString() {
        assertEquals("Vos bâtiments : (gain écus/points|pierre/bois/savoir/tuiles)\n" +
                "\n" +
                "\n" +
                "Vos ouvriers libres : (coût écus|pierre/bois/savoir/tuiles)\n" +
                "\n" +
                "\n" +
                "Vos bâtiments en cours de construction : (gain écus/points|pierre/bois/savoir/tuiles | ressources restantes)\n" +
                "\n" +
                "\n" +
                "Vos écus, vos actions disponibles et vos points :\n" +
                "Écus : 10\t Actions : 3\t Score : 0\n", p.toString());
    }
}