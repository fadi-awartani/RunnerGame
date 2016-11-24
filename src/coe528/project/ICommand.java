package coe528.project;

/**
 * Interface for commands. Based on the Command design pattern.
 * @author Aaron, Anjalo, Fadi
 */
public interface ICommand {
    public void execute();
    public boolean isActive();
    public ICommand addCharacter(RunnerCharacter rc);
}
