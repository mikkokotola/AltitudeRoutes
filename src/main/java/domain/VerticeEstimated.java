package domain;

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

    private long distToGoal;

    public VerticeEstimated(int x, int y, double z) {
        super(x, y, z);
        this.distToGoal = Long.MAX_VALUE;
    }

    public long getDistToGoal() {
        return distToGoal;
    }

    public void setDistToGoal(long distToGoal) {
        this.distToGoal = distToGoal;
    }
    
    @Override
    public int compareTo(Object o) {
        VerticeEstimated other = (VerticeEstimated) o;
        long thisEstimatedDistance = this.getDistToStart() + this.distToGoal;
        long otherEstimatedDistance = other.getDistToStart() + other.distToGoal;
            
        if (otherEstimatedDistance  > thisEstimatedDistance) {
            return -1;
        } else if (otherEstimatedDistance  < thisEstimatedDistance) {
            return 1;
        }
        return 0;
    }

}
