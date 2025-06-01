package ZooManagementSystem.Services;

import java.util.ArrayList;
import java.util.List;

import ZooManagementSystem.Animals.Animal;
import ZooManagementSystem.Animals.Tiger;
import ZooManagementSystem.Repositories.AnimalRepository;

public class TigerService extends AnimalServices<Tiger> {
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
		if(animals.size() == 0) return null;
        List<Tiger> tigers = new ArrayList<Tiger>();
        for (Animal animal : animals) {
            if (animal instanceof Tiger) {
                tigers.add((Tiger) animal);
            }
        }
        return tigers;
    }

    public double feedAll(){
        List<Tiger> tigers = getAll();
        if(tigers == null) return 0;
        double food = 0;
        for(Tiger tiger : tigers){
            food += tiger.feed();
        }
        return food;
    }
}