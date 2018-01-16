package searchAlgo;

import dataStructures.DynamicList;
import dataStructures.MinHeap;
import graph.Edge;
import graph.Graph;
import graph.Vertice;

/**
 * Dijkstra's algorithm for shortest route finding. Uses a custom-made MinHeap
 * as a support data structure.
 *
 * @author Mikko Kotola
 */
public class Dijkstra implements SearchAlgo {

    String name;
    Graph graph;
    MinHeap heap;

    Vertice start;
    Vertice goal;

    /**
     * Initialise search algorithm with a graph.
     *
     * @param graph The graph to be searched
     */
    public Dijkstra(Graph graph) {
        name = "Dijkstra";
        this.graph = graph;
        this.heap = new MinHeap();
    }

    @Override
    public String getName() {
        return name;
    }

    void initialiseSingleSource(Vertice vertice) {
        graph.resetGraph();
        vertice.setDistToStart(0);
        heap.reset();
    }

    void relax(Edge edge) {
        Vertice from = edge.getFrom();
        Vertice to = edge.getTo();
        double weight = edge.getWeight();

        if (to.getDistToStart() > from.getDistToStart() + weight) {
            to.setDistToStart(from.getDistToStart() + weight);
            to.setPath(from);
        }
    }

    /**
     * Execute shortest route search from start vertice to goal vertice.
     *
     * @param start The start Vertice
     * @param goal The goal Vertice
     */
    @Override
    public void runShortestRouteFind(Vertice start, Vertice goal) {
        this.start = start;
        this.goal = goal;

        initialiseSingleSource(start);
        heap.insert(start);

        while (heap.size() > 0) {
            Vertice vert = (Vertice) heap.poll();
            // Stop when goal vertice removed from the heap. Goal distance is final.
            if (vert.getId() == goal.getId()) {
                return;
            }
            Edge[] edges = vert.getEdges();
            for (int i = 0; i < vert.getNumberOfEdges(); i++) {
                double oldDistance = edges[i].getTo().getDistToStart();
                relax(edges[i]);
                Vertice to = edges[i].getTo();
                if (to.getDistToStart() < oldDistance) {
                    if (to.getHeapRef() == -1) {
                        heap.insert(to);
                    } else {
                        heap.decreaseKey(to, to.getKey());
                    }

                }
            }
        }
    }

    /**
     * Returns the length of the shortest path from an executed search.
     *
     * @return double The length of the shortest path from start to goal
     */
    @Override
    public double returnLengthOfShortestRoute() {
        if (goal.getDistToStart() == Double.MAX_VALUE) {
            return -1;
        }
        return goal.getDistToStart();
    }

    /**
     * Returns the shortest path of an executed search as a DynamicList of
     * Vertices.
     *
     * @return DynamicList A DynamicList with shortest path vertices from start
     * to goal.
     */
    @Override
    public DynamicList<Vertice> returnShortestPath() {
        DynamicList<Vertice> route = new DynamicList<>();
        DynamicList<Vertice> routeToReturn = new DynamicList<>();

        if (goal.getPath() != null) {
            route.add(goal);
            Vertice x = goal.getPath();
            while (x.getId() != start.getId()) {
                route.add(x);
                x = x.getPath();
            }
            route.add(start);

            for (int i = route.size() - 1; i >= 0; i--) {
                routeToReturn.add(route.get(i));
            }
        }

        return routeToReturn;
    }

    /**
     * Returns the current size of the heap associated with the search.
     *
     * @return int Heap size
     */
    @Override
    public int heapSize() {
        return heap.size();
    }

    /**
     * Returns the graph this search is conducted on.
     *
     * @return The graph this search is conducted on
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * Returns the start vertice of this search.
     *
     * @return Start vertice of this search
     */
    public Vertice getStart() {
        return start;
    }

    /**
     * Returns the goal vertice of this search.
     *
     * @return Goal vertice of this search
     */
    public Vertice getGoal() {
        return goal;
    }
}
