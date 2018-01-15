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
package ui;

import java.util.Scanner;

/**
 * ConsoleUI is a basic console user interface.
 * @author Mikko Kotola
 */
public class ConsoleUI implements UI {
    Scanner scanner;

    /**
     * Creates new ConsoleUI.
     */
    public ConsoleUI() {
        scanner = new Scanner(System.in);
    }
    
    /**
     * Prints the parameter string to the UI as a line.
     * @param s A line of output
     */
    @Override
    public void print(String s) {
        System.out.println(s);
    }
    
    /**
     * Reads and returns a line from the UI.
     * @return A line of input
     */
    @Override
    public String readLine() {
        return scanner.nextLine();
    }
    
}
