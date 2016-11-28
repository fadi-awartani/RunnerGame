/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aaron
 */
public class ObstacleTest {
    
    public ObstacleTest() {
    }
    
    /**
     * Test of isCollidingWith method, of class Obstacle.
     */
    @Test
    public void testCase3() {
        System.out.println("Test Case 3");
        RunnerCharacter rc = new RunnerCharacter();
        Obstacle instance = new Obstacle(Obstacle.OBSTACLE_1, 100);
        
        boolean result = instance.isCollidingWith(rc);
        assertTrue(result);
    }
    
    /**
     * Test of Obstacle constructor with incorrect boundary
     * Black box (imgIndex != OBSTACLE_1,OBSTACLE_2,OBSTACLE_3)
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCase10() {
        System.out.println("Test Case 10");
        Obstacle test = new Obstacle(4);
    }
}
