package coe528.project;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for ObstacleTest class.
 * @author Aaron, Anjalo, Fadi
 */
public class ObstacleTest {
    
    public ObstacleTest() {
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
    
    /**
     * Test of Obstacle constructor with incorrect boundary
     * Black box (imgIndex != OBSTACLE_1,OBSTACLE_2,OBSTACLE_3)
     * Not good, done in EnvironmentObjectTest.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCase8() {
        System.out.println("Test Case 8");
        Obstacle test = new Obstacle(4);
    }
}
