package ZooManagementSystem;

import java.util.ArrayList;
import java.util.List;

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