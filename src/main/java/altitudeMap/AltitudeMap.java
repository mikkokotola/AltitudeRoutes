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
     * @param xllcorner X-coordinate (etrs-tm35fin) of the map's lower left hand corner
     * @param yllcorner Y-coordinate (etrs-tm35fin) of the map's lower left hand corner
     * @param cellsize Map cell size in meters
     * @param NODATA_value Value used for missing altitude data in the map
     * @param altitudes Altitudes of [y][x]-points on the map with (0,0) top left corner.
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
     * @return double The x coordinate (etrs-tm35fin) of the lower left corner of the map
     */
    public double getXllcorner() {
        return xllcorner;
    }

    /**
     * Returns the y coordinate of the lower left corner of the map.
     * 
     * @return double The y coordinate (etrs-tm35fin) of the lower left corner of the map
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
     * @return double[y][x] 2d array of altitudes with (0,0) in the top left corner
     */
    public double[][] getAltitudes() {
        return altitudes;
    }

    /**
     * Sets the altitude values of all points y-x on the map.
     * 
     * @param altitudes New 2d array of altitudes of y-x points with (0,0) in the top left corner
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
     * Returns a single map-specific x value that corresponds to a longitude
     * coordinate (coordinate system etrs-tm35fin 
     * https://fi.wikipedia.org/wiki/ETRS-TM35FIN).
     * 
     * @param xCoordinate X coordinate of point in etrs-tm35fin
     * 
     * @return int X coordinate value on the map where (0,0) is top left corner
     */
    public int getMapXByEtrsX(double xCoordinate) {
        return ((int)Math.round((xCoordinate - xllcorner)/cellsize));
    }
    
    /**
     * Returns a single map-specific x value that corresponds to a longitude
     * coordinate (coordinate system etrs-tm35fin 
     * https://fi.wikipedia.org/wiki/ETRS-TM35FIN).
     * 
     * @param yCoordinate Y coordinate of point in etrs-tm35fin
     * 
     * @return int Y coordinate value on the map where (0,0) is top left corner
     */
    public int getMapYByEtrsY(double yCoordinate) {
        return ((nrows-1)-((int)Math.round((yCoordinate - yllcorner)/cellsize)));
    }
    
    /**
     * Returns the etrs-tm35fin system longitude (x) coordinate that
     * corresponds to the map-specific x value given as a parameter
     * 
     * @param xCoordinate X coordinate value on the map where (0,0) is top left corner
     * 
     * @return int X coordinate of point in etrs-tm35fin
     */
    public int getEtrsXByMapX(int xCoordinate) {
        return ((int)Math.round((xCoordinate*cellsize)+xllcorner));
    }
    
    /**
     * Returns the etrs-tm35fin system latitude (y) coordinate that
     * corresponds to the map-specific y value given as a parameter
     * 
     * @param yCoordinate Y coordinate value on the map where (0,0) is top left corner
     * 
     * @return int Y coordinate of point in etrs-tm35fin
     */
    public int getEtrsYByMapY(int yCoordinate) {
        return ((int)Math.round(yllcorner+((nrows-1)*cellsize)-(yCoordinate*cellsize)));
    }
    
    /**
     * Sets the altitude value of a y-x point on the map.
     * 
     * @param y Y-coordinate of point
     * @param x X-coordinate of point
     * @param newAltitude New altitude in meters
     * 
     */
    public void setAltitude(int y, int x, double newAltitude) {
        this.altitudes[y][x] = newAltitude;
    }

}
