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

    private int timeJumped;
    private char jumpButton;
    private int staticDistance;
    private  ArrayList cmds = new ArrayList<Command>();
    
    public RunnerCharacter() {
        super();
        
        
    }
    
    public boolean isCollidingWith(Obstacle ob) {
        return getPosition().equals(ob.getPosition());
    }
}
