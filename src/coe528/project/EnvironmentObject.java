/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;


/**
 *
 * @author Aaron
 */
public abstract class EnvironmentObject {
    int x;
    int y;
    BufferedImage img;
    
    public void update() {
        
    }
    public Point getPosition(){
        return null;
    }
    
    
}
