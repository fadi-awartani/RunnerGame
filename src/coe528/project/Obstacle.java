package coe528.project;

/**
 * <p> <b>OVERVIEW:</b><br>
 * A mutable object that represents an obstacle that a RunnerCharacter 
 * can collide with.</p>
 * 
 * <p><b>Abstraction Function:</b> <br>
 * An Obstacle is an object such that it has bounds (position and size)
 * that define its existence, as well as an associated image, and has the
 * property of being able to collide with a RunnerCharacter.</p>
 * 
 * <p><b>Rep Invariant:</b> <br>
 * 1 &le; imageIndex &le; 3, and <br>
 * size.x &ge; 1350, and super.repOk() </p>
 * 
 * @author Aaron, Anjalo, Fadi
 */
public class Obstacle extends EnvironmentObject {
    private static int lastXPosition = 1000;
    
    /**
     * <p>Creates new obstacle. Sets its x coordinate to a random amount in front of the 
     * most recent obstacle that was created.</p>
     * <p>REQUIRES: A valid image index.<br>
     * MODIFIES: None<br>
     * EFFECTS: Initializes instance variables.</p>
     * <p>Bounds are set for each obstacle based on the type of obstacle given in imgIndex.</p>
     * @param imgIndex The image index representing the type of obstacle.
     */
    public Obstacle(int imgIndex) {
        //Initializes the new objects X-position to somewhere between
        // lastXPosition + 350 <= x < lastXPosition + 850. (randomly chosen)
        this(imgIndex, 
            lastXPosition + 350 + (int) (Math.random()*500)); 
    }
    
    /**
     * <p>Creates new obstacle.</p>
     * <p>REQUIRES: A valid image index, and an x-coordinate.<br>
     * MODIFIES: None<br>
     * EFFECTS: Initializes instance variables.</p>
     * <p>Bounds are set for each obstacle based on the type of obstacle given in imgIndex.</p>
     * @param imgIndex The image index representing the type of obstacle.
     * @param x X-coordinate of where obstacle should be.
     */
    public Obstacle(int imgIndex, int x) {  
        super(imgIndex,10,10); //Initializes with a temporary size of 10x10.
        
        lastXPosition = x;
        int y = floorYLocation; //y coordinate of bottom of object (the floor).
        
        int width = 0, height = 0, padding = 5;
        //Set the position, and the real size, based on the image index.
        switch(imgIndex) {
            case OBSTACLE_1:
                width = 50; height = 100;
                break;
            case OBSTACLE_2:
                width = 75; height = 100;
                break;
            case OBSTACLE_3:
                width = 100; height = 60;
                break;
            default:
                throw new IllegalArgumentException("Image index must be an obstacle image.");
        }
        
        size.setBounds(x + padding, 
                y-height + padding, 
                width - padding*2, 
                height - padding*2);
    }
    
    /**
     * <p>Checks whether this object is colliding with the given RunnerCharacter.</p>
     * <p>REQUIRES: rc != null.<br>
     * MODIFIES: None.<br>
     * EFFECTS: None.</p>
     * @param rc The RunnerCharacter to check for collisions.
     * @return true when there is a collision between both objects being considered.
     */
    public boolean isCollidingWith(RunnerCharacter rc) {
        return size.intersects(rc.size);
    }

    /**
     * <p>Updates the obstacle object.</p>
     * <p>REQUIRES: ios != null.<br>
     * MODIFIES: None<br>
     * EFFECTS: Checks whether there is a collision with the main character, and 
     * if there is collision, a DeathCommand is invoked.</p>
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
