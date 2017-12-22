/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import altitudeMap.AltitudeMap;
import io.AsciiMapReader;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mikko Kotola
 */
public class App {
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String filename = "src/main/resources/altitudefiles/M4313A.asc";
        AsciiMapReader asciiMapReader = new AsciiMapReader(filename);
        AltitudeMap map;
        try {
            map = asciiMapReader.readWholeMap();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Testing.");
    }

}
