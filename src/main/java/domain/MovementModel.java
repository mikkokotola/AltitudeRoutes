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
package domain;

/**
 * Movementmodel contains the modelling information for transforming the
 * AltitudeMap to a movement-modelled graph.
 *
 * @author Mikko Kotola
 */
public class MovementModel {

    private double speedFactorUphill;
    private double speedFactorUphillSteep;
    private double speedFactorDownhill;
    private double speedFactorDownhillSteep;
    private double mapCellSize;

    /**
     * Constructor with no parameters sets default values for the uphill and
     * downhill speed factors and the map cell size to 2.0 metres.
     */
    public MovementModel() {
        this.speedFactorUphillSteep = 0.3;
        this.speedFactorUphill = 0.7;
        this.speedFactorDownhill = 0.9;
        this.speedFactorDownhillSteep = 0.6;
        this.mapCellSize = 2.0;
    }

    public MovementModel(double speedFactorUphillSteep, double speedFactorUphill, double speedFactorDownhill, double speedFactorDownhillSteep, double mapCellSize) {
        this.speedFactorUphillSteep = speedFactorUphillSteep;
        this.speedFactorUphill = speedFactorUphill;
        this.speedFactorDownhill = speedFactorDownhill;
        this.speedFactorDownhillSteep = speedFactorDownhillSteep;
        this.mapCellSize = mapCellSize;
    }

    public double getSpeedFactorUphill() {
        return speedFactorUphill;
    }

    public double getSpeedFactorDownhill() {
        return speedFactorDownhill;
    }
    
    /**
     * Returns the edge weight that corresponds to the movement model and the
     * altitudechange given as the parameter.
     */
    public double calculateEdgeWeight(double altitudeChange) {
        double steepness = altitudeChange/mapCellSize;
        double movementFactor;
        if (steepness > 1.5) {
            movementFactor = Double.MAX_VALUE;
        } else if (steepness < -1.5) {
            movementFactor = Double.MAX_VALUE;
        } else if (steepness < -0.7) {
            movementFactor = speedFactorDownhillSteep;
        } else if (steepness < -0.1) {
            movementFactor = speedFactorDownhill;
        } else if (steepness < 0.1) {
            movementFactor = 1;
        } else if (steepness < 0.7) {
            movementFactor = speedFactorUphill;
        } else {
            movementFactor = speedFactorUphillSteep;
        }
        
        return mapCellSize*movementFactor;
    }

}
