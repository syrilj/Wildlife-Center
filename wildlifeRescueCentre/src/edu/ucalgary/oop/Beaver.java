package edu.ucalgary.oop;

import java.util.TreeMap;

/**

 The Beaver class is a subclass of the Animal class, representing a beaver in the animal shelter system.
 */
public class Beaver extends Animal {
    /**
    The behavior of beavers, which is diurnal.
    */
    private static final String BEHAVIOUR = "diurnal";
    private static final String SPECIES = "beaver";
    /**

     Constructs a new Beaver object with the specified ID, name, and orphaned status.
     @param id the ID of the beaver
     @param name the name of the beaver
     */
    public Beaver(String id, String name) {
        super(id, name);
    }
    /**

     Returns the behavior of beavers, which is diurnal.
     @return the behavior of beavers
     */
    public String getBehavior() {
        return BEHAVIOUR;
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
