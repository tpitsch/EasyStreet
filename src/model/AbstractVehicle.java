/*
 * TCSS 305 Assignment 2-EasyStreet
 * Spring 2017
 */
package model;

import java.util.Locale;
import java.util.Map;

/**
 * Super class for all the vehicles.
 * 
 * @author Tyler Pitsch
 * @version 4/22/17
 *
 */
public abstract class AbstractVehicle implements Vehicle {
    /**
     * Initial x value of the object on the map.
     */
    private final int myInitialX;
    /**
     * Initial y value of the object on the map.
     */
    private final int myInitialY;
    /**
     * The initial direction that this object is facing.
     */
    private final Direction myInitalDir;
    /**
     * The current x value of the object on the map.
     */
    private int myX;
    /**
     * The current y value of the object on the map.
     */
    private int myY;
    /**
     * The current direction that this object is facing.
     */
    private Direction myDirection;
    /**
     * How long this object is going to be dead for.
     */
    private final int myDeathTime;
    /**
     * Ticker for how many turns have passed since the collision.
     */
    private int myDeathTicker;
    /**
     * Main constructor for the vehicle.
     * 
     * @param theX value of the x coordinate on the map.
     * @param theY value of the y coordinate on the map.
     * @param theDirection the cardinal direction that this vehicle is facing on the map.
     * @param theDeathTime is the amount of time this vehicle will spend dead if colided with.
     */
    public AbstractVehicle(final int theX, final int theY, final Direction theDirection,
                           final int theDeathTime) {
        myX = theX;
        myY = theY;
        myDirection = theDirection;
        myInitialX = theX;
        myInitialY = theY;
        myInitalDir = theDirection;
        myDeathTime = theDeathTime;
        myDeathTicker = myDeathTime;
    }
    /**
     * canPass holder for the sub-classes to implement per specification.
     * @param theTerrain of the square that we are looking at.
     * @param theLight color of all the lights on the map.
     * @return if the terrain is something that can be passed based on the light color.
     */
    @Override
    public abstract boolean canPass(Terrain theTerrain, Light theLight);

    /**
     * chooseDirection holder for the sub-classes to implement per specification.
     * @param theNeighbors map containing the information about the terrain around a vehicle.
     * @return the direction that this vehicle would like to go in.
     */
    @Override
    public abstract Direction chooseDirection(Map<Direction, Terrain> theNeighbors);
    /**
     * Determines what will happen if two vehicles are occupying the same spot.
     * @param theOther the other vehicle that is in this vehicles spot.
     */
    @Override
    public void collide(final Vehicle theOther) {
        if ((isAlive() && theOther.isAlive()) && (myDeathTime > theOther.getDeathTime())) {
            myDeathTicker = 0;
        }
    }

    /**
     * Returns the total amount of ticks that this object will be dead for. 
     * @return the number of ticks this vehicle will be dead for total.
     */
    @Override
    public int getDeathTime() {
        return myDeathTime;
    }

    /**
     * Generates the name of the image to be used, different if the item is dead or alive.
     * @return the name of the image file to be opened and used.
     */
    @Override
    public String getImageFileName() {
        String name = "";
        if (isAlive()) {
            name = getClass().getSimpleName().toLowerCase(Locale.ENGLISH) + ".gif";
        } else {
            name = getClass().getSimpleName().toLowerCase(Locale.ENGLISH) + "_dead.gif";
        }
        return name;
    }

    /**
     * Returns the direction that this vehicle is facing.
     * @return the direction that this vehicle is facing.
     */
    @Override
    public Direction getDirection() {
        return myDirection;
    }

    /**
     * Gets the x value of the locations this vehicle is currently in.
     * @return the x value of this vehicle on the map.
     */
    @Override
    public int getX() {
        return myX;
    }
    /**
    * Gets the y value of the locations this vehicle is currently in.
    * @return the y value of this vehicle on the map.
    */
    @Override
    public int getY() {
        return myY;
    }
    /**
     * Checks the vehicles death time and ticker to determine if it is alive.
     */
    @Override
    public boolean isAlive() {
        return myDeathTime == myDeathTicker;
    }

    /**
     * Adds a turn to the death ticker, if the item is alive after this then give 
     * it a random direction to move in next turn.
     */
    @Override
    public void poke() {
        myDeathTicker++;
        if (isAlive()) {
            myDirection = Direction.random();
        }
    }

    /**
     * Puts the vehicle back at its original position from when it was made.
     */
    @Override
    public void reset() {
        myX = myInitialX;
        myY = myInitialY;
        myDirection = myInitalDir;
        myDeathTicker = myDeathTime;

    }

    /**
     * Sets the current direction that this vehicle is facing.
     */
    @Override
    public void setDirection(final Direction theDir) {
        myDirection = theDir;
    }

    /**
     * Sets the x value for the vehicle.
     */
    @Override
    public void setX(final int theX) {
        myX = theX;
    }

    /**
     * Sets the y value for the vehicle.
     */
    @Override
    public void setY(final int theY) {
        myY = theY;
    }
    
    /**
     * Return a string representation of this vehicle.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName().toLowerCase(Locale.ENGLISH);
    }

}
