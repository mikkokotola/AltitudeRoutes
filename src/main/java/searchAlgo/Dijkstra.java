package searchAlgo;

import dataStructures.DynamicList;
import dataStructures.MinHeap;
import graph.Edge;
import graph.Graph;
import graph.Vertice;


public class Dijkstra implements SearchAlgo {

    String name;
    Graph graph;
    //private PriorityQueue<Vertice> heap;
    MinHeap heap;
    boolean[][] takenOut;

    Vertice start;
    Vertice goal;

    public Dijkstra(Graph graph) {
        name = "Dijkstra";
        this.graph = graph;
        //this.heap = new PriorityQueue();
        this.heap = new MinHeap((int) (graph.getMap().getNcols() * graph.getMap().getNrows() * 1.5));
        this.takenOut = new boolean[graph.getVertices().length][graph.getVertices()[0].length];
    }

    @Override
    public String getName() {
        return name;
    }

    public void initialiseSingleSource(Vertice vertice) {
        vertice.setDistToStart(0);
    }

    public void relax(Edge edge) {
        Vertice from = edge.getFrom();
        Vertice to = edge.getTo();
        double weight = edge.getWeight();

        if (to.getDistToStart() > from.getDistToStart() + weight) {
            to.setDistToStart(from.getDistToStart() + weight);
            to.setPath(from);
        }
    }

    @Override
    public void runShortestRouteFind(Vertice start, Vertice goal) {
        this.start = start;
        this.goal = goal;

        initialiseSingleSource(start);
        for (int i = 1; i < graph.getVertices().length; i++) {
            for (int j = 1; j < graph.getVertices()[0].length; j++) {
                heap.insert(graph.getVertices()[i][j]);
            }
        }

        while (heap.size() > 0) {
            Vertice vert = (Vertice) heap.poll();
            if (takenOut[vert.getY()][vert.getX()]) {
                continue;
            }
            takenOut[vert.getY()][vert.getX()] = true;
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
                // Stop when goal vertice found and the edge leading to it
                // relaxed for the first time.
                if (to.getId() == goal.getId()) {
                    return;
                }
            }
        }
    }

    @Override
    public double returnLengthOfShortestRoute() {
        if (goal.getDistToStart() == Double.MAX_VALUE) {
            return -1;
        }
        return goal.getDistToStart();
    }

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

}
