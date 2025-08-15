package tests;

import animals.Animal;
import animals.DietType;
import animals.HealthStatus;
import animals.Penguin;
import exceptions.InvalidFeedingException;
import food.Food;
import food.FoodType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PenguinTest {
    private Animal penguin;
    private Food aNewFood;

    @BeforeEach
    void setUp()
    {
        penguin = new Penguin("Sally", 25, "Emperor Penguin", DietType.CARNIVORE, HealthStatus.HUNGRY);
        aNewFood = new Food("Fish", FoodType.MEAT);
    }

    @Test
    void testPenguinClass()
    {
        assertEquals("Sally", penguin.getName());
        assertEquals(25, penguin.getAge());
        assertEquals("Emperor Penguin", penguin.getSpecies());
        assertEquals(DietType.CARNIVORE, penguin.getDietType());
        assertEquals(HealthStatus.HUNGRY, penguin.getHealthStatus());
    }

    @Test
    void testMakeSound()
    {
        penguin.makeSound();
    }

    @Test
    void testFeed()
    {
        penguin.feed(aNewFood);
    }

    @Test
    void testUpdateName()
    {
        penguin.setName("Molly");
        penguin.getName();
    }

    @Test
    void testUpdateAge()
    {
        penguin.setAge(15);
        penguin.getAge();
    }

    @Test
    void testUpdateSpecies()
    {
        penguin.setSpecies("King Penguin");
        penguin.getSpecies();
    }

    @Test
    void testUpdateDiet()
    {
        penguin.setDietType(DietType.HERBIVORE);
        penguin.getDietType();
    }

    @Test
    void testUpdateHealth()
    {
        penguin.setHealthStatus(HealthStatus.SICK);
        penguin.getHealthStatus();
    }

    @Test
    void testFeedAnimalSuccess() throws InvalidFeedingException {
        // Feed the penguin with appropriate food (meat)
        feedAnimal(penguin, aNewFood);

        // No exception should be thrown
    }

    @Test
    void testFeedAnimalInappropriate() {
        // Create inappropriate food for a carnivore (plants)
        Food plants = new Food("Leaves", FoodType.PLANTS);

        // Try to feed the penguin with inappropriate food
        Exception exception = assertThrows(InvalidFeedingException.class, () -> {
            feedAnimal(penguin, plants);
        });

        // Verify the exception message
        String expectedMessage = "Food type PLANTS is not appropriate for Sally with diet type CARNIVORE";
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
