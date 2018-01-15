/*
 * The MIT License
 *
 * Copyright 2017 .
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package graph;

import java.util.Objects;

/**
 * Edge belongs to a graph. An edge connects two vertices and has a weight
 * attribute.
 * @author Mikko Kotola
 */
public class Edge {
    private Vertice from;
    private Vertice to;
    private double weight;
    private long id;

    /**
     * Creates a new edge with the given to and from vertices and weight.
     *
     * @param from The from vertice
     * @param to The to vertice
     * @param weight The weight of the edge
     */
    public Edge(Vertice from,Vertice to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.id = (from.getId()*9000000)+to.getId();
    }

    /**
     * Returns the from vertice.
     * @return The from vertice
     */
    public Vertice getFrom() {
        return from;
    }

    /**
     * Returns the to vertice.
     * @return The to vertice
     */
    public Vertice getTo() {
        return to;
    }

    /**
     * Returns the id of the edge.
     * @return The id of the edge
     */
    public long getId() {
        return id;
    }
    
    /**
     * Returns the weight of the edge.
     * @return The weight of the edge
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the edge.
     * @param weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

}
