/*
 * TCSS 305 - Easy Street
 * Assignment 3 - Backend fun
 */

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Truck class that passes values to parent class for use by GUI.
 * 
 * @author Gehry Guest
 * @version 2.0
 */
public class Truck extends AbstractVehicle {
    
    /** Death timer for Truck. */
    private static final int MY_TRUCK_DEATHTIME = 0;
    
    
    /**
     * Constructs values, passes trucks unique values to super class to use.
     * 
     * @param theX integer
     * @param theY integer
     * @param theDir direction
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
        
        super(theX, theY, theDir, MY_TRUCK_DEATHTIME);
        
    }
    
    /**
     * Tells the GUI what terrain the Truck can and cannot pass.
     * 
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        
        return theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT 
                        || (theTerrain == Terrain.CROSSWALK && theLight != Light.RED);
    }
    
    /**
     * Tells the GUI what direction the Truck should face.
     * 
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        final List<Direction> possibleDir = new ArrayList<Direction>(); 
        
        
        if (theNeighbors.get(getDirection()) == Terrain.STREET 
                        || theNeighbors.get(getDirection()) == Terrain.LIGHT
                        || theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
            
            possibleDir.add(getDirection());
            
        }
        
        if (theNeighbors.get(getDirection().left()) == Terrain.STREET 
                        || theNeighbors.get(getDirection().left()) == Terrain.LIGHT
                        || theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
            
            possibleDir.add(getDirection().left());  
        }
        
        if (theNeighbors.get(getDirection().right()) == Terrain.STREET 
                        || theNeighbors.get(getDirection().right()) == Terrain.LIGHT
                        || theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
            
            possibleDir.add(getDirection().right());
        }

        return possibleDirHelper(possibleDir);
    }
    
    /**
     * helper method to reduce cyclomatic complexity, returns direction from the list.
     * 
     * @param theDirList the list created from chooseDirection method.
     * @return a direction in the given list.
     */
    private Direction possibleDirHelper(final List<Direction> theDirList) {
        
        final Random rand = new Random();
        
        if (!theDirList.isEmpty()) {
            
            final int randDirPick = rand.nextInt(theDirList.size());
            return theDirList.get(randDirPick);
            
        } 
        
        return getDirection().reverse();  
        
    }
}

