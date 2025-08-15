package animals;

import food.Food;

public class Snake extends Animal implements Feedable{
    public Snake(String name, int age, String species, DietType dietType, HealthStatus healthStatus) {
        super(name, age, species, dietType, healthStatus);
    }

    @Override
    public void makeSound() {
        System.out.println("The snake is hissing!");
    }

    @Override
    public void feed(Food food) {
        System.out.println("The snake is being fed: " + food.getFoodType());
    }
}
