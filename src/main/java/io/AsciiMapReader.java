/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io;

import altitudeMap.AltitudeMap;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mkotola
 */
public class AsciiMapReader {

    private String filename;

    public AsciiMapReader(String filename) {
        this.filename = filename;
    }

    public AltitudeMap readWholeMap() throws FileNotFoundException {
        File file = new File(this.filename);
        String[] headers = new String[6];
        double[][] altitudes = new double[3000][3000];
        StringBuilder fileContents = new StringBuilder((int) file.length());
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)));
        //String lineSeparator = System.getProperty("line.separator");

        try {
            int i = 0;
            while (scanner.hasNextLine()) {
                if (i < 6) {
                    headers[i] = scanner.nextLine();

                } else {
                    String line = scanner.nextLine();
                    String[] yValues = line.split(" ");
                    for (int j = 0; j < yValues.length; j++) {
                        altitudes[i - 6][j] = Double.parseDouble(yValues[j]);
                    }
                }
                i++;
            }

        } finally {
            scanner.close();
        }

        int ncols = Integer.parseInt(headers[0].substring(headers[0].length() - 4));
        int nrows = Integer.parseInt(headers[1].substring(headers[0].length() - 4));;
        String[] xllCornerHeader = headers[2].split(" ");
        double xllcorner = Double.parseDouble(xllCornerHeader[xllCornerHeader.length-1]);
        String[] yllCornerHeader = headers[3].split(" ");
        double yllcorner = Double.parseDouble(yllCornerHeader[yllCornerHeader.length-1]);
        String[] cellSizeHeader = headers[4].split(" ");
        double cellsize = Double.parseDouble(cellSizeHeader[cellSizeHeader.length-1]);
        String[] NODATA_valueHeader = headers[5].split(" ");
        double NODATA_value = Double.parseDouble(NODATA_valueHeader[NODATA_valueHeader.length-1]);

        AltitudeMap altitudeMap = new AltitudeMap(ncols, nrows, xllcorner, yllcorner, cellsize, NODATA_value, altitudes);
        
        return altitudeMap;

    }

}
