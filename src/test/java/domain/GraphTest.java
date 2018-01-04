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
package domain;

import altitudeMap.AltitudeMap;
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
public class GraphTest {

    private double accuracy;
    private Graph graph;
    

    public GraphTest() {
        this.accuracy = 0.00001;
        
        int ncols = 3;
        int nrows = 3;
        double xllcorner = 0;
        double yllcorner = 0;
        double cellsize = 2.0;
        double NODATA_value = -9999.000;
        double[][] altitudes = new double[4][4];
        altitudes[1][0] = 123.055;
        altitudes[1][1] = 122.765;
        altitudes[1][2] = 122.722;
        altitudes[2][0] = 128.055;
        altitudes[2][1] = 128.888;
        altitudes[2][2] = 128.900;
        altitudes[3][0] = 123.055;
        altitudes[3][1] = 122.200;
        altitudes[3][2] = 120.300;
        AltitudeMap map = new AltitudeMap(ncols, nrows, xllcorner, yllcorner, cellsize, NODATA_value, altitudes);
        MovementModel movementModel = new MovementModel();
        this.graph = new Graph(map, movementModel, false);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getMapSizeCorrect() {
        assertTrue(graph.getMap().getNcols() == 3 && graph.getMap().getNrows() == 3);
    }

    @Test
    public void getMovementModelCorrect() {
        assertTrue(graph.getMovementModel().getBasicSpeed() == 2.0);
    }

    @Test
    public void getVerticesSizeCorrect() {
        assertTrue(graph.getVertices().length == 4);
    }

    @Test
    public void getVerticeAltitudeCorrect() {
        assertTrue(graph.getVertice(3, 3).getZ() - 120.300 < accuracy);
    }

}
