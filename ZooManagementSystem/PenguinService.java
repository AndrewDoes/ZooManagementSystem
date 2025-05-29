package ZooManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class PenguinService extends Services<Penguin> {
    public PenguinService(AnimalRepository repo) {
        super(repo);
    }

    @Override
    public void addNewAnimal(Penguin animal) {
        if (animal == null) return;
        this.getRepo().addAnimal("Penguin", animal);
    }

    public List<Penguin> getAll() {
        List<Animal> animals = this.getRepo().getAnimals("Penguin");
        List<Penguin> penguins = new ArrayList<Penguin>();
        for (Animal animal : animals) {
            if (animal instanceof Penguin) {
                penguins.add((Penguin) animal);
            }
        }
        return penguins;
    }
}