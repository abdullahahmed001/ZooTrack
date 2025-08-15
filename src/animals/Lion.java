package animals;

import food.Food;

public class Lion extends Animal implements Feedable{
    public Lion(String name, int age, String species, DietType dietType, HealthStatus healthStatus) {
        super(name, age, species, dietType, healthStatus);
    }

    @Override
    public void makeSound() {
        System.out.println(getName() + " is roaring loudly!");
    }

    @Override
    public void feed(Food food) {
        System.out.println("The lion is being fed: " + food.getFoodType());
    }
}
