/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author Aaron
 */
public class JumpCommand implements Command {
    public boolean jump = false;
    private float positionX, positionY;     // Position of the character
    private float velocityX, velocityY;     // Velocity of the character
    private final float gravity = 0.5f;           // How strong is gravity
    
    public JumpCommand() {
        super();   
    }
    
    void Update(float time) {
    positionX += velocityX * time;      // Apply horizontal velocity to X position
    positionY += velocityY * time;      // Apply vertical velocity to X position
    velocityY += gravity * time;        // Apply gravity to vertical velocity
    }
    
    void OnJumpKeyPressed() {
    velocityY = -12.0f;   // Give a vertical boost to the players velocity to start jump
    }   
 

    @Override
    public void execute() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
