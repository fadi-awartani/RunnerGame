/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 *
 * @author Aaron
 */
public class RunnerCharacter extends EnvironmentObject {

    private int timeJumped;
    private char jumpButton;
    private int staticDistance;
    private  ArrayList cmds = new ArrayList<Command>();
    
    public RunnerCharacter() {
        super();
        
        
    }
    
    public boolean isCollidingWith(Obstacle ob) {
        if (new Rectangle2D.Double(x, y, width, height).intersects(ob.x , ob.y, ob.width, ob.height)) {
            return true;
        } else {
            return false;
        }
    }
}
