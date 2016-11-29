package coe528.project;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test cases for ObstacleTest class.
 * @author Aaron, Anjalo, Fadi
 */
public class ObstacleTest {
    
    public ObstacleTest() {
    }
    
    @Before
    public void setUp() {
        EnvironmentObject.loadImages();
    }
    /**
     * Test of isCollidingWith method, of class Obstacle.
     */
    @Test
    public void testCase7() {
        System.out.println("Test Case 7");
        RunnerCharacter rc = new RunnerCharacter();
        Obstacle instance = new Obstacle(Obstacle.OBSTACLE_1, 100);
        
        boolean result = instance.isCollidingWith(rc);
        assertTrue(result);
    }
}
