package ZooManagementSystem.Services;

import java.util.ArrayList;
import java.util.List;

import ZooManagementSystem.DomainModels.Animal;
import ZooManagementSystem.DomainModels.Gender;
import ZooManagementSystem.DomainModels.Lion;
import ZooManagementSystem.Repositories.AnimalRepository;

public class LionService extends AnimalServices<Lion>{
    public LionService(AnimalRepository repo){
        super(repo);
    }

    public void addNewLion(String name, double weight, int age, int gender) {
        Gender genderEnum;
        if(gender == 1){
            genderEnum = Gender.Male;
        }
        else genderEnum = Gender.Female;
        Lion lion = new Lion(name, age, weight, genderEnum);

        addNewAnimal(lion);
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
        if(lions == null) return 0;
        double food = 0;
        for(Lion lion : lions){
            food += lion.feed();
        }
        return food;
    }

    @Override
    public void remove(Lion animal) {
        this.getRepo().removeAnimal("Lion", animal);
    }
}
