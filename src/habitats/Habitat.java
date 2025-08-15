package habitats;

import animals.Animal;
import exceptions.AnimalNotFoundException;
import exceptions.IncompatibleHabitatException;
import exceptions.InvalidFeedingException;
import food.Food;

import java.util.ArrayList;
import java.util.List;

public abstract class Habitat {
    private String name;
    private HabitatType habitatType;
    private List<Animal> animals;

    public Habitat(String name, HabitatType habitatType, List<Animal> animals) {
        this.name = name;
        this.habitatType = habitatType;
        this.animals = new ArrayList<>(animals);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HabitatType getHabitatType() {
        return habitatType;
    }

    public void setHabitatType(HabitatType habitatType) {
        this.habitatType = habitatType;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    /**
     * This is a public method called addAnimal that takes in an animal object as a parameter
     * The goal of this method is to simply add an animal
     */
    public void addAnimal(Animal animal)
    {
        animals.add(animal);
    }

    public void removeAnimal(Animal animal)
    {
        animals.remove(animal);
    }

    /**
     * Step 1: Check if the list of animals is empty
     * Step 2: If the list of animals isn't empty, then loop through the list of animals
     * Step 3: Print information about each animal
     *
     */
    public void listAnimals()
    {
        if (animals.isEmpty()) {
            System.out.println("There are no animals in this habitat.");
            return;
        }

        System.out.println("The animals in this habitat are: ");
        for (Animal animal : animals) {
            System.out.println("- " + animal.getName() + " (" + animal.getSpecies() + "), " 
                + animal.getAge() + " years old, " 
                + animal.getDietType() + ", " 
                + animal.getHealthStatus());
        }
    }

    /**
     * Provides a description of the habitat including its characteristics.
     * @return A string describing the habitat
     */
    public abstract String describeHabitat();

    /**
     * Checks if the habitat is suitable for a specific animal.
     * @param animal The animal to check suitability for
     * @return true if the habitat is suitable for the animal, false otherwise
     */
    public abstract boolean isSuitableForAnimal(Animal animal);

    /**
     * Performs maintenance tasks specific to this type of habitat.
     */
    public abstract void maintainHabitat();

    /**
     * Finds an animal by name in the habitat.
     * @param name The name of the animal to find
     * @return The found animal
     * @throws AnimalNotFoundException If no animal with the given name is found
     */
    public abstract Animal findAnimalByName(String name) throws AnimalNotFoundException;

    /**
     * Adds an animal to the habitat, checking if the habitat is suitable for the animal.
     * @param animal The animal to add
     * @throws IncompatibleHabitatException If the habitat is not suitable for the animal
     */
    public abstract void addAnimalToHabitat(Animal animal) throws IncompatibleHabitatException;

    /**
     * Feeds an animal with food, checking if the food is appropriate for the animal's diet.
     * @param animal The animal to feed
     * @param food The food to feed the animal with
     * @throws InvalidFeedingException If the food is not appropriate for the animal's diet
     */
    public abstract void feedAnimal(Animal animal, Food food) throws InvalidFeedingException;
}
