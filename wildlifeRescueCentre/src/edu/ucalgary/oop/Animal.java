package edu.ucalgary.oop;

import java.time.LocalDateTime;
import java.util.TreeMap;

/**
 The Animal class represents an animal object with an ID, nickname, and orphan status.
 */
public abstract class Animal {
    private String animalId;
    private String nickName;
    private boolean isOrphaned;

    /**
     * Constructs an Animal object with the given ID, nickname, and orphan status.
     *
     * @param animalId the ID of the animal
     * @param nickName the nickname of the animal
     */

    public Animal(String animalId, String nickName) {
        this.animalId = animalId;
        this.nickName = nickName;
        this.isOrphaned = false; //not orphan by default
    }
    
    /**
     * Sets the ID of the animal.
     *
     * @param id the ID of the animal
     */
    public void setAnimalId(String id) {
        this.animalId = id;
    }

    /**
     * Returns the ID of the animal.
     *
     * @return the ID of the animal
     */
    public String getAnimalId() {
        return this.animalId;
    }

    /**
     * Returns the nickname of the animal.
     *
     * @return the nickname of the animal
     */
    public String getNickName() {
        return this.nickName;
    }

    /**
     * Sets the nickname of the animal. Throws an exception if the given nickname is null or empty.
     *
     * @param nickName the nickname of the animal
     * @throws IllegalArgumentException if the given nickname is null or empty
     */
    public void setNickName(String nickName) {
        if (nickName == null || nickName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nickname cannot be null or empty.");
        }
        this.nickName = nickName;
    }

    /**
     * Returns the orphan status of the animal.
     *
     * @return the orphan status of the animal
     */
    public boolean getOrphanStatus() {
        return this.isOrphaned;
    }

    /**
     * Sets the orphan status of the animal.
     *
     * @param status the orphan status of the animal
     */
    public void setOrphanStatus(boolean status) {
        this.isOrphaned = status;
    }

    /**
     * @return the behaviour of the animal
     */
    public abstract String getBehavior();

    /**
     * @return the species of the animal
     */
    public abstract String getSpecies();
}

