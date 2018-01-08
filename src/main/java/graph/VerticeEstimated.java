package graph;

/** VerticeEstimated belongs to a graph. It extends a simple vertice by including
 * distToGoal, which is an estimate of the distance to the goal used by
 * advanced pathfinding algorithms. It overrides the compareTo-method and
 * compares vertices by the estimated total distance (distToStart + distToGoal).
 * 
 * The graph edges are represented as attributes of 
 * vertices (a list of edges starting at the vertice). The x and y coordinates
 * are in the attributes. The altitude of the vertice is saved as the z
 * coordinate. distToStart, distToGoal and path are used by pathfinding algorithms.
 * @author Mikko Kotola
 */

public class VerticeEstimated extends Vertice {

    private double distToGoal;

    public VerticeEstimated(int x, int y, double z) {
        super(x, y, z);
        this.distToGoal = 0;
    }

    @Override
    public double getDistToGoal() {
        return distToGoal;
    }

    @Override
    public void setDistToGoal(double distToGoal) {
        this.distToGoal = distToGoal;
    }

    @Override
    public double getKey() {
        if (this.distToGoal == Double.MAX_VALUE || this.getDistToStart() == Double.MAX_VALUE) {
            return Double.MAX_VALUE;
        }
        return this.getDistToStart() + this.distToGoal;
    }
    
    @Override
    public int compareTo(Object o) {
        VerticeEstimated other = (VerticeEstimated) o;
        double thisEstimatedDistance = this.getDistToStart() + this.distToGoal;
        double otherEstimatedDistance = other.getDistToStart() + other.distToGoal;
            
        if (otherEstimatedDistance  > thisEstimatedDistance) {
            return -1;
        } else if (otherEstimatedDistance  < thisEstimatedDistance) {
            return 1;
        }
        return 0;
    }

}
