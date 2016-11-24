package coe528.project;

import java.util.ArrayList;

/**
 * This class represents the main running character.
 * 
 * Abstraction Function:
 * 
 * Rep Invariant: //TODO: Check if project requirements require abstract classes need abstraction functions and rep invariants.
 * cmds != null
 * @author Aaron, Anjalo, Fadi
 */
public class RunnerCharacter extends EnvironmentObject {
    private static final int charWidth = 40, charHeight = 80;
    public static final int baseSpeed = 500; //pixels/sec 
    
    private  ArrayList<ICommand> cmds = new ArrayList<>();
    
    /**
     * Creates a character and initializes its coordinates.
     */
    public RunnerCharacter() {
        super(EnvironmentObject.MAIN_CHAR, charWidth, charHeight);
        size.translate(100, floorYLocation - charHeight);
    }
    
    /**
     * Checks whether this character is colliding with the given obstacle.
     * @param ob The obstacle to check.
     * @return true when there is a collision.
     */
    public boolean isCollidingWith(Obstacle ob) {
        return size.intersects(ob.size);
    }
    
    /**
     * Updates the character based on who requested the update.
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
     * @param c the command object. 
     */
    public void applyCommand(ICommand c) {
        cmds.add(c.addCharacter(this));
    }
}
