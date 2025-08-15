package animals;

import food.Food;

public class Giraffe extends Animal implements Feedable{
    public Giraffe(String name, int age, String species, DietType dietType, HealthStatus healthStatus) {
        super(name, age, species, dietType, healthStatus);
    }

    @Override
    public void makeSound() {
        System.out.println("The giraffe is snorting");

    }

    @Override
    public void feed(Food food) {
        System.out.println("The giraffe is being fed: " + food.getFoodType());
    }
}
