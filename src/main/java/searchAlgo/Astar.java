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
import searchAlgo.Dijkstra;

/**
 * The A* search algorithm. Performs shortest route searches on the given graph.
 * Uses goal-distance estimation to direct the search.
 * 
 * @author Mikko Kotola
 */
public class Astar extends Dijkstra {
    
    /**
     * Initialise search algorithm with a graph.
     * @param graph The graph to be searched
     */
    public Astar(Graph graph) {
        super(graph);
        this.name = "Astar";
    }
    
    @Override
    void initialiseSingleSource(Vertice vertice) {
        graph.resetGraph();
        vertice.setDistToStart(0);
        heap.reset();
        
        Vertice[][] vertices = this.graph.getVertices();
        for (int i = 0; i <  vertices.length; i++) {
            for (int j = 0; j < vertices[0].length; j++) {
                estimateDistToGoal(vertices[i][j]);
            }
        }
    }

    
    /**
     * Estimated a vertice's distance from the goal. Sets the estimate to the
     * current manhattan distance between the parameter vertice and the goal
     * vertice.
     * 
     * @param vertice The vertice for which the goal distance is to be estimated
     */
    public void estimateDistToGoal(Vertice vertice) {
        double smallestPossibleEdgeWeight = graph.getMovementModel().getMapCellSize()/graph.getMovementModel().getBasicSpeed();
        double distanceToGoal = 
                (Math.abs(this.goal.getX()-vertice.getX())
                + Math.abs(this.goal.getY()-vertice.getY()))
                * smallestPossibleEdgeWeight;
        vertice.setDistToGoal(distanceToGoal);
    }
    
}