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
public class RunnerCharacterTest {
    
    public RunnerCharacterTest() {
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
     * Test of update method, of class RunnerCharacter.
     * Test Jump Command where ios = JumpCommand
     * whitebox
     */
    @Test
    public void testUpdateJump() {
        System.out.println("update");
        IObserverSubject ios = new JumpCommand();
        RunnerCharacter instance = new RunnerCharacter();
        instance.update(ios);
        
           if(ios instanceof JumpCommand) {
             System.out.println("Good");;
        }
        

    }
    /**
     * Test of update method, of class RunnerCharacter.
     * Test Death Command where ios = DeathCommand
     * whitebox
     */
    @Test
    public void testUpdateDeath() {
        System.out.println("update");
        IObserverSubject ios = new DeathCommand();
        RunnerCharacter instance = new RunnerCharacter();
        instance.update(ios);
        
        if(ios instanceof DeathCommand) {
             System.out.println("Good");;
        }

    }
    
    /**
     * Test of update method, of class RunnerCharacter.
     * Test Death Command where ios = null
     * whitebox
     */
    @Test
    public void testUpdateInvalid() {
        System.out.println("update");
        IObserverSubject ios = null;
        RunnerCharacter instance = new RunnerCharacter();
        
        if (!(ios instanceof Environment)) {
            System.out.println("Good");;
        }
            
    }

    /**
     * Test of isCollidingWith method, of class RunnerCharacter.
     * In the case that Obstacle ob = null;
     */
    @Test
    public void testIsCollidingWithInvalid() {
        System.out.println("isCollidingWith");
        Obstacle ob = null;
        RunnerCharacter instance = new RunnerCharacter();
        boolean expResult = false;
        boolean result = instance.isCollidingWith(ob);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isCollidingWith method, of class RunnerCharacter.
     * In the case that Obstacle ob = new Obstacle();
     */
    @Test
    public void testIsCollidingWith() {
        System.out.println("isCollidingWith");
        Obstacle ob = new Obstacle(1);
        RunnerCharacter instance = new RunnerCharacter();
        boolean expResult = true;
        boolean result = instance.isCollidingWith(ob);
        assertEquals(expResult, result);
    }
    
}
