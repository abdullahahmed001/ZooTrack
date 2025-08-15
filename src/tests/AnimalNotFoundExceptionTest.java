package tests;

import animals.Animal;
import animals.DietType;
import animals.HealthStatus;
import animals.Lion;
import animals.Giraffe;
import animals.Snake;
import exceptions.AnimalNotFoundException;
import habitats.Habitat;
import habitats.Savannah;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalNotFoundExceptionTest {
    private Habitat habitat;
    private List<Animal> animalList;

    @BeforeEach
    void setUp() {
        animalList = new ArrayList<>();
        animalList.add(new Lion("Henry", 20, "African Lion", DietType.CARNIVORE, HealthStatus.HUNGRY));
        animalList.add(new Giraffe("Morris", 16, "Northern Giraffe", DietType.HERBIVORE, HealthStatus.SICK));
        animalList.add(new Snake("Molly", 8, "Ball Python", DietType.CARNIVORE, HealthStatus.SICK));
        
        habitat = new Savannah("African Savannah", animalList);
    }

    @Test
    void testFindAnimalByNameSuccess() throws AnimalNotFoundException {
        // Test finding an animal that exists
        Animal foundAnimal = findAnimalByName(habitat, "Henry");
        
        // Verify the animal was found
        assertEquals("Henry", foundAnimal.getName());
        assertEquals("African Lion", foundAnimal.getSpecies());
    }

    @Test
    void testFindAnimalByNameNotFound() {
        // Test finding an animal that doesn't exist
        Exception exception = assertThrows(AnimalNotFoundException.class, () -> {
            findAnimalByName(habitat, "Simba");
        });
        
        // Verify the exception message
        String expectedMessage = "Animal with name 'Simba' not found in habitat African Savannah";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Finds an animal by name in the given habitat.
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
}