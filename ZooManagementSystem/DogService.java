package ZooManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class DogService extends Services<Dog> {
    public DogService(AnimalRepository repo) {
        super(repo);
    }

    @Override
    public void addNewAnimal(Dog animal) {
        if (animal == null) return;
        this.getRepo().addAnimal("Dog", animal);
    }

    public List<Dog> getAll() {
        List<Animal> animals = this.getRepo().getAnimals("Dog");
        List<Dog> dogs = new ArrayList<Dog>();
        for (Animal animal : animals) {
            if (animal instanceof Dog) {
                dogs.add((Dog) animal);
            }
        }
        return dogs;
    }
}
