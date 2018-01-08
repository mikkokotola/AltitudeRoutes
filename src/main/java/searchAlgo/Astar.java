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

import graph.Graph;
import graph.Vertice;
import graph.VerticeEstimated;
import searchAlgo.Dijkstra;

/**
 *
 * @author mkotola
 */
public class Astar extends Dijkstra {
    
    public Astar(Graph graph) {
        super(graph);
        this.name = "Astar";
    }
    
    @Override
    public void initialiseSingleSource(Vertice vertice) {
        vertice.setDistToStart(0);
        Vertice[][] vertices = this.graph.getVertices();
        for (int i = 1; i <  vertices.length; i++) {
            for (int j = 1; j < vertices[0].length; j++) {
                estimateDistToGoal(vertices[i][j]);
            }
        }
    }

    
    /**
     * Estimated a vertice's distance from the goal. Sets the estimate to the
     * current manhattan distance between the parameter vertice and the goal
     * vertice.
     */

    public void estimateDistToGoal(Vertice vertice) {
        double distanceToGoal = (Math.abs(this.goal.getX()-vertice.getX()) 
                + Math.abs(this.goal.getY()-vertice.getY()));
        vertice.setDistToGoal(distanceToGoal);
    }
    
}