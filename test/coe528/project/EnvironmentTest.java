package coe528.project;

import java.awt.Button;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Test cases for EnvironmentTest class.
 * @author Aaron, Anjalo, Fadi
 */
public class EnvironmentTest {
    
    private KeyEvent spacebarEntered = new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, 0, 0,
        KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED);
    
    Environment instance;
    
    public EnvironmentTest() {
        
    }
    
    @Before
    public void setUp() {
        EnvironmentObject.loadImages();
        instance = new Environment();
    }
    
    @After
    public void tearDown() {
        instance.close();
        instance = null;
        System.gc();//start garbage collection
    }
 
    /**
     * Check that the timing mechanism is working.
     */
    @Test
    public void testCase1() throws InterruptedException {
        System.out.println("Test Case 1");
        instance.keyPressed(spacebarEntered); //Start game (and timing mechanism).
        instance.actionPerformed(null);
        Thread.sleep(50);
        instance.actionPerformed(null);
        
        int expResult = 50;
        int result = instance.getTime();
        
        if(Math.abs(result - expResult) > 5)
            fail("Timer is off by more than 5 ms. (" + result + " vs " + expResult + ")");
        
    }
    



    /**
     * Check that it will be game over when the RunnerCharacter dies.
     */
    @Test
    public void testCase2() {
        System.out.println("Test Case 2");
        instance = new Environment();
            RunnerCharacter character = instance.getCharacter();
            character.applyCommand(new DeathCommand());
            character.update(new DeathCommand());
            
            if (instance.isGameOver() == false) {
                fail("It is not yet game over.");
            }
            assertTrue(instance.isGameOver());
 
    }    /**
     * Check that it will be NOT be game over if the RunnerCharacter is alive.
     */
    @Test
    public void testCase3() {
        System.out.println("Test Case 3");
        instance = new Environment();
            RunnerCharacter character = instance.getCharacter();
            //character.applyCommand(new DeathCommand());
            //character.update(new DeathCommand());
            
            assertFalse(instance.isGameOver());
 
    }

    /**
     * Check that a valid RunnerCharacter is attached.
     */
    @Test
    public void testCase4() {
        System.out.println("Test Case 4");
        
        if(!instance.getCharacter().repOk())
            fail("Invalid Runner Character");
    }

    /**
     * Check that the game will start when SPACE is entered. 
     */
    @Test
    public void testCase5() {
        System.out.println("Test Case 5");
        instance = new Environment();
       
        instance.keyPressed(spacebarEntered); //Start game
        assertTrue(instance.isStart());    
    }
    /**
     * Check that the game will NOT start if SPACE is not entered. 
     */
    @Test
    public void testCase6() {
        System.out.println("Test Case 6");
        instance = new Environment();
       
        //instance.keyPressed(spacebarEntered); //Start game
        assertFalse(instance.isStart());    
    }


}
