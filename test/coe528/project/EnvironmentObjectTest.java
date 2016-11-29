package coe528.project;

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
