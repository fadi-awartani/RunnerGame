package coe528.project;

/**
 * An immutable class which represents a jump command for a RunnerCharacter.
 * OVERVIEW: JumpCommand is mutable object. A typical JumpCommand object 
 * would be a RunnerCharacter c with a jump duration of 610ms.
 * 
 * Abstraction Function:
 * A JumpCommand is an object such that it has initial time with a jump duration time and an environment object to invoke.
 * Rep Invariant:
 * (c instanceof RunnerCharacter) || c == null
 * @author Aaron, Anjalo, Fadi
 */
public class JumpCommand implements ICommand, IObserverSubject {
    private static final int jumpDuration = 610; //ms
    private static int latestJumpTime = -jumpDuration;
    private final int initTime;
    private final EnvironmentObject c;
    
    /**
     * Creates an empty JumpCommand. A character must attach itself for this object to be useful.
     * REQUIRES: None
     * MODIFIES: None
     * EFFECTS: EFFECTS: creates a null RunnerCharacter with initial time(initTime) set to the present time 
     */
    public JumpCommand() {  
        initTime = Environment.time();
        latestJumpTime = initTime;
        c = null;
    }
    
    /**
     * Creates a JumpCommand with a RunnerCharacter attached.
     * REQUIRES: c != null
     * MODIFIES: None
     * EFFECTS: Stores c to this.c and stores the initial time to this
     * @param c The character to add.
     * @param initTime The time that the previous JumpCommand calling this method was created.
     */
    private JumpCommand(RunnerCharacter c, int initTime) {      
        this.c = c;
        this.initTime = initTime;
    }
    
    /**
     * Attaches a character to a clone of this object, and returns the updated object.
     * REQUIRES: c != null
     * MODIFIES: None
     * EFFECTS: returns a new JumpCommand with c and initial time(initTime)  
     * @param c The character to add.
     * @return The updated JumpCommand object.
     */
    @Override
    public ICommand addCharacter(RunnerCharacter c) {
        return new JumpCommand(c, initTime);
    }
    
    /**
     * Indicates whether there is a currently active jump command or not.
     * REQUIRES: None
     * MODIFIES: None
     * EFFECTS: returns true if there is a currently active jump command
     * @return Returns true if there are no active jump commands at the moment.
     */
    public static boolean canJump() {       
        return Environment.time() - latestJumpTime >= jumpDuration;
    }
    
    /**
     * Indicates whether this command is still active or not.
     * REQUIRES: None
     * MODIFIES: None
     * EFFECTS: returns true if this command is still active
     * @return Returns true if the command is active.
     */
    @Override
    public boolean isActive(){
        return (Environment.time() - initTime) < jumpDuration;
    }
    
    /**
     * Gives the current height of the jumper corresponding to the time elapsed.
     * The jump animation is approximated by half of a sine period.
     * REQUIRES: None
     * MODIFIES: None
     * EFFECTS: If there is no active command in this, it returns 0. Else it returns
     * the height of the jumper corresponding to the time elapsed.
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
     * Updates the character attached to this command.
     * REQUIRES: None
     * MODIFIES: None
     * EFFECTS: Updates this in RunnerCharacter c  
     */
    @Override
    public void execute(){   
        c.update(this);
    }
    
    public boolean repOk(){
        if(!((c instanceof RunnerCharacter) || c == null))
            return false;
        else if(initTime < 0)
            return false;
        else 
            return true;
    }
    
    /**
     * 
     * @return The string representation of the object.
     */
    @Override
    public String toString() {
        if(c instanceof RunnerCharacter)
            return " A JumpCommand to a RunnerCharacter that has a jump duration of "+jumpDuration;
        else if(c == null)
            return " An empty JumpCommand that has a jump duration of "+jumpDuration;
        else
            return "";     
    }
}
