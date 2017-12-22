package domain;

public class Edge implements Comparable {
    public Vertice from;
    public Vertice to;
    public long weight;

    public Edge(Vertice from,Vertice to, long weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Object o) {
        Edge other = (Edge) o;
        if (other.weight < this.weight) {
            return 1;
        } else if (other.weight > this.weight) {
            return -1;
        }
        return 0;
    }
    
    
}
