package coe528.project;

/**
 * <p><b>OVERVIEW:</b><br> 
 * An immutable class which represents a jump command for a RunnerCharacter.</p>
 * 
 * <p><b>Abstraction Function:</b>
 * A JumpCommand is an object such that it has initial time and a RunnerCharacter
 * object to invoke. </p>
 * 
 * <p><b>Rep Invariant:</b> <br>
 * c != null, and c.initTime &le; Environment.time()</p>
 * 
 * @author Aaron, Anjalo, Fadi
 */
public class JumpCommand implements ICommand, IObserverSubject {
    public static final int jumpDuration = 610; //ms
    private static int latestJumpTime = -jumpDuration;
    private final int initTime;
    private final RunnerCharacter c;
    
    /**
     * <p>Creates an empty JumpCommand. A character must attach itself later,
     * for this object to be useful.</p>
     * <p>REQUIRES: None.<br>
     * MODIFIES: None.<br>
     * EFFECTS: Initializes instance variables.</p>
     */
    public JumpCommand() {  
        initTime = Environment.time();
        latestJumpTime = initTime;
        c = null;
    }
    
    /**
     * <p>Creates a JumpCommand with a RunnerCharacter attached.</p>
     * <p>REQUIRES: c != null.<br>
     * MODIFIES: None.<br>
     * EFFECTS: Initializes instance variables.</p>
     * @param c The character to add.
     * @param initTime The time that the previous JumpCommand calling this method was created.
     */
    private JumpCommand(RunnerCharacter c, int initTime) {    
        if(c == null)
            throw new IllegalArgumentException("Error: Null character attached.");
        
        this.c = c;
        this.initTime = initTime;
    }
    
    /**
     * <p>Attaches a character to a clone of this object, and returns the updated object.</p>
     * <p>REQUIRES: c != null<br>
     * MODIFIES: None.<br>
     * EFFECTS: None.</p>
     * @param c The character to add.
     * @return The updated JumpCommand object.
     */
    @Override
    public ICommand addCharacter(RunnerCharacter c) {
        return new JumpCommand(c, initTime);
    }
    
    /**
     * <p>Indicates whether there is a currently active jump command or not.</p>
     * <p>REQUIRES: None<br>
     * MODIFIES: None.<br>
     * EFFECTS: returns true if its ok to create a new JumpCommand.</p>
     * @return Whether it is ok to create a new JumpCommand.
     */
    public static boolean canJump() {       
        return Environment.time() - latestJumpTime >= jumpDuration;
    }
    
    /**
     * <p>Indicates whether this command is still active or not</p>
     * <p>REQUIRES: None.<br>
     * MODIFIES: None.<br>
     * EFFECTS: None.</p>
     * @return Returns true if the command is active.
     */
    @Override
    public boolean isActive(){
        return (Environment.time() - initTime) < jumpDuration;
    }
    
    /**
     * <p>Gives the current height of the jumper corresponding to the time elapsed.
     * The jump animation is approximated by half of a sine period.</p>
     * <p>REQUIRES: This object to be currently active.<br>
     * MODIFIES: None.<br>
     * EFFECTS: None.</p>
     * @return The current height of the jump in units of pixels. 
     */
    public int getHeight() {
        if(!isActive())
            return 0;
        
        //Returns h(t)
        //h(t) = max_h*sin(2*pi*(1/2T)*t)
        //total jump time, T, = xdistance/xtime. So,
        //h(t) = max_h*sin(pi*(xspeed/xdistance)*t)
        
        double t = (Environment.time() - initTime)/1000.0;
        
        int xspeed = RunnerCharacter.baseSpeed + (int) ((Environment.time()/15000.0)*100);
        double xdistance = xspeed*jumpDuration/1000.0;
        int max_height = 200;
        
        double h_t = max_height*Math.sin(Math.PI*(xspeed/xdistance)*t);
        return (int) Math.round(h_t);
    }
    
    /**
     * <p>Updates the character attached to this command, if command is
     * active.</p>
     * <p>REQUIRES: None<br>
     * MODIFIES: None<br>
     * EFFECTS: Updates the attached RunnerCharacter.</p>
     * @return Whether the command has executed or not.
     */
    @Override
    public boolean execute(){
        if(isActive()) {
            c.update(this);
            return true;
        }
        return false;
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
        String out = "An " + (isActive() ? "" : "in") + "active ";
        
        if(c != null)
            out += "JumpCommand that was initiated at t=" + initTime;
        else
            out += "empty JumpCommand that was initiated at t=" + initTime;
        
        return out;
    }
}
