package edu.ucalgary.oop;

import java.util.TreeMap;

/**
 * The Porcupine class represents a porcupine animal in the system.
 * It extends the Animal class and provides the unique behavior of porcupines.
 */
public class Porcupine extends Animal {

    /**
     * The behavior of porcupines.
     */
    private static final String BEHAVIOR = "crepuscular";
    private static final String SPECIES = "porcupine";



    /**
     * Creates a new Porcupine object with the given id, name, and orphaned status.
     * @param id The unique identifier for the porcupine.
     * @param name The name of the porcupine.
     */


    public Porcupine(String id, String name) {
        super(id, name);
    }

    /**
     * Returns the behavior of the porcupine.
     * @return The behavior of the porcupine.
     */
    public String getBehavior() {
        return BEHAVIOR;
    }

    /**
     *  @return The ID of the animal
     */
    public String getAnimalId() {
        return super.getAnimalId();
    }

    
    /**
     *  @return The NickName of the animal
     */
    public String getNickName() {
        return super.getNickName();
    }

    /**
     *  @return true or false for weather the animal is an orphan
     */
    public boolean getOrphanStatus() {
        return super.getOrphanStatus();
    }


    /**
     *  @return The species of the animal
     */
    public String getSpecies() {
        return SPECIES;
    }

    /**
     *  @param name set the NickName of the animal
     */
    public void setNickName(String name) {
        super.setNickName(name);
    }

    /**
     *  @param status set the orphaned status of the animal
     */
    public void setOrphanStatus(boolean status) {
        super.setOrphanStatus(status);
    }
}


