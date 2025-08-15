package tests;

import animals.Animal;
import animals.DietType;
import animals.HealthStatus;
import animals.Elephant;
import animals.Penguin;
import exceptions.IncompatibleHabitatException;
import habitats.Habitat;
import habitats.Savannah;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IncompatibleHabitatExceptionTest {
    private Habitat habitat;
    private List<Animal> animalList;

    @BeforeEach
    void setUp() {
        animalList = new ArrayList<>();
        habitat = new Savannah("African Savannah", animalList);
    }

    @Test
    void testAddAnimalToHabitatSuccess() throws IncompatibleHabitatException {
        // Test adding an animal that is compatible with the habitat
        Animal elephant = new Elephant("Dumbo", 3, "African Elephant", DietType.HERBIVORE, HealthStatus.HEALTHY);
        
        addAnimalToHabitat(habitat, elephant);
        
        // Verify the animal was added
        assertTrue(habitat.getAnimals().contains(elephant));
    }

    @Test
    void testAddAnimalToHabitatIncompatible() {
        // Test adding an animal that is incompatible with the habitat
        Animal penguin = new Penguin("Pingu", 5, "Emperor Penguin", DietType.CARNIVORE, HealthStatus.HEALTHY);
        
        Exception exception = assertThrows(IncompatibleHabitatException.class, () -> {
            addAnimalToHabitat(habitat, penguin);
        });
        
        // Verify the exception message
        String expectedMessage = "Habitat African Savannah is not suitable for Emperor Penguin";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Adds an animal to a habitat, checking if the habitat is suitable for the animal.
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