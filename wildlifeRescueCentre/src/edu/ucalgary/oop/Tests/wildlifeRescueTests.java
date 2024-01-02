import edu.ucalgary.oop.*;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**

 This class contains JUnit test cases for the Wildlife Rescue project.
 */
public class wildlifeRescueTests {

    /**START OF tests for; Animal.java

     */
    /**
     * Test case to verify the animal ID and its setters.
     */
    @Test
    public void testGetAnimalId() {
        Animal animal = new Beaver("123", "Fluffy");
        assertEquals("123", animal.getAnimalId());
        animal.setAnimalId("456");
        assertEquals("456", animal.getAnimalId());
    }

    /**
     * This class contains a test case to verify the functionality of the setAnimalId method of the Animal class.
     */
    @Test
    public void testSetAnimalId() {
        Animal animal = new Beaver("123", "Fluffy");
        animal.setAnimalId("456");
        assertEquals("456", animal.getAnimalId());
    }

    /**
     * Test case to verify that setting animal ID to null results in null value.
     */
    @Test
    public void testSetAnimalIdWithNull() {
        Animal animal = new Beaver("B123", "Benny");
        animal.setAnimalId(null);
        assertNull(animal.getAnimalId());
    }



    /**
     * Test case to verify that setting animal ID to an empty string results in an empty string.
     */
    @Test
    public void testSetAnimalIdWithEmptyString() {
        Animal animal = new Fox("A123", "Tommy");
        animal.setAnimalId("");
        assertEquals("", animal.getAnimalId());
    }

    /**
     * Test case to verify that setting the nickname to null results in an IllegalArgumentException being thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNickNameWithNull() {
        Animal animal = new Fox("A123", "Tommy");
        animal.setNickName(null);
    }

    
    /**
     * Test case to verify the nickname and its setters.
     */

    @Test
    public void testGetNickName() {
        Animal animal = new Coyote("123", "Fluffy");
        assertEquals("Fluffy", animal.getNickName());
        animal.setNickName("Mittens");
        assertEquals("Mittens", animal.getNickName());

// Verifying that IllegalArgumentException is thrown when nickname is set to null or an empty string.
        assertThrows(IllegalArgumentException.class, () -> {
            animal.setNickName(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            animal.setNickName("");
        });
    }
    /**

     Tests setting a valid one-character nickname for an animal.
     */
    @Test
    public void testSetNickNameValidOneCharacter() {
        Animal animal = new Coyote("1234", "Garfield");
        animal.setNickName("G");
        assertEquals("G", animal.getNickName());
    }
    /**

     Tests setting a valid 100-character nickname for an animal.
     */
    @Test
    public void testSetNickNameValidHundredCharacters() {
        Animal animal = new Raccoon("5678", "Snoopy");
        String nickname = "S".repeat(100);
        animal.setNickName(nickname);
        assertEquals(nickname, animal.getNickName());
    }
    /**

     Tests setting an empty string as a nickname for an animal.
     Expects an IllegalArgumentException to be thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNickNameEmptyString() {
        Animal animal = new Fox("9123", "Tweety");
        animal.setNickName("");
    }
    /**

     Tests setting a null value as a nickname for an animal.
     Expects an IllegalArgumentException to be thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNickNameNull() {
        Animal animal = new Beaver("4567", "Black Beauty");
        animal.setNickName(null);
    }
    /**

     Tests getting the orphan status of an animal that has not been orphaned.
     */
    @Test
    public void testGetOrphanStatusFalse() {
        Animal animal = new Fox("1234", "Garfield");
        assertFalse(animal.getOrphanStatus());
    }
    /**

     Tests getting the orphan status of an animal that has been orphaned.
     */
    @Test
    public void testGetOrphanStatusTrue() {
        Animal animal = new Porcupine("5678", "Snoopy");
        animal.setOrphanStatus(true);
        assertTrue(animal.getOrphanStatus());
    }

    /**
     * This class contains a test case to verify the functionality of the setNickName method of the Animal class.
     */
    @Test
    public void testSetNickName() {
        Animal animal = new Beaver("123", "Fluffy");
        animal.setNickName("Spike");
        assertEquals("Spike", animal.getNickName());
    }


    /**
     * Test case to verify the orphan status and its setters.
     */
    @Test
    public void testGetOrphanStatus() {
        Animal animal = new Porcupine("123", "Fluffy");
        assertFalse(animal.getOrphanStatus());
        animal.setOrphanStatus(true);
        assertTrue(animal.getOrphanStatus());
    }

    /**
     * Tests the Coyote constructor by creating a new coyote with a unique ID and name.
     * Ensures that the ID and name are correctly set and retrievable.
     */
    @Test
    public void testConstructorCoyote() {
        Coyote coyote = new Coyote("1", "Wiley");
        assertEquals("1", coyote.getAnimalId());
        assertEquals("Wiley", coyote.getNickName());
    }

    /**
     * Tests the getBehavior method by creating a new coyote and ensuring the behavior is "crepuscular".
     */
    @Test
    public void testGetBehaviorCoyote() {
        Coyote coyote = new Coyote("1", "Wiley");
        assertEquals("crepuscular", coyote.getBehavior());
    }

    /**
     * Tests the getSpecies method by creating a new coyote and ensuring the species is "coyote".
     */
    @Test
    public void testGetSpeciesCoyote() {
        Coyote coyote = new Coyote("1", "Wiley");
        assertEquals("coyote", coyote.getSpecies());
    }

    /**
     * Tests the setNickName method by creating a new coyote and setting a new nickname.
     * Ensures that the new nickname is correctly set and retrievable.
     */
    @Test
    public void testSetNickNameCoyote() {
        Coyote coyote = new Coyote("1", "Wiley");
        coyote.setNickName("Coyote");
        assertEquals("Coyote", coyote.getNickName());
    }

    /**
     * Tests the setOrphanStatus method by creating a new coyote and setting the orphan status.
     * Ensures that the new status is correctly set and retrievable.
     */
    @Test
    public void testSetOrphanStatusCoyote() {
        Coyote coyote = new Coyote("1", "Wiley");
        coyote.setOrphanStatus(true);
        assertTrue(coyote.getOrphanStatus());
    }


    /**
     * Test case to verify that setting the nickname to an empty string results in an IllegalArgumentException being thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetNickNameWithEmptyString() {
        Animal animal = new Coyote("A123", "Tommy");
        animal.setNickName("");
    }

    /**
     * This class contains a test case to verify the functionality of the getActiveTime method of the Animals belonging to animal class.
     */
    @Test
    public void testGetBehavior() {
        Animal beaver = new Beaver("A123", "Tommy");
        assertEquals("diurnal", beaver.getBehavior());
        Animal coyote = new Coyote("A124", "Jerry");
        assertEquals("crepuscular", coyote.getBehavior());
        Animal racoon = new Raccoon("A125", "Bob");
        assertEquals("nocturnal",racoon.getBehavior());
        Animal fox = new Fox("A126", "Brad");
        assertEquals("nocturnal",fox.getBehavior());
        Animal porcupine = new Porcupine("A125", "Bob");
        assertEquals("crepuscular",porcupine.getBehavior());
    }

    /**
     * This class contains a test case to verify the functionality of the setOrphanStatus method of the Animal class.
     */
    @Test
    public void testSetOrphanStatus() {
        Animal animal = new Fox("A123", "Tommy");
        animal.setOrphanStatus(true);
        assertTrue(animal.getOrphanStatus());
        animal.setOrphanStatus(false);
        assertFalse(animal.getOrphanStatus());
    }

    /**
     * Test case to verify that animal object is constructed correctly and its getters work correctly.
     */
    @Test
    public void testConstructorAndGetters() {
        Animal animal = new Fox("A123", "Tommy");
        assertEquals("A123", animal.getAnimalId());
        assertEquals("Tommy", animal.getNickName());
        assertFalse(animal.getOrphanStatus());
    }

    /**
     * Tests setting a valid nickname for the Porcupine.
     * Expected outcome: Nickname is updated.
     */
    @Test
    public void testSetNickname() {
        Porcupine porcupine = new Porcupine("P1234", "Porky");
        porcupine.setNickName("Spiky");
        assertEquals("Spiky", porcupine.getNickName());
    }
    /**
     * Tests setting an orphaned status for the Porcupine.
     * Expected outcome: Orphaned status is updated.
     */
    @Test
    public void testSetOrphanStatusPorcupine() {
        Porcupine porcupine = new Porcupine("P1234", "Porky");
        porcupine.setOrphanStatus(true);
        assertTrue(porcupine.getOrphanStatus());
    }
    @Test
    public void testGetSpecies() {
        Animal beaver = new Beaver("A123", "Tommy");
        assertEquals("beaver", beaver.getSpecies());
        Animal coyote = new Coyote("A124", "Jerry");
        assertEquals("coyote", coyote.getSpecies());
        Animal raccoon = new Raccoon("A125", "Bob");
        assertEquals("raccoon",raccoon.getSpecies());
        Animal fox = new Fox("A126", "Brad");
        assertEquals("fox",fox.getSpecies());
        Animal porcupine = new Porcupine("A125", "Bob");
        assertEquals("porcupine",porcupine.getSpecies());
    }

    /** END of tests for Animal.java. */

    @Test
    public void testException() {
        EventsException exception = new EventsException();
        assertEquals("Exception occurred during events processing", exception.getMessage());
    }

    /** start of tests for Task.java */
    @Test
    public void testConstructor() {
        int taskId = 1;
        int startHour = 10;
        int duration = 30;
        int maxWindow = 60;
        String taskType = "Feeding";
        Animal animal = new Fox("1", "Simba");
        int prepTime = 5;

        Task task = new Task(taskId, startHour, duration, maxWindow, taskType, animal, prepTime);

        assertEquals(taskId, task.getTaskId());
        assertEquals(startHour, task.getStartHour());
        assertEquals(duration, task.getDuration());
        assertEquals(maxWindow, task.getMaxWindow());
        assertEquals(taskType, task.getTaskType());
        assertEquals(animal, task.getAnimal());
        assertEquals(prepTime, task.getPrepTime());
    }
    /**

     JUnit test case to test the setDuration() method of Task class.
     */

    @Test
    public void testSetDuration() {
        Task task = new Task(1, 10, 30, 60, "Feeding", new Fox("1", "Simba"), 5);
        int duration = 45;

        task.setDuration(duration);

        assertEquals(duration, task.getDuration());
    }
    /**

     JUnit test case to test the setPrepTime() method of Task class.
     */
    @Test
    public void testSetPrepTime() {
        Task task = new Task(1, 10, 30, 60, "Feeding", new Fox("1", "Simba"), 5);
        int prepTime = 10;

        task.setPrepTime(prepTime);

        assertEquals(prepTime, task.getPrepTime());
    }
    /**

     JUnit test case to test the setTaskType() method of Task class.
     */
    @Test
    public void testSetTaskType() {
        Task task = new Task(1, 10, 30, 60, "Feeding", new Fox("1", "Simba"), 5);
        String taskType = "Cleaning";

        task.setTaskType(taskType);

        assertEquals(taskType, task.getTaskType());
    }
    /**

     JUnit test case to test the setMaxWindow() method of Task class.
     */
    @Test
    public void testSetMaxWindow() {
        Task task = new Task(1, 10, 30, 60, "Feeding", new Fox("1", "Simba"), 5);
        int maxWindow = 90;

        task.setMaxWindow(maxWindow);

        assertEquals(maxWindow, task.getMaxWindow());
    }
    /**

     JUnit test case to test the setExtraVolunteer() method of Task class when passed true.
     */

    @Test
    public void testSetExtraVolunteer_true() {
        Task task = new Task(1, 8, 2, 4, "Feeding", new Fox("1", "Simba"), 5);
        task.setExtraVolunteer(true);
        assertTrue(task.getExtraVolunteer());
    }
    /**

     JUnit test case to test the setExtraVolunteer() method of Task class when passed false.
     */
    @Test
    public void testSetExtraVolunteer_false() {
        Task task = new Task(1, 8, 2, 4, "Feeding",new Fox("1", "Simba"), 5);
        task.setExtraVolunteer(false);
        assertFalse(task.getExtraVolunteer());
    }
    /**

     Test case to verify that an empty name parameter is correctly handled by the Beaver class constructor.
     The expected result is an empty string as the NickName of the Beaver object.
     */
    @Test
    public void testEmptyName() {
        Beaver beaver = new Beaver("1", "");
        assertEquals("", beaver.getNickName());
    }

    /**

     Test case to verify that a very long name parameter is correctly handled by the Beaver class constructor.
     The expected result is the same very long string as the NickName of the Beaver object.
     */
    @Test
    public void testVeryLongName() {
        String name = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Beaver beaver = new Beaver("2", name);
        assertEquals(name, beaver.getNickName());
    }

    /**

     Test case to verify that an orphaned beaver object is correctly identified by the Beaver class.
     The expected result is a true value for the orphan status of the Beaver object.
     */
    @Test
    public void testOrphanedBeaver() {
        Beaver beaver = new Beaver("3", "Benny");
        beaver.setOrphanStatus(true);
    }



    /**

     This test case checks the functionality of the getTaskId() method of the Task class.
     A Task object is created with taskId=1 and its getTaskId() method is called.
     The expected result is 1 and is compared to the actual result using assertEquals().
     */
    @Test
    public void testGetTaskId() {
            Task task = new Task(1, 8, 2, 4, "Feeding", new Fox("1", "Simba"), 5);
            assertEquals(1, task.getTaskId());
    }

    /**

     This test case checks the functionality of the getDuration() method of the Task class.
     A Task object is created with duration=60 and its getDuration() method is called.
     The expected result is 60 and is compared to the actual result using assertEquals().
     */
    // create a Task object to be used in tests
    Task task = new Task(1, 8, 60, 120, "Feeding", new Fox("1", "Simba"), 10);

    @Test
    public void testGetDuration() {
        assertEquals(60, task.getDuration());
    }
    /**

     This test case checks the functionality of the getPrepTime() method of the Task class.
     A Task object is created with prepTime=10 and its getPrepTime() method is called.
     The expected result is 10 and is compared to the actual result using assertEquals().
     */
    @Test
    public void testGetPrepTime() {
        assertEquals(10, task.getPrepTime());
    }
    /**

     This test case checks the functionality of the getTaskType() method of the Task class.
     A Task object is created with taskType="Feeding" and its getTaskType() method is called.
     The expected result is "Feeding" and is compared to the actual result using assertEquals().
     */
    @Test
    public void testGetTaskType() {
        assertEquals("Feeding", task.getTaskType());
    }
    /**

     This test case checks the functionality of the getMaxWindow() method of the Task class.
     A Task object is created with maxWindow=120 and its getMaxWindow() method is called.
     The expected result is 120 and is compared to the actual result using assertEquals().
     */
    @Test
    public void testGetMaxWindow() {
        assertEquals(120, task.getMaxWindow());
    }
    /**

     This test case checks the functionality of the getStartHour() method of the Task class.
     A Task object is created with startHour=8 and its getStartHour() method is called.
     The expected result is 8 and is compared to the actual result using assertEquals().
     */
    @Test
    public void testGetStartHour() {
        assertEquals(8, task.getStartHour());
    }
    /**

     This test case checks the functionality of the getExtraVolunteer() method of the Task class.
     A Task object is created with extraVolunteer=false and its getExtraVolunteer() method is called.
     The expected result is false and is compared to the actual result using assertFalse().
     */
    @Test
    public void testGetExtraVolunteer() {
        assertFalse(task.getExtraVolunteer());
    }

    /** END of Task.java tests */




}








