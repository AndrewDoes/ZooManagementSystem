package ZooManagementSystem.Services;

import java.util.ArrayList;
import java.util.List;

import ZooManagementSystem.Animals.Animal;
import ZooManagementSystem.Animals.Lynx;
import ZooManagementSystem.Repositories.AnimalRepository;

public class LynxService extends Services<Lynx> {
    public LynxService(AnimalRepository repo) {
        super(repo);
    }

    @Override
    public void addNewAnimal(Lynx animal) {
        if (animal == null) return;
        this.getRepo().addAnimal("Lynx", animal);
    }

    public List<Lynx> getAll() {
        List<Animal> animals = this.getRepo().getAnimals("Lynx");
        List<Lynx> lynxes = new ArrayList<Lynx>();
        for (Animal animal : animals) {
            if (animal instanceof Lynx) {
                lynxes.add((Lynx) animal);
            }
        }
        return lynxes;
    }
}