package coe528.project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test cases for EnvironmentObjectTest class.
 * @author Aaron, Anjalo, Fadi
 */
public class EnvironmentObjectTest {

    @Before
    public void setUp() {
        EnvironmentObject.loadImages();
    }
    
    /**
     * Test that the draw() function can successfully draw an image.
     */
    @Test
    public void testCase8() {
        EnvironmentObject instance = new EnvironmentObjectImpl(EnvironmentObject.MAIN_CHAR,100,100);
        
        //Create BufferedImage to do test on.
        BufferedImage bi = new BufferedImage(100,100, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();//Graphics object allows us to draw on 
                                      //the above image.
        
        //Draw white box over entire BufferedImage. (Set all pixels to white)
        g.setColor(Color.WHITE);
        g.fillRect(0,0,100,100);
        
        //The method being tested.
        instance.draw((Graphics2D) g);
        g.dispose();
        
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                //Check every pixel. If not white, test passes. (This means the
                //EnvironmentObject instance succeeded in drawing an image.)
                if(bi.getRGB(i, j) != 0xFFFFFFFF) {
                    return;
                }
            }
        }
        
        fail("No image was drawn by the instance.");
    }
    
    /**
     * Test of EnvironmentObject constructor with incorrect image index
     * Black box (imgIndex != MAIN_CHAR,OBSTACLE_1,OBSTACLE_2,OBSTACLE_3)
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCase9() {
        System.out.println("Test Case 9");
        EnvironmentObject.loadImages();
        EnvironmentObject test = new EnvironmentObjectImpl(200,10,10);
    }  

    /**
     * Test of EnvironmentObject constructor with incorrect size boundaries
     * Black box (!(width <= 0 || height <= 0))
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCase10() {
        System.out.println("Test Case 10");
        EnvironmentObject test = new EnvironmentObjectImpl(Obstacle.OBSTACLE_1, 0, 0);
    }
    
    public class EnvironmentObjectImpl extends EnvironmentObject {

        public EnvironmentObjectImpl(int image, int width, int height) {
            super(image, width, height);
        }

        public void update(IObserverSubject ios) {
        }
    }
    
}
