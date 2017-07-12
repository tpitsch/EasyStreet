/*
 * TCSS 305 Assignment 2-EasyStreet
 * Spring 2017
 */
package model;

import java.util.Map;

/**
 * Bicycle vehicle.
 * @author Tyler Pitsch
 * @version 4/22/17
 *
 */
public class Bicycle extends AbstractVehicle implements Vehicle {
    /**
     * The amount of time that this vehicle will stay dead.
     */
    private static final int DEATH_TIME = 30;
    /**
     * Main constructor for the Bicycle.
     * @param theX value of where this vehicle starts on the map.
     * @param theY value of where this vehicle starts on the map.
     * @param theDirection this vehicle is facing initially.
     */
    public Bicycle(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME);
    }
    
    /**
     * Determines if the bicycle can pass the given terrain with the current state of the 
     * lights.  Bicycles can pass over trails and streets as well as lights and 
     * crosswalks as long as they
     * have green lights.
     * 
     * @param theTerrain the terrain we are testing to see if it a valid terrain 
     * @param theLight the current state of the lights on the map. 
     * @return if the bicycle can go across the given terrain and light.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain == Terrain.TRAIL || theTerrain == Terrain.STREET
                        || (theTerrain == Terrain.LIGHT && theLight == Light.GREEN)
                        || (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN);
    }
    
    /**
     * Chooses a direction that the bicycle should go.  Bicycles want to go on trails if they 
     * are near them but will travel on streets going straight, then left, then right, and if 
     * all those fail it will turn around.
     * 
     * @param theNeighbors a map containing the directions around this vehicle and the
     *  terrain in those directions.
     * @return the direction that the bicycle wants to go.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction dir = getDirection();
        if (theNeighbors.get(getDirection()) == Terrain.TRAIL) {
            dir = getDirection();
        } else if (theNeighbors.get(getDirection().left()) == Terrain.TRAIL) {
            dir = getDirection().left();
        } else if (theNeighbors.get(getDirection().right()) == Terrain.TRAIL) {
            dir = getDirection().right();
        } else if (theNeighbors.get(getDirection().reverse()) == Terrain.TRAIL) {
            dir = streetDirection(theNeighbors, dir);
        } else {
            dir = streetDirection(theNeighbors, dir);
        }
        return dir;
    }
    /**
     * Helper method for the choose direction.  Takes care of decisions that need to 
     * be made on streets
     * @param theNeighbors a map containing the directions around this vehicle and the
     *  terrain in those directions.
     * @param theDir that this bicycle is facing.
     * @return the direction that the bicycle should go on a street.
     */
    private Direction streetDirection(final Map<Direction, Terrain> theNeighbors,
                                      final Direction theDir) {
        Direction dir = theDir;
        if (theNeighbors.get(getDirection()) == Terrain.STREET 
                        || theNeighbors.get(getDirection()) == Terrain.LIGHT
                        || theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
            dir = getDirection();
        
        } else if (theNeighbors.get(getDirection().left()) == Terrain.STREET 
                        || theNeighbors.get(getDirection().left()) == Terrain.LIGHT
                        || theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
            dir = dir.left();
            
        } else if (theNeighbors.get(getDirection().right()) == Terrain.STREET 
                        || theNeighbors.get(getDirection().right()) == Terrain.LIGHT
                        || theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
            dir = dir.right();
        } else {
            dir = dir.reverse();
        }
        return dir;
    }
    
    
    
    
}
