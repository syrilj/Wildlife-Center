package edu.ucalgary.oop;

import java.util.TreeMap;

/**

The Coyote class represents a coyote animal in a wildlife system.

 It extends the Animal class and inherits its properties and methods.
 */
public class Coyote extends Animal {

    /**
     * The behavior of coyotes is crepuscular, meaning they are most active at dawn and dusk.
     */
    private static final String BEHAVIOR = "crepuscular";
    private static final String SPECIES = "coyote";
    /**

     Constructs a new coyote with the given ID, name, and orphaned status.
     @param id the unique ID of the coyote
     @param name the name of the coyote
     */
    public Coyote(String id, String name) {
        super(id, name);
    }

    /**
     *  @return the behavior of the coyote as a string
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

