/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
/**
 *
 * @author Aaron
 */
public class Obstacle extends EnvironmentObject {
    private int type;
    private static int lastXPosition = 1000;
    
    public Obstacle() {
        super((int) (EnvironmentObject.OBSTACLE_1 + Math.random()*2),80,80);
        size.x = lastXPosition + 300 + (int) (Math.random()*500) ;
        lastXPosition = size.x;
        size.y = 420;
    }

    @Override
    public void update(IObserverSubject ios) {
        //throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    /**
    * Draws the environment object onto a graphics canvas.
    * @param g The graphics object to draw the EnvrionmentObject on.
    */
    @Override
    public void draw(Graphics2D g) {
        //TODO: Make images get drawn on proper coordinates for the screen. (I (Fadi) will do this)
        g.setColor(Color.blue);
        g.fillRect(
                (int) Math.round(size.getCenterX() - size.width/2.0) - cameraXLocation, 
                (int) Math.round(size.getCenterY() - size.height/2.0), 
                size.width, 
                size.height);
    }
    
}
