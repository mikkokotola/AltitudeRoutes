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
package altitudeMap;

/** AltitudeMap. The class contains a grid map with altitude information. The 
 * altitudes are saved in the double 2d-array altitudes[y][x] with y signifying 
 * the row and x signifying the column. All other attributes are metadata. 
 * @author Mikko Kotola
 */
public class AltitudeMap {
    private String filename;
    private int ncols;
    private int nrows;
    private double xllcorner;
    private double yllcorner;
    private double cellsize;
    private double NODATA_value;
    private double[][] altitudes;

    public AltitudeMap(String filename, int ncols, int nrows, double xllcorner, double yllcorner, double cellsize, double NODATA_value, double[][] altitudes) {
        this.filename = filename;
        this.ncols = ncols;
        this.nrows = nrows;
        this.xllcorner = xllcorner;
        this.yllcorner = yllcorner;
        this.cellsize = cellsize;
        this.NODATA_value = NODATA_value;
        this.altitudes = altitudes;
    }

    public String getFilename() {
        return filename;
    }

    
    public int getNcols() {
        return ncols;
    }

    public int getNrows() {
        return nrows;
    }

    public double getXllcorner() {
        return xllcorner;
    }

    public double getYllcorner() {
        return yllcorner;
    }

    public double getCellsize() {
        return cellsize;
    }

    public double getNODATA_value() {
        return NODATA_value;
    }

    public double[][] getAltitudes() {
        return altitudes;
    }

    public void setAltitudes(double[][] altitudes) {
        this.altitudes = altitudes;
    }
    
    public double getAltitude(int y, int x) {
        return altitudes[y][x];
    }

    public void setAltitude(int y, int x, double newAltitude) {
        this.altitudes[y][x] = newAltitude;
    }
    
}
