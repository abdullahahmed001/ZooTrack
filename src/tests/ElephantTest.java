package tests;

import animals.Animal;
import animals.DietType;
import animals.Elephant;
import animals.HealthStatus;
import exceptions.InvalidFeedingException;
import food.Food;
import food.FoodType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ElephantTest {
    private Animal elephant;
    private Food aNewFood;

    @BeforeEach
    void setUp()
    {
        elephant = new Elephant("Susan", 5, "African elephant", DietType.HERBIVORE, HealthStatus.HEALTHY);
        aNewFood = new Food("Twigs", FoodType.PLANTS);
    }

    @Test
    void testElephantClass()
    {
        assertEquals("Susan", elephant.getName());
        assertEquals(5, elephant.getAge());
        assertEquals(HealthStatus.HEALTHY, elephant.getHealthStatus());
        assertEquals("African elephant", elephant.getSpecies());
        assertEquals(DietType.HERBIVORE, elephant.getDietType());
    }

    @Test
    void testMakeSound()
    {
        elephant.makeSound();
    }

    @Test
    void testFeed()
    {
        elephant.feed(aNewFood);
    }

    @Test
    void testUpdateAge()
    {
        elephant.setAge(10);
        assertEquals(10, elephant.getAge());
    }

    @Test
    void testFeedAnimalSuccess() throws InvalidFeedingException {
        // Feed the elephant with appropriate food (plants)
        feedAnimal(elephant, aNewFood);

        // No exception should be thrown
    }

    @Test
    void testFeedAnimalInappropriate() {
        // Create inappropriate food for a herbivore (meat)
        Food meat = new Food("Meat", FoodType.MEAT);

        // Try to feed the elephant with inappropriate food
        Exception exception = assertThrows(InvalidFeedingException.class, () -> {
            feedAnimal(elephant, meat);
        });

        // Verify the exception message
        String expectedMessage = "Food type MEAT is not appropriate for Susan with diet type HERBIVORE";
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
