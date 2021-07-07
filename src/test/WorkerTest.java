package test;

import model.Worker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerTest {

    Worker w;

    @Before
    public void setUp() {
        w = new Worker("Maître", 5, 0, 3, 2, 0);
    }

    @After
    public void tearDown() {
        w = null;
    }

    @Test
    public void testWorker() {
        assertNotNull(w);
    }

    @Test
    public void testName() {
        assertEquals("Maître", w.getName());
    }

    @Test
    public void testEcus() {
        assertEquals(5, w.getEcus());
    }

    @Test
    public void testStone() {
        assertEquals(0, w.getStone());
    }

    @Test
    public void testWood() {
        assertEquals(3, w.getWood());
    }

    @Test
    public void testKnowledge() {
        assertEquals(2, w.getKnowledge());
    }

    @Test
    public void testTile() {
        assertEquals(0, w.getTile());
    }

    @Test
    public void testToString() {
        assertNotNull(w.toString());
        assertEquals("Maître (5|0/3/2/0)", w.toString());
    }
}