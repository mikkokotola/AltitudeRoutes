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

import movementModel.MovementModel;
import altitudeMap.AltitudeMap;

/**
 * A four-connected directed grid graph. Graph consists of vertices. The graph 
 * edges are represented as attributes of vertices. The graph is a directed 
 * four-connected grid graph, where the edges connect all traversable vertices
 * that are above, below, to the right or to the left of the source vertice.
 * The weight of the edge from vertice M to vertice N is not always equal to
 * the weight of the edge from N to M.
 * @author Mikko Kotola
 */
public class Graph {

    private AltitudeMap map;
    private MovementModel movementModel;
    Vertice[][] vertices;
    
    /**
     * Sets up a graph with a defined AltitudeMap and MovementModel. The graph 
     * edges are represented as attributes of vertices. The first parameter is 
     * the source AltitudeMap. The second parameter is the MovementModel, which
     * is used to define the edges (traversability and weight). The third 
     * parameter is a boolean value indicating if the map is to
     * support goal-estimated shortest route searches (e.g. A*) 
     * (estimated = true) or only plain Dijkstra-type searches 
     * (estimated = false).  
     */

    public Graph(AltitudeMap map, MovementModel movementModel, boolean estimated) {
        this.map = map;
        this.movementModel = movementModel;
        this.vertices = new Vertice[map.getNrows()+1][map.getNcols()+1];
        createVerticeGraphFromMap(estimated);
    }

    // Constructor's utility method.
    private void createVerticeGraphFromMap(boolean estimated) {
        // Create vertices.
        for (int i = 1; i < map.getAltitudes().length; i++) {
            for (int j = 1; j < map.getAltitudes()[0].length; j++) {
                Vertice newV= new Vertice(j, i, map.getAltitude(i, j));
                vertices[i][j] = newV;
            }
        }
        
        // Add edges to vertices.
        for (int i = 1; i < map.getAltitudes().length; i++) {
            for (int j = 1; j < map.getAltitudes()[0].length; j++) {
                addDepartingEdgesToVertice(vertices[i][j]);
            }
        }
    }
  
    /**
     * Adds all departing edges to a vertice. A support method used by 
     * createVerticeGraphFromMap, that adds all departing edges to the edge
     * list of the source vertice. The method exctracts the edges from the
     * AltitudeMap. 
     */
    private void addDepartingEdgesToVertice (Vertice vertice) {
        addEdgeToAbove(vertice);
        addEdgeToBelow(vertice);
        addEdgeToLeft(vertice);
        addEdgeToRight(vertice);
    }
    
    private void addEdgeToAbove(Vertice vertice) {
        if (vertice.getY() > 1) {
            Vertice neighbour = vertices[vertice.getY()-1][vertice.getX()];
            addEdge(vertice, neighbour);
        }
    }

    private void addEdgeToBelow(Vertice vertice) {
        if (vertice.getY() < map.getNrows()) {
            Vertice neighbour = vertices[vertice.getY()+1][vertice.getX()];
            addEdge(vertice, neighbour);
        }
    }
    
    private void addEdgeToLeft(Vertice vertice) {
        if (vertice.getX() > 1) {
            Vertice neighbour = vertices[vertice.getY()][vertice.getX()-1];
            addEdge(vertice, neighbour);
        }
    }
    
    private void addEdgeToRight(Vertice vertice) {
        if (vertice.getX() < map.getNcols()) {
            Vertice neighbour = vertices[vertice.getY()][vertice.getX()+1];
            addEdge(vertice, neighbour);
        }
    }

    // A common utility method for the addEdgeTo-methods. Calculates the edge
    // weight and adds the new edge to the source vertice's edgelist.
    private void addEdge(Vertice vertice, Vertice neighbour) {
        double altitudeChange = neighbour.getZ() - vertice.getZ();
        double edgeWeight = this.movementModel.calculateEdgeWeight(altitudeChange);
        if (edgeWeight != this.movementModel.getImpassableEdgeWeight()) {
            vertice.addEdge(new Edge(vertice, neighbour, edgeWeight));
        }
    }

    public AltitudeMap getMap() {
        return map;
    }

    public MovementModel getMovementModel() {
        return movementModel;
    }

    public Vertice[][] getVertices() {
        return vertices;
    }

    public Vertice getVertice(int x, int y) {
        return vertices[y][x];
    }
    
}
