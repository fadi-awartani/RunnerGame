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
    public boolean jump = false;
    private float positionX, positionY;     // Position of the character
    private float velocityX, velocityY;     // Velocity of the character
    private final float gravity = 0.5f;           // How strong is gravity
    //----
    private static final int jumpDuration = 500;
    private static int latestJumpTime = 0;
    private final int initTime;
    private final RunnerCharacter c;
    
    public JumpCommand(RunnerCharacter c) {
        initTime = Environment.time();
        latestJumpTime = initTime;
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
    public boolean isActive(){
        return Environment.time() - initTime < jumpDuration;
    }
    
    //_---------
    void Update(float time) {
        positionX += velocityX * time;      // Apply horizontal velocity to X position
        positionY += velocityY * time;      // Apply vertical velocity to X position
        velocityY += gravity * time;        // Apply gravity to vertical velocity
    }
    
    void OnJumpKeyPressed() {
        velocityY = -12.0f;   // Give a vertical boost to the players velocity to start jump
    }   
 

    @Override
    public void execute(){
        c.update(this);
    } 
}
