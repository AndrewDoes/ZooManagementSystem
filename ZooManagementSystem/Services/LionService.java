package ZooManagementSystem.Services;

import java.util.ArrayList;
import java.util.List;

import ZooManagementSystem.Animals.Animal;
import ZooManagementSystem.Animals.Lion;
import ZooManagementSystem.Repositories.AnimalRepository;

public class LionService extends Services<Lion>{
    public LionService(AnimalRepository repo){
        super(repo);
    }
    @Override
    public void addNewAnimal(Lion animal) {
        if(animal == null) return;
        this.getRepo().addAnimal("Lion", animal);
    }
    public List<Lion> getAll(){
        List<Animal> animals = getRepo().getAnimals("Lion");
		if(animals.size() == 0) return new ArrayList<Lion>();
        List<Lion> lions = new ArrayList<Lion>();
        for (Animal animal : animals){
            if(animal instanceof Lion){
                lions.add((Lion) animal);
            }
        }
        return lions;
    }

    public double feedAll(){
        List<Lion> lions = getAll();
        double food = 0;
        for(Lion lion : lions){
            food += lion.feed();
        }
        return food;
    }
}
