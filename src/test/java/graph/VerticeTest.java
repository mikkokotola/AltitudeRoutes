/*
 * The MIT License
 *
 * Copyright 2017 .
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package graph;

import graph.Vertice;
import graph.Edge;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mikko Kotola
 */
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
        assertTrue(Math.abs(vertice.getZ() - 120.00) < accuracy);
    }

    @Test
    public void zCorrectAfterChange() {
        vertice.setZ(140.00);
        assertTrue(Math.abs(vertice.getZ() - 140.00) < accuracy);
    }

    @Test
    public void idCorrect() {
        assertTrue(vertice.getId() == 9005);
    }

    @Test
    public void heapRefCorrectAfterSet() {
        vertice.setHeapRef(16);
        assertTrue(vertice.getHeapRef() == 16);
    }

    @Test
    public void distToStartCorrect() {
        assertTrue(vertice.getDistToStart() == Double.MAX_VALUE);
    }

    @Test
    public void distToStartCorrectAfterChange() {
        vertice.setDistToStart(16.0);
        assertTrue(vertice.getDistToStart() == 16.0);
    }

    @Test
    public void keyCorrect() {
        assertTrue(vertice.getKey() == Double.MAX_VALUE);
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
    public void numberOfEdgesZero() {
        assertTrue(vertice.getNumberOfEdges() == 0);
    }

    @Test
    public void edgeListIsEmpty() {
        assertTrue(vertice.getEdges()[0] == null);
    }

    @Test
    public void edgeListCorrectWhenSet() {
        Edge[] edges = new Edge[1];
        //HashMap<Long, Edge> edges = new HashMap<>();
        Vertice newVertice = new Vertice(4, 5, 121.00);
        Edge newEdge = new Edge(this.vertice, newVertice, 9);
        Long newEdgeId = newEdge.getId();
        edges[0] = newEdge;
        vertice.setEdges(edges);
        vertice.setNumberOfEdges(1);
        assertTrue(vertice.getEdges()[0].getId() == newEdgeId);
    }

    @Test
    public void edgeAddedCorrectly() {
        Vertice newVertice = new Vertice(4, 5, 121.00);
        Edge newEdge = new Edge(this.vertice, newVertice, 9);
        Long newEdgeId = newEdge.getId();
        vertice.addEdge(newEdge);
        assertTrue(vertice.getEdges()[0].getId() == newEdgeId);
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

    @Test
    public void equalsCorrectWhenEqual() {
        Vertice newVertice = new Vertice(3, 5, 130.00);
        assertTrue(vertice.equals(newVertice));
    }
    
    @Test
    public void equalsCorrectWhenNotEqual() {
        Vertice newVertice = new Vertice(4, 5, 121.00);
        assertTrue(!vertice.equals(newVertice));
    }

}
