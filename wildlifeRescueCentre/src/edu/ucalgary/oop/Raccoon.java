package edu.ucalgary.oop;

import java.util.TreeMap;

/**

 Represents a Raccoon, which is a type of Animal.
 */
public class Raccoon extends Animal {
    /**
     The behavior of all raccoons, which is "nocturnal".
     */
    private static final String BEHAVIOR = "nocturnal";
    private static final String SPECIES = "raccoon";

    /**
     Creates a new Raccoon object with the given ID, name, and orphaned status.
     @param id the ID of the raccoon
     @param name the name of the raccoon
     */
    public Raccoon(String id, String name) {
        super(id, name);
    }
    /**
     Gets the behavior of the raccoon.
     @return the behavior of the raccoon, which is always "nocturnal"
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
}
