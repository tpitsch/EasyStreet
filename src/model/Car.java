/*
 * TCSS 305 Assignment 2-EasyStreet
 * Spring 2017
 */

package model;

import java.util.Map;

/**
 * Car vehicle.
 * @author Tyler Pitsch
 * @version 4/22/17
 *
 */
public class Car extends AbstractVehicle implements Vehicle {
    /**
     * The amount of time that this vehicle will stay dead.
     */
    private static final int DEATH_TIME = 10;
    /**
     * Main constructor for the Car.
     * @param theX value of where this vehicle starts on the map.
     * @param theY value of where this vehicle starts on the map.
     * @param theDirection this vehicle is facing initially.
     */
    public Car(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME);
    }
    /**
     * Constructor for the taxi to use.
     * @param theX value of where this vehicle starts on the map.
     * @param theY value of where this vehicle starts on the map.
     * @param theDirection this vehicle is facing initially.
     * @param theDeathTime the amount of time this object is to remain dead.
     */
    public Car(final int theX, final int theY, final Direction theDirection,
               final int theDeathTime) {
        super(theX, theY, theDirection, theDeathTime);
    }
    /**
     * Checks to see if the terrain and lights can be crossed by a car. 
     * A car can move across streets as well as lights and crosswalks
     * as long as the lights are not red.
     * 
     * @param theTerrain the terrain we are testing to see if it a valid terrain 
     * @param theLight the current state of the lights on the map. 
     * @return if the car can go across the given terrain and light.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return theTerrain == Terrain.STREET || (theTerrain == Terrain.LIGHT 
                        && theLight != Light.RED) || (theTerrain == Terrain.CROSSWALK 
                        && theLight == Light.GREEN);
    }
    /**
     * Chooses the direction a car should go.  It will choose to go straight, then left
     * then right, if none of those are available then it will turn around.
     * @param theNeighbors a map containing the directions around this vehicle and the
     *  terrain in those directions.
     * @return the direction that the car should go on.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction dir = getDirection();
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
