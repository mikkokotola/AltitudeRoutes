package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Vertice belongs to a graph. The graph edges are represented as attributes of
 * vertices (a list of edges starting at the vertice). The x and y coordinates
 * are in the attributes. The altitude of the vertice is saved as the z
 * coordinate. distToStart and path are used by pathfinding algorithms.
 *
 * @author Mikko Kotola
 */
public class Vertice implements Comparable {

    private int x;
    private int y;
    private double z;
    private long id;

    private long distToStart;
    private Vertice path;

    private Edge[] edges;
    private int numberOfEdges;
    
    Vertice(int x, int y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = (x * 3000) + y;

        this.distToStart = Long.MAX_VALUE;
        this.path = null;

        this.edges = new Edge[4];
        this.numberOfEdges = 0;
        
        //this.edges = new HashMap<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public long getId() {
        return id;
    }

    public long getDistToStart() {
        return distToStart;
    }

    public void setDistToStart(long distToStart) {
        this.distToStart = distToStart;
    }

    public Vertice getPath() {
        return path;
    }

    public void setPath(Vertice path) {
        this.path = path;
    }

    public Edge[] getEdges() {
        return edges;
    }

    public void setEdges(Edge[] edges) {
        this.edges = edges;
    }

    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    public void setNumberOfEdges(int numberOfEdges) {
        this.numberOfEdges = numberOfEdges;
    }

    /**
     * Adds an edge to the vertice's edgelist. Returns true if addition is
     * successful. The method does not check if the edge is already on the
     * edgelist so doubles are possible.
     */
    public void addEdge(Edge edge) {
        edges[numberOfEdges] = edge;
        numberOfEdges++;
    }

    @Override
    public int compareTo(Object o) {
        Vertice other = (Vertice) o;

        if (other.distToStart > this.distToStart) {
            return -1;
        } else if (other.distToStart < this.distToStart) {
            return 1;
        }
        return 0;
    }

}
