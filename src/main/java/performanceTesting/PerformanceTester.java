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
import movementModel.MovementModel;
import searchAlgo.Astar;
import searchAlgo.Dijkstra;
import searchAlgo.SearchAlgo;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Routines for performance testing of search algorithms.
 *
 * @author Mikko Kotola
 */
public class PerformanceTester {

    /**
     * Runs the default performance tests. Map 4313A, default MovementModel,
     * start (500, 500), goal (1000, 1000), times to run 10. Uses all algorithms
     * available. Prints out the results in the console.
     */
    public void runPerformanceTests() {
        String filename = "M4313A";
        MovementModel movementModel = new MovementModel(2.0);
        int startX = 500;
        int startY = 500;
        int goalX = 1000;
        int goalY = 1000;
        int timesToRun = 10;

        runPerformanceTests(filename, movementModel, startX, startY, goalX, goalY, timesToRun);
    }
    
    /**
     * Runs the second set of default performance tests. Map 4313A, default MovementModel,
     * start (700, 1500), goal (1500, 50), times to run 10. Uses all algorithms
     * available. Prints out the results in the console.
     */
    public void runPerformanceTests2() {
        String filename = "M4313A";
        MovementModel movementModel = new MovementModel(2.0);
        int startX = 700;
        int startY = 1500;
        int goalX = 1500;
        int goalY = 50;
        int timesToRun = 10;

        runPerformanceTests(filename, movementModel, startX, startY, goalX, goalY, timesToRun);
    }
    
    /**
     * Runs the third set of default performance tests. Map 4313A, default MovementModel,
     * start (100, 2900), goal (2900, 100), times to run 10. Uses all algorithms
     * available. Prints out the results in the console.
     */
    public void runPerformanceTests3() {
        String filename = "M4313A";
        MovementModel movementModel = new MovementModel(2.0);
        int startX = 100;
        int startY = 2900;
        int goalX = 2900;
        int goalY = 100;
        int timesToRun = 10;

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
        System.out.println("");
        System.out.println("* AltitudeRoutes / Performance testing *");
        System.out.println("");
        System.out.println("Running performance tests, this might take several minutes...");
        AltitudeMap map = readInMapFromAscii("altitudefiles/" + filename + ".asc");
        Graph graph = new Graph(map, movementModel);

        DynamicList<SearchAlgo> searchAlgos = new DynamicList<>();
        searchAlgos.add(new Dijkstra(graph));
        searchAlgos.add(new Astar(graph));

        // Number of algos in use.
        int numberOfAlgos = searchAlgos.size();
        
        DynamicList<DynamicList<Long>> resultTimes = new DynamicList<>();
        DynamicList<DynamicList<Integer>> resultOpened = new DynamicList<>();
        DynamicList<DynamicList<Integer>> resultInvestigated = new DynamicList<>();

        long[] timeSums = new long[numberOfAlgos];
        int[] openedSums = new int[numberOfAlgos];
        DynamicList<Long> fastest = new DynamicList<>();
        DynamicList<Long> slowest = new DynamicList<>();
        DynamicList<Long> most = new DynamicList<>();
        DynamicList<Long> least = new DynamicList<>();
        for (int i = 0; i < numberOfAlgos; i++) {
            resultTimes.add(new DynamicList<>());
            resultOpened.add(new DynamicList<>());
            resultInvestigated.add(new DynamicList<>());
            timeSums[i] = 0l;
        }

        // Run a performance test a number of time for each searchalgo on the list.
        for (int i = 0; i < timesToRun; i++) {
            for (int j = 0; j < numberOfAlgos; j++) {
                resultTimes.get(j).add((Long) runPerformanceTest(searchAlgos.get(j), graph, startX, startY, goalX, goalY));
                int opened = graph.countOpened();
                resultOpened.get(j).add(opened);
                resultInvestigated.get(j).add(opened-searchAlgos.get(j).heapSize());
            }
        }

        // Sum up the result times and opened vertices per algorithm and record fastest and slowest time per algo
        for (int i = 0; i < numberOfAlgos; i++) {
            long fastestTime = Long.MAX_VALUE;
            long slowestTime = -1;
            long leastVert = Long.MAX_VALUE;
            long mostVert = -1;
            for (int j = 0; j < timesToRun; j++) {
                long currentRes = resultTimes.get(i).get(j);
                timeSums[i] += currentRes;
                if (currentRes > slowestTime) {
                    slowestTime = currentRes;
                }
                if (currentRes < fastestTime) {
                    fastestTime = currentRes;
                }
                int currentOpened = resultOpened.get(i).get(j);
                openedSums[i] += currentOpened;
                if (currentOpened > mostVert) {
                    mostVert = currentOpened;
                }
                if (currentOpened < leastVert) {
                    leastVert = currentOpened;
                }
            }
            fastest.add(fastestTime);
            slowest.add(slowestTime);
            most.add(mostVert);
            least.add(leastVert);
        }

        printPerformanceTestResultsToConsole(filename, startX, startY, goalX, goalY, timesToRun, numberOfAlgos, searchAlgos, timeSums, resultTimes, slowest, fastest, resultOpened, resultInvestigated);

    }

    private void printPerformanceTestResultsToConsole(String filename, int startX, int startY, int goalX, int goalY, int timesToRun, int numberOfAlgos, DynamicList<SearchAlgo> searchAlgos, long[] timeSums, DynamicList<DynamicList<Long>> resultTimes, DynamicList<Long> slowest, DynamicList<Long> fastest, DynamicList<DynamicList<Integer>> resultOpened, DynamicList<DynamicList<Integer>> resultInvestigated) {
        // Print performance test results to console
        System.out.println("");
        System.out.println("* Performance test results *");
        System.out.println("Map: " + filename);
        System.out.println("Start point (X,Y): " + startX + ", " + startY);
        System.out.println("Goal point (X,Y): " + goalX + ", " + goalY);
        System.out.println("Runs: " + timesToRun);

        for (int i = 0; i < numberOfAlgos; i++) {
            System.out.println("Algorithm " + (i + 1) + ": " + searchAlgos.get(i).getName());
            System.out.println("  Average running time: " + (double) timeSums[i] / resultTimes.get(i).size() + " ms");
            System.out.println("  Median running time: " + medianLong(resultTimes.get(i)) + " ms");
            System.out.println("  Slowest running time: " + slowest.get(i) + " ms");
            System.out.println("  Fastest running time: " + fastest.get(i) + " ms");
            System.out.println("  Median opened vertices: " + medianInt(resultOpened.get(i)));
            System.out.println("  Median investigated vertices: " + medianInt(resultInvestigated.get(i)));
        }
    }

    private double medianLong(DynamicList<Long> result) {
        if (result.size() % 2 == 0) {
            return ((result.get((int) Math.floor(result.size() / 2)) + result.get((int) Math.ceil(result.size() / 2))) / 2);
        }

        return (result.get((int) Math.floor(result.size() / 2)));
    }
    
    private double medianInt(DynamicList<Integer> result) {
        if (result.size() % 2 == 0) {
            return ((result.get((int) Math.floor(result.size() / 2)) + result.get((int) Math.ceil(result.size() / 2))) / 2);
        }

        return (result.get((int) Math.floor(result.size() / 2)));
    }

    /**
     * Run a single performance test with the given parameters.
     *
     * @param searchAlgo The search algorithm to be used
     * @param graph The graph of the search
     * @param startX Start point x coordinate
     * @param startY Start point y coordinate
     * @param goalX Goal point x coordinate
     * @param goalY Goal point y coordinate
     
     * @return long Milliseconds to perform the shortest path search
     */
    public long runPerformanceTest(SearchAlgo searchAlgo, Graph graph, int startX, int startY, int goalX, int goalY) {
        long timeStart = System.currentTimeMillis();
        searchAlgo.runShortestRouteFind(graph.getVertice(startX, startY), graph.getVertice(goalX, goalY));
        long timeEnd = System.currentTimeMillis();
        return (timeEnd - timeStart);
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
