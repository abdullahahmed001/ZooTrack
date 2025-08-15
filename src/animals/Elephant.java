package animals;

import food.Food;
import food.FoodType;

public class Elephant extends Animal implements Feedable
{
    public Elephant(String name, int age, String species, DietType dietType, HealthStatus healthStatus) {
        super(name, age, species, dietType, healthStatus);
    }

    /**
     * This is a public void method makeSound which prints out the name of the animal and the sound they make
     * Since this is a void method, it doesn't return anything and doesn't take in any arguments
     */
    @Override
    public void makeSound() {
        System.out.println(getName() + " trumps loudly!");
    }

    /**
     * This is a public method called feed which takes in a food parameter
     * It prints out what type of food the elephant is being fed
     * @param food
     */
    @Override
    public void feed(Food food) {
        System.out.println("The elephant is being fed: " + food.getFoodType());
    }

}
