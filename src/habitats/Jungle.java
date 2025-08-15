package habitats;

import animals.Animal;
import animals.DietType;
import exceptions.AnimalNotFoundException;
import exceptions.IncompatibleHabitatException;
import exceptions.InvalidFeedingException;
import food.Food;
import food.FoodType;

import java.util.List;

public class Jungle extends Habitat {
    public Jungle(String name, List<Animal> animals) {
        super(name, HabitatType.JUNGLE, animals);
    }

    @Override
    public String describeHabitat() {
        return "A dense " + getName() + " habitat with high humidity and diverse plant life, " +
               "home to " + getAnimals().size() + " animals.";
    }

    @Override
    public boolean isSuitableForAnimal(Animal animal) {
        // Jungle is suitable for most animals except those that require very dry conditions
        // For simplicity, we'll say it's suitable for all animals except certain diet types
        return animal.getDietType() != DietType.CARNIVORE || getAnimals().size() < 5;
    }

    @Override
    public void maintainHabitat() {
        System.out.println("Maintaining the jungle habitat: clearing overgrown vegetation, " +
                          "checking humidity levels, and ensuring water sources are clean.");
    }

    @Override
    public Animal findAnimalByName(String name) throws AnimalNotFoundException {
        for (Animal animal : getAnimals()) {
            if (animal.getName().equals(name)) {
                return animal;
            }
        }
        throw new AnimalNotFoundException("Animal with name '" + name + "' not found in habitat " + getName());
    }

    @Override
    public void addAnimalToHabitat(Animal animal) throws IncompatibleHabitatException {
        if (!isSuitableForAnimal(animal)) {
            throw new IncompatibleHabitatException("Habitat " + getName() + " is not suitable for " + animal.getSpecies());
        }
        addAnimal(animal);
        System.out.println("Added " + animal.getName() + " to " + getName());
    }

    @Override
    public void feedAnimal(Animal animal, Food food) throws InvalidFeedingException {
        if ((animal.getDietType() == DietType.HERBIVORE && food.getFoodType() != FoodType.PLANTS) ||
            (animal.getDietType() == DietType.CARNIVORE && food.getFoodType() != FoodType.MEAT)) {
            throw new InvalidFeedingException("Food type " + food.getFoodType() + " is not appropriate for " + animal.getName() + " with diet type " + animal.getDietType());
        }
        animal.feed(food);
        System.out.println("Successfully fed " + animal.getName() + " with " + food.getName());
    }
}
