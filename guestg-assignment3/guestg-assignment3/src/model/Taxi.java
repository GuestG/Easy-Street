/*
 * TCSS 305 - Easy Street
 * Assignment 3 - Backend fun
 */

package model;

import java.util.Map;

/**
 * Taxi class that passes values to parent class for use by GUI.
 * 
 * @author Gehry Guest
 * @version 2.0
 */
public class Taxi extends AbstractVehicle { 
    
    /** Death timer for Taxi. */
    private static final int MY_TAXI_DEATHTIME = 10;
    
    /** Pause time for red cross walk light. */
    private int myRestCount;
   
    /** Pause time threshold for red cross walk light.*/
    private final int myTaxiRestCount = 3;
    
    /**
     * Taxi class that is a subclass of car, passes values to parent class for use by GUI.
     * 
     * @param theX integer
     * @param theY integer
     * @param theDir direction
     */
    public Taxi(final int theX, final int theY, final Direction theDir) {
        
        super(theX, theY, theDir, MY_TAXI_DEATHTIME);
        
    }
    
    /**
     * Tells the GUI what terrain the Taxi can and cannot pass.
     * 
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        
        if (theTerrain == Terrain.STREET 
                || (theTerrain == Terrain.LIGHT && theLight != Light.RED)
                || (theTerrain == Terrain.CROSSWALK && theLight != Light.RED)) {
        
            return true;
            
        } 
        
        if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED) {
        
            myRestCount += 1;
            
        } 
    
        if (myRestCount == myTaxiRestCount) {
            
            myRestCount = 0;
            return true;
            
        } 
        
        return false;
        
    }
    
    /**
     * Tells the GUI what terrain the Taxi can and cannot pass.
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
