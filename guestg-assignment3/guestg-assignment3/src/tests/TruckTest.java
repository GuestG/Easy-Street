/*
 * TCSS 305 - Easy Street
 * Assignment 3 - Backend fun
 */

package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Direction;
import model.Light;
import model.Terrain;
import model.Truck;
import org.junit.Test;

/**
 * Unit testing for truck class.
 * 
 * @author Gehry Guest
 * @version 2.0
 */
public class TruckTest {
    
    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 100;
    
    /** Test method for Truck constructor. */
    @Test
    public void testHumanConstructor() {
        
        final Truck t = new Truck(10, 11, Direction.NORTH);
        
        assertEquals(10, t.getX());
        
        assertEquals(11, t.getY());
        
        assertEquals(Direction.NORTH, t.getDirection());
        
        assertEquals(0, t.getDeathTime());
        
        assertTrue(t.isAlive());
        
    }
    
    /** Test method for Truck setters. */
    @Test
    public void testTruckSetters() {
        final Truck t = new Truck(5, 15, Direction.NORTH);
        
        t.setX(7);
        assertEquals(7, t.getX());
        
        t.setY(8);
        assertEquals(8, t.getY());
        
        t.setDirection(Direction.EAST);
        assertEquals(Direction.EAST, t.getDirection());
        
    }
    
    /**
     * Test method for {@link Truck#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {
        
        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.LIGHT);
        validTerrain.add(Terrain.CROSSWALK);
                
        final Truck t = new Truck(0, 0, Direction.NORTH);
        
        // test each terrain type as a destination
        for (final Terrain destinationTerrain : Terrain.values()) {
            
            // try the test under each light condition
            for (final Light currentLightCondition : Light.values()) {
                
                // if it can pass a light, color doesnt matter.
                if (destinationTerrain == Terrain.LIGHT) {
                
                    assertTrue(t.canPass(destinationTerrain, currentLightCondition));
                    
                } else if (destinationTerrain == Terrain.CROSSWALK) {

                    //cannot pass red lights on crosswalks.
                    if (currentLightCondition == Light.RED) {
                        
                        assertFalse(t.canPass(destinationTerrain,
                                              currentLightCondition));
                        
                    } else { // light is yellow or green.
                        
                        assertTrue(t.canPass(destinationTerrain,
                                          currentLightCondition));
                        
                    }
                } else if (!validTerrain.contains(destinationTerrain)) {
 
                    assertFalse(t.canPass(destinationTerrain, currentLightCondition));
                    
                }
                if (destinationTerrain == Terrain.STREET) {
                    
                    assertTrue(t.canPass(destinationTerrain, currentLightCondition));
                    
                }
            } 
        }
    }
    
    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionStreet() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.STREET);
        neighbors.put(Direction.NORTH, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.STREET);
        neighbors.put(Direction.SOUTH, Terrain.STREET);
        
        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;
        
        final Truck t = new Truck(0, 0, Direction.NORTH);
        
        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
            
            final Direction d = t.chooseDirection(neighbors);
            
            if (d == Direction.WEST) {
                
                seenWest = true;
                
            } else if (d == Direction.NORTH) {
                
                seenNorth = true;
                
            } else if (d == Direction.EAST) {
                
                seenEast = true;
                
            } else if (d == Direction.SOUTH) {
                
                seenSouth = true;
                
            }
        }
 
        assertTrue(seenWest && seenNorth && seenEast);
            
        assertFalse(seenSouth);
    }
    
    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionLight() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.LIGHT);
        neighbors.put(Direction.NORTH, Terrain.LIGHT);
        neighbors.put(Direction.EAST, Terrain.LIGHT);
        neighbors.put(Direction.SOUTH, Terrain.LIGHT);
        
        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;
        
        final Truck t = new Truck(0, 0, Direction.NORTH);
        
        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
            
            final Direction d = t.chooseDirection(neighbors);
            
            if (d == Direction.WEST) {
                
                seenWest = true;
                
            } else if (d == Direction.NORTH) {
                
                seenNorth = true;
                
            } else if (d == Direction.EAST) {
                
                seenEast = true;
                
            } else if (d == Direction.SOUTH) {
                
                seenSouth = true;
                
            }
        }
 
        assertTrue(seenWest && seenNorth && seenEast);
            
        assertFalse(seenSouth);
    }
    
    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionCrossWalk() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.CROSSWALK);
        neighbors.put(Direction.NORTH, Terrain.CROSSWALK);
        neighbors.put(Direction.EAST, Terrain.CROSSWALK);
        neighbors.put(Direction.SOUTH, Terrain.CROSSWALK);
        
        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;
        
        final Truck t = new Truck(0, 0, Direction.NORTH);
        
        for (int count = 0; count < TRIES_FOR_RANDOMNESS; count++) {
            
            final Direction d = t.chooseDirection(neighbors);
            
            if (d == Direction.WEST) {
                
                seenWest = true;
                
            } else if (d == Direction.NORTH) {
                
                seenNorth = true;
                
            } else if (d == Direction.EAST) {
                
                seenEast = true;
                
            } else if (d == Direction.SOUTH) {
                
                seenSouth = true;
                
            }
        }
 
        assertTrue(seenWest && seenNorth && seenEast);
            
        assertFalse(seenSouth);
    }
    
    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionReverse() {
        
        for (final Terrain t : Terrain.values()) {
            
            if (t != Terrain.STREET && t != Terrain.CROSSWALK &&  t != Terrain.LIGHT) {
                
                final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
                
                neighbors.put(Direction.WEST, t);
                neighbors.put(Direction.NORTH, t);
                neighbors.put(Direction.EAST, t);
                neighbors.put(Direction.SOUTH, Terrain.STREET);
                
                final Truck tr = new Truck(0, 0, Direction.NORTH);
                
                assertEquals(Direction.SOUTH, tr.chooseDirection(neighbors));
            }
                
        }
    }
}
