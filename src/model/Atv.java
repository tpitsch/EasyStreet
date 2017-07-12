/*
 * TCSS 305 Assignment 2-EasyStreet
 * Spring 2017
 */
package model;

import java.util.Map;

/**
 * ATV vehicle.
 * 
 * @author Tyler Pitsch
 * @version 4/22/17
 *
 */
public class Atv extends AbstractVehicle implements Vehicle {
    /**
     * The amount of time that this vehicle will stay dead.
     */
    private static final int DEATH_TIME = 20;
    /**
     * Main constructor for the atv vehicle.
     * 
     * @param theX value of where this vehicle starts on the map.
     * @param theY value of where this vehicle starts on the map.
     * @param theDirection this vehicle is facing initially.
     */
    public Atv(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME);
    }
    
    /**
     * Determines if the atv can cross certain terrain with the current lights.
     * an atv can cross any terrain with any light color except for the wall.
     * @param theTerrain the terrain we are testing to see if it a valid terrain 
     * @param theLight the current state of the lights on the map. 
     * @return if the atv can go across the given terrain and light.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean result = true;
        if (theTerrain == Terrain.WALL) {
            result = false;
        }
        return result;
    }
    /**
     * Decides the direction that the atv should go in.  atv will choose
     *  a random direction to go in but never reverse or if the direction it 
     *  chooses is a wall.
     *  @param theNeighbors a map containing all the directions around this 
     *  vehicle and the terrain that is in those directions.
     *  @return the direction that the atv wants to move in.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction dir = Direction.random();
        while (theNeighbors.get(dir) == Terrain.WALL
                        || dir == getDirection().reverse()) {
            dir = Direction.random();
        }
        
        return dir;
    }
 

}
