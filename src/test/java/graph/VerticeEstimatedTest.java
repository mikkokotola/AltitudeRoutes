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

import graph.VerticeEstimated;
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
public class VerticeEstimatedTest {

    private VerticeEstimated verticeEst;
    private double accuracy;

    public VerticeEstimatedTest() {
        
        this.accuracy = 0.00001;
    }

    @Before
    public void setUp() {
        verticeEst = new VerticeEstimated(3, 5, 120.00);
    }

    @Test
    public void getDistToGoalWorks() {
        assertTrue(verticeEst.getDistToGoal() == 0.0);
    }

    @Test
    public void distToGoalCorrectAfterSetting() {
        verticeEst.setDistToStart(3.5);
        verticeEst.setDistToGoal(18.0);
        assertTrue(verticeEst.getDistToGoal() - 21.5 < accuracy);
    }

    @Test
    public void keyCorrect() {
        assertTrue(verticeEst.getKey() == Double.MAX_VALUE);
    }

    @Test
    public void compareToLargerWorks() {
        VerticeEstimated newVertice = new VerticeEstimated(4, 5, 121.00);
        verticeEst.setDistToStart(3);
        newVertice.setDistToStart(4);
        verticeEst.setDistToGoal(10);
        newVertice.setDistToGoal(10);
        assertTrue(verticeEst.compareTo(newVertice) == -1);
    }

    @Test
    public void compareToSmallerWorks() {
        VerticeEstimated newVertice = new VerticeEstimated(4, 5, 121.00);
        verticeEst.setDistToStart(4);
        newVertice.setDistToStart(4);
        verticeEst.setDistToGoal(11);
        newVertice.setDistToGoal(10);
        assertTrue(verticeEst.compareTo(newVertice) == 1);
    }

    @Test
    public void compareToEqualWorks() {
        VerticeEstimated newVertice = new VerticeEstimated(4, 5, 121.00);
        verticeEst.setDistToStart(3);
        newVertice.setDistToStart(4);
        verticeEst.setDistToGoal(11);
        newVertice.setDistToGoal(10);
        assertTrue(verticeEst.compareTo(newVertice) == 0);
    }

}
