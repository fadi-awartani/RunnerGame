/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 * An immutable class.
 * @author Fadi
 */
public class DeathCommand implements ICommand, IObserverSubject {
    private RunnerCharacter c;
    private final int initTime;
    
    public DeathCommand() {
        initTime = Environment.time();
    }
    
    @Override
    public void addCharacter(RunnerCharacter rc) {
        c = rc;
    }
    
    @Override
    public void execute() {
        if(c != null)
            c.update(this);
    }
    
    /**
     * Determines if this command should be taken care of.
     * @return Always returns true in this case.
     */
    @Override
    public boolean isActive() {
        return true;
    }
}
