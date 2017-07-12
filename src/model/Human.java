/*
 * TCSS 305 Assignment 2-EasyStreet
 * Spring 2017
 */
package model;

import java.util.Map;

/**
 * Human vehicle.
 * @author Tyler Pitsch
 * @version 4/22/17
 *
 */

public class Human extends AbstractVehicle implements Vehicle {
    /**
     * The amount of time that this vehicle will stay dead.
     */
    private static final int DEATH_TIME = 50;
    /**
     * Main constructor for the Human.
     * @param theX value of where this vehicle starts on the map.
     * @param theY value of where this vehicle starts on the map.
     * @param theDirection this vehicle is facing initially.
     */
    public Human(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME);
    }
    /**
     * Check that a human can pass a given terrain type and light color. 
     * Humans can cross grass as well as crosswalks as long as they are yellow 
     * or red.
     * 
     * @param theTerrain the terrain we are testing to see if it a valid terrain 
     * @param theLight the current state of the lights on the map. 
     * @return if the human can go across the given terrain and light.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain == Terrain.GRASS || (theTerrain == Terrain.CROSSWALK
                        && theLight != Light.GREEN);
    }
    /**
     * Chooses the direction a human should go.  On grass humans will walk in a random
     * direction.  If they are near a crosswalk they will choose to stop and face it until
     * they can cross.  If there is no valid place for them to walk they wil turn around. 
     * @param theNeighbors a map containing the directions around this vehicle and the
     *  terrain in those directions.
     * @return the direction that the human should go on.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) { 
        Direction dir = Direction.random();
        if (theNeighbors.get(getDirection()) == Terrain.CROSSWALK) {
            dir = getDirection();
        } else if (theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK) {
            dir = getDirection().left();
        } else if (theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK) {
            dir = getDirection().right();
        } else if (theNeighbors.get(getDirection().reverse()) == Terrain.CROSSWALK) {
            dir = getRandomDirection(theNeighbors, dir);
        } 
        if (canMove(theNeighbors)) {
            dir = getRandomDirection(theNeighbors, dir);
        } else {
            dir = getDirection().reverse();
        }
        
        
        return dir;
    }
    
    /**
     * Helper for the choosedirection.  Scans around the human to see if there is any 
     * valid space for them to move on.
     * @param theNeighbors is the mpa conting the terrain around them
     * @return if they can move or not. if not it means they must reverse.
     */
    private boolean canMove(final Map<Direction, Terrain> theNeighbors) {
        return theNeighbors.get(getDirection()) == Terrain.GRASS
                        || theNeighbors.get(getDirection()) == Terrain.CROSSWALK
                        || theNeighbors.get(getDirection().left()) == Terrain.GRASS
                        || theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK
                        || theNeighbors.get(getDirection().right()) == Terrain.GRASS
                        || theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK;
    }
    /**
     * Helper for the chooseDirection. generates a random direction and checks
     *  to make sure that it is a good
     * path to take.
     * @param theNeighbors map containing 
     * @param theRanDir the random direction that was initial chosen, if invalid will 
     * be rerolled until it is valid.
     * @return the direction that the human should move in.
     */
    private Direction getRandomDirection(final Map<Direction, Terrain> theNeighbors,
                                         final Direction theRanDir) { 
        Direction dir = theRanDir;
        while (theNeighbors.get(dir) != Terrain.GRASS 
                        && theNeighbors.get(dir) != Terrain.CROSSWALK 
                        || dir == getDirection().reverse()) {
            dir = Direction.random();
        }
        return dir;
    }
}
