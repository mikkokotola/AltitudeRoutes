package domain;

public class VerticeEstimated extends Vertice {

    long estimate;

    public VerticeEstimated(int id, int x, int y, double z) {
        super(id, x, y, z);
        this.estimate = Long.MAX_VALUE;

    }

    @Override
    public int compareTo(Object o) {
        VerticeEstimated other = (VerticeEstimated) o;
        long thisEstimatedDistance = this.distance + other.distance;
        long otherEstimatedDistance = other.distance + other.estimate;
            
        if (otherEstimatedDistance  > thisEstimatedDistance) {
            return -1;
        } else if (otherEstimatedDistance  < thisEstimatedDistance) {
            return 1;
        }
        return 0;
    }

}
