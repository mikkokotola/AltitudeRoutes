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
    private int heapRef;

    private double distToStart;
    private Vertice path;

    private Edge[] edges;
    private int numberOfEdges;
    
    public Vertice(int x, int y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = (x * 3000) + y;
        this.heapRef = -1;

        this.distToStart = Double.MAX_VALUE;
        this.path = null;

        this.edges = new Edge[4];
        this.numberOfEdges = 0;
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

    public int getHeapRef() {
        return heapRef;
    }

    public void setHeapRef(int heapRef) {
        this.heapRef = heapRef;
    }

    public double getDistToStart() {
        return distToStart;
    }

    public void setDistToStart(double distToStart) {
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
     * Adds an edge to the vertice's edgelist. The method does not check if 
     * the edge is already on the edgelist so doubles are possible.
     */
    public void addEdge(Edge edge) {
        edges[numberOfEdges] = edge;
        numberOfEdges++;
    }

    public double getKey() {
        return this.distToStart;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertice other = (Vertice) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
