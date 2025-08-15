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

class InvalidFeedingExceptionTest {
    private Animal lion;
    private Food meat;
    private Food plants;

    @BeforeEach
    void setUp() {
        lion = new Lion("Henry", 20, "African Lion", DietType.CARNIVORE, HealthStatus.HUNGRY);
        meat = new Food("Meat", FoodType.MEAT);
        plants = new Food("Leaves", FoodType.PLANTS);
    }

    @Test
    void testFeedAnimalSuccess() throws InvalidFeedingException {
        // Test feeding an animal with appropriate food
        feedAnimal(lion, meat);
        
        // No exception should be thrown
    }

    @Test
    void testFeedAnimalInappropriate() {
        // Test feeding an animal with inappropriate food
        Exception exception = assertThrows(InvalidFeedingException.class, () -> {
            feedAnimal(lion, plants);
        });
        
        // Verify the exception message
        String expectedMessage = "Food type PLANTS is not appropriate for Henry with diet type CARNIVORE";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Feeds an animal with food, checking if the food is appropriate for the animal's diet.
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