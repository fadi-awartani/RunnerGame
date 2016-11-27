package coe528.project;

 /**
  * <p><b>OVERVIEW:</b> <br>
  * An immutable class that represents a death command being given to a character.</p>
  * 
  * <p> <b>Abstraction Function:</b><br>
  * A DeathCommand is an object such that it has an initiation time and an 
  * environment object to invoke.</p>
  * 
  * <p><b>Rep Invariant:</b><br>
  * c != null, and <br>
  * 0 &lt; c.initTime &le; Environment.time() </p>
  * 
  * <!-- HTML Comment: Note: &lt; equals '<', and &le; equals '<=' -->
  * @author Aaron, Anjalo, Fadi
  */
public class DeathCommand implements ICommand, IObserverSubject {
    private final RunnerCharacter c;
    private final int initTime;
    
    /**
     * <p>Creates an empty DeathCommand. A character must attach itself for this object to be useful.</p>
     * <p>EFFECTS: Initializes instance variables.<br>
     * MODIFIES: None.<br>
     * REQUIRES: None.</p>
     */
    public DeathCommand() {
        initTime = Environment.time();
        c = null;
    }
    
    /**
     * <p>Creates a DeathCommand with a RunnerCharacter attached.</p>
     * <p>EFFECTS: Initializes instance variables.<br>
     * MODIFIES: None.<br>
     * REQUIRES: A valid RunnerCharacter object.</p>
     * @param rc The character to add.
     * @param init_time The time that the previous Command calling this method was created.
     */
    private DeathCommand(RunnerCharacter rc, int init_time) {
        if(rc == null)
            throw new IllegalArgumentException("Error: Null character attached.");
        
        initTime = init_time;
        c = rc;
    }
    
    /**
     * <p>Attaches a character to a clone of this object, and returns the updated object.</p>
     * <p>EFFECTS: None.<br>
     * MODIFIES: None.<br>
     * REQUIRES: A valid RunnerCharacter object.</p>
     * @param rc The character to add.
     * @return The updated DeathCommand object.
     */
    @Override
    public ICommand addCharacter(RunnerCharacter rc) {
        return new DeathCommand(rc, initTime);
    }
    
    /**
     * <p>Update character attached to this death command.</p>
     * <p>EFFECTS: The attached RunnerCharacter is updated.<br>
     * MODIFIES: None.<br>
     * REQUIRES: A valid RunnerCharacter object.</p>
     */
    @Override
    public void execute() {
        if(c != null) {
            c.update(this);
        }
    }
    
    /**
     * <p>Determines if this command should be taken care of.</p>
     * <p>EFFECTS: None.<br>
     * MODIFIES: None.<br>
     * REQUIRES: None.</p>
     * @return Always returns true in this case.
     */
    @Override
    public boolean isActive() {
        return true;
    }
     
    public boolean repOk(){
        return c != null && initTime <= Environment.time() && initTime > 0;
    }
    
    /**
     * 
     * @return The string representation of the object.
     */
    @Override
    public String toString() {
        if(c != null)
            return "A DeathCommand that was initiated at t=" + initTime;
        else
            return "An empty DeathCommand that was initiated at t=" + initTime;
    }
}
