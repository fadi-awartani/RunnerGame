/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author Fadi
 */
public class Environment extends JPanel implements ActionListener, KeyListener {
    int i = 0;//Remove
    
    public Environment() {
        super();
    }
    
    @Override
    public void paintComponent(Graphics g2)
    {
        //Initialize graphics.
        Graphics2D g = (Graphics2D) g2;
        
        //Draw white background.
        g.setColor(Color.white);
        g.fillRect(0,0,this.getWidth(),this.getHeight());//x,y,width,height (Coordinates start at top left corner)
        
        g.setColor(Color.pink);
        g.fillRect(100 + (i++ % 800), 100, 50, 50); //x,y,width,height
    }    

    @Override
    /*
    Timer is attached to this: this gets called many times a second.
    */
    public void actionPerformed(ActionEvent ae) {
        this.repaint();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //Method stub. (Unused functionality)
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //Method stub. (Unused functionality)
    }
    
    public static void loadImages() {
        
    }
}
