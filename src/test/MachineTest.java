package test;

import model.Machine;
import model.Worker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class MachineTest {

    Machine m;

    @Before
    public void setUp() {
        m = new Machine("Une grue", "stone",2, 1, 0, 1, 0, 1);
    }

    @After
    public void tearDown() {
        m = null;
    }

    @Test
    public void testBuilding() {
        assertNotNull(m);
    }

    @Test
    public void testName() {
        assertEquals("Une grue", m.getName());
    }

    @Test
    public void testEcus() {
        assertEquals(0, m.getEcus());
    }

    @Test
    public void testScore() {
        assertEquals(1, m.getScore());
    }

    @Test
    public void testStone() {
        assertEquals(0, m.getStone());
    }

    @Test
    public void testWood() {
        assertEquals(1, m.getWood());
    }

    @Test
    public void testKnowledge() {
        assertEquals(0, m.getKnowledge());
    }

    @Test
    public void testTile() {
        assertEquals(1, m.getTile());
    }

    @Test
    public void testIsConstructed() {
        assertFalse(m.isConstructed());
        m.assignWorker(new Worker("Travailleur", 0, 5, 4, 4, 4));
        assertTrue(m.isConstructed());
    }

    @Test
    public void testAssigned() {
        assertNotNull(m.getAssigned());
        assertEquals(new ArrayList<>(), m.getAssigned());

        Worker w = new Worker("Travailleur", 0, 5, 4, 4, 4);
        m.assignWorker(w);

        assertEquals(new ArrayList<>(Collections.singletonList(w)), m.getAssigned());
    }

    @Test
    public void testRemainingResources() {
        assertEquals("(X/X|0/1/0/1)", m.remainingResources());
    }

    @Test
    public void testAssignWorker() {
        assertEquals(new ArrayList<>(), m.getAssigned());
        Worker w = new Worker("Travailleur", 0, 5, 4, 4, 4);
        m.assignWorker(w);
        assertEquals(new ArrayList<>(Collections.singletonList(w)), m.getAssigned());
    }

    @Test
    public void testGetMaterial() {
        assertEquals("stone", m.getMaterial());
    }

    @Test
    public void testGetMaterialValue() {
        assertEquals(2, m.getMaterialValue());
    }

    @Test
    public void testToString() {
        assertNotNull(m.toString());
        assertEquals("Une grue (0/1|0/1/0/1)", m.toString());
    }
}