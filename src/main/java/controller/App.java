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
package controller;

import altitudeMap.AltitudeMap;
import domain.Dijkstra;
import domain.Edge;
import domain.Graph;
import domain.MovementModel;
import domain.Vertice;
import io.AsciiMapReader;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/** App. The main controller class.
 *
 * @author Mikko Kotola
 */
public class App {
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // READ IN ALTITUDEMAP.
        //String filename = "src/main/resources/altitudefiles/M4313A.asc";
        String filename = "altitudefiles/M4313A.asc";
        AsciiMapReader asciiMapReader = new AsciiMapReader(filename);
        AltitudeMap map = null;
        try {
            map = asciiMapReader.readWholeMap();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // CREATE MOVEMENTMODEL.
        MovementModel movementModel = new MovementModel();
        
        
        // Temporary, print out AltitudeMapDetails
        System.out.println("--------");
        System.out.println("AltitudeMap Details:");
        System.out.println("Cols " + map.getNcols());
        System.out.println("Cols in elev:" + map.getAltitudes()[1].length);
        System.out.println("Rows " + map.getNrows() );
        System.out.println("Rows in elev:" + map.getAltitudes().length);
        
        // Temporary, for testing the formation of the Graph.
        Graph graph = new Graph(map, movementModel, false);
        Vertice vert = graph.getVertice(2, 2);
        System.out.println("--------");
        System.out.println("Vertice:");
        System.out.println("x: " + vert.getX());
        System.out.println("y: " + vert.getY());
        System.out.println("z: " + vert.getZ());
        System.out.println("Dist to start: " + vert.getDistToStart());
        
        // Test the Edgelists.
        Edge[] edges = vert.getEdges();
        for (int i = 0; i < vert.getNumberOfEdges(); i++) {
            Vertice from = edges[i].getFrom();
            Vertice to = edges[i].getTo();
            double weight = edges[i].getWeight();
            System.out.println("---");
            System.out.println("Edge " + i);
            System.out.println("From " + from.getX() + ", " + from.getY());
            System.out.println("To " + to.getX() + ", " + to.getY());
            System.out.println("Weight " + weight);
        }
        
        // Test finding a simple shortest route.
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.runShortestRouteFind(graph.getVertice(1, 1), graph.getVertice(6, 1));
        System.out.println("---");
        System.out.println("Shortest route:");
        
        
        System.out.println("V (6,1): dist " + graph.getVertice(6, 1).getDistToStart() + ", path " + graph.getVertice(6, 1).getPath().getX() + ", " + graph.getVertice(6, 1).getPath().getY());
        System.out.println("V (3,1): dist " + graph.getVertice(3, 1).getDistToStart() + ", path " + graph.getVertice(3, 1).getPath().getX() + ", " + graph.getVertice(3, 1).getPath().getY());
        System.out.println("V (2,1): dist " + graph.getVertice(2, 1).getDistToStart() + ", path " + graph.getVertice(2, 1).getPath().getX() + ", " + graph.getVertice(2, 1).getPath().getY());
        System.out.println("V (1,1): dist " + graph.getVertice(1, 1).getDistToStart());
        
    }

}
