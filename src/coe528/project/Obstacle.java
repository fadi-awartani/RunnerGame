package coe528.project;

/**
 * Represents an obstacle that the character can collide with.
 * Abstraction Function:
 * 
 * 
 * Rep Invariant:
 * 
 * 
 * @author Aaron, Anjalo, Fadi
 */
public class Obstacle extends EnvironmentObject {
    private static int lastXPosition = 1000;
    
    /**
     * Creates new obstacle. Sets its x coordinate to a random amount in front of the 
     * most recent obstacle that was created.
     * @param imgIndex The image index representing the type of obstacle.
     */
    public Obstacle(int imgIndex) {
        super(imgIndex,10,10);
        
        int x = lastXPosition + 350 + (int) (Math.random()*500);
        int y = floorYLocation; //of bottom of object.
        lastXPosition = x;
        
        switch(imgIndex) {
            case EnvironmentObject.OBSTACLE_1:
                size.setBounds(x, y-100, 50, 100);
                break;
            case EnvironmentObject.OBSTACLE_2:
                size.setBounds(x, y-100, 75, 100);
                break;
            case EnvironmentObject.OBSTACLE_3:
                size.setBounds(x, y-60, 100, 60);
                break;
            default:
                throw new IllegalArgumentException("Image index must be an obstacle image.");
        }
    }

    /**
     * Updates the obstacle object; Checks whether there is a collision with the
     * main character.
     * @param ios The subject object that is updating this observer. 
     */
    @Override
    public void update(IObserverSubject ios) {
        if(ios instanceof Environment) {
            Environment e = (Environment) ios;
            if(e.getCharacter().isCollidingWith(this))
                e.getCharacter().applyCommand(new DeathCommand());
        }
    }
}
