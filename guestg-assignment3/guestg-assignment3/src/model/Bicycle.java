/*
 * TCSS 305 - Easy Street
 * Assignment 3 - Backend fun
 */

package model;

import java.util.Map;

/**
 * Bicycle class that passes values to parent class for use by GUI.
 * 
 * @author Gehry Guest
 * @version 2.0
 */
public class Bicycle extends AbstractVehicle {
    
    /** Death time for Bicycle. */ 
    private static final int MY_BICYCLE_DEATHTIME = 20;
    
    /**
     * Constructs values for Bicycle, and passes them to parent class.
     * 
     * @param theX integer
     * @param theY integer
     * @param theDir direction
     */
    public Bicycle(final int theX, final int theY, final Direction theDir) {
        
        super(theX, theY, theDir, MY_BICYCLE_DEATHTIME);
        
    }
    
    /**
     * Tells the GUI what terrain the Bicycle can and cannot pass.
     * 
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        
        return theTerrain == Terrain.STREET 
                        || (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN)
                        || (theTerrain == Terrain.LIGHT && theLight == Light.GREEN)
                        || theTerrain == Terrain.TRAIL;
    }

    /**
     * Tells the GUI what terrain the Bicycle can and cannot pass.
     * 
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        Direction chosenDir = getDirection();
        
        if (theNeighbors.get(getDirection()) == Terrain.TRAIL) {
      
            chosenDir = getDirection();
            
        } else if (theNeighbors.get(getDirection().left()) == Terrain.TRAIL) {
      
            chosenDir = getDirection().left();
            
        } else if (theNeighbors.get(getDirection().right()) == Terrain.TRAIL) {
      
            chosenDir = getDirection().right();
            
        } else {
            
            return dirPicker(theNeighbors);
        }
        
        return chosenDir;  
        
    }
    
    /**
     * Helper method to reduce cyclomatic complexity in choose direction method.
     * 
     * @param theNeighborsHelper takes same inputs as choose direction method.
     * @return Direction for use in choose direction method.
     */
    private Direction dirPicker(final Map<Direction, Terrain> theNeighborsHelper) {
        
        Direction chosenDir = getDirection();
        
        if (theNeighborsHelper.get(getDirection()) == Terrain.STREET
                      || theNeighborsHelper.get(getDirection()) == Terrain.LIGHT
                      || theNeighborsHelper.get(getDirection()) == Terrain.CROSSWALK) {
          
            chosenDir = getDirection(); 
            
        } else if (theNeighborsHelper.get(getDirection().left()) == Terrain.STREET
                      || theNeighborsHelper.get(getDirection().left()) == Terrain.LIGHT
                      || theNeighborsHelper.get(getDirection().left()) == Terrain.CROSSWALK) {
          
            chosenDir = getDirection().left();
            
        } else if (theNeighborsHelper.get(getDirection().right()) == Terrain.STREET
                      || theNeighborsHelper.get(getDirection().right()) == Terrain.LIGHT
                      || theNeighborsHelper.get(getDirection().right()) == Terrain.CROSSWALK) {
          
            chosenDir = getDirection().right();
            
        } else {
          
            chosenDir = getDirection().reverse();
            
        }
        
        return chosenDir;
        
    }
}
