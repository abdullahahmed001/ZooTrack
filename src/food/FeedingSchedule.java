package food;
import animals.Animal;

import java.util.*;
public class FeedingSchedule {
    private Map<Animal, String> feedingSchedule;

    public FeedingSchedule(Map<Animal, String> feedingMap) {
        this.feedingSchedule = new HashMap<>(feedingMap);
    }

    public void assignFeedingTime(Animal animal, String time)
    {
        feedingSchedule.put(animal, time);
        System.out.println("Assigned: " + animal.getName() + " to be fed at: "  + time);
    }

    public String getFeedingTime(Animal animal)
    {
        return feedingSchedule.getOrDefault(animal, "No feeding time assigned");
    }

    public void printFeedingSchedule()
    {
        if(feedingSchedule.isEmpty())
        {
            System.out.println("There are no animals to be fed.");
            return;
        }

        for(Map.Entry<Animal, String> anEntry: feedingSchedule.entrySet())
        {
            System.out.println(anEntry.getKey().getName() + " ---> " + anEntry.getValue());
        }
    }

    /**
     * This method is responsible for removing an animal
     * @param animal
     */
    public void removeAnimal(Animal animal)
    {
        feedingSchedule.remove(animal);
    }
}
