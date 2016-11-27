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
    public void testBoundary() {
        
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
    public void testInvalidBoundary() {
    Obstacle test = new Obstacle(4);
    }
    
    
    /**
     * Test of update method, of class Obstacle.
     * Test where ios = Environment
     * whitebox
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        IObserverSubject ios = new Environment();
        Obstacle instance = new Obstacle(1);
        instance.update(ios);
        
        if (ios instanceof Environment) {
            System.out.println("Good");
        }
            
    }


    /**
     * Test of update method, of class RunnerCharacter.
     * Test Death Command where ios != Environment
     * whitebox
     */
    @Test
    public void testUpdateInvalid() {
        System.out.println("update");
        IObserverSubject ios = null;
        Obstacle instance = new Obstacle(1);
        instance.update(ios);
        
        if(!(ios instanceof Environment)) {
            System.out.println("Good");
        }
   
            
    } 
    
    /**
     * Test of update method, of class Obstacle.
     * Test where ios = Environment, but isCollidingWith = false
     * whitebox
     */
    @Test
    public void testUpdate2() {
        System.out.println("update");
        IObserverSubject ios = new Environment();
        Obstacle instance = null;
        instance.update(ios);
        
        if(ios instanceof Environment) {
            Environment e = (Environment) ios;
            if(!(e.getCharacter().isCollidingWith(instance)))
                 System.out.println("Good");
        }    
    }
    
}
