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
 * MinHeap is a minimum heap for vertices. It supports the normal heap
 * operations and a change-priority-operation, which changes the vertice's
 * (identified by its key) priority in the heap.
 *
 * @author Mikko Kotola
 */
public class MinHeap {

    private MinHeapVertice[] array;
    private int size;

    /**
     * Creates a new MinHeap with initial backing array size given as parameter.
     *
     * @param initialSize Initial size of heap array.
     */
    public MinHeap(int initialSize) {
        this.array = new MinHeapVertice[initialSize];
        this.size = 0;
    }

    /**
     * Creates a new MinHeap with initial backing array size 10.
     */
    public MinHeap() {
        this.array = new MinHeapVertice[10];
        this.size = 0;
    }

    /**
     * Resets the heap to size 0 and an empty backing array size 10.
     */
    public void reset() {
        this.array = new MinHeapVertice[10];
        this.size = 0;
    }

    /**
     * Inserts the specified element into this priority queue.
     *
     * @param vertice The vertice to be inserted
     */
    public void insert(Vertice vertice) {
        double key = vertice.getKey();
        MinHeapVertice heapVert = new MinHeapVertice(key, vertice);
        if (size == array.length - 1) {
            growArraySize();
        }
        size++;
        int i = size;
        while (i > 1 && array[parent(i)].getKey() > key) {
            array[i] = array[parent(i)];
            array[i].getVertice().setHeapRef(i);
            i = parent(i);
        }
        array[i] = heapVert;
        array[i].getVertice().setHeapRef(i);
    }

    private int parent(int i) {
        return (int) Math.floor(i / 2);
    }

    private int left(int i) {
        return 2 * i;
    }

    private int right(int i) {
        return 2 * i + 1;
    }

    private void heapify(int i) {
        int l = left(i);
        int r = right(i);
        int smallest;

        if (r <= size) {
            if (array[l].getKey() < array[r].getKey()) {
                smallest = l;
            } else {
                smallest = r;
            }
            if (array[i].getKey() > array[smallest].getKey()) {
                exchangeHeapVertices(i, smallest);
                heapify(smallest);
            }

        } else if (l == size && array[i].getKey() > array[l].getKey()) {
            exchangeHeapVertices(i, l);
        }

    }

    private void exchangeHeapVertices(int a, int b) {
        MinHeapVertice temp = array[a];
        array[a] = array[b];
        array[a].getVertice().setHeapRef(a);
        array[b] = temp;
        array[b].getVertice().setHeapRef(b);
    }

    /**
     * Retrieves, but does not remove, the top of this heap, or returns null if
     * the heap is empty.
     *
     * @return The vertice with the lowest key in the heap. Null is heap is
     * empty.
     */
    public Vertice peek() {
        return array[1].getVertice();
    }

    /**
     * Retrieves and removes the top of this heap, or returns null if the heap
     * is empty.
     *
     * @return The vertice with the lowest key in the heap. Null is heap is
     * empty.
     */
    public Vertice poll() {
        MinHeapVertice top = array[1];
        array[1] = array[size];
        size--;
        if (size < array.length / 2.5) {
            reduceArraySize();
        }
        heapify(1);
        top.getVertice().setHeapRef(-2);
        return top.getVertice();
    }

    /**
     * Increases the key of the given vertice. Does nothing if the new key is
     * lower than the old key.
     *
     * @param vertice The vertice that needs updating
     * @param newKey The new key.
     * @return True if vertice found, false if not.
     */
    public boolean increaseKey(Vertice vertice, double newKey) {
        int i = vertice.getHeapRef();
        if (i == -1) {
            return false;
        }

        if (newKey > array[i].getKey()) {
            array[i].setKey(newKey);
            heapify(i);
        }
        return true;
    }

    /**
     * Decreases the key of the given vertice. Does nothing if the new key is
     * higher than the old key.
     *
     * @param vertice The vertice that needs updating
     * @param newKey The new key.
     * @return True if vertice found, false if not.
     */
    public boolean decreaseKey(Vertice vertice, double newKey) {
        int i = vertice.getHeapRef();
        if (i == -1 || i == -2) {
            return false;
        }

        if (newKey < array[i].getKey()) {
            array[i].setKey(newKey);
            while (i > 1 && array[parent(i)].getKey() > array[i].getKey()) {
                exchangeHeapVertices(i, parent(i));
                i = parent(i);
            }
        }
        return true;
    }

    /**
     * Returns the number of vertices in the heap.
     *
     * @return The number of vertices in the heap.
     */
    public int size() {
        return size;
    }

    private void growArraySize() {
        MinHeapVertice[] newArray = new MinHeapVertice[size * 2];
        for (int i = 1; i <= size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    private void reduceArraySize() {
        if (size > 10) {
            MinHeapVertice[] newArray = new MinHeapVertice[array.length / 2];
            for (int i = 1; i <= size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        } 
//        else {
//            array = new MinHeapVertice[1];
//        }

    }

}
