package tests;

import animals.Animal;
import animals.DietType;
import animals.HealthStatus;
import animals.Lion;
import exceptions.InvalidFeedingException;
import food.Food;
import food.FoodType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LionTest {
    private Animal lion;
    private Food aNewFood;

    @BeforeEach
    void setUp()
    {
        lion = new Lion("Henry", 20, "African Lion", DietType.CARNIVORE, HealthStatus.HUNGRY);
        aNewFood = new Food("Zebras", FoodType.MEAT);
    }

    @Test
    void LionTestClass()
    {
        assertEquals("Henry", lion.getName());
        assertEquals(20, lion.getAge());
        assertEquals("African Lion", lion.getSpecies());
        assertEquals(DietType.CARNIVORE, lion.getDietType());
        assertEquals(HealthStatus.HUNGRY, lion.getHealthStatus());
    }

    @Test
    void testMakeSound()
    {
        lion.makeSound();
    }

    @Test
    void testFeed()
    {
        lion.feed(aNewFood);
    }

    @Test
    void testFeedAnimalSuccess() throws InvalidFeedingException {
        // Feed the lion with appropriate food (meat)
        feedAnimal(lion, aNewFood);

        // No exception should be thrown
    }

    @Test
    void testFeedAnimalInappropriate() {
        // Create inappropriate food for a carnivore (plants)
        Food plants = new Food("Leaves", FoodType.PLANTS);

        // Try to feed the lion with inappropriate food
        Exception exception = assertThrows(InvalidFeedingException.class, () -> {
            feedAnimal(lion, plants);
        });

        // Verify the exception message
        String expectedMessage = "Food type PLANTS is not appropriate for Henry with diet type CARNIVORE";
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
