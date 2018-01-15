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

import dataStructures.DynamicList;
import graph.Vertice;
import java.util.ArrayList;

/**
 * Interface of search algorithms.
 * @author Mikko Kotola
 */
public interface SearchAlgo {

    /**
     * Returns the length of the shortest route. Returns -1 if the route does 
     * not exist.
     * 
     * @return double The length of the shortest route, -1 if no route exists
     */
    double returnLengthOfShortestRoute();

    /**
     * Returns the shortest path as list Vertices starting from the start
     * vertice and ending at the goal vertice.
     * 
     * @return DynamicList The shortest path vertices as a DynamicList
     */
    DynamicList<Vertice> returnShortestPath();

    /**
     * Runs the find sequence to search the graph for a shortest route from
     * vertice start to vertice goal.
     * 
     * @param start The start vertice
     * @param goal The goal vertice
     */
    void runShortestRouteFind(Vertice start, Vertice goal);

    /**
     * Returns the search algorithm name as a string.
     * 
     * @return String The name of the search algorithm
     */
    String getName();
    
    /**
     * Returns the current heap size of the search algorithm.
     * 
     * @return int Current size of heap of search algorithm
     */
    int heapSize();

}
