/*
 * The MIT License
 *
 * Copyright 2018 .
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
package dataStructures;

import graph.Vertice;
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
public class MinHeapTest {

    private MinHeap heap;
    private Vertice vert;
    private Vertice vert2;
    private Vertice vert3;
    
    private double accuracy;

    public MinHeapTest() {
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
        heap = new MinHeap(3);
        vert = new Vertice(3, 5, 120.00);
        vert.setDistToStart(12.0);
        vert2 = new Vertice(4, 5, 120.00);
        vert2.setDistToStart(14.0);
        vert3 = new Vertice(3, 6, 120.00);
        vert3.setDistToStart(10.0);
        
    }

    @After
    public void tearDown() {
    }

    @Test
    public void heapSizeCorrectWhenEmpty() {
        assertTrue(heap.size() == 0);
    }

    @Test
    public void heapSizeCorrectAfterInsert() {
        heap.insert(vert);
        assertTrue(heap.size() == 1);
    }

    @Test
    public void topVerticeCorrectAfterInsert() {
        heap.insert(vert);
        assertTrue(heap.peek().getId() == vert.getId());
    }

    @Test
    public void topVerticeCorrectAfterMultipleInsert() {
        heap.insert(vert);
        heap.insert(vert2);
        heap.insert(vert3);
        assertTrue(heap.peek().getId() == vert3.getId());
    }

    @Test
    public void topVerticeCorrectAfterPolls() {
        heap.insert(vert);
        heap.insert(vert2);
        heap.insert(vert3);
        heap.poll();
        heap.poll();
        assertTrue(heap.peek().getId() == vert2.getId());
    }

    @Test
    public void heapSizeCorrectAfterPolls() {
        heap.insert(vert);
        heap.insert(vert2);
        heap.insert(vert3);
        heap.poll();
        heap.poll();
        heap.poll();
        assertTrue(heap.size() == 0);
    }

    @Test
    public void topVerticeCorrectAfterDecreaseKey() {
        heap.insert(vert);
        heap.insert(vert2);
        heap.insert(vert3);
        vert2.setDistToStart(8.0);
        heap.decreaseKey(vert2, 8.0);
        assertTrue(heap.peek().getId() == vert2.getId());
    }

    @Test
    public void keyCorrectAfterDecreaseKey() {
        heap.insert(vert);
        heap.insert(vert2);
        heap.insert(vert3);
        vert2.setDistToStart(8.0);
        heap.decreaseKey(vert2, 8.0);
        assertTrue(heap.peek().getKey() - 8.0 < accuracy);
    }

    @Test
    public void topVerticeCorrectAfterIncreaseKey() {
        heap.insert(vert);
        heap.insert(vert2);
        heap.insert(vert3);
        vert3.setDistToStart(20.0);
        heap.increaseKey(vert3, 20.0);
        assertTrue(heap.peek().getId() == vert.getId());
    }

    @Test
    public void keyCorrectAfterIncreaseKey() {
        heap.insert(vert);
        heap.insert(vert2);
        heap.insert(vert3);
        vert3.setDistToStart(20.0);
        heap.increaseKey(vert3, 20.0);
        heap.poll();
        heap.poll();
        assertTrue(heap.peek().getKey() - 20.0 < accuracy);
    }

}
