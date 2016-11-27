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
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        IObserverSubject ios = null;
        RunnerCharacter instance = new RunnerCharacter();
        instance.update(ios);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of applyCommand method, of class RunnerCharacter.
     */
    @Test
    public void testApplyCommand() {
        System.out.println("applyCommand");
        ICommand c = null;
        RunnerCharacter instance = new RunnerCharacter();
        instance.applyCommand(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of repOk method, of class RunnerCharacter.
     */
    @Test
    public void testRepOk() {
        System.out.println("repOk");
        RunnerCharacter instance = new RunnerCharacter();
        boolean expResult = false;
        boolean result = instance.repOk();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class RunnerCharacter.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        RunnerCharacter instance = new RunnerCharacter();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
   /**
     * Test of update method, of class RunnerCharacter.
     * Test Jump Command where ios = JumpCommand
     * whitebox
     */
    @Test
    public void testCase7() {
        System.out.println("Test Case 7");
        Environment instance = new Environment();
        RunnerCharacter rc = new RunnerCharacter();
        IObserverSubject ios = new JumpCommand();
       
        ArrayList<ICommand> cmds = new ArrayList<>();
        cmds.add(new JumpCommand());
 
           if(cmds.get(0) instanceof JumpCommand) {
             boolean test = true;
        }
    }

   /**
     * Test of update method, of class RunnerCharacter.
     * Test Jump Command where ios = DeathCommand
     * whitebox
     */
    @Test
    public void testCase8() {
        System.out.println("Test Case 8");
        Environment instance = new Environment();
        RunnerCharacter rc = new RunnerCharacter();
        IObserverSubject ios = new DeathCommand();
        rc.update(ios);
        ArrayList<ICommand> cmds = new ArrayList<>();
        cmds.add(new DeathCommand());
 
           if(cmds.get(0) instanceof DeathCommand) {
             boolean test = true;
        }
    }

}
