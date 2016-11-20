/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author Fadi
 */
public class RunnerGame {
    private static final int screenWidth = 960, screenHeight = 540;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame f = new JFrame("COE 528 Project - Runner Game");
        Environment e = new Environment();
        e.setIgnoreRepaint(true);
        f.setContentPane(e);
        f.setSize(screenWidth, screenHeight);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addKeyListener(e);
        f.setVisible(true);
        
        //Get center position based on screen size.
        int posX = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - screenWidth)/2;
        int posY = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - screenHeight)/2 - 25;
		
        f.setLocation(posX,posY);
        
        Timer framerate = new Timer(1000/60, e); //60Hz refresh rate (1000 ms/60 frames)
        Environment.loadImages();
        framerate.start();
    }
}
