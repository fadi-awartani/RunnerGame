package coe528.project;

import java.util.ArrayList;

/**
 * This class represents the main running character.
 * OVERVIEW: RunnerCharacter is an mutable object. A typical RunnerCharacter 
 * would be a rectangle with a height of 80 and a width of 40. This rectangle is 
 * represented as an image.
 * 
 * Abstraction Function:
 * A RunnerCharacter is an object that is generated in Environment and its checked for any invoked JumpCommand or DeathCommand. All
 * the applied commands are stored in cmds. It also helps confirm if it has collided with any Obstacle in a given moment.
 * has the x position of the last obstacle.
 * 
 * Rep Invariant: 
 * cmds != null
 * 
 * @author Aaron, Anjalo, Fadi
 */
public class RunnerCharacter extends EnvironmentObject {
    private static final int charWidth = 40, charHeight = 80;
    public static final int baseSpeed = 500; //pixels/sec 
    
    private  ArrayList<ICommand> cmds = new ArrayList<>();
    
    /**
     * EFFECTS: A rectangle, represented as an image, is created with a height of 80 and
     * width of 40. The rectangle size is then translated to move 100 on the x-axis. And its moved
     * (floorYLocation-charHeight) down the y-axis.
     * REQUIRES: None
     * MODIFIES: None
     */
    public RunnerCharacter() {
        super(EnvironmentObject.MAIN_CHAR, charWidth, charHeight);
        size.translate(100, floorYLocation - charHeight);
    }
    
    /**
     * Checks whether this character is colliding with the given obstacle.
     * REQUIRES: ob != null
     * MODIFIES: None
     * EFFECTS: returns true if this object intersects obstacle object
     * @param ob The obstacle to check.
     * @return true when there is a collision.
     */
    public boolean isCollidingWith(Obstacle ob) {
        return size.intersects(ob.size);
    }
    
    /**
     * Updates the character based on who requested the update.
     * REQUIRES: ios != null
     * MODIFIES: None
     * EFFECTS: If ios is an instance of JumpCommand, then its position and height is updated.
     * If ios is an instance of the DeathCommand, then 
     * the game ends. It also runs any commands that are active in cmds
     * @param ios The subject for this observer, that called this function.
     */
    @Override
    public void update(IObserverSubject ios) {
        //Update position
        if(ios instanceof Environment) {
            int addX = (baseSpeed + (int)((Environment.time()/15000.0)*100))/60;
            size.translate(addX, 0); //TODO: Add 
            cameraXLocation += addX;
            
            boolean commandActive = false;
            //Run all commands if active.
            for(ICommand c : cmds) {
                if(c.isActive()) {
                    commandActive = true;
                    c.execute();
                }
            }
            
            //Set y-location of character to 0 if no command was active.
            if(!commandActive)
                size.setLocation((int) size.getX(), floorYLocation - charHeight);
        }
        
        //Update height
        if(ios instanceof JumpCommand) {
            JumpCommand jc = (JumpCommand) ios;
            size.setLocation((int) size.getX(), floorYLocation - charHeight - jc.getHeight());
        }
        
        //End game
        if(ios instanceof DeathCommand) {
            Environment.gameOver();
        }
    }
    
    /**
     * Applies command to be done to the character.
     * REQUIRES: c != null
     * MODIFIES: None
     * EFFECTS: Adds this to ICommand, and then its added to cmds array.
     * @param c the command object. 
     */
    public void applyCommand(ICommand c) {
        cmds.add(c.addCharacter(this));
    }
    
    public boolean repOk(){
        if(cmds == null)
            return false;
        else    
            return true;
    }
    
    public String toString() {
        String s =  super.toString() + ". The speed of the RunnerCharacter is " + baseSpeed + ". These are the commands that are applied: ";
        for(ICommand c : cmds)
            s += c.toString();
        return s;
    }
}
