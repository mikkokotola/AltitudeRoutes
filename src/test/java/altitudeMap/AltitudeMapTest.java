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
package altitudeMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mikko Kotola
 */
public class AltitudeMapTest {
    private AltitudeMap map;
    private double accuracy;
    
    public AltitudeMapTest() {
        this.accuracy = 0.00001;
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        int ncols = 3;
        int nrows = 3;
        double xllcorner = 0;
        double yllcorner = 0;
        double cellsize = 2.0;
        double NODATA_value = -9999.000;
        double[][] altitudes = new double[4][4];
        altitudes[1][1] = 123.055;
        altitudes[1][2] = 123.060;
        altitudes[1][3] = 123.070;
        altitudes[2][1] = 128.055;
        altitudes[2][2] = 128.888;
        altitudes[2][3] = 128.900;
        altitudes[3][1] = 123.055;
        altitudes[3][2] = 122.200;
        altitudes[3][3] = 120.300;
        map = new AltitudeMap("testmap.asc", ncols, nrows, xllcorner, yllcorner, cellsize, NODATA_value, altitudes);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void filenameCorrect() {
        assertTrue(map.getFilename().equals("testmap.asc"));
    }

    @Test
    public void ncolsCorrect() {
        assertTrue(map.getNcols() == 3);
    }
    
    @Test
    public void nrowsCorrect() {
        assertTrue(map.getNrows() == 3);
    }
    
    @Test
    public void xllcornerCorrect() {
        assertTrue(map.getXllcorner() == 0);
    }
    
    @Test
    public void yllcornerCorrect() {
        assertTrue(map.getYllcorner() == 0);
    }
    
    @Test
    public void cellSizeCorrect() {
        assertTrue(map.getCellsize() == 2.0);
    }
    
    @Test
    public void nodataValueCorrect() {
        assertTrue(map.getNODATA_value() - (-9999.000) < accuracy);
    }
    
    @Test
    public void altitudesCorrectAfterSet() {
        double[][] newAltitudes = new double[4][4];
        newAltitudes[1][1] = 100.000;
        newAltitudes[1][2] = 100.000;
        newAltitudes[1][3] = 100.000;
        newAltitudes[2][1] = 100.000;
        newAltitudes[2][2] = 100.000;
        newAltitudes[2][3] = 100.000;
        newAltitudes[3][1] = 100.000;
        newAltitudes[3][2] = 200.000;
        newAltitudes[3][3] = 300.000;
        map.setAltitudes(newAltitudes);
        assertTrue(map.getAltitudes()[3][3] - 300.000 < accuracy);
    }
    
    @Test
    public void singleAltitudeCorrectAfterSet() {
        map.setAltitude(3, 2, 250.000);
        assertTrue(map.getAltitude(3, 2) - 250.000 < accuracy);
    }
    
    @Test
    public void getXbyCoordinatesCorrect() {
        assertTrue(map.getXbyCoordinates(4.500) == 2);
    }
    
    @Test
    public void getYbyCoordinatesCorrect() {
        assertTrue(map.getYbyCoordinates(2.500) == 2);
    }
    
    
}
