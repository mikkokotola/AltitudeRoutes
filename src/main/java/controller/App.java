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
import dataStructures.DynamicList;
import searchAlgo.SearchAlgo;
import searchAlgo.Dijkstra;
import searchAlgo.Astar;
import graph.Graph;
import graph.Vertice;
import graph.Edge;
import movementModel.MovementModel;
import io.AsciiMapReader;
import io.ImageDrawer;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import performanceTesting.PerformanceTester;
import ui.ConsoleUI;
import ui.UI;

/**
 * App. The main controller class.
 *
 * @author Mikko Kotola
 */
public class App {

    private UI ui;

    /**
     * Main method.
     *
     * @param args Does not take any command line arguments
     */
    public static void main(String[] args) {
        //ConsoleUI ui = new ConsoleUI();
        //App app = new App(ui);
        //app.run();

        // RUN PERFORMANCE TEST ROUTINES. 
        // Uncomment the first line and the test routine you wish to run.
        // To go straight to the performance testing, comment the 3 lines above.
        PerformanceTester tester = new PerformanceTester();
        tester.runPerformanceTests();
        //tester.runPerformanceTests2();
        //tester.runPerformanceTests3();
    }

    public App(UI ui) {
        this.ui = ui;
    }

    /**
     * Runs the normal user interface routine.
     */
    public void run() {
        ui.print("");
        ui.print("* Welcome to AltitudeRoutes *");
        ui.print("");
        ui.print("The application uses location data produced by the National Land Survey of Finland (Maanmittauslaitoksen maastotietokannan 12/2017 aineistoa).");

        AltitudeMap map = readMapByFileName(ui);
        MovementModel movementModel = new MovementModel(map.getCellsize());

        if (map != null) {
            printMapDetails(map);
            ui.print("Creating graph...");
            Graph graph = new Graph(map, movementModel);
            
            boolean quit = false;
            while (!quit) {
                SearchAlgo searchAlgo = readAlgo(graph);
                if (searchAlgo == null) {
                    quit = true;
                } else {
                    String routeAlgorithmName = searchAlgo.getName();

                    String coordinateSystem = readCoordinateSystem();

                    int xLowerLimit = 0;
                    int xHigherLimit = 0;
                    int yLowerLimit = 0;
                    int yHigherLimit = 0;

                    if (coordinateSystem.equals("etrsTm35Fin")) {
                        xLowerLimit = map.getEtrsXByMapX(0); // (int) Math.ceil(map.getEtrsXByMapX(0))
                        xHigherLimit = map.getEtrsXByMapX(map.getNcols() - 1); // (int) Math.floor(map.getEtrsXByMapX(map.getNcols()-1))
                        yLowerLimit = map.getEtrsYByMapY(map.getNrows() - 1); // (int) Math.ceil(map.getYllcorner())
                        yHigherLimit = map.getEtrsYByMapY(0); // (int) Math.floor(map.getEtrsYByMapY(0))
                    } else if (coordinateSystem.equals("mapSpecific")) {
                        xLowerLimit = 0;
                        xHigherLimit = map.getNcols() - 1;
                        yLowerLimit = 0;
                        yHigherLimit = map.getNrows() - 1;
                    }
                    ui.print("Enter x-coordinate of start: (" + xLowerLimit + "-" + xHigherLimit + ")");
                    int startX = readCoordinate(ui, xLowerLimit, xHigherLimit);
                    ui.print("Enter y-coordinate of start: (" + yLowerLimit + "-" + yHigherLimit + ")");
                    int startY = readCoordinate(ui, yLowerLimit, yHigherLimit);
                    ui.print("Enter x-coordinate of goal: (" + xLowerLimit + "-" + xHigherLimit + ")");
                    int goalX = readCoordinate(ui, xLowerLimit, xHigherLimit);
                    ui.print("Enter y-coordinate of goal: (" + yLowerLimit + "-" + yHigherLimit + ")");
                    int goalY = readCoordinate(ui, yLowerLimit, yHigherLimit);

                    printSearchDetails(startX, startY, goalX, goalY, routeAlgorithmName);

                    // Coordinate conversion if needed.
                    if (coordinateSystem.equals("etrsTm35Fin")) {
                        startX = map.getMapXByEtrsX(startX);
                        startY = map.getMapYByEtrsY(startY);
                        goalX = map.getMapXByEtrsX(goalX);
                        goalY = map.getMapYByEtrsY(goalY);
                    }

                    long timeStart = System.currentTimeMillis();
                    searchAlgo.runShortestRouteFind(graph.getVertice(startX, startY), graph.getVertice(goalX, goalY));
                    long timeEnd = System.currentTimeMillis();
                    ui.print("Search complete");
                    ui.print("Running time of search: " + (timeEnd - timeStart) + "ms.");

                    double lengthOfShortestPath = searchAlgo.returnLengthOfShortestRoute();
                    DynamicList<Vertice> shortestPath = searchAlgo.returnShortestPath();

                    printShortestPathAsVerticeList(graph, searchAlgo, lengthOfShortestPath, shortestPath, coordinateSystem);

                    exportMapImage(map, searchAlgo, startX, startY, goalX, goalY, graph, shortestPath, coordinateSystem);
                    
                    ui.print("");
                    ui.print("Search complete, entering new search within the selected map.");
                }
            }
        }
        ui.print("");
        ui.print("* Thank you, run again! *");

    }

    private String readCoordinateSystem() {
        String coordinateSystem = null;
        while (true) {
            ui.print("");
            ui.print("Enter start and goal coordinates in M = map-specific values or E = etrs-tm35fin coordinate system?");
            coordinateSystem = ui.readLine();
            if (coordinateSystem.toLowerCase().equals("m")) {
                ui.print("Map-specific selected.");
                return ("mapSpecific");
            } else if (coordinateSystem.toLowerCase().equals("e")) {
                ui.print("ETRS-TM35FIN selected.");
                return ("etrsTm35Fin");
            } else {
                ui.print("Not a valid coordinate system value.");
            }
        }
    }

    private void printSearchDetails(int startX, int startY, int goalX, int goalY, String routeAlgorithmName) {
        ui.print("--------");
        ui.print("* Shortest route *");
        ui.print("From: (" + startX + ", " + startY + ")");
        ui.print("To: (" + goalX + ", " + goalY + ")");
        ui.print("Route algorithm: " + routeAlgorithmName);
        ui.print("Searching...");
    }

    private void exportMapImage(AltitudeMap map, SearchAlgo searchAlgo, int startX, int startY, int goalX, int goalY, Graph graph, DynamicList<Vertice> shortestPath, String coordinateSystem) {
        ui.print("");

        ui.print("Export map image (y/n)?");
        String answer = ui.readLine();

        if (answer.equals("y") || answer.equals("yes")) {
            String picFileName = map.getFilename().substring(0, map.getFilename().indexOf(".")) + "_" + searchAlgo.getName() + "_" + startX + "-" + startY + "_" + goalX + "-" + goalY;
            ui.print("Exporting image to " + picFileName + ".PNG ...");
            drawMapImage(graph, shortestPath, searchAlgo, picFileName, coordinateSystem);
        }
    }

    private void printShortestPathAsVerticeList(Graph graph, SearchAlgo searchAlgo, double lengthOfShortestPath, DynamicList<Vertice> shortestPath, String coordinateSystem) {
        ui.print("");
        String answer;
        if (lengthOfShortestPath > 0) {
            ui.print("Print shortest path as vertice list (y/n)?");
            answer = ui.readLine();
            if (answer.equals("y") || answer.equals("yes")) {
                ui.print("Path:");
                if (coordinateSystem.equals("etrsTm35Fin")) {
                    ui.print("Coordinate system: ETRS-TM35FIN");
                } else if (coordinateSystem.equals("mapSpecific")) {
                    ui.print("Coordinate system: map-relative (0,0) in top left corner");
                }
                for (int i = 0; i < shortestPath.size(); i++) {
                    Vertice v = shortestPath.get(i);
                    int xToPrint = v.getX();
                    int yToPrint = v.getY();

                    // Coordinate conversion if needed.
                    if (coordinateSystem.equals("etrsTm35Fin")) {
                        xToPrint = graph.getMap().getEtrsXByMapX(xToPrint);
                        yToPrint = graph.getMap().getEtrsYByMapY(yToPrint);
                    }

                    ui.print("(" + xToPrint + ", " + yToPrint + ", " + v.getZ() + "), cumulative distance from start " + v.getDistToStart());
                }
                ui.print("");
            }
            ui.print("Length of shortest path: " + lengthOfShortestPath);
        } else if (lengthOfShortestPath == 0) {
            ui.print("Length of shortest path: 0");
        } else {
            ui.print("No path found.");
        }
        int opened = graph.countOpened();
        ui.print("Vertices opened during search: " + opened);
        ui.print("Vertices investigated during search: " + (opened - searchAlgo.heapSize()));

    }

    private void printMapDetails(AltitudeMap map) {
        ui.print("");
        ui.print("--------");
        ui.print("AltitudeMap Details:");
        ui.print("File: " + map.getFilename());
        ui.print("Cols " + map.getNcols());
        ui.print("Rows " + map.getNrows());
        ui.print("--------");
    }

    private SearchAlgo readAlgo(Graph graph) {
        SearchAlgo searchAlgo = null;
        String algoName = null;
        while (true) {
            ui.print("");
            ui.print("***");
            ui.print("");
            ui.print("New search");
            ui.print("Selected map file: " + graph.getMap().getFilename());
            ui.print("Enter search algorithm (D = Dijkstra, A = Astar, enter to quit):");
            algoName = ui.readLine();
            if (algoName.toLowerCase().equals("dijkstra") || algoName.toLowerCase().equals("d")) {
                ui.print("Dijkstra selected.");
                return searchAlgo = new Dijkstra(graph);
            } else if (algoName.toLowerCase().equals("astar") || algoName.toLowerCase().equals("a")) {
                ui.print("Astar selected.");
                return searchAlgo = new Astar(graph);
            } else if (algoName.equals("") || algoName.equals("quit") || algoName.equals("exit")) {
                ui.print("No algo entered, quitting.");
                return null;
            } else {
                ui.print("Enter a valid algo name or enter to quit.");
            }
        }
    }

    private static void drawMapImage(Graph graph, DynamicList<Vertice> shortestPath, SearchAlgo searchAlgo, String picFileName, String coordinateSystem) {
        ImageDrawer imDrawer = new ImageDrawer();

        try {
            imDrawer.draw(graph, shortestPath, searchAlgo, picFileName, coordinateSystem);
        } catch (Exception e) {
        }
    }

    private AltitudeMap readMapByFileName(UI ui) {

        String fileName;
        while (true) {
            ui.print("");
            ui.print("Place file in the folder /altitudefiles .");
            ui.print("Then enter filename (e.g. testMap3 or M4313A) without file postfix (.asc):");

            fileName = ui.readLine();

            if (fileName.equals("") || fileName.equals("quit") || fileName.equals("exit")) {
                ui.print("No map entered, quitting.");
                return null;
            }

            ui.print("Selected map file: " + fileName);
            ui.print("");
            ui.print("Reading in AltitudeMap...");

            AltitudeMap map = readInMapFromAscii("altitudefiles/" + fileName + ".asc");

            if (map != null) {
                return (map);
            } else {
                ui.print("The map file was not found. Try again. Enter to quit.");
            }
        }
    }

    private AltitudeMap readInMapFromAscii(String filename) {
        AsciiMapReader asciiMapReader = new AsciiMapReader(filename);
        AltitudeMap map = null;
        try {
            map = asciiMapReader.readWholeMap();
        } catch (FileNotFoundException ex) {
            return (null);
        }
        return map;
    }

    private static int readCoordinate(UI ui, int lowerLimit, int higherLimit) {
        int number = -1;
        while (true) {
            try {
                number = Integer.parseInt(ui.readLine());
            } catch (NumberFormatException e) {
                ui.print("Not a valid integer, enter an integer in the range " + lowerLimit + "-" + higherLimit + ".");
            }

            if (number < lowerLimit || number > higherLimit) {
                ui.print("Not a valid coordinate, enter an integer in the range " + lowerLimit + "-" + higherLimit + ".");
            } else {
                break;
            }
        }

        return number;
    }
}
