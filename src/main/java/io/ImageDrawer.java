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
package io;

import dataStructures.DynamicList;
import graph.Graph;
import graph.Vertice;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import searchAlgo.SearchAlgo;

/**
 * ImageDrawer draws images of the maps and the performed searches.
 *
 * @author Mikko Kotola
 */
public class ImageDrawer {

    /**
     * Draws an image of the parameter graph including search information: 
     * opened and investigated vertices and shortest route.
     *
     * @param graph The graph used in the search
     * @param shortestRoute The shortest path to be drawn
     * @param filename The name of the file to which the image is to be written
     *
     */
    public void draw(SearchAlgo searchAlgo, String filename, String coordinateSystem) {
        Graph graph = searchAlgo.getGraph();
        
        try {
            int width = graph.getMap().getNcols() + 500;
            int height = graph.getMap().getNrows();
            
            // If image too small, set height to 380 to have room for legend
            if (graph.getMap().getNrows() < 380 ) {
               height = 380; 
            }
            
            // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
            // into integer pixels
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
                       

            Graphics2D graphic = bi.createGraphics();
            graphic.setBackground(Color.WHITE);
            graphic.setColor(Color.WHITE);
            graphic.fillRect(0,0, width, height);
            

            // Define the lowest and highest point.
            double lowest = Double.MAX_VALUE;
            double highest = Double.MIN_VALUE;
            for (int i = 0; i < graph.getMap().getNcols(); i++) {
                for (int j = 0; j < graph.getMap().getNrows(); j++) {
                    if (graph.getVertice(i, j).getZ() < lowest) {
                        lowest = graph.getVertice(i, j).getZ();
                    }
                    if (graph.getVertice(i, j).getZ() > highest) {
                        highest = graph.getVertice(i, j).getZ();
                    }
                }
            }

            double range = highest - lowest;

            drawMap(graph, highest, range, graphic);
            DynamicList<Vertice> shortestRoute = searchAlgo.returnShortestPath();
            if (!shortestRoute.isEmpty()) {
                drawRoute(graphic, shortestRoute);
            }
            
            int pX = writePictureInfoToLegend(graphic, graph, filename, searchAlgo);
            
            drawColorScale(graphic, highest, pX, lowest);
            
            // Alternative picture formats.
            ImageIO.write(bi, "PNG", new File("images/" + filename + ".PNG"));
            //ImageIO.write(bi, "JPEG", new File("images/" + filename + ".JPG"));
            //ImageIO.write(bi, "gif", new File("images/" + filename + ".gif"));
            //ImageIO.write(bi, "BMP", new File("images/" + filename + ".BMP"));

        } catch (IOException ie) {
            ie.printStackTrace();
        }

    }

    private void drawMap(Graph graph, double highest, double range, Graphics2D graphic) {
        for (int i = 0; i < graph.getMap().getNcols(); i++) {
            for (int j = 0; j < graph.getMap().getNrows(); j++) {
                // The higher the point, the smaller the toneval
                double toneVal = (highest - graph.getVertice(i, j).getZ()) * (235 / range);
                if (graph.getVertice(i, j).getHeapRef() == -2) {
                    // Found and investigated vertices get values in the violet-dark blue range. Lowest points get darkest colors.
                    graphic.setColor(new Color((int) Math.floor(245 - toneVal), 0, 200));
                } else if (graph.getVertice(i, j).getHeapRef() > 0) {
                    // Found but not investigated vertices get values in the light blue-green tones. Lowest points get greenest colors.
                    graphic.setColor(new Color(0, 150, (int) Math.floor(245 - toneVal)));
                } else {
                    // Not found vertices get colors in the yellow-red tones. Lowest points get reddest colors.
                    graphic.setColor(new Color(245, (int) Math.floor(245 - toneVal), 0));
                }
                
                graphic.drawLine(i, j, i, j);
            }
        }
        
    }
    
    private void drawRoute(Graphics2D graphic, DynamicList<Vertice> shortestRoute) {
        graphic.setColor(Color.cyan);
        for (int k = 0; k < shortestRoute.size(); k++) {
            int i = shortestRoute.get(k).getX();
            int j = shortestRoute.get(k).getY();
            graphic.drawLine(i, j, i, j);
        }
    }
    

    private int writePictureInfoToLegend(Graphics2D graphic, Graph graph, String filename, SearchAlgo searchAlgo) {
        
        DynamicList<Vertice> shortestRoute = searchAlgo.returnShortestPath();
        // Write the picture information to legend as text.
        Font font = new Font("TimesRoman", Font.PLAIN, 10);
        graphic.setFont(font);
        graphic.setPaint(Color.BLACK);
        int pX = graph.getMap().getNcols() + 20;
        graphic.drawString("* AltitudeRoutes *",pX ,20);
        graphic.drawString("File: " + filename + ".PNG",pX ,35);
        graphic.drawString("From: map-relative " + searchAlgo.getStart().getX() + ", " + searchAlgo.getStart().getY() + "; ETRS-TM35FIN " + graph.getMap().getEtrsXByMapX(searchAlgo.getStart().getX()) + ", " + graph.getMap().getEtrsYByMapY(searchAlgo.getStart().getY()), pX , 50);
        graphic.drawString("To: map-relative " + searchAlgo.getGoal().getX() + ", " + searchAlgo.getGoal().getY()+ "; ETRS-TM35FIN " + graph.getMap().getEtrsXByMapX(searchAlgo.getGoal().getX()) + ", " + graph.getMap().getEtrsYByMapY(searchAlgo.getGoal().getY()), pX , 65);
        graphic.drawString("Algorithm: " + searchAlgo.getName(), pX, 80);
        graphic.drawString("Length of shortest path: " + searchAlgo.returnLengthOfShortestRoute(), pX, 95);
        int opened = graph.countOpened();
        graphic.drawString("Vertices found during search: " + opened, pX, 110);
        graphic.drawString("Vertices investigated during search: " + (opened - searchAlgo.heapSize()), pX, 125);
        
        graphic.drawString("Map details",pX, 270);
        graphic.drawString("Columns: " + graph.getMap().getNcols(),pX, 285);
        graphic.drawString("Rows: " + graph.getMap().getNrows(),pX, 300);
        graphic.drawString("Map cell size: " + graph.getMap().getCellsize(),pX, 315);
        graphic.drawString("X of lower left corner (ETRS-TM35FIN): " + graph.getMap().getXllcorner(),pX, 330);
        graphic.drawString("Y of lower left corner (ETRS-TM35FIN): " + graph.getMap().getYllcorner(),pX, 345);
        graphic.drawString("Map source data: National Land Survey of Finland", pX, 360);
        return pX;
    }

    private void drawColorScale(Graphics2D graphic, double highest, int pX, double lowest) {
        // Draw color scale
        graphic.drawString("Highest altitude : " + highest + " m", pX + 180, 160);
        graphic.drawString("Lowest altitude : " + lowest + " m", pX + 180, 244);
        graphic.drawString("Not found", pX, 145);
        graphic.drawString("Found", pX + 60, 145);
        graphic.drawString("Investigated", pX + 120, 145);
        for (int i = 0; i < 48; i++) {
            graphic.setColor(new Color(245, 245-(i*5), 0));
            graphic.fillRect(pX, 150 + i*2, 50, 2);
            graphic.setColor(new Color(0, 150, 245-(i*5)));
            graphic.fillRect(pX+60, 150 + i*2, 50, 2);
            graphic.setColor(new Color(245-(i*5), 0, 200));
            graphic.fillRect(pX+120, 150 + i*2, 50 ,2);
            
        }
    }
}
