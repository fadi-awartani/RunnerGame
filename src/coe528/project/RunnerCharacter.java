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
    private static final int baseSpeed = 300; //pixels/sec 
    private  ArrayList<ICommand> cmds = new ArrayList<>();
    
    public RunnerCharacter() {
        super(EnvironmentObject.MAIN_CHAR, 100, 100);
    }
    
    public boolean isCollidingWith(Obstacle ob) {
        return size.intersects(ob.size);
    }

    @Override
    public void update(IObserverSubject ios) {
        if(ios instanceof Environment) {
            size.translate((baseSpeed)/60, 0); //TODO: Add 
            for(ICommand c : cmds) {
                if(c.isActive())
                    c.execute();
            }
        }
        
        if(ios instanceof JumpCommand) {
            JumpCommand jc = (JumpCommand) ios;
            size.translate(0,jc.getVelocity());
        }
    }
    
    public void applyCommand(ICommand c) {
        c.addCharacter(this);
        cmds.add(c);
    }
}
