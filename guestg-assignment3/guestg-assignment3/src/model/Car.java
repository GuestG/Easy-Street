/*
 * TCSS 305 - Easy Street
 * Assignment 3 - Backend fun
 */

package model;

import java.util.Map;

/**
 * Car class that passes values to parent class for use by GUI.
 * 
 * @author Gehry Guest
 * @version 2.0
 */
public class Car extends AbstractVehicle {
    
    /** Death timer for Car. */
    private static final int MY_CAR_DEATHTIME = 5;
    
    /**
     * Constructs values for car and passes them to parent class.
     * 
     * @param theX integer
     * @param theY integer
     * @param theDir Direction
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        
        super(theX, theY, theDir, MY_CAR_DEATHTIME);
        
    }
    
    /**
     * Tells the GUI what terrain the Car can and cannot pass.
     * 
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        
        return theTerrain == Terrain.STREET 
                        || (theTerrain == Terrain.LIGHT && theLight != Light.RED)
                        || (theTerrain == Terrain.CROSSWALK && theLight != Light.RED)
                        || (theTerrain == Terrain.CROSSWALK && theLight != Light.YELLOW);
    }
    
    /**
     * Tells the GUI what terrain the Car can and cannot pass.
     * 
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {

        Direction dir = getDirection();
        
        if (theNeighbors.get(getDirection()) == Terrain.STREET 
                        || theNeighbors.get(getDirection()) == Terrain.LIGHT
                        || theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
           
            dir = getDirection();
            
        } else if (theNeighbors.get(getDirection().left()) == Terrain.STREET 
                        || theNeighbors.get(getDirection().left()) == Terrain.LIGHT
                        || theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
               
            dir  = getDirection().left();  
            
        } else if (theNeighbors.get(getDirection().right()) == Terrain.STREET 
                        || theNeighbors.get(getDirection().right()) == Terrain.LIGHT
                        || theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
            
            dir = getDirection().right();
            
        } else {
            
            dir = getDirection().reverse();
            
        } 
        
        return dir;
        
    }
}
