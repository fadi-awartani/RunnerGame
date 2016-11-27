package coe528.project;

import java.util.ArrayList;

/**
 * OVERVIEW: A mutable class that represents a controllable running character.
 * 
 * Abstraction Function:
 * An RunnerCharacter is an object such that it has bounds (position and size)
 * that define its existence, as well as an associated image, and has commands that
 * are applied to it.
 * 
 * Rep Invariant: 
 * imageIndex = 0, and super.repOk()
 * 
 * @author Aaron, Anjalo, Fadi
 */
public class RunnerCharacter extends EnvironmentObject {
    private static final int charWidth = 40, charHeight = 80;
    public static final int baseSpeed = 500; //pixels/sec 
    
    private  ArrayList<ICommand> cmds = new ArrayList<>();
    
    /**
     * EFFECTS: Initializes instance variables.
     * REQUIRES: None
     * MODIFIES: None
     */
    public RunnerCharacter() {
        super(EnvironmentObject.MAIN_CHAR, charWidth, charHeight);
        size.translate(100, floorYLocation - charHeight);
    }
    
    /**
     * Updates the character based on who requested the update.
     * REQUIRES: ios != null.
     * MODIFIES: Position of this object, if ios is an Environment or JumpCommand. 
     *           Modifies the game state if ios is a DeathCommand.
     * EFFECTS: Game state, position of this.
     * @param ios The subject for this observer, who called this function.
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
     * MODIFIES: None.
     * EFFECTS: Adds the given command to the command array.
     * @param c The command object.
     */
    public void applyCommand(ICommand c) {
        cmds.add(c.addCharacter(this));
    }
    
    public boolean repOk(){
        return imageIndex == 0 && super.repOk();
    }
    
    /**
     * 
     * @return The string representation of the RunnerCharacter.
     */
    public String toString() {
        String s =  super.toString() + "\nCommands applied to this character:";
       for(ICommand c : cmds)
            s += "\n" + c.toString();
        
        return s;
    }
}
