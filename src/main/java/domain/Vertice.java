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

    private HashMap<Long, Edge> edges;

    Vertice(int x, int y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = (x * 3000) + y;

        this.distToStart = Long.MAX_VALUE;
        this.path = null;

        this.edges = new HashMap<>();
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

    public HashMap<Long, Edge> getEdges() {
        return edges;
    }

    public void setEdges(HashMap<Long, Edge> edges) {
        this.edges = edges;
    }

    public void addEdge(Edge edge) {
        this.edges.put(edge.getId(), edge);
    }

    public void removeEdge(Long edgeId) {
        this.edges.remove(edgeId);
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
