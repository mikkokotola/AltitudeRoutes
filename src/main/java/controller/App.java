/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import io.AsciiMapReader;

/**
 *
 * @author Mikko Kotola
 */
public class App {
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String filename = "/altitudefiles/M4313A.asc";
        AsciiMapReader asciiMapReader = new AsciiMapReader(filename);
        
    }

}
