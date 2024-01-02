package edu.ucalgary.oop;


/**
 * Represents a task that needs to be performed by a volunteer for a specific animal.
 */
public class Task {

    //new
    private int taskId;
    private int startHour;
    private int duration;
    private int maxWindow;
    private String taskType;
    private Animal animal;
    private boolean extraVolunteer;
    private int prepTime;

    /**
     * constructor for Task
     * @param taskId id of the task
     * @param startHour starting hour of the task
     * @param duration of the task
     * @param maxWindow that the task can be completed in
     * @param taskType is the description of the task
     * @param animal that the task is for
     * @param prepTime how long it takes to prep for a task
     */
    public Task(int taskId, int startHour, int duration, int maxWindow,
                String taskType, Animal animal, int prepTime) throws IllegalArgumentException{
        if (startHour < 0 || startHour >23){
            throw new IllegalArgumentException();
        }
        this.taskId = taskId;
        this.startHour = startHour;
        this.duration = duration;
        this.maxWindow = maxWindow;
        this.taskType = taskType;
        this.animal = animal;
        this.extraVolunteer = false; //default val
        this.prepTime = prepTime; // 0 minutes default val
    }


    /**
     * setters for Task
     */

    /**
     * @param duration to complete the task
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * @param prepTime to set up the task
     */
    public void setPrepTime(int prepTime) throws IllegalArgumentException {
        this.prepTime = prepTime;
    }

    /**
     * @param taskType the type of task
     */
    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    /**
     * @param maxWindow of the task
     */
    public void setMaxWindow(int maxWindow) {
        this.maxWindow = maxWindow;
    }

    /**
     * @param startHour starting hour of the task
     */
    public void setStartHour(int startHour) throws IllegalArgumentException {
        if (startHour < 0 || startHour > 23){
            throw new IllegalArgumentException();
        }
        this.startHour = startHour;
    }

    /**
     * @param animal of the task being executed
     */
    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    /**
     * @param extraVolunteer status that indicates if extra volunteer is needed
     */
    public void setExtraVolunteer(boolean extraVolunteer) {
        this.extraVolunteer = extraVolunteer;
    }

    /**
     * getters for Task
     */

    /**
     * @return the taskId for the task
     */
    public int getTaskId(){
        return this.taskId;
    }

    /**
     * @return the duration of the task
     */
    public int getDuration(){
        return this.duration;
    }

    /**
     * @return the prepTime for the task
     */
    public int getPrepTime(){
        return this.prepTime;
    }

    /**
     * @return the type of task
     */
    public String getTaskType(){
        return this.taskType;
    }

    /**
     * @return the maxWindow of task
     */
    public int getMaxWindow(){
        return this.maxWindow;
    }

    /**
     * @return the startHour of the task
     */
    public int getStartHour(){
        return this.startHour;
    }

    /**
     * @return the animal of task
     */
    public Animal getAnimal(){
        return this.animal;
    }

    /**
     * @return the extraVolunteer status of the task
     */
    public boolean getExtraVolunteer(){
        return this.extraVolunteer;
    }



}

