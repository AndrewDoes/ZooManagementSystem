package ZooManagementSystem;

import java.util.ArrayList;
import java.util.List;

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
        List<Animal> animals = this.getRepo().getAnimals("Lion");
        List<Lion> lions = new ArrayList<Lion>();
        for (Animal animal : animals){
            if(animal instanceof Lion){
                lions.add((Lion) animal);
            }
        }
        return lions;
    }
}
