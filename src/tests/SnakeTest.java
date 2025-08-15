package tests;

import animals.Animal;
import animals.DietType;
import animals.HealthStatus;
import animals.Snake;
import exceptions.InvalidFeedingException;
import food.Food;
import food.FoodType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {
    private Animal snake;
    private Food aNewFood;

    @BeforeEach
    void setUp()
    {
        snake = new Snake("Molly", 8, "Ball Python", DietType.CARNIVORE, HealthStatus.SICK);
        aNewFood = new Food("Frogs", FoodType.MEAT);
    }

    @Test
    void testSnakeClass()
    {
        assertEquals("Molly", snake.getName());
        assertEquals(8, snake.getAge());
        assertEquals("Ball Python", snake.getSpecies());
        assertEquals(DietType.CARNIVORE, snake.getDietType());
        assertEquals(HealthStatus.SICK, snake.getHealthStatus());
    }

    @Test
    void testMakeSound()
    {
        snake.makeSound();
    }

    @Test
    void testFeed()
    {
        snake.feed(aNewFood);
    }

    @Test
    void testFeedAnimalSuccess() throws InvalidFeedingException {
        // Feed the snake with appropriate food (meat)
        feedAnimal(snake, aNewFood);

        // No exception should be thrown
    }

    @Test
    void testFeedAnimalInappropriate() {
        // Create inappropriate food for a carnivore (plants)
        Food plants = new Food("Leaves", FoodType.PLANTS);

        // Try to feed the snake with inappropriate food
        Exception exception = assertThrows(InvalidFeedingException.class, () -> {
            feedAnimal(snake, plants);
        });

        // Verify the exception message
        String expectedMessage = "Food type PLANTS is not appropriate for Molly with diet type CARNIVORE";
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
