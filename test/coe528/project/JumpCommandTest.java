/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.awt.Button;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fadi
 */
public class JumpCommandTest {
    
    public JumpCommandTest() {
    }
    
    Environment e;
    Timer t;
    
    @Before
    public void setUp() {
        EnvironmentObject.loadImages();
        e = new Environment(); //Initialize environment (for initializing time)
        
        //Set up timer for this object:
        t = new Timer(1000/60, e);
        t.start();
        
        e.keyPressed(new KeyEvent(new Button(), KeyEvent.KEY_PRESSED, 0, 0,
            KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED)); //Start game.
    }
    
    @After
    public void tearDown() {
        //t.stop();
        e.close();
        e = null;
        System.gc();
    }

    /**
     * Test that a jumpCommand will return both a height of greater than 100, and 
     * less than 100, during two moments of its duration. (Showing that it
     * changes over time, and is within a reasonable range.)
     */
    @Test
    public void testCase6() throws InterruptedException {
        JumpCommand instance = new JumpCommand();
        boolean greater100 = false, less100 = false;
        
        //Run for 1 second, 60 times.
        for(int i = 0; i < 60; i++) {
            if(instance.getHeight() > 100)
                greater100 = true;
            
            if(instance.getHeight() < 100)
                less100 = true;
            
            Thread.sleep(1000/60);
        }
        
        assertTrue(greater100 && less100);
    }
    
}
