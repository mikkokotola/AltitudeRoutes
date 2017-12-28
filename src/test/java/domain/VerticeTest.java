package domain;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VerticeTest {

    private Vertice vertice;
    private double accuracy;

    public VerticeTest() {
        this.accuracy = 0.00001;
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.vertice = new Vertice(3, 5, 120.00);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void xCorrect() {
        assertTrue(vertice.getX() == 3);
    }

    @Test
    public void yCorrect() {
        assertTrue(vertice.getY() == 5);
    }

    @Test
    public void zCorrect() {
        assertTrue((vertice.getZ() - 120.00) < accuracy);
    }

    @Test
    public void zCorrectAfterChange() {
        vertice.setZ(140.00);
        assertTrue((vertice.getZ() - 140.00) < accuracy);
    }

    @Test
    public void idCorrect() {
        assertTrue(vertice.getId() == 9005);
    }

    @Test
    public void distToStartCorrect() {
        assertTrue(vertice.getDistToStart() == Long.MAX_VALUE);
    }

    @Test
    public void distToStartCorrectAfterChange() {
        vertice.setDistToStart(16);
        assertTrue(vertice.getDistToStart() == 16);
    }

    @Test
    public void pathNullWhenNotSet() {
        assertTrue(vertice.getPath() == null);
    }

    @Test
    public void pathCorrectWhenSet() {
        Vertice newPath = new Vertice(1000, 500, 99.00);
        long newPathId = newPath.getId();
        vertice.setPath(newPath);
        assertTrue(vertice.getPath().getId() == newPathId);
    }

    @Test
    public void edgeListIsEmpty() {
        assertTrue(vertice.getEdges().isEmpty());
    }

    @Test
    public void edgeListCorrectWhenSet() {
        HashMap<Long, Edge> edges = new HashMap<>();
        Vertice newVertice = new Vertice(4, 5, 121.00);
        Edge newEdge = new Edge(this.vertice, newVertice, 9);
        Long newEdgeId = newEdge.getId();
        edges.put(newEdgeId, newEdge);
        vertice.setEdges(edges);
        assertTrue(vertice.getEdges().containsKey(newEdgeId));
    }

    @Test
    public void edgeAddedCorrectly() {
        Vertice newVertice = new Vertice(4, 5, 121.00);
        Edge newEdge = new Edge(this.vertice, newVertice, 9);
        Long newEdgeId = newEdge.getId();
        vertice.addEdge(newEdge);
        assertTrue(vertice.getEdges().containsKey(newEdgeId));
    }

    @Test
    public void edgeRemovedCorrectly() {
        Vertice newVertice = new Vertice(4, 5, 121.00);
        Edge newEdge = new Edge(this.vertice, newVertice, 9);
        Long newEdgeId = newEdge.getId();
        vertice.addEdge(newEdge);
        vertice.removeEdge(newEdgeId);
        assertTrue(vertice.getEdges().isEmpty());
    }

    @Test
    public void compareToLargerWorks() {
        Vertice newVertice = new Vertice(4, 5, 121.00);
        vertice.setDistToStart(3);
        newVertice.setDistToStart(4);
        assertTrue(vertice.compareTo(newVertice) == -1);
    }

    @Test
    public void compareToSmallerWorks() {
        Vertice newVertice = new Vertice(4, 5, 121.00);
        vertice.setDistToStart(3);
        newVertice.setDistToStart(2);
        assertTrue(vertice.compareTo(newVertice) == 1);
    }

    @Test
    public void compareToEqualWorks() {
        Vertice newVertice = new Vertice(4, 5, 121.00);
        vertice.setDistToStart(3);
        newVertice.setDistToStart(3);
        assertTrue(vertice.compareTo(newVertice) == 0);
    }

}
