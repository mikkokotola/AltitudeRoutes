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
     * is used to define the edges (traversability and weight). The graph
     * supports both start- and goal-estimated shortest route searches (e.g. A*) 
     * and plain start-estimated Dijkstra-type searches.  
     * 
     * @param map The source AltitudeMap for the graph
     * @param movementModel The source MovementModel for the graph
     */

    public Graph(AltitudeMap map, MovementModel movementModel) {
        this.map = map;
        this.movementModel = movementModel;
        this.vertices = new Vertice[map.getNrows()][map.getNcols()];
        createVerticeGraphFromMap();
    }

    // Constructor's utility method.
    private void createVerticeGraphFromMap() {
        // Create vertices.
        for (int i = 0; i < map.getAltitudes().length; i++) {
            for (int j = 0; j < map.getAltitudes()[0].length; j++) {
                Vertice newV= new Vertice(j, i, map.getAltitude(i, j));
                vertices[i][j] = newV;
            }
        }
        
        // Add edges to vertices.
        for (int i = 0; i < map.getAltitudes().length; i++) {
            for (int j = 0; j < map.getAltitudes()[0].length; j++) {
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
        if (vertice.getY() > 0) {
            Vertice neighbour = vertices[vertice.getY()-1][vertice.getX()];
            addEdge(vertice, neighbour);
        }
    }

    private void addEdgeToBelow(Vertice vertice) {
        if (vertice.getY() < map.getNrows()-1) {
            Vertice neighbour = vertices[vertice.getY()+1][vertice.getX()];
            addEdge(vertice, neighbour);
        }
    }
    
    private void addEdgeToLeft(Vertice vertice) {
        if (vertice.getX() > 0) {
            Vertice neighbour = vertices[vertice.getY()][vertice.getX()-1];
            addEdge(vertice, neighbour);
        }
    }
    
    private void addEdgeToRight(Vertice vertice) {
        if (vertice.getX() < map.getNcols()-1) {
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

    /**
     * Returns the AltitudeMap that the graph has been created from.
     * @return AltitudeMap The AltitudeMap that the graph has been created from
     */
    public AltitudeMap getMap() {
        return map;
    }

    /**
     * Returns the MovementModel associated with the graph. 
     * @return MovementModel MovementModel used in creating the graph from a map
     */
    public MovementModel getMovementModel() {
        return movementModel;
    }

    /**
     * Returns the vertice lists (2D array Vertice[y][x] with coordinates map 
     * relative, (0,0) in the top left corner).
     * @return 2D array [y][x] with coordinates map relative, (0,0) in the top left corner
     */
    public Vertice[][] getVertices() {
        return vertices;
    }

    /**
     * Returns a single vertice in the given coordinates (map relative,
     * (0,0) in the top left corner)).
     * @param x X-coordinate map relative (0,0) in top left corner
     * @param y Y-coordinate map relative (0,0) in top left corner
     * @return The vertice
     */
    public Vertice getVertice(int x, int y) {
        return vertices[y][x];
    }
    
    /**
     * Resets the graph. A reset graph is in the same state as a new graph
     * created from a given map. This enables consecutive searches to be
     * performed without creating a new graph.
     */
    public void resetGraph() {
        for (int i = 0; i < map.getAltitudes().length; i++) {
            for (int j = 0; j < map.getAltitudes()[0].length; j++) {
                vertices[i][j].setDistToStart(Double.MAX_VALUE);
                vertices[i][j].setDistToGoal(0);
                vertices[i][j].setPath(null);
                vertices[i][j].setHeapRef(-1);
            }
        }
    }
    /**
     * Counts the number of vertices opened by a search. The counting is done
     * by counting the vertices that have a path value that is not null.
     * 
     * @return int The number of vertices opened during a search (path not null)
     */
    public int countOpened() {
        int count = 0;
        for (int i = 0; i < map.getAltitudes().length; i++) {
            for (int j = 0; j < map.getAltitudes()[0].length; j++) {
                if (vertices[i][j].getPath() != null) {
                    count++;
                }
                
            }
        }
        return count;
    }
    
    
}
