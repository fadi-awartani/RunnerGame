/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 * An immutable class 
 * @author Aaron
 */
public class JumpCommand implements ICommand, IObserverSubject {
    private static final int jumpDuration = 500;
    private static int latestJumpTime = 0;
    private final int initTime;
    private RunnerCharacter c;
    
    public JumpCommand() {
        initTime = Environment.time();
        latestJumpTime = initTime;
    }
    
    public void addCharacter(RunnerCharacter c) {
        this.c = c;
    }
    
    /**
     * Indicates whether there is a currently active jump command or not.
     * @return Returns true if there are no active jump commands at the moment.
     */
    public static boolean canJump() {
        return Environment.time() - latestJumpTime > jumpDuration;
    }
    
    /**
     * Indicates whether this command is still active or not.
     * @return Returns true if the command is active.
     */
    @Override
    public boolean isActive(){
        return Environment.time() - initTime < jumpDuration;
    }
    
    /**
     * Gives the current velocity of the jumper corresponding to the time elapsed.
     * Gravity is assumed to be -9.8 m/s^2, and meter to pixel conversion
     * is assumed to be 75 pixels/meter.
     * @return The current velocity of the jump in units of pixels/frame. 
     */
    public int getVelocity() {
        //Initial velocity required to get symmetrical jump over a time of t_total:
        //Vi = (-a/2)*t_total;
        //V(t) = Vi + a*t;
        
        //The equations above represented in code:
        double vi = (-9.8/2)*(jumpDuration/1000.0); //Unit: m/s
        double v_t = vi - 9.8*(Environment.time() - initTime)/1000.0; // m/s
        
        //Convert to integer, and to units of pixels/frame.
        int v_t_int = (int) Math.round(v_t*75/60); // pixels/s * (frames/s)^-1 = pixels/frame
        return v_t_int;
    }
    
    @Override
    public void execute(){
        c.update(this);
    } 
}
