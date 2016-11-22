/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author Aaron
 */
public interface ICommand {
    public void execute();
    public boolean isActive();
    public void addCharacter(RunnerCharacter rc);
}
