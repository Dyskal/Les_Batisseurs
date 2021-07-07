package test;

import model.Building;
import model.Worker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class BuildingTest {

    Building b;

    @Before
    public void setUp() {
        b = new Building("La cathédrale", 20, 8, 5, 4, 4, 4);
    }

    @After
    public void tearDown() {
        b = null;
    }

    @Test
    public void testBuilding() {
        assertNotNull(b);
    }

    @Test
    public void testName() {
        assertEquals("La cathédrale", b.getName());
    }

    @Test
    public void testEcus() {
        assertEquals(20, b.getEcus());
    }

    @Test
    public void testScore() {
        assertEquals(8, b.getScore());
    }

    @Test
    public void testStone() {
        assertEquals(5, b.getStone());
    }

    @Test
    public void testWood() {
        assertEquals(4, b.getWood());
    }

    @Test
    public void testKnowledge() {
        assertEquals(4, b.getKnowledge());
    }

    @Test
    public void testTile() {
        assertEquals(4, b.getTile());
    }

    @Test
    public void testIsConstructed() {
        assertFalse(b.isConstructed());
        b.assignWorker(new Worker("Travailleur", 0, 5, 4, 4, 4));
        assertTrue(b.isConstructed());
    }

    @Test
    public void testAssigned() {
        assertNotNull(b.getAssigned());
        assertEquals(new ArrayList<>(), b.getAssigned());

        Worker w = new Worker("Travailleur", 0, 5, 4, 4, 4);
        b.assignWorker(w);

        assertEquals(new ArrayList<>(Collections.singletonList(w)), b.getAssigned());
    }

    @Test
    public void testRemainingResources() {
        assertEquals("(X/X|5/4/4/4)", b.remainingResources());
    }

    @Test
    public void testAssignWorker() {
        assertEquals(new ArrayList<>(), b.getAssigned());
        Worker w = new Worker("Travailleur", 0, 5, 4, 4, 4);
        b.assignWorker(w);
        assertEquals(new ArrayList<>(Collections.singletonList(w)), b.getAssigned());
    }

    @Test
    public void testToString() {
        assertNotNull(b.toString());
        assertEquals("La cathédrale (20/8|5/4/4/4)", b.toString());
    }
}