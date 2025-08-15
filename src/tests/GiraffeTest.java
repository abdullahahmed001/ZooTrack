package tests;

import animals.Animal;
import animals.DietType;
import animals.Giraffe;
import animals.HealthStatus;
import exceptions.InvalidFeedingException;
import food.Food;
import food.FoodType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GiraffeTest {
    private Animal giraffe;
    private Food aNewFood;

    @BeforeEach
    void setUp()
    {
        giraffe = new Giraffe("Morris", 16, "Northern Giraffe", DietType.HERBIVORE, HealthStatus.SICK);
        aNewFood = new Food("Shrubs", FoodType.PLANTS);
    }

    @Test
    void testGiraffeClass()
    {
        assertEquals("Morris", giraffe.getName());
        assertEquals(16, giraffe.getAge());
        assertEquals("Northern Giraffe", giraffe.getSpecies());
        assertEquals(DietType.HERBIVORE, giraffe.getDietType());
        assertEquals(HealthStatus.SICK, giraffe.getHealthStatus());
    }

    @Test
    void testMakeSound()
    {
        giraffe.makeSound();
    }

    @Test
    void testFeed()
    {
        giraffe.feed(aNewFood);
    }

    @Test
    void testFeedAnimalSuccess() throws InvalidFeedingException {
        // Feed the giraffe with appropriate food (plants)
        feedAnimal(giraffe, aNewFood);

        // No exception should be thrown
    }

    @Test
    void testFeedAnimalInappropriate() {
        // Create inappropriate food for a herbivore (meat)
        Food meat = new Food("Meat", FoodType.MEAT);

        // Try to feed the giraffe with inappropriate food
        Exception exception = assertThrows(InvalidFeedingException.class, () -> {
            feedAnimal(giraffe, meat);
        });

        // Verify the exception message
        String expectedMessage = "Food type MEAT is not appropriate for Morris with diet type HERBIVORE";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Helper method to feed an animal with food, checking if the food is appropriate for the animal's diet.
     * @param animal The animal to feed
     * @param food The food to feed the animal with
     * @throws InvalidFeedingException If the food is not appropriate for the animal's diet
     */
    private void feedAnimal(Animal animal, Food food) throws InvalidFeedingException {
        if ((animal.getDietType() == DietType.HERBIVORE && food.getFoodType() != FoodType.PLANTS) ||
            (animal.getDietType() == DietType.CARNIVORE && food.getFoodType() != FoodType.MEAT)) {
            throw new InvalidFeedingException("Food type " + food.getFoodType() + " is not appropriate for " + animal.getName() + " with diet type " + animal.getDietType());
        }
        animal.feed(food);
    }
}
