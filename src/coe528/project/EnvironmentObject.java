package coe528.project;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * An abstract class that represents an object that will exist in the environment.
 * Each object has an image associated with it, as well as bounds that define
 * where it can physically interact.
 * 
 * Abstraction Function:
 * 
 * 
 * Rep Invariant:
 * imageIndex <= lengthOf(imgs)
 * size.x > 0
 * size.y > 0
 * size.width > 0
 * size.height > 0
 * 
 * @author Aaron, Anjalo, Fadi
 */
public abstract class EnvironmentObject {
    //OVERVIEW: EnvironmentObject is a mutable object. A typical EnviormentObject 
    //would be a rectangle with a specific width, height and image.
    
    //Constants representing the index of each image (in imgs[] below).
    public static final int MAIN_CHAR = 0, OBSTACLE_1 = 1, OBSTACLE_2 = 2, OBSTACLE_3 = 3;
    
    //Static variables
    private static BufferedImage[] imgs;
    protected static int cameraXLocation = 0;
    protected static int floorYLocation = 430;
    
    //Instance variables
    protected Rectangle size; //Size of object that will be able to physically interact with other objects.
    private int imageIndex;

    public EnvironmentObject(int image, int width, int height) {
    //REQUIRES: image > 0
    //EFFECTS: A new rectangle is created in the origin(0,0) with magnitude width and height
        if(image < 0 || image >= imgs.length)
            throw new IllegalArgumentException("Image index invalid.");
        
        if(width <= 0 || height <= 0)
            throw new IllegalArgumentException("Invalid dimensions.");
        
        imageIndex = image;
        size = new Rectangle(0,0,width,height);
    }
    
    public abstract void update(IObserverSubject ios);
    
    /**
    * Draws the environment object onto a graphics canvas.
    * @param g The graphics object to draw the EnvrionmentObject on.
    */
    public void draw(Graphics2D g) {
    //REQUIRES: g != null
    //MODIFIES: g
    //EFFECTS: g is redrawn with an image that has a position in the 2D plane 
        int imgWidth = imgs[imageIndex].getWidth();
        int imgHeight = imgs[imageIndex].getHeight();
        
        g.drawImage(imgs[imageIndex], 
                (int) Math.round(size.getCenterX() - imgWidth/2.0) - cameraXLocation, 
                (int) Math.round(size.getCenterY() - imgHeight/2.0), null);
    }
    
    /**
    * This method initializes the static images array.
    */
    public static void loadImages() {
    //EFFECTS: load the array imgs with the image of a runner character and three
    //different types of obstacles
        try {
            imgs = new BufferedImage[]{
                ImageIO.read(EnvironmentObject.class.getResource("Images/char.png")),      //MAIN_CHAR
                ImageIO.read(EnvironmentObject.class.getResource("Images/obs1.png")),   //OBSTACLE_1
                ImageIO.read(EnvironmentObject.class.getResource("Images/obs2.png")),   //OBSTACLE_2
                ImageIO.read(EnvironmentObject.class.getResource("Images/obs3.png"))};  //OBSTACLE_3
        } catch (IOException ex) {
            System.err.println("Image loading error at EnvironmentObject");
            System.exit(-1);
        }
    }
    
    public boolean repOk(){
        if(imageIndex > imgs.length)
            return false;
        else if(size.x < 0)
            return false;
        else if(size.y < 0)
            return false;
        else if(size.width < 0)
            return false;
        else if(size.height < 0)
            return false;
        else
            return true;
    }
}
