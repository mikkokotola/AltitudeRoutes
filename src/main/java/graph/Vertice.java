package graph;

import java.util.HashMap;
import java.util.Objects;

/**
 * Vertice belongs to a graph. The graph edges are represented as attributes of
 * vertices (a list of edges starting at the vertice). The x and y coordinates
 * are in the attributes. The altitude of the vertice is saved as the z
 * coordinate. distToStart, distToGoal and path are used by pathfinding 
 * algorithms.
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
    private double distToGoal;
    private Vertice path;

    private Edge[] edges;
    private int numberOfEdges;
    
    /**
     * Creates and initiates a new vertice.
     * @param x The x coordinate
     * @param y The y coordinate
     * @param z The z coordinate = altitude in meters
     */
    public Vertice(int x, int y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = (x * 3000) + y;
        this.heapRef = -1;

        this.distToStart = Double.MAX_VALUE;
        this.distToGoal = 0;
        this.path = null;

        this.edges = new Edge[4];
        this.numberOfEdges = 0;
    }

    /**
     * Returns the x coordinate.
     * @return The x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y coordinate.
     * @return The y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the z coordinate = altitude in meters.
     * @return The z coordinate = altitude in meters
     */
    public double getZ() {
        return z;
    }

    /**
     * Sets the z coordinate = altitude in meters.
     * @param z The z coordinate = altitude in meters
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Returns the id of the vertice. The id is calculated using x and y coordinates.
     * @return The id of the vertice
     */
    public long getId() {
        return id;
    }

    /**
     * Returns the heap reference value of the vertice.
     * @return The heap reference value
     */
    public int getHeapRef() {
        return heapRef;
    }

    /**
     * Sets the heap reference value of the vertice.
     * @param heapRef The heap reference value of the vertice.
     */
    public void setHeapRef(int heapRef) {
        this.heapRef = heapRef;
    }

    /**
     * Returns the distance to start of the vertice.
     * @return The distance to start of the vertice
     */
    public double getDistToStart() {
        return distToStart;
    }

    /**
     * Sets the distance to start of the vertice.
     * @param distToStart The distance to start of the vertice
     */
    public void setDistToStart(double distToStart) {
        this.distToStart = distToStart;
    }

    /**
     * Returns the previous vertice on the shortest path from the start vertice
     * to this vertice.
     * @return The previous vertice on the shortest path from the start vertice
     */
    public Vertice getPath() {
        return path;
    }

    /**
     * Sets the previous vertice on the shortest path from the start vertice
     * to this vertice.
     * @param path The previous vertice on the shortest path from the start vertice
     */
    public void setPath(Vertice path) {
        this.path = path;
    }

    /**
     * Returns the array of edges departing from this vertice.
     * @return The egde array
     */
    public Edge[] getEdges() {
        return edges;
    }

    /**
     * Sets the array of edges departing from this vertice.
     * @param edges The edge array
     */
    public void setEdges(Edge[] edges) {
        this.edges = edges;
    }

    /**
     * Returns the number of edges departing from this vertice.
     * @return The number of edges departing from this vertice.
     */
    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    /**
     * Sets the number of edges departing from this vertice.
     * @param numberOfEdges The number of edges departing from this vertice.
     */
    public void setNumberOfEdges(int numberOfEdges) {
        this.numberOfEdges = numberOfEdges;
    }

    /**
     * Adds an edge to the vertice's edgelist. The method does not check if 
     * the edge is already on the edgelist so doubles are possible.
     * 
     * @param edge The edge to be added 
     */
    public void addEdge(Edge edge) {
        edges[numberOfEdges] = edge;
        numberOfEdges++;
    }

    /**
     * Calculates and return the key of this vertice. The key is calculated as
     * the sum of distance to start and distance to goal.
     * @return The key
     */
    public double getKey() {
        return this.distToStart + this.distToGoal;
    }
    
    /**
     * Returns the distance to goal of this vertice. Not used for search algos 
     * that do not estimate distance to goal.
     * 
     * @return double The distance to goal of the vertice
     */
    public double getDistToGoal() {
        return distToGoal;
    }

     /**
     * Sets the distance to goal of this vertice. Not used for search algos 
     * that do not estimate distance to goal.
     * 
     * @param distToGoal The distance to goal to be set for the vertice
     */
    public void setDistToGoal(double distToGoal) {
        this.distToGoal = distToGoal;
    }

    
    /**
     * Returns -1 if this vertices key is smaller than other vertices. 1 if other
     * vertice's key is smaller. 0 is key are equal.
     * 
     * @param o The object to be compared to
     * @return The comparison result
     */
    @Override
    public int compareTo(Object o) {
        Vertice other = (Vertice) o;

        double thisEstimatedDistance = this.getDistToStart() + this.distToGoal;
        double otherEstimatedDistance = other.getDistToStart() + other.distToGoal;
            
        if (otherEstimatedDistance  > thisEstimatedDistance) {
            return -1;
        } else if (otherEstimatedDistance  < thisEstimatedDistance) {
            return 1;
        }
        return 0;
    }

    /**
     * Returns true if the vertice ids are the same. This is the case if x and y
     * coordinates are the same.
     * 
     * @param obj The object to be compared to
     * @return True if equal, false otherwise
     */
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
