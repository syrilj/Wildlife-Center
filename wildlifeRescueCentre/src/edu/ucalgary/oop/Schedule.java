package edu.ucalgary.oop;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Schedule {

    private static final String DB_URL = "jdbc:mysql://localhost/EWR";
    private static final String USER = "oop";
    private static final String PASS = "password";

    /**
     * Returns a connection to the database using the DB_URL, USER, and PASS constants.
     *
     * @return a Connection object to the database
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println("Error connecting to database");
            e.printStackTrace();
        }
        return connection;
    }

    private static final Connection conn = getConnection();
    private static ResultSet res;
    private static List<Animal> animals = new ArrayList<>();

    // Create a TreeMap to store the tasks associated with each hour
    private static TreeMap<Integer, List<Task>> taskMap = new TreeMap<>();

    public static void main(String[] args) throws InterruptedException {
        readAnimals();
        getTaskMap();

        //add general feeding and cleaning tasks
        addTasks();
        generateScheduleGUI();
        //generate schedule
        generateSchedule();
    }

    /**
     * Reads animal data from the database and initializes the list of animals.
     * This method retrieves data from two tables: ANIMALS and TREATMENTS.
     * ANIMALS table stores the basic information of each animal including its ID, nickname and species.
     * TREATMENTS table stores the treatments to be applied to each animal and the start hour of each treatment.
     * If the animal is an orphan, the orphan status of the animal will be set to true.
     * After retrieving data from the database, this method creates new animal objects for each row in the ANIMALS table.
     * Depending on the animal's species, the method instantiates a new object of the corresponding class: Beaver, Raccoon, Fox, Coyote, or Porcupine.
     * For each treatment in the TREATMENTS table that has a treatment ID of 1, indicating the animal is an orphan, the orphan status of the corresponding animal object is set to true.
     *
     * @throws SQLException if there is an error executing the SQL query
     */

    public static void readAnimals() {
        try {
            Statement stmt = conn.createStatement();
            res = stmt.executeQuery("SELECT ANIMALS.* " +
                    "FROM ANIMALS " +
                    "ORDER BY ANIMALS.AnimalID ASC;");

            while (res.next()) {

                String animalType = res.getString("AnimalSpecies");
                String nickName = res.getString("AnimalNickname");
                String ID = res.getString("AnimalID");

                if (animalType.equals("beaver")) {
                    Beaver newAnimal = new Beaver(ID, nickName); //remove isOrphaned from Beaver class
                    animals.add(newAnimal);
                } else if (animalType.equals("raccoon")) {
                    Raccoon newAnimal = new Raccoon(ID, nickName); //remove isOrphaned from Raccoon class
                    animals.add(newAnimal);
                } else if (animalType.equals("fox")) {
                    Fox newAnimal = new Fox(ID, nickName); //remove isOrphaned from Raccoon class
                    animals.add(newAnimal);
                } else if (animalType.equals("coyote")) {
                    Coyote newAnimal = new Coyote(ID, nickName); //remove isOrphaned from Coyote class
                    animals.add(newAnimal);
                } else if (animalType.equals("porcupine")) {
                    Porcupine newAnimal = new Porcupine(ID, nickName); //remove isOrphaned from Porcupine class
                    animals.add(newAnimal);
                }

            }

            stmt = conn.createStatement();
            res = stmt.executeQuery("SELECT ANIMALS.*, TREATMENTS.TreatmentId " +
                    "FROM ANIMALS " +
                    "JOIN TREATMENTS ON ANIMALS.AnimalID = TREATMENTS.AnimalID;");

            while (res.next()) {
                int treatmentId = res.getInt("TreatmentID");
                int animalId = res.getInt("AnimalID");

                //check if animal is an orphan (treatmentID == 1)
                if (treatmentId == 1) {
                    animals.get(animalId - 1).setOrphanStatus(true);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a map of tasks for each hour of the day from the database and stores it in taskMap.
     * Each task is associated with an animal and a start hour, duration, max window and description.
     * If multiple tasks have the same start hour, they are stored in an ArrayList in the taskMap.
     */


    public static void getTaskMap() {

        try {
            Statement stmt = conn.createStatement();
            res = stmt.executeQuery("SELECT ANIMALS.*, TASKS.*, TREATMENTS.StartHour " +
                    "FROM TREATMENTS " +
                    "JOIN ANIMALS ON ANIMALS.AnimalID = TREATMENTS.AnimalID " +
                    "JOIN TASKS ON TREATMENTS.TaskID = TASKS.TaskID " +
                    "ORDER BY TREATMENTS.StartHour ASC;");


            // Loop through the ResultSet and add each task to the appropriate hour
            while (res.next()) {
                Integer startHour = res.getInt("StartHour");
                int id = res.getInt("TaskID");
                int duration = res.getInt("Duration");
                int maxWindow = res.getInt("MaxWindow");
                int animalId = res.getInt("AnimalID");
                String taskName = res.getString("Description");
                Animal animal = animals.get(animalId - 1);

                Task newTask = new Task(id, startHour, duration, maxWindow, taskName, animal, 0);

                if (taskMap.containsKey(startHour)) {
                    taskMap.get(startHour).add(newTask);
                } else {
                    ArrayList<Task> newTaskList = new ArrayList<Task>();
                    newTaskList.add(newTask);
                    taskMap.put(startHour, newTaskList);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This class adds general feeding and cleaning tasks to the TaskMap.
     * The class also has getters and setters for these properties.
     *
     * @return None
     */
    public static void addTasks() {
        animals.forEach((animal -> {
            String cleanTask = "Clean Cage";
            String feedTask = "Feed Animal";
            int cleanId = 69; //taskID for cleaning cages
            int feedId = 70;  //taskID for general feeding
            int cleanTime = 100;
            int startHourClean = -10; //initialize
            int durationClean = -10; //initialize
            int maxWindowClean = -10; //initialize
            int startHourFeed = -10; //initialize
            int durationFeed = -10; //initialize
            int maxWindowFeed = -10; //initialize
            int feedPrepTime = -10; //initialize

            String animalType = animal.getSpecies();

            if (animalType.equals("beaver")) {
                /**
                 * Beavers are diurnal.
                 * Each takes 5 minutes to feed with no preparation time.
                 * It takes 5 minutes to clean each cage.
                 * Diurnal animals are fed in a 3-hour window starting at 8 AM (8).
                 */

                //cleaning data
                startHourClean = 0;
                durationClean = 5; //duration in minutes
                maxWindowClean = 23; //maxWindow in hours

                //feeding data
                startHourFeed = 8;
                durationFeed = 5; //duration in minutes
                maxWindowFeed = 3; //maxWindow in hours
                feedPrepTime = 0; //duration in minutes

            } else if (animalType.equals("raccoon")) {
                /**
                 * Raccoons are nocturnal.
                 * Each takes 5 minutes to feed with no preparation time.
                 * It takes 5 minutes to clean each cage.
                 * Nocturnal animals are fed in a 3-hour window starting at midnight (0).
                 * That is, feeding is scheduled for 12 AM, 1 AM, or 2 AM.
                 */

                //cleaning data
                startHourClean = 0;
                durationClean = 5; //duration in minutes
                maxWindowClean = 23; //maxWindow in hours

                //feeding data
                startHourFeed = 0;
                durationFeed = 5; //duration in minutes
                maxWindowFeed = 3; //maxWindow in hours
                feedPrepTime = 0; //duration in minutes
            } else if (animalType.equals("fox")) {
                /**
                 * Foxes are nocturnal.
                 * Each takes 5 minutes to feed and the feeding preparation time is 5 minutes.
                 * It takes 5 minutes to clean each cage.
                 * Nocturnal animals are fed in a 3-hour window starting at midnight (0).
                 * That is, feeding is scheduled for 12 AM, 1 AM, or 2 AM.
                 */

                //cleaning data
                startHourClean = 0;
                durationClean = 5; //duration in minutes
                maxWindowClean = 23; //maxWindow in hours

                //feeding data
                startHourFeed = 0;
                durationFeed = 5; //duration in minutes
                maxWindowFeed = 3; //maxWindow in hours
                feedPrepTime = 5; //duration in minutes
            } else if (animalType.equals("coyote")) {
                /**
                 * Coyotes are crepuscular.
                 * Each takes 5 minutes to feed and the feeding preparation time is 10 minutes.
                 * It takes 5 minutes to clean each cage.
                 * Crepuscular animals are fed in a 3-hour window starting at 7 PM (19).
                 */

                //cleaning data
                startHourClean = 0;
                durationClean = 5; //duration in minutes
                maxWindowClean = 23; //maxWindow in hours

                //feeding data
                startHourFeed = 19;
                durationFeed = 5; //duration in minutes
                maxWindowFeed = 3; //maxWindow in hours
                feedPrepTime = 10; //duration in minutes
            } else if (animalType.equals("porcupine")) {
                /**
                 * Porcupines are crepuscular.
                 * Each takes 5 minutes to feed with no preparation time.
                 * It takes 10 minutes to clean each cage.
                 * Crepuscular animals are fed in a 3-hour window starting at 7 PM (19).
                 */

                //cleaning data
                startHourClean = 0;
                durationClean = 10; //duration in minutes
                maxWindowClean = 23; //maxWindow in hours

                //feeding data
                startHourFeed = 19;
                durationFeed = 5; //duration in minutes
                maxWindowFeed = 3; //maxWindow in hours
                feedPrepTime = 0; //duration in minutes
            }
            //Instantiate Tasks
            Task cleanCages = new Task(cleanId, startHourClean, durationClean, maxWindowClean, cleanTask, animal, 0);
            Task feedAnimal = new Task(feedId, startHourFeed, durationFeed, maxWindowFeed, feedTask, animal, feedPrepTime);

            if (taskMap.containsKey(startHourClean)) {
                taskMap.get(startHourClean).add(cleanCages);
            } else {
                ArrayList<Task> newTaskList = new ArrayList<Task>();
                newTaskList.add(cleanCages);
                taskMap.put(startHourClean, newTaskList);
            }

            if (taskMap.containsKey(startHourFeed)) {
                if (!(feedAnimal.getAnimal().getOrphanStatus() == true)) {
                    taskMap.get(startHourFeed).add(feedAnimal);
                }

            } else {
                ArrayList<Task> newTaskList = new ArrayList<Task>();
                if (!(feedAnimal.getAnimal().getOrphanStatus() == true)) {
                    newTaskList.add(feedAnimal);
                    taskMap.put(startHourFeed, newTaskList);
                }
            }
        }));
    }


    /**
     * Generates a daily schedule for animal care tasks based on what is in the TaskMap.
     * <p>
     * The method prompts the user for volunteer confirmation when an hour is over capacity
     * <p>
     * and assigns backup volunteers when necessary. The schedule is saved to a file named
     * <p>
     * "hourly_schedule.txt" in the current directory.
     *
     * @throws InterruptedException If the thread is interrupted while waiting for user input
     */
    public static void generateSchedule() throws InterruptedException {
        StringBuilder scheduleBuilder = new StringBuilder();
        Scanner userIN = new Scanner(System.in);

        LocalDate currentDate = LocalDate.now();
        scheduleBuilder.append("Schedule for " + currentDate + "\n\n");
        Map<Integer, Boolean> backupVolunteersMap = new HashMap<>();
        for (int i = 0; i <= 23; i++) {
            backupVolunteersMap.put(i, false);
        }

        for (int hour = 0; hour <= 23; hour++) {
            StringBuilder hourSchedule = new StringBuilder();
            hourSchedule.append(hour + ":00\n");

            boolean hasTasks = false;
            boolean addVolunteerStatus = false;
            boolean reLoop = false;
            int duration = 0;
            int maxDuration = 60;

            // Sort task list based on maxWindow
            List<Task> sortedTaskList = taskMap.values().stream()
                    .flatMap(List::stream)
                    .sorted(Comparator.comparingInt(Task::getMaxWindow))
                    .collect(Collectors.toList());

            for (Task task : sortedTaskList) {
                //properties of each task
                String descr = task.getTaskType();
                String name = task.getAnimal().getNickName();
                int taskID = task.getTaskId();
                int currStartHour = task.getStartHour();
                int currMaxWindow = task.getMaxWindow();

                if (currStartHour == hour) {
                    duration += task.getDuration() + task.getPrepTime();
                    if (duration > maxDuration) {
                        if (!addVolunteerStatus) {
                            // Check if there is a backup volunteer available for this hour
                            boolean backupVolunteerAvailable = backupVolunteersMap.get(hour);
                            if (!backupVolunteerAvailable) {
                                String input = volunteerConfirmationGUI(hour, taskID, descr, name);
                                if (input.equals("yes")) {
                                    addVolunteerStatus = true;
                                    JOptionPane.showMessageDialog(null, "Backup Volunteer Added for Hour: " + hour + ".");
                                    backupVolunteersMap.put(hour, true);
                                } else if (input.equals("no")) {
                                    addVolunteerStatus = false;
                                }
                            } else {
                                // Backup volunteer is available for this hour
                                addVolunteerStatus = true;
                            }

                            if (addVolunteerStatus) {
                                hourSchedule.append("[ + backup volunteer]\n");
                                maxDuration = 120;
                            } else {
                                //hour is FULL! we need to move tasks
                                if (currMaxWindow > 1) {
                                    task.setStartHour(currStartHour + 1); //increment starthour
                                    task.setMaxWindow(currMaxWindow - 1); //decrement maxWindow
                                    hour--; //keep loop on current hour
                                    reLoop = true;
                                    break;
                                } else {
                                    //volunteer is required
                                    System.out.println("The hour is currently FULL and the next task cannot be moved.\n" +
                                            "An extra volunteer MUST be added to complete the remaining tasks.");
                                    addVolunteerStatus = true;
                                    hourSchedule.append("[ + backup volunteer]\n");
                                    maxDuration = 120;
                                }
                            }
                        } else {
                            //hour is FULL and volunteer is already assigned
                            if (currMaxWindow > 1) {
                                task.setStartHour(currStartHour + 1); //increment starthour
                                task.setMaxWindow(currMaxWindow - 1); //decrement maxWindow
                                hour--; //keep loop on current hour
                                reLoop = true;
                                break;
                            }
                        }
                    }
                    hourSchedule.append("* " + descr + " " + "(" + name + ") " + duration + "\n");
                    hasTasks = true;
                }
            }
            if (reLoop) {
                continue;
            }
            if (hasTasks || backupVolunteersMap.get(hour)) { // if there are tasks or a

                scheduleBuilder.append(hourSchedule);
            }
        }


        // Put in File
        try {
            File outputFile = new File("hourly_schedule.txt");
            FileWriter writer = new FileWriter(outputFile);
            writer.write(scheduleBuilder.toString());
            writer.close();
            System.out.println("Schedule written to file: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method to generate the schedule with a GUI interface.
     */
    public static void generateScheduleGUI() {

        final boolean[] volunteerStatus = {false};
        // Create the JFrame and set its properties
        JFrame frame = new JFrame("EWR Schedule Builder");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.setContentPane(mainPanel);

        // Create the title label and add it to the main panel
        JLabel titleLabel = new JLabel("EWR");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create the button panel and add it to the main panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        // Create the "Generate Schedule" button and add an ActionListener to set volunteerStatus to true and close the window
        JButton generateButton = new JButton("Generate Schedule");
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volunteerStatus[0] = true;
                frame.dispose();

            }
        });
        buttonPanel.add(generateButton);

        // Create the "Modify Start Hour" button and add an ActionListener to prompt the user for input
        JButton modifyStartHourButton = new JButton("Modify Start Hour");
        modifyStartHourButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Prompt the user for input and modify the start hour accordingly
                System.out.println("Modifying the start hour...");
                modifyStartHoursGUI(taskMap);
                getTaskMap();

            }
        });
        buttonPanel.add(modifyStartHourButton);


        // Set the JFrame visible
        frame.setVisible(true);

        // Wait for the JFrame to close
        while (frame.isVisible()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        // Check the volunteer status and generate the schedule accordingly
        if (volunteerStatus[0]) {
            // Generate the schedule
            System.out.println("Generating the schedule...");
        } else {
            // Exit the program
            System.out.println("Exiting the program...");
            System.exit(0);
        }
    }

    /**
     * A method to prompt the user to confirm if they want to volunteer using a GUI interface.
     *
     * @return the user's input (either "yes" or "no").
     */
    public static String volunteerConfirmationGUI(int hour, int id, String description, String name) throws InterruptedException {
        final String[] input = {""};
        JFrame frame = new JFrame("Volunteer Confirmation");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.setContentPane(mainPanel);

        JLabel messageLabel = new JLabel("<html>Backup volunteer NEEDED " + "<br>- Hour: " + hour + "<br>- Task:  " + id + " : " +
                description + "<br>- Animal: " + name + "<br><br>Please click 'YES' to add a volunteer" +
                "<br>OR <br>'NO' to shift the task to the next time slot.</html>");

        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(messageLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        JButton noButton = new JButton("NO");
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                input[0] = "no";
                frame.dispose();
            }
        });
        buttonPanel.add(noButton);

        JButton yesButton = new JButton("YES");
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                input[0] = "yes";
                frame.dispose();
            }
        });
        buttonPanel.add(yesButton);

        buttonPanel.setPreferredSize(new Dimension(500, 50));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        while (frame.isVisible()) {
            Thread.sleep(1);
        }

        if (input[0].isEmpty()) {
            throw new InterruptedException("Window was closed without selecting a button");
        }

        if (input[0].equals("yes")) {
            messageLabel.setText("<html>Generated Schedule! Ready to Print to Text File</html>");
            yesButton.setVisible(false);
            noButton.setVisible(false);

            JButton okButton = new JButton("OK");
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });
            buttonPanel.add(okButton);
            buttonPanel.revalidate();
            buttonPanel.repaint();
            buttonPanel.setPreferredSize(new Dimension(500, 50));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

        return input[0];
    }


    /**
     * Modifies the start hours of tasks in the specified task map using a graphical user interface.
     *
     * @param taskMap a map containing lists of tasks, where each task list represents tasks for a specific day
     */
    public static void modifyStartHoursGUI(Map<Integer, List<Task>> taskMap) {
        boolean validInput = false;

        // loop until the user enters a valid input
        while (!validInput) {
            // get the task ID and new start hour from the user
            String taskIDString = JOptionPane.showInputDialog(null, "Enter the task ID that you want to move:");
            if (taskIDString == null) {
                // user clicked cancel
                return;
            }
            int taskID;
            try {
                taskID = Integer.parseInt(taskIDString);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid task ID! Please enter a valid integer.");
                continue;
            }

            String newStartHourString = JOptionPane.showInputDialog(null, "Enter the new start hour (0-23):");
            if (newStartHourString == null) {
                // user clicked cancel
                return;
            }
            int newStartHour;
            try {
                newStartHour = Integer.parseInt(newStartHourString);
                // check if the new start hour is valid
                if (newStartHour < 0 || newStartHour > 23) {
                    JOptionPane.showMessageDialog(null, "Invalid start hour! Please enter a value between 0 and 23.");
                    continue;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid start hour! Please enter a valid integer.");
                continue;
            }

            // find the task with the specified ID and update its start hour
            boolean taskFound = false;
            for (List<Task> taskList : taskMap.values()) {
                for (Task task : taskList) {
                    if (task.getTaskId() == taskID) {
                        System.out.println("Moving task " + taskID + " from hour " + task.getStartHour() + " to hour " + newStartHour);
                        task.setStartHour(newStartHour);
                        JOptionPane.showMessageDialog(null, "Start hour updated for task " + taskID);
                        taskFound = true;
                        break;
                    }
                }
                if (taskFound) {
                    break;
                }
            }

            // if the task was not found, print an error message
            if (!taskFound) {
                JOptionPane.showMessageDialog(null, "Task not found! Please enter a valid task ID.");
                continue;
            }

            // check if the new start hour conflicts with any existing tasks
            boolean conflict = false;
            for (List<Task> taskList : taskMap.values()) {
                for (Task task : taskList) {
                    if (task.getTaskId() != taskID && task.getStartHour() == newStartHour) {
                        conflict = true;
                        break;
                    }
                }
                if (conflict) {
                    break;
                }
            }

            // if there is a conflict, ask the user if they want to move the conflicting task
            if (conflict) {
                boolean movedConflictingTask = false;
                while (!movedConflictingTask) {
                    int option = JOptionPane.showConfirmDialog(null, "There is a conflict with another task at the new start hour. Do you want to move the conflicting task?");
                    if (option == JOptionPane.YES_OPTION) {
                        for (List<Task> taskList : taskMap.values()) {
                            for (Task task : taskList) {
                                if (task.getStartHour() == newStartHour && task.getTaskId() != taskID) {
                                    int newTaskStartHour = task.getStartHour() + 1;
                                    String message = "Moving task " + task.getTaskId() + " from hour " + task.getStartHour() + " to " + newTaskStartHour;
                                    JOptionPane.showMessageDialog(null, message, "Task Conflicted", JOptionPane.INFORMATION_MESSAGE);
                                    task.setStartHour(newTaskStartHour);
                                    // check if the new start hour conflicts with any other tasks
                                    boolean stillConflict = false;
                                    for (List<Task> tList : taskMap.values()) {
                                        for (Task t : tList) {
                                            if (t.getStartHour() != task.getStartHour() && t.getStartHour() == newTaskStartHour) {
                                                stillConflict = true;
                                                break;
                                            }
                                        }
                                        if (stillConflict) {
                                            break;
                                        }
                                    }
                                    if (stillConflict) {
                                        // there is still a conflict, continue loop
                                        continue;
                                    } else {
                                        // no conflict, break loop
                                        movedConflictingTask = true;
                                        break;
                                    }
                                }
                            }
                            if (movedConflictingTask) {
                                break;
                            }
                        }
                    } else {
                        // user chose not to move the conflicting task, break loop
                        break;
                    }
                }


                // set the new start hour for the selected task and show a message dialog
                if (taskMap.containsKey(taskID)) {
                    taskMap.get(taskID).get(0).setStartHour(newStartHour);
                    String message = "Start hour updated for task " + taskID + " (moved to " + newStartHour + ")";
                    JOptionPane.showMessageDialog(null, message, "Start Hour Updated", JOptionPane.INFORMATION_MESSAGE);
                }


            } else {
                // if there is no conflict, set the new start hour for the selected task and show a message dialog
                List<Task> tasks = taskMap.get(taskID);
                if (tasks != null && !tasks.isEmpty()) {
                    tasks.get(0).setStartHour(newStartHour);
                    String message = "Start hour updated for task " + taskID + " (moved to " + newStartHour + ")";
                    JOptionPane.showMessageDialog(null, message, "Start Hour Updated", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            validInput = true;
        }
    }
}
