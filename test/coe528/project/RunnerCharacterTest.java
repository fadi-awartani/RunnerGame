/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

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
public class RunnerCharacterTest {
    
    public RunnerCharacterTest() {
        Environment a = new Environment();
    }
    
    @BeforeClass
    public static void setUpClass() {
        Environment a = new Environment();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Environment a = new Environment();
    }
    
    @After
    public void tearDown() {
    }
    


    /**
     * Test of update method, of class RunnerCharacter.
     * Case if the command was a jump command;
     */
    @Test
    public void testCase7() {
        System.out.println("update");
        IObserverSubject ios = new JumpCommand();
        RunnerCharacter instance = new RunnerCharacter();
        ArrayList<ICommand> cmds = new ArrayList<>();
        cmds.add(new JumpCommand());
        
        if (cmds.get(0) == instance.getIObserverSubject()) {
        boolean test = true;
        }

    }
    
    /**
     * Test of update method, of class RunnerCharacter.
     * Case if the command was a death command;
     */
    @Test
    public void testCase8() {
        System.out.println("Test Case 8");
        IObserverSubject ios = new DeathCommand();
        RunnerCharacter instance = new RunnerCharacter();
        ArrayList<ICommand> cmds = new ArrayList<>();
        cmds.add(new DeathCommand());
        
        if (cmds.get(0) == instance.getIObserverSubject()) {
        boolean test = true;
        instance.update(ios);
        }

    }
    
    /**
     * Test of isCollidingWith method, of class RunnerCharacter.
     * In the case that Obstacle ob = new Obstacle();
     */
    @Test
    public void testCase9() {
        System.out.println("Test Case 9");
        Obstacle ob = new Obstacle(1);
        RunnerCharacter instance = new RunnerCharacter();
        boolean expResult = false;
        boolean result = instance.isCollidingWith(ob);
        assertEquals(expResult, result);
    }
    
}
