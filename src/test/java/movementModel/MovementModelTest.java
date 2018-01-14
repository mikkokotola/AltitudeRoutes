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
package movementModel;

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
public class MovementModelTest {

    private MovementModel mmodel;
    private double accuracy;

    public MovementModelTest() {
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
        this.mmodel = new MovementModel(0.5, 0.9, 0.9, 0.5, 2.0, 2.0, Double.MAX_VALUE);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getSpeedFactorUphillSteepCorrect() {
        assertTrue(Math.abs(0.5 - this.mmodel.getSpeedFactorUphillSteep()) < accuracy);
    }

    @Test
    public void getSpeedFactorUphillCorrect() {
        assertTrue(Math.abs(0.9 - this.mmodel.getSpeedFactorUphill()) < accuracy);
    }

    @Test
    public void getSpeedFactorDownhillCorrect() {
        assertTrue(Math.abs(0.9 - this.mmodel.getSpeedFactorDownhill()) < accuracy);
    }

    @Test
    public void getSpeedFactorDownhillSteepCorrect() {
        assertTrue(Math.abs(0.5 - this.mmodel.getSpeedFactorDownhillSteep()) < accuracy);
    }
    
    @Test
    public void getBasicSpeedCorrect() {
        assertTrue(Math.abs(2.0 - this.mmodel.getBasicSpeed()) < accuracy);
    }
    
    @Test
    public void getImpassableEdgeWeightCorrect() {
        assertTrue(Math.abs(Double.MAX_VALUE - this.mmodel.getImpassableEdgeWeight()) < accuracy);
    }
    
    @Test
    public void defaultModelConstructorWorks() {
        MovementModel dmodel = new MovementModel(2.0);
        assertTrue(dmodel.getSpeedFactorDownhill() > 0);
    }

    @Test
    public void impassableDownhillCorrect() {
        double res = mmodel.calculateEdgeWeight(-4.0);
        assertTrue(Math.abs(Double.MAX_VALUE - res) < accuracy);
    }

    @Test
    public void steepDownhillCorrect() {
        double res = mmodel.calculateEdgeWeight(-2.0);
        assertTrue(Math.abs(2.0 - res) < accuracy);
    }

    @Test
    public void downhillCorrect() {
        double res = mmodel.calculateEdgeWeight(-0.4);
        assertTrue(Math.abs(1.11111111 - res) < accuracy);
    }

    @Test
    public void flatCorrect() {
        double res = mmodel.calculateEdgeWeight(0.0);
        assertTrue(Math.abs(1.0 - res) < accuracy);
    }

    @Test
    public void uphillCorrect() {
        double res = mmodel.calculateEdgeWeight(0.5);
        assertTrue(Math.abs(1.11111111 - res) < accuracy);
    }

    @Test
    public void steepUphillCorrect() {
        double res = mmodel.calculateEdgeWeight(1.6);
        assertTrue(Math.abs(2.0 - res) < accuracy);
    }

    @Test
    public void impassableUphillCorrect() {
        double res = mmodel.calculateEdgeWeight(8.0);
        assertTrue(Math.abs(Double.MAX_VALUE - res) < accuracy);
    }

}
