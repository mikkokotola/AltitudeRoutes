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
package performanceTesting;

import altitudeMap.AltitudeMap;
import controller.App;
import dataStructures.DynamicList;
import graph.Graph;
import io.AsciiMapReader;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import movementModel.MovementModel;
import searchAlgo.Astar;
import searchAlgo.Dijkstra;
import searchAlgo.SearchAlgo;

/**
 * Routines for performance testing of search algorithms.
 *
 * @author Mikko Kotola
 */
public class PerformanceTester {

    /**
     * Runs the performance tests.
     */
    public void runPerformanceTests() {
        // Define map file for performance tests here.
        String filename = "M4313A";
        AltitudeMap map = readInMapFromAscii("altitudefiles/" + filename + ".asc");
        MovementModel movementModel = new MovementModel();
        Graph graph = new Graph(map, movementModel);

        // Define start and end coordinates for performance test run here
        int startX = 500;
        int startY = 500;
        int goalX = 1000;
        int goalY = 1000;
        
        // Define times to run
        int timesToRun = 2;

        SearchAlgo algo1 = null;
        SearchAlgo algo2 = null;
        DynamicList<Long> resultTimes1 = new DynamicList<>();
        DynamicList<Long> resultTimes2 = new DynamicList<>();
        for (int i = 1; i <= timesToRun; i++) {
            
            // Select algorithm for performance test run here.
            algo1 = new Dijkstra(graph);
            algo2 = new Astar(graph);

            graph.resetGraph();
            resultTimes1.add(runPerformanceTest(algo1, graph, startX, startY, goalX, goalY));
            graph.resetGraph();
            resultTimes2.add(runPerformanceTest(algo2, graph, startX, startY, goalX, goalY));
        }

        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < resultTimes1.size(); i++) {
            sum1 += resultTimes1.get(i);
            sum2 += resultTimes2.get(i);
        }
        System.out.println("Map: " + filename);;
        System.out.println("Start point (X,Y): " + startX + ", " + startY);
        System.out.println("Goal point (X,Y): " + goalX + ", " + goalY);
        System.out.println("Algorithm 1: " );
        System.out.println("Algorithm 2: " + algo2.getName());
        System.out.println("Runs: " + timesToRun);
        System.out.println("Average running time / " + algo1.getName() + ": " + (double) sum1 / resultTimes1.size() + " ms");
        System.out.println("Median running time / " + algo1.getName() + ": " + median(resultTimes1) + " ms");
        System.out.println("Average running time / " + algo2.getName() + ": " + (double) sum2 / resultTimes2.size() + " ms");
        System.out.println("Median running time / " + algo2.getName() + ": " + median(resultTimes2) + " ms");
    }
    
    private double median(DynamicList<Long> resultTimes) {
        if (resultTimes.size()%2 == 0) {
            return ((resultTimes.get((int) Math.floor(resultTimes.size()/2)) + resultTimes.get((int) Math.ceil(resultTimes.size()/2)))/2);
        } 
        
        return (resultTimes.get((int) Math.floor(resultTimes.size()/2)));
    }

    /**
     * Run a single performance test with the given parameters.
     *
     * @return long Milliseconds to perform the shortest path search
     */
    public long runPerformanceTest(SearchAlgo searchAlgo, Graph graph, int xStart, int yStart, int xGoal, int yGoal) {
        long timeStart = System.currentTimeMillis();
        searchAlgo.runShortestRouteFind(graph.getVertice(xStart, yStart), graph.getVertice(xGoal, yGoal));
        long timeEnd = System.currentTimeMillis();
        return (timeEnd - timeStart);
    }

    public static AltitudeMap readInMapFromAscii(String filename) {
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
