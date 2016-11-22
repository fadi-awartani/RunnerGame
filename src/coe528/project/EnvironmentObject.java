/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


/**
 *
 * @author Aaron
 */
public abstract class EnvironmentObject {
    //Constants representing the index of an image in imgs[] below.
    private static final int MAIN_CHAR = 0, OBSTACLE_1 = 1;
    private int x, y, width, height, imageIndex;
    private static BufferedImage[] imgs;
    
    public EnvironmentObject(int image, int width, int height) {
        if(image < 0 || image >= imgs.length)
            throw new IllegalArgumentException("Image index invalid.");
        
        if(width <= 0 || height <= 0)
            throw new IllegalArgumentException("Invalid dimensions.");
        
        imageIndex = image;
    }
    
    public void update() {
    }
    
    /*public Point getPosition(){
        return new Point(x,y);
    }*/
    
    /**
    * This method initializes the static images array.
    * @param g The graphics object to draw the EnvrionmentObject on.
    */
    public void draw(Graphics2D g) {
        g.drawImage(imgs[imageIndex], x, y, width, height, null);
    }
    
    /**
    * This method initializes the static images array.
    */
    public static void loadImages() {
        try {
            imgs = new BufferedImage[]{
                ImageIO.read(EnvironmentObject.class.getResource("Images/t.png")), //MAIN_CHAR
                null /*OBSTACLE_1*/ };
        } catch (IOException ex) {
            System.err.println("Image loading error at EnvironmentObject");
            System.exit(-1);
        }
    }
}
