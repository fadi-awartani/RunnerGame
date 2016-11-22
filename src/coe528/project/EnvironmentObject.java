/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * An abstract class that represents an object that will exist in the environment.7
 * Each object has an image associated with it, as well as bounds that define 
 * 
 * @author Aaron
 */
public abstract class EnvironmentObject {
    //Constants representing the index of each image (in imgs[] below).
    public static final int MAIN_CHAR = 0, OBSTACLE_1 = 1, OBSTACLE_2 = 2;
    private static final int[][] imgSizes = //Size of each image in the same order as above.
        {{100,100},     //MAIN_CHAR (width,height)
        {100,100},      //OBSTACLE_1
        {100,100}};     //OBSTACLE_2
    
    //Static variables
    private static BufferedImage[] imgs;
    
    //Instance variables
    protected Rectangle size; //Size of object that will be able to physically interact with other objects.
    private int imageWidth, imageHeight, imageIndex;

    public EnvironmentObject(int image, int width, int height) {
        if(image < 0 || image >= imgs.length)
            throw new IllegalArgumentException("Image index invalid.");
        
        if(width <= 0 || height <= 0)
            throw new IllegalArgumentException("Invalid dimensions.");
        
        imageIndex = image;
        size = new Rectangle(0,0,width,height);
    }
    
    public abstract void update();
    
    /*public Point getPosition(){
        return new Point(x,y);
    }*/
    
    /**
    * Draws the environment object onto a graphics canvas.
    * @param g The graphics object to draw the EnvrionmentObject on.
    */
    public void draw(Graphics2D g) {
        int imgWidth = imgSizes[imageIndex][0];
        int imgHeight = imgSizes[imageIndex][1];
        
        //TODO: Make images get drawn on proper coordinates for the screen. (I (Fadi) will do this)
        g.drawImage(imgs[imageIndex], 
                (int) Math.round(size.getCenterX() - imgWidth/2.0), 
                (int) Math.round(size.getCenterY() - imgHeight/2.0), 
                imgWidth, 
                imgHeight, null);
    }
    
    /**
    * This method initializes the static images array.
    */
    public static void loadImages() {
        try {
            imgs = new BufferedImage[]{
                ImageIO.read(EnvironmentObject.class.getResource("Images/t.png")), //MAIN_CHAR
                null, //OBSTACLE_1
                null /*OBSTACLE_2*/ };
        } catch (IOException ex) {
            System.err.println("Image loading error at EnvironmentObject");
            System.exit(-1);
        }
    }
}
