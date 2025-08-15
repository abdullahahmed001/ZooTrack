package staff;

import animals.Animal;
import animals.DietType;
import exceptions.InvalidFeedingException;
import food.FeedingSchedule;
import food.Food;
import food.FoodType;

/**
 * Manages feeding schedules and safe feeding operations for animals.
 */
public class FeedingScheduleManager {
    private final FeedingSchedule feedingSchedule;

    /**
     * Create a manager with an existing FeedingSchedule instance.
     * @param feedingSchedule schedule to manage
     */
    public FeedingScheduleManager(FeedingSchedule feedingSchedule) {
        this.feedingSchedule = feedingSchedule;
    }

    /**
     * Assign a feeding time for an animal by delegating to FeedingSchedule.
     * @param animal animal to schedule
     * @param time time string (e.g., "09:00 AM")
     */
    public void assignFeedingTime(Animal animal, String time) {
        feedingSchedule.assignFeedingTime(animal, time);
    }

    /**
     * Get the feeding time for a given animal.
     */
    public String getFeedingTime(Animal animal) {
        return feedingSchedule.getFeedingTime(animal);
    }

    /**
     * Remove an animal from the feeding schedule.
     */
    public void removeAnimal(Animal animal) {
        feedingSchedule.removeAnimal(animal);
    }

    /**
     * Print the entire feeding schedule.
     */
    public void printFeedingSchedule() {
        feedingSchedule.printFeedingSchedule();
    }

    /**
     * Feed an animal with safety checks against diet vs food type.
     * Throws InvalidFeedingException when inappropriate food is used.
     */
    public void feedAnimal(Animal animal, Food food) throws InvalidFeedingException {
        validateFoodForDiet(animal, food);
        animal.feed(food);
    }

    /**
     * Validate that the given food is appropriate for the animal's diet.
     * Rule (derived from tests):
     *  - HERBIVORE -> only PLANTS or FRUITS
     *  - CARNIVORE -> only MEAT
     *  - OMNIVORE  -> any of PLANTS, FRUITS, MEAT
     */
    private void validateFoodForDiet(Animal animal, Food food) throws InvalidFeedingException {
        DietType diet = animal.getDietType();
        FoodType type = food.getFoodType();
        boolean ok;
        switch (diet) {
            case HERBIVORE:
                ok = (type == FoodType.PLANTS || type == FoodType.FRUITS);
                break;
            case CARNIVORE:
                ok = (type == FoodType.MEAT);
                break;
            case OMNIVORE:
            default:
                ok = true;
        }
        if (!ok) {
            throw new InvalidFeedingException("Food type " + type + " is not appropriate for " + animal.getName() + " with diet type " + diet);
        }
    }
}
