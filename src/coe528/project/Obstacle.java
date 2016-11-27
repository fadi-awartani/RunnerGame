package coe528.project;

/**
 * OVERVIEW: A mutable object that represents an obstacle that a RunnerCharacter 
 * can collide with.
 * 
 * Abstraction Function:
 * An Obstacle is an object such that it has bounds (position and size)
 * that define its existence, as well as an associated image, and has the
 * property of being able to collide with a RunnerCharacter.
 * 
 * Rep Invariant:
 * 1 <= imageIndex <= 3, and
 * size.x >= 1350, and super.repOk()
 * 
 * @author Aaron, Anjalo, Fadi
 */
public class Obstacle extends EnvironmentObject {
    private static int lastXPosition = 1000;
    
    /**
     * Creates new obstacle. Sets its x coordinate to a random amount in front of the 
     * most recent obstacle that was created.
     * REQUIRES: A valid image index.
     * MODIFIES: None
     * EFFECTS: Initializes instance variables.
     * Bounds are set for each obstacle based on the type of obstacle given in imgIndex.
     * @param imgIndex The image index representing the type of obstacle.
     */
    public Obstacle(int imgIndex) {  
        super(imgIndex,10,10); //Initializes with a temporary size of 10x10.
        
        //Initializes the new objects X-position to somewhere between
        // lastXPosition + 350 <= x < lastXPosition + 850. (randomly chosen)
        int x = lastXPosition + 350 + (int) (Math.random()*500);
        int y = floorYLocation; //y coordinate of bottom of object (the floor).
        lastXPosition = x;
        
        //Set the position, and the real size, based on the image index.
        switch(imgIndex) {
            case OBSTACLE_1:
                size.setBounds(x, y-100, 50, 100);
                break;
            case OBSTACLE_2:
                size.setBounds(x, y-100, 75, 100);
                break;
            case OBSTACLE_3:
                size.setBounds(x, y-60, 100, 60);
                break;
            default:
                throw new IllegalArgumentException("Image index must be an obstacle image.");
        }
    }
    
    /**
     * Checks whether this object is colliding with the given RunnerCharacter.
     * REQUIRES: rc != null.
     * MODIFIES: None.
     * EFFECTS: None.
     * @param rc The RunnerCharacter to check for collisions.
     * @return true when there is a collision between both objects being considered.
     */
    public boolean isCollidingWith(RunnerCharacter rc) {
        return size.intersects(rc.size);
    }

    /**
     * Updates the obstacle object.
     * REQUIRES: ios != null.
     * MODIFIES: None
     * EFFECTS: Checks whether there is a collision with the main character, and 
     * if there is collision, a DeathCommand is invoked.
     * @param ios The subject object that is updating this observer. 
     */
    @Override
    public void update(IObserverSubject ios) {
        if(ios instanceof Environment) {
            Environment e = (Environment) ios;
            if(this.isCollidingWith(e.getCharacter()))
                e.getCharacter().applyCommand(new DeathCommand());
        }
    }
        
    public boolean repOk(){
        return size.x >= 1350 && imageIndex >= 1 && imageIndex <= 3 && super.repOk();
    }
    
    //toString() is the same as in the parent class, thus, it is not overridden.
}
