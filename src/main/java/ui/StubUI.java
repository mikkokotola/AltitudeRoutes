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

import dataStructures.DynamicList;

/**
 *  A stub UI for testing.
 * @author Mikko Kotola
 */
public class StubUI implements UI {
    private DynamicList<String> in;
    private DynamicList<String> out;
    int outLine;

    /**
     * Creates a new stub UI with the parameter list on string lines as output.
     * @param out List of string lines as output
     */
    public StubUI(DynamicList<String> out) {
        this.in = new DynamicList<>();
        this.out = out;
        outLine = -1;
    }

    /**
     * Creates a new stub UI with empty lists of input and output.
     */
    public StubUI() {
        this.in = new DynamicList<>();
        this.out = new DynamicList<>();
        outLine = -1;
    }
    
    /**
     * Prints out a string to the input list.
     * @param s The input string
     */
    @Override
    public void print(String s) {
        in.add(s);
    }

    /**
     * Returns the next line of output from the preset output string list.
     * @return The output string
     */
    @Override
    public String readLine() {
        outLine++;
        return out.get(outLine);
    }
    
    /**
     * Adds a new string to the end of the output list.
     * @param output
     */
    public void addOutput(String output) {
        out.add(output);
    }
    
    /**
     * Clears the input and output lists.
     */
    public void clear() {
        in.clear();
        out.clear();
    }

    /**
     * Returns the list of input strings.
     * @return The list of input strings as a DynamicList
     */
    public DynamicList<String> getIn() {
        return in;
    }

    /**
     * Sets the list of input strings
     * @param in The list of input strings as a DynamicList
     */
    public void setIn(DynamicList<String> in) {
        this.in = in;
    }

    /**
     * Returns the list of output strings.
     * @return The list of output strings as a DynamicList
     */
    public DynamicList<String> getOut() {
        return out;
    }

    /**
     * Sets the list of output strings.
     * @param out The list of output strings as a DynamicList
     */
    public void setOut(DynamicList<String> out) {
        this.out = out;
    }

    /**
     * Returns the current output line number (the last line of output to have 
     * been sent).
     * @return The current output line
     */
    public int getOutLine() {
        return outLine;
    }

    /**
     * Sets the current output line number (the last line of output to have been
     * sent).
     * @param outLine The current output line
     */
    public void setOutLine(int outLine) {
        this.outLine = outLine;
    }
    
}
