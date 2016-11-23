package coe528.project;

import java.util.ArrayList;

/**
 * 
 * @author Aaron
 */
public class RunnerCharacter extends EnvironmentObject {
    private static final int charWidth = 40, charHeight = 80, baseSpeed = 600; //pixels/sec 
    
    private  ArrayList<ICommand> cmds = new ArrayList<>();
    
    public RunnerCharacter() {
        super(EnvironmentObject.MAIN_CHAR, charWidth, charHeight);
        size.translate(100, floorYLocation - charHeight);
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
                size.setLocation((int) size.getX(), floorYLocation - charHeight);
        }
        
        if(ios instanceof JumpCommand) {
            JumpCommand jc = (JumpCommand) ios;
            size.setLocation((int) size.getX(), floorYLocation - charHeight - jc.getHeight());
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
