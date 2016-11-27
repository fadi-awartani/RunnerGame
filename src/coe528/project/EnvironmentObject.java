package coe528.project;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * OVERVIEW: An abstract mutable class that represents an object that will exist in the environment.
 * 
 * Abstraction Function:
 * An EnvironmentObject is an object such that it has bounds (position and size)
 * that define its existence, as well as an associated image.
 * 
 * Rep Invariant:
 * imageIndex <= lengthOf(imgs), and
 * size.x > 0, and
 * size.y > 0, and
 * size.width > 0, and
 * size.height > 0
 * 
 * @author Aaron, Anjalo, Fadi
 */
public abstract class EnvironmentObject {
    //Constants representing the index of each image (in imgs[] below).
    public static final int MAIN_CHAR = 0, OBSTACLE_1 = 1, OBSTACLE_2 = 2, OBSTACLE_3 = 3;
    
    //Static variables
    private static BufferedImage[] imgs;
    protected static int cameraXLocation = 0;
    protected static int floorYLocation = 430;
    
    //Instance variables
    protected Rectangle size; //Size of object that will be able to physically interact with other objects.
    final protected int imageIndex;

    /**
     * Constructor that must be called from each child of this class. 
     * REQUIRES: An image index 'image' > 0, and valid size parameters.
     * EFFECTS: Initializes instance variables.
     * MODIFIES: None.
     * @param image Index of image to be used for this object.
     * @param width Width of bounds of object, in pixels.
     * @param height Height of bounds of object, in pixels.
     */
    public EnvironmentObject(int image, int width, int height) {
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
    * REQUIRES: A valid Graphics2D object, g.
    * MODIFIES: g.
    * EFFECTS: The image associated with this object is drawn on g. 
    * @param g The graphics object to draw the EnvrionmentObject on.
    */
    public void draw(Graphics2D g) {
        int imgWidth = imgs[imageIndex].getWidth();
        int imgHeight = imgs[imageIndex].getHeight();
        
        g.drawImage(imgs[imageIndex], 
                (int) Math.round(size.getCenterX() - imgWidth/2.0) - cameraXLocation, 
                (int) Math.round(size.getCenterY() - imgHeight/2.0), null);
    }
    
    /**
    * This method initializes the static images array.
    * REQUIRES: None.
    * MODIFIES: The static image array for this class.
    * EFFECTS: Load the array imgs with images of different types of 
    * EnvironmentObjects.
    */
    public static void loadImages() {
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
    
    /**
     * 
     * @return The string representation of the object.
     */
    @Override
    public String toString() {
        String type = "";
        switch(imageIndex) {
            case MAIN_CHAR:
                type = "Main character";
                break;
            case OBSTACLE_1:
                type = "Obstacle 1: Block";
                break;
            case OBSTACLE_2:
                type = "Obstacle 2: Block";
                break;
            case OBSTACLE_3:
                type = "Obstacle 3: Fire";
                break;
        }
        return type + ", at (" + size.x + "," + size.y + "), width = " + size.width + 
                ", height = " + size.height;
    }
}
