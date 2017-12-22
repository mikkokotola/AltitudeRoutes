/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package altitudeMap;

/**
 *
 * @author Mikko Kotola
 */
public class AltitudeMap {
    private int ncols;
    private int nrows;
    private double xllcorner;
    private double yllcorner;
    private double cellsize;
    private double NODATA_value;
    private double[][] altitudes;

    public AltitudeMap(int ncols, int nrows, double xllcorner, double yllcorner, double cellsize, double NODATA_value, double[][] altitudes) {
        this.ncols = ncols;
        this.nrows = nrows;
        this.xllcorner = xllcorner;
        this.yllcorner = yllcorner;
        this.cellsize = cellsize;
        this.NODATA_value = NODATA_value;
        this.altitudes = altitudes;
    }
    
    
    
}
