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
package searchAlgo;

import altitudeMap.AltitudeMap;
import graph.Graph;
import graph.Vertice;
import java.util.ArrayList;
import movementModel.MovementModel;
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
public class AstarTest {
    private double accuracy;
    private MovementModel movementModel;
    private Graph graph;
    private Astar astar;

    public AstarTest() {
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
        int ncols = 3;
        int nrows = 3;
        double xllcorner = 0;
        double yllcorner = 0;
        double cellsize = 2.0;
        double NODATA_value = -9999.000;
        double[][] altitudes = new double[4][4];
        altitudes[1][1] = 123.055;
        altitudes[1][2] = 123.060;
        altitudes[1][3] = 123.070;
        altitudes[2][1] = 128.055;
        altitudes[2][2] = 128.888;
        altitudes[2][3] = 128.900;
        altitudes[3][1] = 123.055;
        altitudes[3][2] = 122.200;
        altitudes[3][3] = 120.300;
        AltitudeMap map = new AltitudeMap(ncols, nrows, xllcorner, yllcorner, cellsize, NODATA_value, altitudes);
        movementModel = new MovementModel();
        graph = new Graph(map, movementModel, true);
        astar = new Astar(graph);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void possibleShortestRouteCorrect() {
        astar.runShortestRouteFind(graph.getVertice(1, 1), graph.getVertice(3, 1));
        double res = astar.returnLengthOfShortestRoute();
        assertTrue(res - 3.0 < accuracy);
    }

    @Test
    public void impossibleRouteCorrect() {
        astar.runShortestRouteFind(graph.getVertice(1, 1), graph.getVertice(1, 2));
        double res = astar.returnLengthOfShortestRoute();
        assertTrue(res == movementModel.getImpassableEdgeWeight());
    }

    @Test
    public void shortestRouteLegCorrect() {
        astar.runShortestRouteFind(graph.getVertice(1, 1), graph.getVertice(3, 1));
        ArrayList<Vertice> res = astar.returnShortestPath();
        assertTrue(res.get(1).getZ() - 123.060 < accuracy);
    }
    
}
