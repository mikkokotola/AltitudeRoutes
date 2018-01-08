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

    public StubUI(DynamicList<String> out) {
        this.in = new DynamicList<>();
        this.out = out;
        outLine = -1;
    }
    public StubUI() {
        this.in = new DynamicList<>();
        this.out = new DynamicList<>();
        outLine = -1;
    }
    
    
    @Override
    public void print(String s) {
        in.add(s);
    }

    @Override
    public String readLine() {
        outLine++;
        return out.get(outLine);
    }
    
    public void addOutput(String output) {
        out.add(output);
    }
    
    public void clear() {
        in.clear();
        out.clear();
    }

    public DynamicList<String> getIn() {
        return in;
    }

    public void setIn(DynamicList<String> in) {
        this.in = in;
    }

    public DynamicList<String> getOut() {
        return out;
    }

    public void setOut(DynamicList<String> out) {
        this.out = out;
    }

    public int getOutLine() {
        return outLine;
    }

    public void setOutLine(int outLine) {
        this.outLine = outLine;
    }
    
    
    
}
