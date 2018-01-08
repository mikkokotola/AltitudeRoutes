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
 * @author mkotola
 */
public class MinHeapVerticeTest {
    private Vertice vert;
    private MinHeapVertice heapVert;
    
    public MinHeapVerticeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        vert = new Vertice(3, 5, 120.00);
        vert.setDistToStart(55.0);
        heapVert = new MinHeapVertice(vert.getKey(), vert);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getKeyCorrect() {
        assertTrue(heapVert.getKey() == 55.0);
    }
    
    @Test
    public void setKeyCorrect() {
        heapVert.setKey(44.0);
        assertTrue(heapVert.getKey() == 44.0);
    }

    @Test
    public void getVerticeCorrect() {
        assertTrue(heapVert.getVertice().getId() == vert.getId());
    }
    
    @Test
    public void setVerticeCorrect() {
        vert = new Vertice(4, 6, 100.00);
        heapVert.setVertice(vert);
        assertTrue(heapVert.getVertice().getId() == vert.getId());
    }
}
