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
    private double speedFactorImpassable;
    private double mapCellSize;
    private double basicSpeed;
    private double impassableEdgeWeight;

    /**
     * Constructor with no parameters sets default values for the uphill and
     * downhill speed factors and the given map cell size. The basic
     * speed is set to 2.0 (a rough equivalent of meters per second).
     * @param mapCellSize The map cell size in meters
     */
    public MovementModel(double mapCellSize) {
        this.speedFactorUphillSteep = 0.3;
        this.speedFactorUphill = 0.7;
        this.speedFactorDownhill = 0.9;
        this.speedFactorDownhillSteep = 0.6;
        this.mapCellSize = mapCellSize;
        this.basicSpeed = 2.0;
        this.impassableEdgeWeight = Double.MAX_VALUE;
    }

    /**
     * Constructor with parameters creates a MovementModel with the given
     * parameters. Steepness = altitudeChange / mapCellSize. Steepness of over
     * 1.5 or under -1.5 is inpassable. Speedfactor of -0.1 lessThan steepness lessThanOrEqual 0.1 is
     * considered flat and given an speedfactor 1.0. NOTE: All speedfactors must
     * be lessThanOrEqual 1 for goal-estimating algorithms' heuristics to be admissible!
     * @param speedFactorUphillSteep Speedfactor of steep uphill (0.7 lessThanOrEqual steepness lessThanOrEqual 1.5)
     * @param speedFactorUphill Speedfactor of uphill (0.1 lessThanOrEqual steepness lessThan 0.7)
     * @param speedFactorDownhill Speedfactor of downhill (-0.7 lessThanOrEqual steepness lessThan -0.1)
     * @param speedFactorDownhillSteep Speedfactor of steep downhill (-1.5 lessThanOrEqual steepness lessThan -0.7)
     * @param mapCellSize Map cell size in meters
     * @param basicSpeed Basic speed, rough equivalent of meters per second
     * @param impassableEdgeWeight The impassable steepness edge weight, e.g. 
     * Double.MAX_VALUE
     */
    public MovementModel(double speedFactorUphillSteep, double speedFactorUphill, double speedFactorDownhill, double speedFactorDownhillSteep, double mapCellSize, double basicSpeed, double impassableEdgeWeight) {
        this.speedFactorUphillSteep = speedFactorUphillSteep;
        this.speedFactorUphill = speedFactorUphill;
        this.speedFactorDownhill = speedFactorDownhill;
        this.speedFactorDownhillSteep = speedFactorDownhillSteep;
        this.mapCellSize = mapCellSize;
        this.basicSpeed = basicSpeed;
        this.impassableEdgeWeight = impassableEdgeWeight;
    }

    /**
     * Returns the speedfactor of steep uphill (0.7 lessThanOrEqual steepness lessThanOrEqual 1.5)
     * @return Speedfactor of steep uphill (0.7 lessThanOrEqual steepness lessThanOrEqual 1.5)
     */
    public double getSpeedFactorUphillSteep() {
        return speedFactorUphillSteep;
    }

    /**
     * Returns the speedfactor of uphill (0.1 lessThanOrEqual steepness lessThan 0.7)
     * @return Speedfactor of uphill (0.1 lessThanOrEqual steepness lessThan 0.7)
     */
    public double getSpeedFactorUphill() {
        return speedFactorUphill;
    }

    /**
     * Returns the speedfactor of downhill (-0.7 lessThanOrEqual steepness lessThan -0.1)
     * @return Speedfactor of downhill (-0.7 lessThanOrEqual steepness lessThan -0.1)
     */
    public double getSpeedFactorDownhill() {
        return speedFactorDownhill;
    }

    /**
     * Returns the speedfactor of steep downhill (-1.5 lessThanOrEqual steepness lessThan -0.7)
     * @return Speedfactor of steep downhill (-1.5 lessThanOrEqual steepness lessThan -0.7)
     */
    public double getSpeedFactorDownhillSteep() {
        return speedFactorDownhillSteep;
    }

    /**
     * Returns the basic speed, in rough equivalent of meters per second
     * @return The basic speed, in rough equivalent of meters per second
     */
    public double getBasicSpeed() {
        return basicSpeed;
    }

    /**
     * Return the edge weight of impassable edges (steepness lessThan -1.5 or greaterThan 1.5)
     * @return Edge weight of impassable edges (steepness lessThan -1.5 or greaterThan 1.5)
     */
    public double getImpassableEdgeWeight() {
        return impassableEdgeWeight;
    }
    
    /**
     * Returns the map cell size in meters
     * @return The map cell size in meters
     */
    public double getMapCellSize() {
        return mapCellSize;
    }

    /**
     * Returns the edge weight that corresponds to the movement model and the
     * altitudechange given as the parameter.
     * 
     * @param altitudeChange The altitude change for which edge weight is to
     * be calculated
     * 
     * @return double The calculated edge weight (rough equivalent of meters/sec)
     */
    public double calculateEdgeWeight(double altitudeChange) {
        double steepness = altitudeChange / mapCellSize;
        double movementFactor;
        if (steepness > 1.5) {
            return impassableEdgeWeight;
        } else if (steepness < -1.5) {
            return impassableEdgeWeight;
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

        return mapCellSize / (basicSpeed * movementFactor);
    }

}
