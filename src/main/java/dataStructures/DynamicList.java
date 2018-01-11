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

/**
 * DynamicList is a generic dynamic list for objects. It follows ArrayList's
 * behaviour and is implemented as an array.
 *
 * @author Mikko Kotola
 */
public class DynamicList<E> {

    private int size;
    private Object[] elements;

    /**
     * Creates a new DynamicList and initiates the array.
     */
    public DynamicList() {
        elements = new Object[10];
        size = 0;
    }
    
    
    /**
     * Adds and element to the end of the list. List size grows by one.
     * 
     * @param e Element to be appended to this list.
     */
    public void add(E e) {
        if (size == elements.length) {
            grow();
        }
        elements[size] = e;
        size++;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any 
     * subsequent elements to the right (adds one to their indices). 
     * List size grows by one.
     * 
     */
    public void add(int index, E e) {
        if (size == elements.length) {
            grow();
        }
        
        size++;
        for (int i = index; i < size-1; i++) {
            elements[i+1] = elements[i];
        }
        elements[index] = e;
    }
       
    /**
     * Returns but does not remove the element at the given index. Throws an
     * IndexOutOfBoundsException if the index is not valid.
     *  
     * @param i Index of the element to be returned.
     * @return E The object found at the parameter index.
     */
    public E get(int i) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index: " + i + ", list minimum index 0, maximum index " + (size - 1));
        }
        return (E) elements[i];
    }

    /**
     * Removes the element at the given index. Shifts any subsequent elements 
     * to the left (subtracts one from their indices).
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", list minimum index 0, maximum index " + (size - 1));
        }
        E element = (E) elements[index];
        for (int i = index; i <= size-2; i++) {
            elements[i] = elements[i+1];
        }
        size--;
        if (size < elements.length/3) {
            reduceSize();
        }
        return element;
    }

    /**
     * Clears the list.
     */
    public void clear() {
        elements = new Object[10];
        size = 0;
    }

    /**
     * Returns the number of elements on the list.
     * @return int Returns the number of elements on the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns the length of the backing array.
     * @return int Returns the length of the backing array
     */
    int arrayLength() {
        return this.elements.length;
    }
    
    /**
     * Returns the number of elements on the list.
     * @return boolean Returns true if the size of the list is zero.
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }    
    
    private void grow() {
        Object[] newElements = new Object[elements.length * 2];
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private void reduceSize() {
        Object[] newElements = new Object[elements.length/2];
        for (int i = 0; i < size-1; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

}
