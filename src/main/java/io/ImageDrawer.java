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

/**
 *
 * @author Mikko Kotola
 */
public class ImageDrawer {

    public void draw(Graph graph, DynamicList<Vertice> shortestPath, String filename) throws Exception {
        try {
            int width = graph.getMap().getNcols(), height = graph.getMap().getNrows();

            // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
            // into integer pixels
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D graphic = bi.createGraphics();

            //Font font = new Font("TimesRoman", Font.BOLD, 20);
            //graphic.setFont(font);
            //String message = "www.java2s.com!";
            //FontMetrics fontMetrics = graphic.getFontMetrics();
            //int stringWidth = fontMetrics.stringWidth(message);
            //int stringHeight = fontMetrics.getAscent();
            //graphic.setPaint(Color.black);
            graphic.setBackground(Color.BLACK);

            // Define the lowest and highest point.
            double lowest = Double.MAX_VALUE;
            double highest = Double.MIN_VALUE;
            for (int i = 1; i <= graph.getMap().getNcols(); i++) {
                for (int j = 1; j <= graph.getMap().getNrows(); j++) {
                    if (graph.getVertice(i, j).getZ() < lowest) {
                        lowest = graph.getVertice(i, j).getZ();
                    }
                    if (graph.getVertice(i, j).getZ() > highest) {
                        highest = graph.getVertice(i, j).getZ();
                    }
                }
            }

            double range = highest - lowest;

            for (int i = 1; i <= graph.getMap().getNcols(); i++) {
                for (int j = 1; j <= graph.getMap().getNrows(); j++) {
                    if (graph.getVertice(i, j).getDistToStart() < Double.MAX_VALUE) {
                        double toneVal = (highest - graph.getVertice(i, j).getZ()) * (255 / range);
                        graphic.setColor(new Color((int) Math.floor(255 - toneVal), 0, 0));
                    } else {
                        double toneVal = (highest - graph.getVertice(i, j).getZ()) * (255 / range);
                        graphic.setColor(new Color(255, (int) Math.floor(255 - toneVal), 0));
                    }

                    graphic.drawLine(i-1, j-1, i-1, j-1);
                }
            }
            
            graphic.setColor(Color.cyan);
            for (int k = 0; k < shortestPath.size(); k++) {
                int i = shortestPath.get(k).getX();
                int j = shortestPath.get(k).getY();
                graphic.drawLine(i-1, j-1, i-1, j-1);
                
            }
            

            //graphic.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);
            //String filenameStripped = filename.substring(0, filename.indexOf("."));
            ImageIO.write(bi, "PNG", new File("images/" + filename + ".PNG"));
            //ImageIO.write(bi, "JPEG", new File("images/" + filename + ".JPG"));
            //ImageIO.write(bi, "gif", new File("images/" + filename + ".gif"));
            //ImageIO.write(bi, "BMP", new File("images/" + filename + ".BMP"));

        } catch (IOException ie) {
            ie.printStackTrace();
        }

    }
}
