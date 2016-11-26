package coe528.project;

/**
 * An immutable class which represents a jump command for a RunnerCharacter.
 * 
 * Abstraction Function:
 * 
 * Rep Invariant:
 * 
 * @author Aaron, Anjalo, Fadi
 */
public class JumpCommand implements ICommand, IObserverSubject {
    //OVERVIEW: JumpCommand is mutable object. A typical JumpCommand object 
    //would be a RunnerCharacter c with a jump duration of 610ms.
    private static final int jumpDuration = 610; //ms
    private static int latestJumpTime = -jumpDuration;
    private final int initTime;
    private final RunnerCharacter c;
    
    /**
     * Creates an empty JumpCommand. A character must attach itself for this object to be useful.
     */
    public JumpCommand() {
    //EFFECTS: creates a null RunnerCharacter with initial time(initTime) set to the present time     
        initTime = Environment.time();
        latestJumpTime = initTime;
        c = null;
    }
    
    /**
     * Creates a JumpCommand with a RunnerCharacter attached.
     * @param c The character to add.
     * @param initTime The time that the previous JumpCommand calling this method was created.
     */
    private JumpCommand(RunnerCharacter c, int initTime) {
    //REQUIRES: c != null
    //EFFECTS: Stores c to this.c and stores the initial time to this       
        this.c = c;
        this.initTime = initTime;
    }
    
    /**
     * Attaches a character to a clone of this object, and returns the updated object.
     * @param c The character to add.
     * @return The updated JumpCommand object.
     */
    @Override
    public ICommand addCharacter(RunnerCharacter c) {
    //REQUIRES: c != null
    //EFFECTS: returns a new JumpCommand with c and initial time(initTime)     
        return new JumpCommand(c, initTime);
    }
    
    /**
     * Indicates whether there is a currently active jump command or not.
     * @return Returns true if there are no active jump commands at the moment.
     */
    public static boolean canJump() {
    //EFFECTS: returns true if there is a currently active jump command          
        return Environment.time() - latestJumpTime >= jumpDuration;
    }
    
    /**
     * Indicates whether this command is still active or not.
     * @return Returns true if the command is active.
     */
    @Override
    public boolean isActive(){
    //EFFECTS: returns true if this command is still active
        return (Environment.time() - initTime) < jumpDuration;
    }
    
    /**
     * Gives the current height of the jumper corresponding to the time elapsed.
     * The jump animation is approximated by half of a sine period.
     * @return The current height of the jump in units of pixels. 
     */
    public int getHeight() {
    //EFFECTS: If there is no active command in this, it returns 0. Else it returns
    //the height of the jumper corresponding to the time elapsed.
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
     */
    @Override
    public void execute(){
    //EFFECTS: Updates this in RunnerCharacter c        
        c.update(this);
    } 
}
