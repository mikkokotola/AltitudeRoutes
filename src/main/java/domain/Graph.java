package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;



public class Graph {

    HashMap<Integer, Vertice> vertices;
    
    public Graph() {
        this.vertices = new HashMap<>();
    }


    private void formNeighborLists(int[] from, int[] to, long[] weight) {
        // The neighborlists (egdes starting a a vertice) are saved in the vertices.

    }

}
