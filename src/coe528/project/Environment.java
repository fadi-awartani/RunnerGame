package coe528.project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
 *
 * @author Fadi
 */
public class Environment extends JPanel implements ActionListener, KeyListener, IObserverSubject {
    private ArrayList<EnvironmentObject> objects = new ArrayList<>();
    private BufferedImage background, floor, gameover;
    private Graphics2D g;
    private static boolean startGame = false;
    private Font font = new Font("arial", Font.BOLD, 50); //Font of start and game over
    
    private static int globalTime = 0; //Time since game has started. (in milliseconds)
    private static long lastFrameTime = 0; //UTC time of last frame processed.
    private static boolean gameOver = false;
    
    public Environment() {
        super();
        objects.add(new RunnerCharacter());
        
        for(int i = 0; i < 1000; i++)
            objects.add(new Obstacle((int) (EnvironmentObject.OBSTACLE_1 + Math.random()*3)));
        
        try {
            background = ImageIO.read(EnvironmentObject.class.getResource("Images/bg.jpg"));
            floor = ImageIO.read(EnvironmentObject.class.getResource("Images/floor.png"));
            gameover = ImageIO.read(EnvironmentObject.class.getResource("Images/gameover.png"));
        } catch (IOException ex) {
            System.err.println("Image loading error at Environment");
            System.exit(-1);
        }
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
        
        g.drawImage(background, 0, 0, null);
        g.drawImage(floor, 0, 410, null);
        
        //Menu
       if (startGame == false) { 
            g.setFont(font);
            g.setColor(Color.black);
            g.drawString("Press SPACE to START GAME", 50, 400);
            g.drawString("Infinite Runner", 50, 100);
        }  
       //Start Game
        else if(startGame == true) {
        
//Draw all objects.
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
    }

    /**
    * Timer is attached to this: this gets called many times a second.
    * @param ae ActionEvent to be called from observer subject.
    */
    @Override
    public void actionPerformed(ActionEvent ae) {
        //Update each object.
        if(!gameOver) {
            for(EnvironmentObject eo : objects) {
                eo.update(this);
            }
        }
        
        //Update global time variable.
        if(!gameOver && lastFrameTime != 0)
            globalTime += System.currentTimeMillis() - lastFrameTime;
        
        lastFrameTime = System.currentTimeMillis();
        this.repaint();//Repaints screen (calls paintComponent)
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == KeyEvent.VK_SPACE && JumpCommand.canJump()){
            startGame = true;
            getCharacter().applyCommand(new JumpCommand());
        }
    }

    /**
    * Obtains the running character from the ArrayList 'objects'.
    * @return the RunnerCharacter object.
    */
    public RunnerCharacter getCharacter() {
        if(objects.get(0) instanceof RunnerCharacter)
            return (RunnerCharacter) objects.get(0);
        
        System.err.println("Error: no running character.");
        System.exit(-1);
        return null; //To calm down the compiler.
    }
    
    /**
     * The game is over when this function is called. All visible processes stop.
     */
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
