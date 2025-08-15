package animals;

import food.Food;

public abstract class Animal implements Feedable {
    private String name;
    private int age;
    private String species;
    private DietType dietType;
    private HealthStatus healthStatus;

    public Animal(String name, int age, String species, DietType dietType, HealthStatus healthStatus) {
        this.name = name;
        this.age = age;
        this.species = species;
        this.dietType = dietType;
        this.healthStatus = healthStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public DietType getDietType() {
        return dietType;
    }

    public void setDietType(DietType dietType) {
        this.dietType = dietType;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    public abstract void makeSound();
    public abstract void feed(Food food);
}
