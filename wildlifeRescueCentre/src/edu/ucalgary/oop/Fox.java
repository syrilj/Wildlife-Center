package edu.ucalgary.oop;

import java.util.TreeMap;

/**
 Represents a fox animal that extends the Animal class.
 */
public class Fox extends Animal {
    // Behavior of the fox
    private static final String BEHAVIOR = "nocturnal";
    private static final String SPECIES = "fox";

    /**
     Constructs a new Fox object with the given id, name, and orphaned status.
     @param id the id of the fox
     @param name the name of the fox
     */
    public Fox(String id, String name) {
        super(id, name);
    }

    /**
     *  @return the behavior of the fox
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