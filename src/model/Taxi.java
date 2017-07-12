/*
 * TCSS 305 Assignment 2-EasyStreet
 * Spring 2017
 */
package model;


/**
 * Taxi vehicle.
 * @author Tyler Pitsch
 * @version 4/22/17
 *
 */
public class Taxi extends Car implements Vehicle {
    /**
     * The amount of time that this vehicle will stay dead.
     */
    private static final int DEATH_TIME = 10;
    /**
     * A taxi will wait at a red crosswalk for 3 turns then continue on.
     */
    private static final int WAIT = 3;
    /**
     * Counter for when the taxi should rush forward through the 
     * red crosswalk.
     */
    private int myRush;
    /**
     * Main constructor for the Taxi.
     * @param theX value of where this vehicle starts on the map.
     * @param theY value of where this vehicle starts on the map.
     * @param theDirection this vehicle is facing initially.
     */
    public Taxi(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME);
        myRush = 0;
    }
    /**
     * Follows the same rules as a car but will rush though red crosswalks
     * after 3 turns if waiting.
     * @param theTerrain that we are testing to see if the Taxi can cross.
     * @param theLight the current status of the lights on the map.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean value = theTerrain == Terrain.STREET || (theTerrain == Terrain.LIGHT 
                        && theLight != Light.RED) || (theTerrain == Terrain.CROSSWALK 
                        && theLight == Light.GREEN || theLight == Light.YELLOW);
        if (theTerrain == Terrain.CROSSWALK && theLight == Light.RED) {
            if (myRush == WAIT) {
                value = true;
                myRush = 0;
            } else {
                myRush++;
            }
        }
        return value;
    }
    
    
}
