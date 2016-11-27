package coe528.project;

 /**OVERVIEW: An immutable class that represents a death command being given to a character.
 * 
 * Abstraction Function:
 * A DeathCommand is an object such that it has initial time and an environment object to invoke.
 * 
 * Rep Invariant:
 * (c instanceof RunnerCharacter) || c == null
 * 
 * @author Aaron, Anjalo, Fadi
 */
public class DeathCommand implements ICommand, IObserverSubject {
    private final EnvironmentObject c;
    private final int initTime;
    
    /**
     * Creates an empty DeathCommand. A character must attach itself for this object to be useful.
     * EFFECTS: Initializes instance variables.
     * MODIFIES: None.
     * REQUIRES: None.
     */
    public DeathCommand() {
        initTime = Environment.time();
        c = null;
    }
    
    /**
     * Creates a DeathCommand with a RunnerCharacter attached.
     * EFFECTS: Initializes instance variables.
     * MODIFIES: None.
     * REQUIRES: A valid RunnerCharacter object.
     * @param rc The character to add.
     * @param init_time The time that the previous Command calling this method was created.
     */
    private DeathCommand(RunnerCharacter rc, int init_time) {
        initTime = init_time;
        c = rc;
    }
    
    /**
     * Attaches a character to a clone of this object, and returns the updated object.
     * EFFECTS: None.
     * MODIFIES: None.
     * REQUIRES: A valid RunnerCharacter object.
     * @param c The character to add.
     * @return The updated DeathCommand object.
     */
    @Override
    public ICommand addCharacter(RunnerCharacter rc) {
        return new DeathCommand(rc, initTime);
    }
    
    /**
     * Update character attached to this death command.
     * EFFECTS: The attached RunnerCharacter is updated.
     * MODIFIES: None.
     * REQUIRES: A valid RunnerCharacter object.
     */
    @Override
    public void execute() {
        if(c != null) {
            c.update(this);
        }
    }
    
    /**
     * Determines if this command should be taken care of.
     * EFFECTS: None.
     * MODIFIES: None.
     * REQUIRES: None.
     * @return Always returns true in this case.
     */
    @Override
    public boolean isActive() {
        return true;
    }
     
    public boolean repOk(){
        if((c instanceof RunnerCharacter) || c == null)
            return true;
        else 
            return false;
    }
    
    /**
     * 
     * @return The string representation of the object.
     */
    @Override
    public String toString() {
        if(c instanceof RunnerCharacter)
            return " A DeathCommand to a RunnerCharacter that has an initial time of" + initTime;
        else if(c == null)
            return " An empty DeathCommand that has an initial time of" + initTime;
        else
            return "";     
    }
}
