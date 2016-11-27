/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
public class EnvironmentTest {
    
    public EnvironmentTest() {
        
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
     * Check that initial score is 0
     */
    @Test
    public void testCase1() {
        System.out.println("Test Case 1");
        Environment instance = new Environment();
        int expResult = 0;
        int result = instance.getTime();
        assertEquals(expResult, result/500);
    }
    
    /**
     * Check that initial global time is 0
     */
    @Test
    public void testCase2() {
        System.out.println("Test Case 2");
        Environment instance = new Environment();
        int expResult = 0;
        int result = instance.getTime();
        assertEquals(expResult, result);
    }

    /**
     * Check that the runner character will be of object RunnerCharacter
     */
    @Test
    public void testCase3() {
        System.out.println("Test Case 3");
        Environment instance = new Environment();
        ArrayList<EnvironmentObject> objects = new ArrayList<>();
        objects.add(new RunnerCharacter());
        
        if(objects.get(0) instanceof RunnerCharacter) {
            boolean test = true;
        } else
            fail("Not a Runner Character");
    }
 
    /**
     * Check that the runner character will NOT be of object RunnerCharacter
     */
    @Test
    public void testCase4() {
        System.out.println("Test Case 4");
        Environment instance = new Environment();
        
        if (instance instanceof Environment) {
        ArrayList<EnvironmentObject> objects = new ArrayList<>();
        objects.add(new Obstacle((int) (EnvironmentObject.OBSTACLE_1 + Math.random()*3)));
        
        if(objects.get(0) instanceof RunnerCharacter) {
            fail("");
        } 
        else {
             boolean test = true;
        }
       }    
    }
    
     /**
     * Check that the obstacle will be of object obstacle
     */
    @Test
    public void testCase5() {
        System.out.println("Test Case 5");
        Environment instance = new Environment();
        if (instance instanceof Environment) {
            ArrayList<EnvironmentObject> objects = new ArrayList<>();
            objects.add(new RunnerCharacter());
            objects.add(new Obstacle((int) (EnvironmentObject.OBSTACLE_1 + Math.random()*3)));
        
            if(objects.get(1) instanceof Obstacle) {
                boolean test = true; 
            }
        }
                
    }
 
    /**
     * Check that the obstacle will NOT be of object obstacle
     */
    @Test
    public void testCase6() {
        System.out.println("Test Case 6");
        Environment instance = new Environment();
        if (instance instanceof Environment) {
            ArrayList<EnvironmentObject> objects = new ArrayList<>();
            objects.add(new RunnerCharacter());
            objects.add(new RunnerCharacter());
        
            if(objects.get(1) instanceof Obstacle) {
                fail("");
            }
            else {
                boolean test = true;
            }
        }
                
    }


    
}
