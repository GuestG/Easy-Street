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
 * Human class that passes values to parent class for use by GUI.
 * 
 * @author Gehry Guest
 * @version 2.0
 */
public class Human extends AbstractVehicle {
    
    /** Death timer for Human. */
    private static final int MY_HUMAN_DEATHTIME = 25;
    
    
    /**
     * Constructs values for Human and passes them to parent class.
     * 
     * @param theX integer
     * @param theY integer
     * @param theDir direction
     */
    public Human(final int theX, final int theY, final Direction theDir) {
        
        super(theX, theY, theDir, MY_HUMAN_DEATHTIME);
    }

    /**
     * Tells the GUI what terrain the Human can and cannot pass.
     * 
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        
        return theTerrain == Terrain.GRASS 
                        || (theTerrain == Terrain.CROSSWALK && theLight == Light.YELLOW)
                        || (theTerrain == Terrain.CROSSWALK && theLight == Light.RED);
    }

    /**
     * Tells the Human what Direction to face.
     * 
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
     
        final List<Direction> possibleDir = new ArrayList<Direction>();
       
        final Random rand = new Random();
        
        Direction crossWalkDir = getDirection().reverse();
        
        if (theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
        
            crossWalkDir = getDirection();
            
        } else if (theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
        
            crossWalkDir = getDirection().left();
            
        } else if (theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
        
            crossWalkDir = getDirection().right();
            
        } else if (theNeighbors.get(getDirection()) == Terrain.GRASS) {
        
            possibleDir.add(getDirection());
            
        } else if (theNeighbors.get(getDirection().left()) == Terrain.GRASS) {
            
            possibleDir.add(getDirection().left());
            
        } else if (theNeighbors.get(getDirection().right()) == Terrain.GRASS) {
            
            possibleDir.add(getDirection().right());
            
        }  
        
        if (!possibleDir.isEmpty()) {
          
            final int randDirPick = rand.nextInt(possibleDir.size());
            return possibleDir.get(randDirPick);
            
        } 
         
        return crossWalkDir;

    } 
}
