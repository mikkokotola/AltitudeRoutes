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
package io;

import altitudeMap.AltitudeMap;
import controller.App;
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
 * AsciiMapReader. Use to read an ascii grid map from a file into
 * memory.
 *
 * @author Mikko Kotola
 */
public class AsciiMapReader {

    private String filename;

    /**
     * Creates a new AsciiMapReader with the given filename of the source ascii
     * map.
     * @param filename
     */
    public AsciiMapReader(String filename) {
        this.filename = filename;
    }
    
    /**
     * ReadWholeMap reads the whole altitudemap into memory.
     * 
     * @return AltitudeMap is an object representation of the ascii map.
     * @throws FileNotFoundException Throws a an exception if the file
     * with the given file name is not found.
     *
     */
    public AltitudeMap readWholeMap() throws FileNotFoundException {
        File file = new File(this.filename);
        String[] headers = new String[6];
        double[][] altitudes = null;
        StringBuilder fileContents = new StringBuilder((int) file.length());
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)));

        int nrows = -1;
        int ncols = -1;
        try {
            int i = 0;
            while (scanner.hasNextLine()) {
                if (i < 6) {
                    headers[i] = scanner.nextLine();
                    if (i == 1) {
                        ncols = Integer.parseInt(headers[0].substring(headers[0].length() - 4));
                    } else if (i == 2) {
                        nrows = Integer.parseInt(headers[1].substring(headers[0].length() - 4));
                        altitudes = new double[nrows][ncols];
                    }
                
                } else {
                    String line = scanner.nextLine();
                    String[] yValues = line.split(" ");
                    for (int j = 1; j < yValues.length; j++) {
                        altitudes[i - 6][j - 1] = Double.parseDouble(yValues[j]);
                    }
                }
                i++;
            }

        } finally {
            scanner.close();
        }

        
        String[] xllCornerHeader = headers[2].split(" ");
        double xllcorner = Double.parseDouble(xllCornerHeader[xllCornerHeader.length - 1]);
        String[] yllCornerHeader = headers[3].split(" ");
        double yllcorner = Double.parseDouble(yllCornerHeader[yllCornerHeader.length - 1]);
        String[] cellSizeHeader = headers[4].split(" ");
        double cellsize = Double.parseDouble(cellSizeHeader[cellSizeHeader.length - 1]);
        String[] NODATA_valueHeader = headers[5].split(" ");
        double NODATA_value = Double.parseDouble(NODATA_valueHeader[NODATA_valueHeader.length - 1]);

        //String filenameWithoutPostFix = filename.substring(0, filename.indexOf('.'));
        String filenameWithoutPath = filename.substring(filename.indexOf('/')+1);
        AltitudeMap altitudeMap = new AltitudeMap(filenameWithoutPath, ncols, nrows, xllcorner, yllcorner, cellsize, NODATA_value, altitudes);

        return altitudeMap;

    }

}
