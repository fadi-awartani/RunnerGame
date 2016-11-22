/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;
import java.awt.geom.Rectangle2D;
/**
 *
 * @author Aaron
 */
public class Obstacle extends EnvironmentObject{
    private int type;
    
    public Obstacle() {
        super((int) (EnvironmentObject.OBSTACLE_1 + Math.random()*2),100,100);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
