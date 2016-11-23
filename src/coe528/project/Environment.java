/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Fadi
 */
public class Environment extends JPanel implements ActionListener, KeyListener, IObserverSubject {
    private int lvl = 0;//Level of the Runner
    private int score = 0;//Score of the Runner
    private static boolean gameOver = false;
    //-----
    private ArrayList<EnvironmentObject> objects = new ArrayList<>();
    private Graphics2D g;
    
    private static int globalTime = 0; //Time since game has started. (in milliseconds)
    private static long lastFrameTime = 0; //UTC time of last frame.
    
    public Environment() {
        super();
        objects.add(new RunnerCharacter());
        for(int i = 0; i < 1000; i++)
            objects.add(new Obstacle());
    }
    
    @Override
    public void paintComponent(Graphics g2)
    {
        //Initialize graphics.
        Graphics2D g = (Graphics2D) g2;
        
        //Draw white background.
        g.setColor(Color.white);
        g.fillRect(0,0,this.getWidth(),this.getHeight());//x,y,width,height (Coordinates start at top left corner)
        
        for(EnvironmentObject eo : objects) {
            eo.draw(g);
        }
        
        g.setColor(Color.black);
        score = globalTime/500;
        g.drawString("Score: " + score, 850, 40);
        
        if(gameOver) {
            g.setColor(Color.red);
            g.drawString("Game over!!", 400, 400);
        }
    }    

    /**
    * Timer is attached to this: this gets called many times a second.
    * @param ae ActionEvent to be called from observer subject.
    */
    @Override
    public void actionPerformed(ActionEvent ae) {
        //Update object positions.
        if(!gameOver)
        for(EnvironmentObject eo : objects) {
            eo.update(this);
        }
        
        //Check for collisions between the running character and obstacles.
        for(int i = objects.size() - 1; i > 0; i--) {
            EnvironmentObject o = objects.get(i);
            if(o instanceof Obstacle && getCharacter().isCollidingWith((Obstacle)o) ) {
                getCharacter().applyCommand(new DeathCommand());
            }
        }
        
        if(lastFrameTime != 0 && !gameOver)
            globalTime += System.currentTimeMillis() - lastFrameTime;
        
        lastFrameTime = System.currentTimeMillis();
        this.repaint();//Repaints screen (calls paintComponent)
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_SPACE && JumpCommand.canJump()){
            getCharacter().applyCommand(new JumpCommand());
        }
    }

    /**
    * Obtains the running character from the ArrayList 'objects'.
    * @return the RunnerCharacter object.
    */
    private RunnerCharacter getCharacter() {
        /*for(EnvironmentObject eo : objects) {
            if(eo instanceof RunnerCharacter)
                return (RunnerCharacter) eo;
        }*/
        if(objects.get(0) instanceof RunnerCharacter)
            return (RunnerCharacter) objects.get(0);
        
        System.err.println("Error: no running character.");
        System.exit(-1);
        return null; //To calm down the compiler.
    }
    
    public static void gameOver() {
        gameOver = true;
    }
    /**
     * Returns the time since the game has started.
     * @return The global time variable (in milliseconds).
     */
    public static int time() {
        return globalTime;
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
        //Method stub. (Unused functionality)
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        //Method stub. (Unused functionality)
    }
}
