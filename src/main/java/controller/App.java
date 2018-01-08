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
import searchAlgo.Dijkstra;
import graph.Edge;
import graph.Graph;
import movementModel.MovementModel;
import searchAlgo.SearchAlgo;
import graph.Vertice;
import io.AsciiMapReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import searchAlgo.Astar;
import ui.ConsoleUI;

/**
 * App. The main controller class.
 *
 * @author Mikko Kotola
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConsoleUI ui = new ConsoleUI();

        ui.print("");
        ui.print("* Welcome to AltitudeRoutes *");
        ui.print("");
        ui.print("The application uses location data produced by the National Land Survey of Finland (Maanmittauslaitoksen maastotietokannan 12/2017 aineistoa).");

        // READ IN ALTITUDEMAP.
        //String filename = "src/main/resources/altitudefiles/M4313A.asc";
        String filename = "altitudefiles/M4313A.asc";
        ui.print("");
        ui.print("Selected map file: " + filename);
        ui.print("Reading in AltitudeMap...");

        AltitudeMap map = readInMapFromAscii(filename);

        // CREATE MOVEMENTMODEL.
        MovementModel movementModel = new MovementModel();

        ui.print("Creating graph...");
        // CREATE GRAPH.
        Graph graph = new Graph(map, movementModel, false);

        // Test finding a simple shortest route.
        // Set the start and goal coordinates here.
        ui.print("");
        String algoName = readName(ui);
        SearchAlgo searchAlgo = null;
        if (algoName.equals("Dijkstra")) {
            searchAlgo = new Dijkstra(graph);
        } else if (algoName.equals("Astar")) {
            searchAlgo = new Astar(graph);
        }
        
        String routeAlgorithmName = searchAlgo.getName();

        // Print out AltitudeMapDetails
        ui.print("--------");
        ui.print("AltitudeMap Details:");
        ui.print("File: " + filename);
        ui.print("Cols " + map.getNcols());
        ui.print("Rows " + map.getNrows());
        ui.print("--------");

        // Set the start and goal coordinates here.
        ui.print("");
        ui.print("Enter x-coordinate of start: (1-3000)");
        int startX = readCoordinate(ui);
        ui.print("Enter y-coordinate of start: (1-3000");
        int startY = readCoordinate(ui);
        ui.print("Enter x-coordinate of goal: (1-3000)");
        int goalX = readCoordinate(ui);
        ui.print("Enter y-coordinate of goal: (1-3000)");
        int goalY = readCoordinate(ui);

        ui.print("--------");
        ui.print("* Shortest route *");
        ui.print("From: (" + startX + ", " + startY + ")");
        ui.print("To: (" + goalX + ", " + goalY + ")");
        ui.print("Route algorithm: " + routeAlgorithmName);
        ui.print("Searching...");
        searchAlgo.runShortestRouteFind(graph.getVertice(startX, startY), graph.getVertice(goalX, goalY));
        double lengthOfShortestPath = searchAlgo.returnLengthOfShortestRoute();
        ArrayList<Vertice> shortestPath = searchAlgo.returnShortestPath();
        ui.print("");
        ui.print("Path:");
        for (int i = 0; i < shortestPath.size(); i++) {
            Vertice v = shortestPath.get(i);
            ui.print("(" + v.getX() + ", " + v.getY() + ", " + v.getZ() + "), cumulative distance from start " + v.getDistToStart());

        }
        ui.print("");
        ui.print("Length of shortest path: " + lengthOfShortestPath);
        ui.print("");
        ui.print("* Thank you, run again! *");

    }

    private static String readName(ConsoleUI ui) {
        String algoName;
        while (true) {
            ui.print("Enter search algorithm (D = Dijkstra, A = Astar):");
            algoName = ui.readLine();
            if (algoName.equals("D") || algoName.equals("Dijkstra")) {
                ui.print("Dijkstra selected.");
                return "Dijkstra";
            } else if (algoName.equals("A") || algoName.equals("Astar")) {
                ui.print("Astar selected.");
                return "Astar";
            } else {
                ui.print("Not a valid algorithm.");
            }
        }
    }

    private static int readCoordinate(ConsoleUI ui) {
        int number = -1;
        while (true) {
            try {
                number = Integer.parseInt(ui.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Not a valid integer, enter an integer in the range 1-3000.");
            }

            if (number < 1 || number > 3000) {
                ui.print("Not a valid coordinate, enter an integer in the range 1-3000.");
            } else {
                break;
            }
        }

        return number;
    }

    private static AltitudeMap readInMapFromAscii(String filename) {
        AsciiMapReader asciiMapReader = new AsciiMapReader(filename);
        AltitudeMap map = null;
        try {
            map = asciiMapReader.readWholeMap();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;
    }

}
