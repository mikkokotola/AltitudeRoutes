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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mkotola
 */
public class DynamicListTest {
    private DynamicList<Integer> list;
    
    public DynamicListTest() {
        list = new DynamicList<>();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        list.clear();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void isEmptyWorks() {
        assertTrue(list.isEmpty());
    }
    
    @Test
    public void addWithIndexWorks() {
        list.add(0, 36);
        assertTrue(list.get(0) == 36);
    }
    
    @Test
    public void removeWorks() {
        list.add(0, 36);
        list.add(37);
        list.add(38);
        Integer removed = list.remove(0);
        assertTrue(removed == 36);
    }
    
    @Test
    public void listSizeCorrectAfterRemove() {
        list.add(0, 36);
        list.add(37);
        list.add(38);
        list.remove(0);
        assertTrue(list.size() == 2);
    }
    
}
