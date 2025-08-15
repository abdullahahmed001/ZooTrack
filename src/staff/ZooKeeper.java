package staff;

import animals.Animal;
import animals.DietType;
import exceptions.InvalidFeedingException;
import food.FeedingSchedule;
import food.Food;
import food.FoodType;

/**
 * Represents a zoo staff member responsible for feeding and scheduling.
 */
public class ZooKeeper {
    private String name;

    public ZooKeeper() {
    }

    public ZooKeeper(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Feed an animal after validating the diet-food compatibility.
     */
    public void feedAnimal(Animal animal, Food food) throws InvalidFeedingException {
        validateFoodForDiet(animal, food);
        animal.feed(food);
    }

    /**
     * Assign a feeding time via the given FeedingSchedule.
     */
    public void assignFeedingTime(FeedingSchedule schedule, Animal animal, String time) {
        schedule.assignFeedingTime(animal, time);
    }

    /**
     * Make a simple announcement (prints to stdout).
     */
    public void makeAnnouncement(String message) {
        System.out.println((name != null ? name + ": " : "") + message);
    }

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
