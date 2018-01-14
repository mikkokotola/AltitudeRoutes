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
package controller;

import dataStructures.DynamicList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ui.StubUI;
import ui.UI;

/**
 *
 * @author Mikko Kotola
 */
public class AppTest {
    private App app;
    private StubUI stubUI;
       
    public AppTest() {
        DynamicList<String> list = new DynamicList<>();
        StubUI stubUI = new StubUI(list);
        this.stubUI = stubUI;
        this.app = new App(stubUI);
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        stubUI.clear();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void DijkstraTest() {
        stubUI.addOutput("testMap3");
        stubUI.addOutput("D");
        stubUI.addOutput("M");
        stubUI.addOutput("15");
        stubUI.addOutput("13");
        stubUI.addOutput("15");
        stubUI.addOutput("17");
        stubUI.addOutput("n");
        stubUI.addOutput("n");
        stubUI.addOutput("");
        app.run();
        assertTrue(stubUI.getIn().get(42).equals("Length of shortest path: 10.0"));
    }
    
    @Test
    public void AstarTest() {
        stubUI.addOutput("testMap3");
        stubUI.addOutput("A");
        stubUI.addOutput("M");
        stubUI.addOutput("15");
        stubUI.addOutput("13");
        stubUI.addOutput("15");
        stubUI.addOutput("17");
        stubUI.addOutput("n");
        stubUI.addOutput("n");
        stubUI.addOutput("");
        app.run();
        assertTrue(stubUI.getIn().get(42).equals("Length of shortest path: 10.0"));
    }
    
}
