package ZooManagementSystem.Services;

import java.util.ArrayList;
import java.util.List;

import ZooManagementSystem.DomainModels.Animal;
import ZooManagementSystem.DomainModels.Gender;
import ZooManagementSystem.DomainModels.Lynx;
import ZooManagementSystem.Repositories.AnimalRepository;

public class LynxService extends AnimalServices<Lynx> {
    public LynxService(AnimalRepository repo) {
        super(repo);
    }

    public void addNewLynx(String name, double weight, int age, int gender) {
        Gender genderEnum;
        if(gender == 1){
            genderEnum = Gender.Male;
        }
        else genderEnum = Gender.Female;
        Lynx lynx = new Lynx(name, age, weight, genderEnum);
        addNewAnimal(lynx);
    }

    @Override
    public void addNewAnimal(Lynx animal) {
        if (animal == null) return;
        this.getRepo().addAnimal("Lynx", animal);
    }

    public List<Lynx> getAll() {
        List<Animal> animals = this.getRepo().getAnimals("Lynx");
		if(animals.size() == 0) return null;
        List<Lynx> lynxes = new ArrayList<Lynx>();
        for (Animal animal : animals) {
            if (animal instanceof Lynx) {
                lynxes.add((Lynx) animal);
            }
        }
        return lynxes;
    }

    public double feedAll(){
        List<Lynx> lynxes = getAll();
        if(lynxes == null) return 0;
        double food = 0;
        for(Lynx lynx : lynxes){
            food += lynx.feed();
        }
        return food;
    }

    @Override
    public void remove(Lynx animal) {
        this.getRepo().removeAnimal("Lynx", animal);
    }
}