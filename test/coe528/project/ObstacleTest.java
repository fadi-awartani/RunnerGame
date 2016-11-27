/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aaron
 */
public class ObstacleTest {
    
    public ObstacleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

  
    /**
     * Test of Obstacle constructor following boundary
     * Black box (1 < imgIndex < 3)
     * 1 = OBSTACLE_1, 2 = OBSTACLE_2, 3 = OBSTACLE_3
     */
    @Test
    public void testCase9() {
        System.out.println("Test Case 9");
        Obstacle test1 = new Obstacle(1);
        assertEquals(1, EnvironmentObject.OBSTACLE_1);
        
        Obstacle test2 = new Obstacle(2);
        assertEquals(2, EnvironmentObject.OBSTACLE_2);
        
        Obstacle test3 = new Obstacle(3);
        assertEquals(3, EnvironmentObject.OBSTACLE_3);

    }
    
    /**
     * Test of Obstacle constructor with incorrect boundary
     * Black box (imgIndex != OBSTACLE_1,OBSTACLE_2,OBSTACLE_3
     */
    @Test (expected = IllegalArgumentException.class)
    public void testCase10() {
        System.out.println("Test Case 10");
    Obstacle test = new Obstacle(4);
    }

    /**
     * Test of isCollidingWith method, of class Obstacle.
     */
    @Test
    public void testIsCollidingWith() {
        System.out.println("isCollidingWith");
        RunnerCharacter rc = null;
        Obstacle instance = null;
        boolean expResult = false;
        boolean result = instance.isCollidingWith(rc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class Obstacle.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        IObserverSubject ios = null;
        Obstacle instance = null;
        instance.update(ios);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of repOk method, of class Obstacle.
     */
    @Test
    public void testRepOk() {
        System.out.println("repOk");
        Obstacle instance = null;
        boolean expResult = false;
        boolean result = instance.repOk();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
