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
public class EdgeTest {
    private double accuracy;
    private Vertice vertice1;
    private Vertice vertice2;
    private Edge edge;

    public EdgeTest() {
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
        vertice1 = new Vertice(100, 10, 15.00);
        vertice2 = new Vertice(101, 10, 15.45);
        edge = new Edge(vertice1, vertice2, 5.0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void fromCorrect() {
        long id1 = vertice1.getId();
        assertTrue(edge.getFrom().getId() == id1);
    }

    @Test
    public void toCorrect() {
        long id2 = vertice2.getId();
        assertTrue(edge.getTo().getId() == id2);
    }

    @Test
    public void idCorrect() {
        long id1 = vertice1.getId();
        long id2 = vertice2.getId();
        long expectedId = id1*9000000 + id2;
        assertTrue(edge.getId() == expectedId);
    }

    @Test
    public void weightCorrect() {
        assertTrue(Math.abs(edge.getWeight() - 5.0) < accuracy);
    }

    @Test
    public void weightCorrectAfterSetting() {
        edge.setWeight(212);
        assertTrue(Math.abs(edge.getWeight() - 212) < accuracy);
    }

}
