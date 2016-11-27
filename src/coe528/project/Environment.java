package coe528.project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * OVERVIEW: This mutable class represents the main visible environment. It contains all objects and draws the graphics.
 * (This is done by its parent class, JPanel.) This class is an observer for
 * ActionListener and KeyListener, and is a subject for EnvironmentObjects. 
 * 
 * Abstraction Function:
 * An object such that it contains EnvironmentObjects of type RunnerCharacter
 * and Obstacle.
 * 
 * Rep Invariant:
 * globalTime >= 0, and
 * 'objects' contains:
 * at least one RunnerCharacter object, and
 * at least one Obstacle object.
 * 
 * @author Aaron, Anjalo, Fadi
 */
public class Environment extends JPanel implements ActionListener, KeyListener, IObserverSubject {
    private ArrayList<EnvironmentObject> objects = new ArrayList<>();
    private BufferedImage background, floor, gameover;
    
    private int globalTime = 0; //Time since game has started. (in milliseconds)
    private long lastFrameTime = 0; //UTC time of last frame processed.
    private static Environment newestInstance;
    private static boolean gameOver = false;
    private boolean startGame = false;
    private static final Font font = new Font("arial", Font.BOLD, 50); //Font of start and game over
    
    /**
     * Creates an environment object. Initializes all environment objects and loads images.
     */
    public Environment() {
        super();
        objects.add(new RunnerCharacter()); //create main character.
        
        //Initialize environment with 500 obstacles.
        for(int i = 0; i < 500; i++) {
            objects.add(new Obstacle((int) (EnvironmentObject.OBSTACLE_1 + Math.random()*3)));//Random obstacle type.
        }
        
        //Load images.
        try {
            background = ImageIO.read(EnvironmentObject.class.getResource("Images/bg.jpg"));
            floor = ImageIO.read(EnvironmentObject.class.getResource("Images/floor.png"));
            gameover = ImageIO.read(EnvironmentObject.class.getResource("Images/gameover.png"));
        } catch (IOException ex) {
            System.err.println("Image loading error at Environment");
            System.exit(-1);
        }
        
        newestInstance = this;
    }
    
    /**
     * This method is automatically called by the graphics library when the screen
     * needs to be refreshed. It draws all graphics on screen.
     * @param g2 The graphics object given by the caller.
     */
    @Override
    public void paintComponent(Graphics g2)
    {
        //Initialize graphics object.
        Graphics2D g = (Graphics2D) g2;
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);//Turn anti-aliasing on for text.
        
        g.drawImage(background, 0, 0, null);
        g.drawImage(floor, 0, 410, null);
        
        //Main Menu
       if (!startGame) { 
            g.setFont(font);
            g.setColor(Color.black);
            g.drawString("Press SPACE to START GAME", 70, 380);
            g.drawString("Infinite Runner", 50, 80);
            return;
        }
        
        //Draw all environment objects.
        for(EnvironmentObject eo : objects) {
            eo.draw(g);
        }
        
        //Draw score label.
        g.setFont(new Font("arial", Font.BOLD, 24));
        g.setColor(Color.black);
        int score = globalTime/500;
        g.drawString("Score: " + score, 800, 40);
        
        //Draw "game over" label when the game is over.
        if(gameOver) {
            g.drawImage(gameover, 250, 100, null);
        }
    }

    /**
    * The timer is the only object that calls this: this method generates the refresh rate.
    * @param ae ActionEvent to be given from observer subject.
    */
    @Override
    public void actionPerformed(ActionEvent ae) {
        //Update each object.
        if(startGame && !gameOver) {
            for(EnvironmentObject eo : objects) {
                eo.update(this);
            }
        }
        
        //Update global time variable.
        if(startGame && !gameOver && lastFrameTime != 0)
            globalTime += System.currentTimeMillis() - lastFrameTime;
        
        if(startGame)
            lastFrameTime = System.currentTimeMillis();
        
        super.repaint();//Repaints screen (calls paintComponent)
    }

    /**
     * Automatically called when a key is pressed. Is an observer for a KeyEvent.
     * @param ke KeyEvent that describes the event that generated this call.
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_SPACE && JumpCommand.canJump()){
            startGame = true;
            getCharacter().applyCommand(new JumpCommand());
        }
        
        //Prints the toString() of the character.
        if(ke.getKeyCode() == KeyEvent.VK_C) {
            System.out.println(getCharacter());
        }
        
        //Prints the toString() of this.
        if(ke.getKeyCode() == KeyEvent.VK_E) {
            System.out.println(this);
        }
    }

    /**
    * Obtains the running character from the ArrayList 'objects'.
    * @return the RunnerCharacter object.
    */
    public RunnerCharacter getCharacter() {
        //RunnerCharacter is always in index 0 of the array list.
        if(objects.get(0) instanceof RunnerCharacter)
            return (RunnerCharacter) objects.get(0);
        
        System.err.println("Error: no running character.");
        System.exit(-1);
        return null; //To calm down the compiler.
    }
    
    /**
     * The game ends when this function is called. All visible processes stop.
     */
    public static void gameOver() {
        gameOver = true;
    }
    
    /**
     * Returns the time since this object was created.
     * @return The global time variable (in milliseconds).
     */
    public int getTime() {
        return globalTime;
    }
    
    /**
     * Returns the time since the latest object was created.
     * @return The global time variable (in milliseconds).
     */
    public static int time() {
        return newestInstance.getTime();
    }
    
    /**
     *
     * @return The string representation for this Environment.
     */
    @Override
    public String toString() {
        return "";
    }
    
    /**
     * Method stub. (Unused functionality)
     * @param ke Unused.
     */
    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
    /**
     * Method stub. (Unused functionality)
     * @param ke Unused.
     */
    @Override
    public void keyTyped(KeyEvent ke) {
    }
}
