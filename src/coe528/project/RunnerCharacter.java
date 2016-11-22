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
        super(EnvironmentObject.MAIN_CHAR, 100, 100);
        
    }
    
    public boolean isCollidingWith(Obstacle ob) {
        return size.intersects(ob.size);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
