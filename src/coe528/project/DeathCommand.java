package coe528.project;

/**
 * An immutable class that represents a death command was given to a character.
 * Abstraction Funtion:
 * 
 * Rep Invariant:
 * 
 * @author Aaron, Anjalo, Fadi
 */
public class DeathCommand implements ICommand, IObserverSubject {
    //OVERVIEW: DeathCommand is an mutable object. A typical DeathCommand object 
    //would be a RunnerCharacter c with a intial time initTime.
    private final RunnerCharacter c;
    private final int initTime;
    
    /**
     * Creates an empty DeathCommand. A character must attach itself for this object to be useful.
     */
    public DeathCommand() {
    //EFFECTS: creates a null RunnerCharacter with initial time(initTime) set to the present time    
        initTime = Environment.time();
        c = null;
    }
    
    /**
     * Creates a DeathCommand with a RunnerCharacter attached.
     * @param rc The character to add.
     * @param init_time The time that the previous Command calling this method was created.
     */
    private DeathCommand(RunnerCharacter rc, int init_time) {
    //REQUIRES: rc != null
    //EFFECTS: Stores rc to this.c and stores the initial time to this    
        initTime = init_time;
        c = rc;
    }
    
    /**
     * Attaches a character to a clone of this object, and returns the updated object.
     * @param c The character to add.
     * @return The updated DeathCommand object.
     */
    @Override
    public ICommand addCharacter(RunnerCharacter rc) {
    //REQUIRES: c != null
    //EFFECTS: returns a new DeathCommand with rc and initial time(initTime)          
        return new DeathCommand(rc, initTime);
    }
    
    /**
     * Update character attached to this death command.
     */
    @Override
    public void execute() {
    //EFFECTS: if c!= null, then it updates the c with this     
        if(c != null) {
            c.update(this);
        }
    }
    
    /**
     * Determines if this command should be taken care of.
     * @return Always returns true in this case.
     */
    @Override
    public boolean isActive() {
        //EFFECTS: returns true always    
        return true;
    }
}
