import animals.*;
import exceptions.AnimalNotFoundException;
import exceptions.IncompatibleHabitatException;
import exceptions.InvalidFeedingException;
import food.*;
import habitats.*;
import staff.FeedingScheduleManager;
import staff.ZooKeeper;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Habitat> habitats = new ArrayList<>();
        FeedingSchedule feedingSchedule = new FeedingSchedule(new HashMap<>());
        FeedingScheduleManager scheduleManager = new FeedingScheduleManager(feedingSchedule);
        ZooKeeper keeper = new ZooKeeper("Alex");

        boolean running = true;
        while (running) {
            // Menu display (no extra helper method)
            System.out.println("\n=== ZOO APPLICATION MENU ===");
            System.out.println("1. Create Habitat (Jungle/Savannah)");
            System.out.println("2. List Habitats");
            System.out.println("3. Describe a Habitat");
            System.out.println("4. Add Animal to Habitat");
            System.out.println("5. List Animals in a Habitat");
            System.out.println("6. Assign Feeding Time to Animal");
            System.out.println("7. Print Feeding Schedule");
            System.out.println("8. Feed an Animal");
            System.out.println("9. ZooKeeper Announcement");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": {
                    // Create Habitat
                    System.out.print("Enter habitat name: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Choose habitat type (1=Jungle, 2=Savannah): ");
                    String typeChoice = scanner.nextLine().trim();
                    Habitat habitat;
                    if ("1".equals(typeChoice)) {
                        habitat = new Jungle(name, new ArrayList<>());
                    } else if ("2".equals(typeChoice)) {
                        habitat = new Savannah(name, new ArrayList<>());
                    } else {
                        System.out.println("Invalid habitat type.");
                        break;
                    }
                    habitats.add(habitat);
                    System.out.println("Created habitat: " + habitat.getName() + " (" + habitat.getHabitatType() + ")");
                    break;
                }
                case "2": {
                    // List Habitats
                    if (habitats.isEmpty()) {
                        System.out.println("No habitats available.");
                        break;
                    }
                    for (int i = 0; i < habitats.size(); i++) {
                        Habitat h = habitats.get(i);
                        System.out.println((i + 1) + ". " + h.getName() + " (" + h.getHabitatType() + ") - Animals: " + h.getAnimals().size());
                    }
                    break;
                }
                case "3": {
                    // Describe a Habitat and maintain it
                    if (habitats.isEmpty()) {
                        System.out.println("No habitats available. Please create one first.");
                        break;
                    }
                    // list habitats
                    for (int i = 0; i < habitats.size(); i++) {
                        Habitat h = habitats.get(i);
                        System.out.println((i + 1) + ". " + h.getName() + " (" + h.getHabitatType() + ") - Animals: " + h.getAnimals().size());
                    }
                    // read index
                    int idx;
                    while (true) {
                        System.out.print("Select habitat by number: ");
                        String s = scanner.nextLine().trim();
                        try {
                            idx = Integer.parseInt(s) - 1;
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid integer.");
                        }
                    }
                    if (idx < 0 || idx >= habitats.size()) {
                        System.out.println("Invalid habitat selection.");
                        break;
                    }
                    Habitat h = habitats.get(idx);
                    System.out.println(h.describeHabitat());
                    h.maintainHabitat();
                    break;
                }
                case "4": {
                    // Add Animal to Habitat
                    if (habitats.isEmpty()) {
                        System.out.println("No habitats available. Please create one first.");
                        break;
                    }
                    for (int i = 0; i < habitats.size(); i++) {
                        Habitat ha = habitats.get(i);
                        System.out.println((i + 1) + ". " + ha.getName() + " (" + ha.getHabitatType() + ") - Animals: " + ha.getAnimals().size());
                    }
                    int idx;
                    while (true) {
                        System.out.print("Select habitat by number: ");
                        String s = scanner.nextLine().trim();
                        try {
                            idx = Integer.parseInt(s) - 1;
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid integer.");
                        }
                    }
                    if (idx < 0 || idx >= habitats.size()) {
                        System.out.println("Invalid habitat selection.");
                        break;
                    }
                    Habitat hSel = habitats.get(idx);

                    System.out.println("Choose animal type: 1=Lion, 2=Elephant, 3=Giraffe, 4=Penguin, 5=Snake");
                    String aType = scanner.nextLine().trim();
                    System.out.print("Enter animal name: ");
                    String name = scanner.nextLine().trim();
                    int age;
                    while (true) {
                        System.out.print("Enter age (integer): ");
                        String s = scanner.nextLine().trim();
                        try {
                            age = Integer.parseInt(s);
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid integer.");
                        }
                    }
                    System.out.print("Enter species: ");
                    String species = scanner.nextLine().trim();
                    // choose diet
                    System.out.println("Choose diet: 1=HERBIVORE, 2=OMNIVORE, 3=CARNIVORE");
                    String dc = scanner.nextLine().trim();
                    DietType diet = DietType.OMNIVORE;
                    if ("1".equals(dc)) diet = DietType.HERBIVORE;
                    else if ("2".equals(dc)) diet = DietType.OMNIVORE;
                    else if ("3".equals(dc)) diet = DietType.CARNIVORE;
                    else System.out.println("Invalid choice, defaulting to OMNIVORE.");
                    // choose health
                    System.out.println("Choose health: 1=SICK, 2=HEALTHY, 3=HUNGRY");
                    String hc = scanner.nextLine().trim();
                    HealthStatus health = HealthStatus.HEALTHY;
                    if ("1".equals(hc)) health = HealthStatus.SICK;
                    else if ("2".equals(hc)) health = HealthStatus.HEALTHY;
                    else if ("3".equals(hc)) health = HealthStatus.HUNGRY;
                    else System.out.println("Invalid choice, defaulting to HEALTHY.");

                    Animal animal = null;
                    switch (aType) {
                        case "1":
                            animal = new Lion(name, age, species, diet, health);
                            break;
                        case "2":
                            animal = new Elephant(name, age, species, diet, health);
                            break;
                        case "3":
                            animal = new Giraffe(name, age, species, diet, health);
                            break;
                        case "4":
                            animal = new Penguin(name, age, species, diet, health);
                            break;
                        case "5":
                            animal = new Snake(name, age, species, diet, health);
                            break;
                        default:
                            System.out.println("Invalid animal type.");
                            break;
                    }
                    try {
                        // If animal is null due to invalid type, skip
                        if ("1".equals(aType) || "2".equals(aType) || "3".equals(aType) || "4".equals(aType) || "5".equals(aType)) {
                            hSel.addAnimalToHabitat(animal);
                        }
                    } catch (IncompatibleHabitatException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }
                case "5": {
                    // List Animals in a Habitat
                    if (habitats.isEmpty()) {
                        System.out.println("No habitats available. Please create one first.");
                        break;
                    }
                    for (int i = 0; i < habitats.size(); i++) {
                        Habitat h = habitats.get(i);
                        System.out.println((i + 1) + ". " + h.getName() + " (" + h.getHabitatType() + ") - Animals: " + h.getAnimals().size());
                    }
                    int idx;
                    while (true) {
                        System.out.print("Select habitat by number: ");
                        String s = scanner.nextLine().trim();
                        try {
                            idx = Integer.parseInt(s) - 1;
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid integer.");
                        }
                    }
                    if (idx < 0 || idx >= habitats.size()) {
                        System.out.println("Invalid habitat selection.");
                        break;
                    }
                    habitats.get(idx).listAnimals();
                    break;
                }
                case "6": {
                    // Assign Feeding Time
                    if (habitats.isEmpty()) {
                        System.out.println("No habitats available. Please create one first.");
                        break;
                    }
                    for (int i = 0; i < habitats.size(); i++) {
                        Habitat h = habitats.get(i);
                        System.out.println((i + 1) + ". " + h.getName() + " (" + h.getHabitatType() + ") - Animals: " + h.getAnimals().size());
                    }
                    int idx;
                    while (true) {
                        System.out.print("Select habitat by number: ");
                        String s = scanner.nextLine().trim();
                        try {
                            idx = Integer.parseInt(s) - 1;
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid integer.");
                        }
                    }
                    if (idx < 0 || idx >= habitats.size()) {
                        System.out.println("Invalid habitat selection.");
                        break;
                    }
                    Habitat h = habitats.get(idx);
                    if (h.getAnimals().isEmpty()) {
                        System.out.println("No animals in this habitat.");
                        break;
                    }
                    h.listAnimals();
                    System.out.print("Enter animal name: ");
                    String name = scanner.nextLine().trim();
                    Animal animal;
                    try {
                        animal = h.findAnimalByName(name);
                    } catch (AnimalNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                        break;
                    }
                    System.out.print("Enter feeding time (e.g., 09:00 AM): ");
                    String time = scanner.nextLine().trim();
                    scheduleManager.assignFeedingTime(animal, time);
                    break;
                }
                case "7": {
                    feedingSchedule.printFeedingSchedule();
                    break;
                }
                case "8": {
                    // Feed an Animal
                    if (habitats.isEmpty()) {
                        System.out.println("No habitats available. Please create one first.");
                        break;
                    }
                    for (int i = 0; i < habitats.size(); i++) {
                        Habitat h = habitats.get(i);
                        System.out.println((i + 1) + ". " + h.getName() + " (" + h.getHabitatType() + ") - Animals: " + h.getAnimals().size());
                    }
                    int idx;
                    while (true) {
                        System.out.print("Select habitat by number: ");
                        String s = scanner.nextLine().trim();
                        try {
                            idx = Integer.parseInt(s) - 1;
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid integer.");
                        }
                    }
                    if (idx < 0 || idx >= habitats.size()) {
                        System.out.println("Invalid habitat selection.");
                        break;
                    }
                    Habitat h = habitats.get(idx);
                    if (h.getAnimals().isEmpty()) {
                        System.out.println("No animals in this habitat.");
                        break;
                    }
                    h.listAnimals();
                    System.out.print("Enter animal name: ");
                    String name = scanner.nextLine().trim();
                    Animal animal;
                    try {
                        animal = h.findAnimalByName(name);
                    } catch (AnimalNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                        break;
                    }
                    System.out.print("Enter food name: ");
                    String foodName = scanner.nextLine().trim();
                    System.out.println("Choose food type: 1=PLANTS, 2=FRUITS, 3=MEAT");
                    String fc = scanner.nextLine().trim();
                    FoodType type = FoodType.MEAT;
                    if ("1".equals(fc)) type = FoodType.PLANTS;
                    else if ("2".equals(fc)) type = FoodType.FRUITS;
                    else if ("3".equals(fc)) type = FoodType.MEAT;
                    else System.out.println("Invalid choice, defaulting to MEAT.");
                    Food food = new Food(foodName, type);
                    try {
                        scheduleManager.feedAnimal(animal, food);
                        System.out.println("Fed " + animal.getName() + " with " + food.getName());
                    } catch (InvalidFeedingException e) {
                        System.out.println("Feeding error: " + e.getMessage());
                    }
                    break;
                }
                case "9": {
                    System.out.print("Announcement message: ");
                    keeper.makeAnnouncement(scanner.nextLine());
                    break;
                }
                case "0": {
                    running = false;
                    System.out.println("Exiting Zoo Application. Goodbye!");
                    break;
                }
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
