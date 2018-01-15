/*
 * The MIT License
 *
 * Copyright 2018 .
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
package dataStructures;

import graph.Vertice;

/**
 *  MinHeapVertice is a class used by the MinHeap for storing vertices.
 * @author Mikko Kotola
 */
class MinHeapVertice {
    private double key;
    private Vertice vertice;

    /**
     * Creates a new MinHeapVertice with the given key and Vertice reference.
     *
     * @param key The key used for the heap prioritization
     * @param vertice The vertice reference
     */

    public MinHeapVertice(double key, Vertice vertice) {
        this.key = key;
        this.vertice = vertice;
    }

    /**
     * Return the key.
     *
     * @return double The key used for the heap prioritization
     */
    public double getKey() {
        return key;
    }

    /**
     * Sets a new key.
     *
     * @param key The key used for the heap prioritization
     */
    public void setKey(double key) {
        this.key = key;
    }

    /**
     * Return the vertice reference.
     *
     * @return Vertice The vertice
     */
    public Vertice getVertice() {
        return vertice;
    }

    /**
     * Sets a new vertice reference.
     *
     * @param vertice The vertice
     */
    public void setVertice(Vertice vertice) {
        this.vertice = vertice;
    }
}
