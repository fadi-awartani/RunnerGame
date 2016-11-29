package coe528.project;

import java.awt.Button;
import java.awt.event.KeyEvent;
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
     * Check that it will be game over when a DeathCommand is applied to the
     * RunnerCharacter.
     */
    @Test
    public void testCase2() {
        System.out.println("Test Case 2");
        RunnerCharacter character = instance.getCharacter();
        character.applyCommand(new DeathCommand());
        character.update(instance);
        
        assertTrue(instance.isGameOver()); 
    } 
    
    /**
     * Check that the gameOver flag is not set when the game starts. 
     * (Even after being updated many times)
     */
    @Test
    public void testCase3() {
        System.out.println("Test Case 3");
        
        for(int i = 0; i < 500; i++)
            instance.actionPerformed(null);
            
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
       
        instance.keyPressed(spacebarEntered); //Start game
        assertTrue(instance.isStart());    
    }
}
