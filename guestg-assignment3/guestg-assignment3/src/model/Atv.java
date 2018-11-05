/*
 * TCSS 305 - Easy Street
 * Assignment 3 - Backend fun
 */

package model;

import java.util.Map;

/**
 * ATV class that passes values to parent class for use by GUI.
 * 
 * @author Gehry Guest
 * @version 2.0
 */
public class Atv extends AbstractVehicle {

    /** Has constant death time constant for ATV. */
    private static final int MY_ATV_DEATHTIME = 15;
    
    /**
     * Takes abstract constructors parameters.
     * 
     * @param theX integer
     * @param theY integer
     * @param theDir direction
     */
    public Atv(final int theX, final int theY, final Direction theDir) {
        
        super(theX, theY, theDir, MY_ATV_DEATHTIME);
        
    }

    /**
     * Tells the GUI what terrain the ATV can and cannot pass.
     * 
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        
        return theTerrain != Terrain.WALL;
        
    }

    /**
     * Tells the GUI what direction the ATV should face.
     * 
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        
        return Direction.random();    
        
    }
}
