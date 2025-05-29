package ZooManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class TigerService extends Services<Tiger> {
    public TigerService(AnimalRepository repo) {
        super(repo);
    }

    @Override
    public void addNewAnimal(Tiger animal) {
        if (animal == null) return;
        this.getRepo().addAnimal("Tiger", animal);
    }

    public List<Tiger> getAll() {
        List<Animal> animals = this.getRepo().getAnimals("Tiger");
        List<Tiger> tigers = new ArrayList<Tiger>();
        for (Animal animal : animals) {
            if (animal instanceof Tiger) {
                tigers.add((Tiger) animal);
            }
        }
        return tigers;
    }
}