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
 * <p><b>OVERVIEW:</b> <br>
 * This mutable class represents the main visible environment. It contains all objects and draws the graphics.
 * (This is done by its parent class, JPanel.) This class is an observer for
 * ActionListener and KeyListener, and is a subject for EnvironmentObjects. </p>
 * 
 * <p><b>Abstraction Function:</b> <br>
 * An object such that it contains EnvironmentObjects of type RunnerCharacter
 * and Obstacle.</p>
 * 
 * <p><b>Rep Invariant:</b><br>
 * globalTime &ge; 0, and
 * 'objects' contains:<br>
 * at least one RunnerCharacter object, and<br>
 * at least one Obstacle object.</p>
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
    private static boolean startGame = false;
    private static final Font font = new Font("arial", Font.BOLD, 50); //Font of start and game over
    
    /**
     * <p>Creates an environment object. Initializes all environment objects and loads images.</p>
     * <p>REQUIRES: None.<br>
     * MODIFIES: None.<br>
     * EFFECTS: Initializes instance variables.</p>
     */
    public Environment() {
        super();
        objects.add(new RunnerCharacter()); //create main character.
        
        //Initialize environment with 500 obstacles.
        for(int i = 0; i < 500; i++) {
            addObstacle(new Obstacle((int) (EnvironmentObject.OBSTACLE_1 + Math.random()*3)));
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
     * <p>Adds an obstacle to the list of objects in this Environment.</p>
     * <p>REQUIRES: An obstacle object.<br>
     * MODIFIES: The EnvironmentObject ArrayList.<br>
     * EFFECTS: Adds an item to the ArrayList.</p>
     * @param ob The obstacle to add.
     */
    public void addObstacle(Obstacle ob) {
        objects.add(ob);//Random obstacle type.
    }
    
    /**
     * <p>This method is automatically called by the graphics library when the screen
     * needs to be refreshed. It draws all graphics on screen.</p>
     * <p>REQUIRES: A graphics object to draw on. (generated by parent class)<br>
     * MODIFIES: None.<br>
     * EFFECTS: None.</p>
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
    * <p> The timer is the only object that calls this: this method generates the refresh rate. </p>
    * <p>REQUIRES: ActionEvent object.<br>
     * MODIFIES: All observers are updated.<br>
     * EFFECTS: The time variables.</p>
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
        
        if(ae != null && ae.getSource() instanceof javax.swing.Timer)
            super.repaint();//Repaints screen (calls paintComponent)
    }

    /**
     * <p>Automatically called when a key is pressed. Is an observer for a KeyEvent.</p>
     * <p>REQUIRES: KeyEvent object.<br>
     * MODIFIES: None.<br>
     * EFFECTS: The RunnerCharacter object gets a command applied to it.</p>
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
    * <p> Obtains the running character from the ArrayList 'objects'. </p>
    * <p>REQUIRES: None.<br>
     * MODIFIES: None.<br>
     * EFFECTS: Exits the program if no character exists.</p>
    * @return The RunnerCharacter object.
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
     * <p> The game ends when this function is called. All visible processes stop. </p>
     * <p>REQUIRES: None.<br>
     * MODIFIES: The state of each Environment object.<br>
     * EFFECTS: The state.</p>
     */
    public static void gameOver() {
        gameOver = true;
    }
    
    /**
     * <p> Determines whether the game is over or not. </p>
     * <p>REQUIRES: None.<br>
     * MODIFIES: None.<br>
     * EFFECTS: None.</p>
     * @return Whether the game is over or not.
     */
    public static boolean isGameOver() {
        return gameOver;
    }
    
    /**
     * <p>Determines whether the game has started. </p>
     * <p>REQUIRES: None.<br>
     * MODIFIES: None.<br>
     * EFFECTS: None.</p>
     * @return Whether the game has started or not.
     */
    public static boolean isStart() {
        return startGame;
    }
    
    /**
     * Returns the time since this object was created.
     * @return The global time variable (in milliseconds).
     */
    public int getTime() {
        return globalTime;
    }
    
    /**
     * <p> Returns the time since the latest object was created. </p>
     * <p>REQUIRES: None.<br>
     * MODIFIES: None.<br>
     * EFFECTS: None.</p>
     * @return The global time variable (in milliseconds).
     */
    public static int time() {
        return newestInstance.getTime();
    }
    
    /**
     * <p> Allow the object to become garbage-collected. For stability in 
     * JUnit test cases.</p>
     * <p>REQUIRES: None.<br>
     * MODIFIES: state variables.<br>
     * EFFECTS: resets state of object.</p>
     */
    public void close() {
        if(newestInstance == this) {
            newestInstance = null;
        }
        gameOver = false;
        startGame = false;
    }
    
    /**
     *
     * @return The string representation for this Environment.
     */
    @Override
    public String toString() {
        String ret = "An Environment object with the following EnvironmentObjects:\n";
        
        for(EnvironmentObject eo : objects) {
            ret += eo.toString() + "\n";
        }
        
        return ret + "\n";
    }
    
    public boolean repOk() {
        boolean runnerExists = false, obstacleExists = false;
        for(EnvironmentObject eo : objects) {
            if (eo instanceof RunnerCharacter) {
                runnerExists = true;
                if(obstacleExists) {
                    break;
                }
            }
            
            if (eo instanceof Obstacle) {
                obstacleExists = true;
                if(runnerExists) {
                    break;
                }
            }
        }
        return runnerExists && obstacleExists && getTime() >= 0;
    }
    
    /**
     * Method stub. (Unused functionality)
     * @param ke Unused.
     * @deprecated
     */
    @Deprecated
    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
    /**
     * Method stub. (Unused functionality)
     * @param ke Unused.
     * @deprecated
     */
    @Deprecated
    @Override
    public void keyTyped(KeyEvent ke) {
    }
}
