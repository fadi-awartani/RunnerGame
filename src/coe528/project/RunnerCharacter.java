/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.util.ArrayList;

/**
 * 
 * @author Aaron
 */
public class RunnerCharacter extends EnvironmentObject {
    private static final int baseHeight = 400, baseSpeed = 500; //pixels/sec 
    private  ArrayList<ICommand> cmds = new ArrayList<>();
    
    public RunnerCharacter() {
        super(EnvironmentObject.MAIN_CHAR, 50, 100);
        size.translate(100, baseHeight);
    }
    
    public boolean isCollidingWith(Obstacle ob) {
        return size.intersects(ob.size);
    }

    @Override
    public void update(IObserverSubject ios) {
        if(ios instanceof Environment) {
            int addX = (baseSpeed)/60;
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
            
            if(!commandActive)
                size.setLocation((int) size.getX(), baseHeight);
        }
        
        if(ios instanceof JumpCommand) {
            JumpCommand jc = (JumpCommand) ios;
            size.setLocation((int) size.getX(), baseHeight - jc.getHeight());
        }
        
        if(ios instanceof DeathCommand) {
            Environment.gameOver();
        }
    }
    
    public void applyCommand(ICommand c) {
        c.addCharacter(this);
        cmds.add(c);
    }
}
