package ZooManagementSystem.Repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ZooManagementSystem.Animals.Animal;
import ZooManagementSystem.Animals.Dog;
import ZooManagementSystem.Animals.Lion;
import ZooManagementSystem.Animals.Lynx;
import ZooManagementSystem.Animals.Penguin;
import ZooManagementSystem.Animals.Tiger;
import ZooManagementSystem.Enums.DogType;
import ZooManagementSystem.Enums.Gender;

public class AnimalRepository {
    private Map<String, List<Animal>> animalMap;

    public AnimalRepository() {
        animalMap = new HashMap<>();
        // initializeAnimals();
    }

    public void initializeAnimals(){
        addAnimal("Penguin", new Penguin("Penny", 10, 200));
        addAnimal("Penguin", new Penguin("Lenny", 10, 198));
        addAnimal("Penguin", new Penguin("Tim", 10, 195));
        addAnimal("Lion", new Lion("Bird", 5, 36, Gender.Male));
        addAnimal("Lion", new Lion("Hungry", 20, 80, Gender.Male));
        addAnimal("Lion", new Lion("Skinny", 10, 40, Gender.Female));
        addAnimal("Lion", new Lion("Chunky", 24, 90, Gender.Female));
        addAnimal("Tiger", new Tiger("Mask", 20, 15, Gender.Male));
        addAnimal("Tiger", new Tiger("Husk", 5, 20, Gender.Female));
        addAnimal("Lynx", new Lynx("Timpo", 8, 15, Gender.Male));
        addAnimal("Dog", new Dog("Leo", 8, 10, DogType.Akita, Gender.Male));
    }

    public void addAnimal(String animalType, Animal animal) {
        if (!animalMap.containsKey(animalType)) {
            animalMap.put(animalType, new ArrayList<>());
        }
        animalMap.get(animalType).add(animal);
    }

    public List<Animal> getAnimals(String animalType) {
        return animalMap.get(animalType);
    }

    public List<Animal> getAllAnimal(){
        List<Animal> allAnimals = new ArrayList<>();
        for (List<Animal> animals : animalMap.values()) {
            allAnimals.addAll(animals);
        }
        return allAnimals;
    }

    public void removeAnimal(String animalType, Animal animal) {
        if (animalMap.containsKey(animalType)) {
            animalMap.get(animalType).remove(animal);
        }
    }

    public void clearAnimals(String animalType) {
        if (animalMap.containsKey(animalType)) {
            animalMap.get(animalType).clear();
        }
    }

    public void clearAllAnimals() {
        animalMap.clear();
    }
}