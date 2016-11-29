package coe528.project;

/**
 * <p> <b>OVERVIEW:</b>
 * Interface for commands. Based on the Command design pattern. </p>
 * @author Aaron, Anjalo, Fadi
 */
public interface ICommand {
    public boolean execute();
    public boolean isActive();
    public ICommand addCharacter(RunnerCharacter rc);
}
