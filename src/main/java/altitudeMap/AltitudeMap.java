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

/**
 * AltitudeMap. The class contains a grid map with altitude information. The
 * altitudes are saved in the double 2d-array altitudes[y][x] with y signifying
 * the row and x signifying the column. All other attributes are metadata.
 *
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

    /**
     * Creates new AltitudeMap.
     * 
     * @param filename Name of map file
     * @param ncols Number of columns
     * @param nrows Number of rows
     * @param xllcorner X-coordinate of the map's lower left hand corner
     * @param yllcorner Y-coordinate of the map's lower left hand corner
     * @param cellsize Map cell size in meters
     * @param NODATA_value Value used for missing altitude data in the map
     * @param altitudes Altitudes of y-x-points on the map. Y is the first coordinate.
     */
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

    /**
     * Returns the filename of the ascii map that the AltitudeMap was created from.
     * 
     * @return String The origin filename of ascii map
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Returns the number of columns on the map.
     * 
     * @return int The number of columns on the map
     */
    public int getNcols() {
        return ncols;
    }

    /**
     * Returns the number of rows on the map.
     * 
     * @return int The number of rows on the map
     */
    public int getNrows() {
        return nrows;
    }

    /**
     * Returns the x coordinate of the lower left corner of the map.
     * 
     * @return double The x coordinate of the lower left corner of the map
     */
    public double getXllcorner() {
        return xllcorner;
    }

    /**
     * Returns the y coordinate of the lower left corner of the map.
     * 
     * @return double The y coordinate of the lower left corner of the map
     */
    public double getYllcorner() {
        return yllcorner;
    }

    /**
     * Returns the cell size of the map in meters.
     * 
     * @return double Map cell size in meters
     */
    public double getCellsize() {
        return cellsize;
    }

    /**
     * Returns the no data value of the map.
     * 
     * @return double No data value of the map
     */
    public double getNODATA_value() {
        return NODATA_value;
    }

    /**
     * Returns the altitude values of all points y-x on the map.
     * 
     * @return double[][] 2d array of altitudes of y-x points
     */
    public double[][] getAltitudes() {
        return altitudes;
    }

    /**
     * Sets the altitude values of all points y-x on the map.
     * 
     * @param altitudes New 2d array of altitudes of y-x points
     */
    public void setAltitudes(double[][] altitudes) {
        this.altitudes = altitudes;
    }

    /**
     * Returns a single altitude value for a y-x point.
     * 
     * @param y Y-coordinate of point
     * @param x X-coordinate of point
     * 
     * @return double Altitude of point y-x in meters
     */
    public double getAltitude(int y, int x) {
        return altitudes[y][x];
    }

    /**
     * Sets the altitude value of a y-x point on the map.
     * 
     * @param y Y-coordinate of point
     * @param x X-coordinate of point
     * @param newAltitude New altitude in meters
     * 
     * @return double Altitude of point y-x in meters
     */
    public void setAltitude(int y, int x, double newAltitude) {
        this.altitudes[y][x] = newAltitude;
    }

}
