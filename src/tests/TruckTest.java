/*
 * TCSS 305 Assignment 2-EasyStreet
 * Spring 2017
 */
package tests;

import static org.junit.Assert.*;

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
 * Test method for the truck.
 * @author Tyler Pitsch
 * @version 4/22/17
 *
 */
public class TruckTest {
    
    /**
     * 
     */
    private static final int RANDOM_COUNT = 50;

    /**
     * Test method for the Truck.
     */
    @Test
    public void testTruck() {
        final Truck t = new Truck(5, 3, Direction.SOUTH);
        assertEquals("Truck x failure", 5, t.getX());
        assertEquals("Truck y failure", 3, t.getY());
        assertEquals("Truck direction failure", Direction.SOUTH, t.getDirection());
        assertEquals("Truck death time failure", 0, t.getDeathTime());
        assertTrue("Truck isAlive() failure", t.isAlive());  
    }
    /**
     * Tests all the Trucks setters.
     */
    @Test
    public void testAllTruckSetters() {
        final Truck t = new Truck(1, 2, Direction.WEST);
        t.setX(5);
        t.setY(12);
        t.setDirection(Direction.EAST);
        assertEquals("Human setX failed!", 5, t.getX());
        assertEquals("Human setY failed!", 12, t.getY());
        assertEquals("Human setDirection failed!", Direction.EAST, t.getDirection());
    }
    /**
     * Tests the can pass method for Truck.
     */
    @Test
    public void testCanPass() {
        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.CROSSWALK);
        validTerrain.add(Terrain.LIGHT);
        
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        for (final Terrain destinationTerrain : Terrain.values()) {
            for (final Light currentLightCondition : Light.values()) {
                if (destinationTerrain == Terrain.STREET) {
                    assertTrue(truck.canPass(destinationTerrain, currentLightCondition));
                } else if (destinationTerrain == Terrain.CROSSWALK) {
                    if (currentLightCondition == Light.RED) {
                        assertFalse(truck.canPass(destinationTerrain,
                                          currentLightCondition));
                    } else {
                        assertTrue(truck.canPass(destinationTerrain,
                                          currentLightCondition));
                    }
                } else if (destinationTerrain == Terrain.LIGHT) {
                    assertTrue(truck.canPass(destinationTerrain, currentLightCondition));
                }
            } 
        }
    }
    
    /**
     * Test method for chooseDirection with all streets.
     */
    @Test
    public void testChooseDirectionStreets() {
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
        
        for (int count = 0; count < RANDOM_COUNT; count++) {
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
 
        assertTrue("Truck chooseDirection() fails to select randomly "
                   + "among all possible valid choices!",
                   seenWest && seenNorth && seenEast);
            
        assertFalse("Truck chooseDirection() reversed direction when not necessary!",
                    seenSouth);
    }
    
    /**
     * Test method for chooseDirection with all Lights.
     */
    @Test
    public void testChooseDirectionLights() {
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
        
        for (int count = 0; count < RANDOM_COUNT; count++) {
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
 
        assertTrue("Truck chooseDirection() fails to select randomly "
                   + "among all possible valid choices!",
                   seenWest && seenNorth && seenEast);
            
        assertFalse("Truck chooseDirection() reversed direction when not necessary!",
                    seenSouth);
    }
    
    /**
     * Test method for chooseDirection with all Xwalks.
     */
    @Test
    public void testChooseDirectionCrosswalks() {
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
        
        for (int count = 0; count < RANDOM_COUNT; count++) {
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
 
        assertTrue("Truck chooseDirection() fails to select randomly "
                   + "among all possible valid choices!",
                   seenWest && seenNorth && seenEast);
            
        assertFalse("Truck chooseDirection() reversed direction when not necessary!",
                    seenSouth);
    }
    /**
     * Tests that the Truck will turn around correctly.
     */
    @Test
    public void testChooseDirectionReverse() {
        for (final Terrain t : Terrain.values()) {
            if (t != Terrain.STREET && t != Terrain.CROSSWALK && t != Terrain.LIGHT) {
                
                final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
                neighbors.put(Direction.WEST, t);
                neighbors.put(Direction.NORTH, t);
                neighbors.put(Direction.EAST, t);
                neighbors.put(Direction.SOUTH, Terrain.STREET);
                
                final Truck truck = new Truck(0, 0, Direction.NORTH);
                assertEquals(Direction.SOUTH, truck.chooseDirection(neighbors));
            }
                
        }
    }
    
    
    
}
