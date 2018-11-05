/*
 * TCSS 305 - Easy Street
 * Assignment 3 - Backend fun
 */

package model;

/**
 * Abstract class for use when creating vehicles by having defaults, to be 
 * used in subclasses.
 * 
 * @author Gehry Guest
 * @version 2.0
 */
public abstract class AbstractVehicle implements Vehicle {
    
    /** Holds starting y value for objects. */
    private final int myResetX;
    
    /** Holds starting y value for objects. */
    private final int myResetY;
    
    /** Holds starting direction vehicle is facing value for objects. */
    private final Direction myResetDir;
    
    /** Holds x integer value for objects. */
    private int myX;
    
    /** Holds y integer value for objects. */
    private int myY;
    
    /** Holds direction vehicle is facing value for objects. */
    private Direction myDir;
    
    /** Holds starting direction vehicle is facing value for objects. */
    private int myDeathTime;
    
    /** Holds starting direction vehicle is facing value for objects. */
    private boolean myVehicleIsAlive;
    
    /** Holds how many pokes left to be revived. */
    private int myDeathTimeCount;
    
    /**
     * Constructs Values for use in GUI. 
     * 
     * @param theX takes x values of vehicles.
     * @param theY takes y values of vehicles.
     * @param theDir takes the direction of vehicles.
     * @param theDeathTime takes the deathTime of vehicles.
     */
    public AbstractVehicle(final int theX, final int theY, final Direction theDir, 
                           final int theDeathTime) {
        myX = theX;
        myY = theY;
        myDir = theDir;
        myResetX = theX;
        myResetY = theY;
        myResetDir = theDir;
        myDeathTime = theDeathTime;
        myDeathTimeCount = 0;
        myVehicleIsAlive = true;
    }
    
    /**
     * Determines if vehicles have collided.
     * 
     */
    @Override
    public void collide(final Vehicle theOther) {
        if (myDeathTime == theOther.getDeathTime()) {
        
            myVehicleIsAlive = true;
            
        } else if (isAlive() && theOther.isAlive()) {
        
            myDeathTimeCount = myDeathTime;
            myVehicleIsAlive = myDeathTime < theOther.getDeathTime();
            
        } 
    }
    
    /**
     * Tells death time of vehicle.
     * 
     */
    @Override
    public int getDeathTime() {
        
        return myDeathTime;
    }

    /**
     * Grabs image of vehicle by using the class name.
     * 
     */
    @Override
    public String getImageFileName() {
        if (myVehicleIsAlive) {

            return getClass().getSimpleName().toLowerCase() + ".gif";
        }
       
        return getClass().getSimpleName().toLowerCase() + "_dead.gif";
    }

    /**
     * Gets the direction that the vehicle is facing.
     * 
     * @return direction vehicle is facing.
     */
    @Override
    public Direction getDirection() {
        
        return myDir;
    }

    /**
     * Gets the x value for vehicle.
     * 
     * @return myX value for which vehicle is at.
     */
    @Override
    public int getX() {

        return myX;
    }
    
    /**
     * Gets the y value for vehicle.
     * 
     * @return myY value for which vehicle is at.
     */
    @Override
    public int getY() {

        return myY;
    }

    /**
     * Tells if vehicle is Alive.
     * 
     * @return boolean value for vehicle.
     */
    @Override
    public boolean isAlive() {

        return myVehicleIsAlive;
    }
    
    /**
     * A death timer, to know how long vehicle is dead.
     * 
     */
    @Override
    public void poke() {

        if (isAlive()) {
        
            myDir = Direction.random();
            
        } else {
            
            if (myDeathTimeCount == 0) {
            
                myVehicleIsAlive = true;
                
            }
            
            myDeathTimeCount -= 1;
            
        }
    }

    /**
     * Resets all vehicles to initial positions.
     * 
     */
    @Override
    public void reset() {
        
        myX = myResetX;
        myY = myResetY;
        myDir = myResetDir;
        myVehicleIsAlive = true;
    }

    /**
     * sets direction of vehicle.
     * 
     */
    @Override
    public void setDirection(final Direction theDir) {

        myDir = theDir;
    }

    /**
     * Sets x value for vehicle.
     * 
     */
    @Override
    public void setX(final int theX) {
        
        myX = theX;
    }

    /**
     * Sets y value for vehicle.
     * 
     */
    @Override
    public void setY(final int theY) {
        
        myY = theY;
    }
    
    /**
     * This is called by GUI to display name in debug mode.
     * 
     * @return String of class.
     */
    @Override
    public String toString() {
        
        final String className = getClass().getSimpleName();
        return className;
    }
}
