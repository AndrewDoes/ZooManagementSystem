package ZooManagementSystem.Services;
import java.util.ArrayList;
import java.util.List;

import ZooManagementSystem.Animals.Animal;
import ZooManagementSystem.Animals.Tiger;
import ZooManagementSystem.Enums.Gender;
import ZooManagementSystem.Repositories.AnimalRepository;

public class TigerService extends AnimalServices<Tiger> {
    public TigerService(AnimalRepository repo) {
        super(repo);
    }

    public void addNewTiger(String name, double weight, int age, int gender) {
        Gender genderEnum;
        if(gender == 1){
            genderEnum = Gender.Male;
        }
        else genderEnum = Gender.Female;
        Tiger tiger = new Tiger(name, age, weight, genderEnum);
        addNewAnimal(tiger);
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

    @Override
    public void remove(Tiger animal) {
        this.getRepo().removeAnimal("Tiger", animal);
    }
}