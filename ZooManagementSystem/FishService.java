package ZooManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class FishService {
    private AnimalRepository repo;

    public FishService(AnimalRepository repo){
        this.repo = repo;
    };

    public List<Fish> getAllFish(){
        List<Animal> animals = repo.getAnimals("Fish");
        List<Fish> fishes = new ArrayList<Fish>();

        for(Animal animal : animals){
            if(animal instanceof Fish){
                fishes.add((Fish) animal);
            }
        }
        return fishes;
    }

    public void AddFish(int type, int age, double length, List<Colour> singularFishColours,Pattern Pattern){
		Fish new_fish=null;
        // int age, int lifeSpan, double length, List<Colour> colours, Pattern pattern
			if(type == 3) {
				new_fish = new ClownFish(age, length);
			} else if(type == 2) {
				new_fish = new GoldFish(age, length);
			} else if (type == 1) {
				new_fish = new Fish(age,length, singularFishColours, Pattern);
			}
            repo.addAnimal("Fish", new_fish);
            return;
	}
	//Type of Fish 1 = Reg Fish
	//Type of Fish 2 = GoldFish
	//Type of Fish 3 = ClownFish
	public List<Fish> addRandomFish(int num) {
		for (int i = 0; i < num; i++) {
				int ageTemp = (int)(Math.random() * 10 + 1);
				double lengthTemp = Math.random() * 10 + 1;
				int typeOfFish = (int)(Math.random() * 3 + 1);
				if(typeOfFish == 2) {
					repo.addAnimal("Fish", new GoldFish(ageTemp, lengthTemp));
					continue;
				}
				if(typeOfFish == 3) {
					repo.addAnimal("Fish", new ClownFish(ageTemp, lengthTemp));
					continue;
				}
                Fish fish = new Fish(ageTemp, lengthTemp, FishAttributeRandomizer.randomColour(), FishAttributeRandomizer.randomPattern());
                repo.addAnimal("Fish", fish);
			}
        return getAllFish();
    }


}
