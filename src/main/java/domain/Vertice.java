package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class Vertice implements Comparable{
    int id;
    int x;
    int y;
    double z;
    
    long distance;
    Vertice path;
    
    HashMap<Integer, Edge> edges;
    int parent;
    ArrayList<Vertice> children;

    Vertice(int id, int x, int y, double z) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        
        this.distance = Long.MAX_VALUE;
        this.path = null;
        
        this.edges = new HashMap<>();
        this.parent = id;
        this.children = new ArrayList<>();
    }
    
    
    @Override
    public int compareTo(Object o) {
        Vertice other = (Vertice) o;
        
        if (other.distance > this.distance) {
            return -1;
        } else if (other.distance < this.distance) {
            return 1;
        }
        return 0;
    }
    
    
}
