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
     * Runs the default performance tests. Map 4313A, default MovementModel,
     * start (500, 500), goal (1000, 1000), times to run 5. Uses all algorithms
     * available. Prints out the results in the console.
     */
    public void runPerformanceTests() {
        String filename = "M4313A";
        MovementModel movementModel = new MovementModel();
        int startX = 500;
        int startY = 500;
        int goalX = 1000;
        int goalY = 1000;
        int timesToRun = 5;

        runPerformanceTests(filename, movementModel, startX, startY, goalX, goalY, timesToRun);
    }

    /**
     * Runs the performance test routine with the given parameters. Uses all
     * algorithms available. Prints out the results in the console.
     *
     * @param filename The filename of map to be used
     * @param movementModel The movement model to be used
     * @param startX Start point x coordinate
     * @param startY Start point y coordinate
     * @param goalX Goal point x coordinate
     * @param goalY Goal point y coordinate
     * @param timesToRun The amount of times for the searches to be executed
     *
     */
    public void runPerformanceTests(String filename, MovementModel movementModel, int startX, int startY, int goalX, int goalY, int timesToRun) {
        AltitudeMap map = readInMapFromAscii("altitudefiles/" + filename + ".asc");
        Graph graph = new Graph(map, movementModel);

        DynamicList<SearchAlgo> searchAlgos = new DynamicList<>();
        searchAlgos.add(new Dijkstra(graph));
        searchAlgos.add(new Astar(graph));

        // Number of algos in use.
        int numberOfAlgos = searchAlgos.size();
        // Create resulttimes lists, one for each searchAlgo.
        DynamicList<DynamicList<Long>> resultTimes = new DynamicList<>();

        DynamicList<Long> sums = new DynamicList<>();
        DynamicList<Long> fastest = new DynamicList<>();
        DynamicList<Long> slowest = new DynamicList<>();
        for (int i = 0; i < numberOfAlgos; i++) {
            resultTimes.add(new DynamicList<>());
            sums.add(0l);
        }

        // Run a performance test a number of time for each searchalgo on the list.
        for (int i = 1; i <= timesToRun; i++) {
            for (int j = 0; j < numberOfAlgos; j++) {
                graph.resetGraph();
                resultTimes.get(j).add((Long) runPerformanceTest(searchAlgos.get(j), graph, startX, startY, goalX, goalY));
            }
        }

        // Sum up the result times per algorithm and record fastest and slowest time per algo
        for (int i = 0; i < numberOfAlgos; i++) {
            long fastestTime = Long.MAX_VALUE;
            long slowestTime = -1;
            for (int j = 0; j < timesToRun; j++) {
                long currentRes = resultTimes.get(i).get(j);
                sums.add(i, sums.get(i) + currentRes);
                if (currentRes > slowestTime) {
                    slowestTime = currentRes;
                } else if (currentRes < fastestTime) {
                    fastestTime = currentRes;
                }
            }
            fastest.add(fastestTime);
            slowest.add(slowestTime);
        }

        // Print performance test results to console
        System.out.println("Map: " + filename);;
        System.out.println("Start point (X,Y): " + startX + ", " + startY);
        System.out.println("Goal point (X,Y): " + goalX + ", " + goalY);
        System.out.println("Runs: " + timesToRun);

        for (int i = 0; i < numberOfAlgos; i++) {
            System.out.println("Algorithm " + (i + 1) + ": " + searchAlgos.get(i).getName());
            System.out.println("  Average running time: " + (double) sums.get(i) / resultTimes.get(i).size() + " ms");
            System.out.println("  Median running time: " + median(resultTimes.get(i)) + " ms");
            System.out.println("  Slowest running time: " + slowest.get(i) + " ms");
            System.out.println("  Fastest running time: " + fastest.get(i) + " ms");

        }

    }

    private double median(DynamicList<Long> resultTimes) {
        if (resultTimes.size() % 2 == 0) {
            return ((resultTimes.get((int) Math.floor(resultTimes.size() / 2)) + resultTimes.get((int) Math.ceil(resultTimes.size() / 2))) / 2);
        }

        return (resultTimes.get((int) Math.floor(resultTimes.size() / 2)));
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
