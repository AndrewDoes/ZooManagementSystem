package ZooManagementSystem.Services;

import java.util.ArrayList;
import java.util.List;

import ZooManagementSystem.DomainModels.Animal;
import ZooManagementSystem.DomainModels.Dog;
import ZooManagementSystem.DomainModels.DogType;
import ZooManagementSystem.DomainModels.Gender;
import ZooManagementSystem.Repositories.AnimalRepository;

public class DogService extends AnimalServices<Dog> {
    public DogService(AnimalRepository repo) {
        super(repo);
    }

    @Override
    public void addNewAnimal(Dog animal) {
        if (animal == null) return;
        this.getRepo().addAnimal("Dog", animal);
    }

    public List<Dog> getAll() {
        List<Animal> animals = this.getRepo().getAnimals("Dog");
		if(animals.size() == 0) return null;
        List<Dog> dogs = new ArrayList<Dog>();
        for (Animal animal : animals) {
            if (animal instanceof Dog) {
                dogs.add((Dog) animal);
            }
        }
        return dogs;
    }

    public double feedAll(){
        List<Dog> dogs = getAll();
        if(dogs == null) return 0;
        double food = 0;
        for(Dog dog : dogs){
            food += dog.feed();
        }
        return food;
    }

    public void AddNewDog(String name_d, int age_d, double weight_d, int dogtype, int dog_){
    	Gender gender;
    	DogType Type;
    	if(dog_==1){
    		gender=Gender.Male;
    	}
    	else {
    		gender=Gender.Female;
    	}
    	if(dogtype==1){
    		Type=DogType.Akita;
    	} else if (dogtype==2) {
    		Type=DogType.Bulldog;
    	} else if (dogtype==3) {
    		Type=DogType.Poodle;
    	} else {
    		Type=DogType.Terriers;
    	}
    	Dog newDog= new Dog(name_d,age_d,weight_d,Type,gender);
    	addNewAnimal(newDog);
    	System.out.println("Animals Added");
    }

    @Override
    public void remove(Dog dog) {
        this.getRepo().removeAnimal("Dog", dog);
    }
}
