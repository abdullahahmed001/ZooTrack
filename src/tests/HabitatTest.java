package tests;

import animals.Animal;
import animals.DietType;
import animals.HealthStatus;
import animals.Lion;
import animals.Penguin;
import exceptions.AnimalNotFoundException;
import exceptions.IncompatibleHabitatException;
import habitats.Habitat;
import habitats.HabitatType;
import habitats.Savannah;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HabitatTest {
    private Habitat habitat;
    private Animal testAnimal;
    private List<Animal> animalList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        animalList = new ArrayList<>();
        testAnimal = new Lion("Simba", 5, "African Lion", DietType.CARNIVORE, HealthStatus.HEALTHY);
        habitat = new Savannah("Test Habitat", animalList);
    }

    @Test
    void testConstructor() {
        // Test that the constructor properly initializes the habitat
        assertEquals(HabitatType.SAVANNAH, habitat.getHabitatType());
        assertEquals(0, habitat.getAnimals().size());

        // Test with animals in the list
        List<Animal> animals = new ArrayList<>();
        animals.add(testAnimal);
        Habitat habitatWithAnimals = new Savannah("Test Habitat 2", animals);
        assertEquals(1, habitatWithAnimals.getAnimals().size());
        assertEquals(testAnimal, habitatWithAnimals.getAnimals().get(0));
    }

    @Test
    void testGetSetHabitatType() {
        assertEquals(HabitatType.SAVANNAH, habitat.getHabitatType());
        habitat.setHabitatType(HabitatType.OCEAN);
        assertEquals(HabitatType.OCEAN, habitat.getHabitatType());
    }

    @Test
    void testGetSetAnimals() {
        assertEquals(0, habitat.getAnimals().size());

        List<Animal> newAnimals = new ArrayList<>();
        newAnimals.add(testAnimal);
        habitat.setAnimals(newAnimals);

        assertEquals(1, habitat.getAnimals().size());
        assertEquals(testAnimal, habitat.getAnimals().get(0));
    }

    @Test
    void testAddAnimal() {
        assertEquals(0, habitat.getAnimals().size());
        habitat.addAnimal(testAnimal);
        assertEquals(1, habitat.getAnimals().size());
        assertEquals(testAnimal, habitat.getAnimals().get(0));
    }

    @Test
    void testRemoveAnimal() {
        habitat.addAnimal(testAnimal);
        assertEquals(1, habitat.getAnimals().size());

        habitat.removeAnimal(testAnimal);
        assertEquals(0, habitat.getAnimals().size());
    }

    @Test
    void testListAnimalsEmpty() {
        System.setOut(new PrintStream(outContent));

        habitat.listAnimals();

        assertEquals("There are no animals in this habitat." + System.lineSeparator(), outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    void testListAnimalsWithAnimals() {
        System.setOut(new PrintStream(outContent));

        habitat.addAnimal(testAnimal);
        habitat.listAnimals();

        String expected = "The animals in this habitat are: " + System.lineSeparator() +
                "- Simba (African Lion), 5 years old, CARNIVORE, HEALTHY" + System.lineSeparator();
        assertEquals(expected, outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    void testDescribeHabitat() {
        String description = habitat.describeHabitat();
        assertNotNull(description);
        assertTrue(description.contains("Test Habitat"));
        assertTrue(description.contains("0 animals"));
    }

    @Test
    void testIsSuitableForAnimal() {
        // Test with a Lion (which should be suitable for a Savannah)
        assertTrue(habitat.isSuitableForAnimal(testAnimal));

        // Add more animals to test different scenarios
        Animal herbivore = new Lion("Herb", 3, "Herbivore Test", DietType.HERBIVORE, HealthStatus.HEALTHY);
        assertTrue(habitat.isSuitableForAnimal(herbivore));
    }

    @Test
    void testMaintainHabitat() {
        System.setOut(new PrintStream(outContent));

        habitat.maintainHabitat();

        // Just verify that some output is produced
        assertTrue(outContent.toString().length() > 0);

        System.setOut(originalOut);
    }

    @Test
    void testFindAnimalByNameSuccess() throws AnimalNotFoundException {
        // Add an animal to the habitat
        habitat.addAnimal(testAnimal);

        // Find the animal by name
        Animal foundAnimal = findAnimalByName(habitat, "Simba");

        // Verify the animal was found
        assertEquals(testAnimal, foundAnimal);
    }

    @Test
    void testFindAnimalByNameNotFound() {
        // Try to find an animal that doesn't exist
        Exception exception = assertThrows(AnimalNotFoundException.class, () -> {
            findAnimalByName(habitat, "NonExistentAnimal");
        });

        // Verify the exception message
        String expectedMessage = "Animal with name 'NonExistentAnimal' not found in habitat Test Habitat";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testAddAnimalToHabitatSuccess() throws IncompatibleHabitatException {
        // Add a compatible animal to the habitat
        addAnimalToHabitat(habitat, testAnimal);

        // Verify the animal was added
        assertTrue(habitat.getAnimals().contains(testAnimal));
    }

    @Test
    void testAddAnimalToHabitatIncompatible() {
        // Create an incompatible animal (Penguin is not compatible with Savannah)
        Animal penguin = new Penguin("Pingu", 5, "Emperor Penguin", DietType.CARNIVORE, HealthStatus.HEALTHY);

        // Try to add the incompatible animal
        Exception exception = assertThrows(IncompatibleHabitatException.class, () -> {
            addAnimalToHabitat(habitat, penguin);
        });

        // Verify the exception message
        String expectedMessage = "Habitat Test Habitat is not suitable for Emperor Penguin";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Helper method to find an animal by name in a habitat.
     * @param habitat The habitat to search in
     * @param name The name of the animal to find
     * @return The found animal
     * @throws AnimalNotFoundException If no animal with the given name is found
     */
    private Animal findAnimalByName(Habitat habitat, String name) throws AnimalNotFoundException {
        for (Animal animal : habitat.getAnimals()) {
            if (animal.getName().equals(name)) {
                return animal;
            }
        }
        throw new AnimalNotFoundException("Animal with name '" + name + "' not found in habitat " + habitat.getName());
    }

    /**
     * Helper method to add an animal to a habitat, checking if the habitat is suitable for the animal.
     * @param habitat The habitat to add the animal to
     * @param animal The animal to add
     * @throws IncompatibleHabitatException If the habitat is not suitable for the animal
     */
    private void addAnimalToHabitat(Habitat habitat, Animal animal) throws IncompatibleHabitatException {
        if (!habitat.isSuitableForAnimal(animal)) {
            throw new IncompatibleHabitatException("Habitat " + habitat.getName() + " is not suitable for " + animal.getSpecies());
        }
        habitat.addAnimal(animal);
    }
}
