package test;

import model.Board;
import model.Building;
import model.Worker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class BoardTest {

    Board d;
    Worker w;
    Building b;

    @Before
    public void setUp() {
        w = new Worker("Constructeur", 0, 5, 4, 4, 4);
        b = new Building("Batiment", 20, 8, 5, 4, 4, 4);
        d = new Board(new ArrayList<>(Collections.singletonList(w)), new ArrayList<>(Collections.singletonList(b)));
    }

    @After
    public void tearDown() {
        b = null;
    }

    @Test
    public void testWorkers() {
        assertEquals(new ArrayList<>(Collections.singletonList(w)), d.getWorkers());
    }

    @Test
    public void testBuildings() {
        assertEquals(new ArrayList<>(Collections.singletonList(b)), d.getBuildings());
    }

    @Test
    public void testToString() {
        assertNotNull(b.toString());
        assertEquals("Batiment (20/8|5/4/4/4)", b.toString());
    }
}