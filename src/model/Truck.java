/*
 * TCSS 305 Assignment 2-EasyStreet
 * Spring 2017
 */
package model;

import java.util.Map;

/**
 * Truck vehicle.
 * @author Tyler Pitsch
 * @version 4/22/17
 *
 */
public class Truck extends AbstractVehicle implements Vehicle {
    /**
     * The amount of time that this vehicle will stay dead.
     */
    private static final int DEATH_TIME = 0;
    /**
     * Main constructor for the Truck.
     * @param theX value of where this vehicle starts on the map.
     * @param theY value of where this vehicle starts on the map.
     * @param theDirection this vehicle is facing initially.
     */
    public Truck(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME);
    }
    
    /**
     * Checks to see if the truck can go through the given terrain and 
     * the current light set of the map.  Trucks can go through all 
     * lights no matter the color and crosswalks that are not red.  They 
     * also travel along the streets.
     * @param theTerrain that we are testing if we can cross.
     * @param theLight the color of the lights on the map.
     * @return if the Truck can move or not.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        
        return theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT 
                        || (theTerrain == Terrain.CROSSWALK && theLight == Light.GREEN
                        || theLight == Light.YELLOW);
    }
    /**
     * Chooses which way the truck will move in.  It will move in a random direction
     * if there is no place for it to move it will reverse.
     * @param theNeighbors a map of all the terrain in a given direction around this truck.
     * @return the direction that we should move in.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {

        Direction dir = Direction.random();
        if (canMove(theNeighbors)) {
            while (theNeighbors.get(dir) != Terrain.STREET
                            && theNeighbors.get(dir) != Terrain.LIGHT
                            && theNeighbors.get(dir) != Terrain.CROSSWALK
                            || dir == getDirection().reverse()) {
                dir = Direction.random();
            }
        } else {
            dir = getDirection().reverse();
        }
        return dir;
    }
    /**
     * Helper for the Truck, decides if it can move in a direction based on the map.
     * @param theNeighbors of the current position of this truck.
     * @return if the truck has a valid place to move to. 
     */
    private boolean canMove(final Map<Direction, Terrain> theNeighbors) {
        return theNeighbors.get(getDirection()) == Terrain.STREET 
                || theNeighbors.get(getDirection()) == Terrain.LIGHT
                || theNeighbors.get(getDirection()) == Terrain.CROSSWALK
                || theNeighbors.get(getDirection().left()) == Terrain.STREET 
                || theNeighbors.get(getDirection().left()) == Terrain.LIGHT
                || theNeighbors.get(getDirection().left()) == Terrain.CROSSWALK
                || theNeighbors.get(getDirection().right()) == Terrain.STREET 
                || theNeighbors.get(getDirection().right()) == Terrain.LIGHT
                || theNeighbors.get(getDirection().right()) == Terrain.CROSSWALK;
    }
    
}
