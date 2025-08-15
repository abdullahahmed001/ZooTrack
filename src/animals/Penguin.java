package animals;

import food.Food;

public class Penguin extends Animal implements Feedable{
    public Penguin(String name, int age, String species, DietType dietType, HealthStatus healthStatus) {
        super(name, age, species, dietType, healthStatus);
    }

    @Override
    public void makeSound() {
        System.out.println("The penguin is squawking!");
    }

    @Override
    public void feed(Food food) {
        System.out.println("The penguin is being fed: " + food.getFoodType());
    }
}
