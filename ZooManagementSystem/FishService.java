package ZooManagementSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FishService extends Services<Fish>{
    private AnimalRepository repo;

    public FishService(AnimalRepository repo){
        super(repo);
    };

    public List<Fish> getAll(){
        List<Animal> animals = repo.getAnimals("Fish");
        List<Fish> fishes = new ArrayList<Fish>();

        for(Animal animal : animals){
            if(animal instanceof Fish){
                fishes.add((Fish) animal);
            }
        }
        return fishes;
    }

    //ini simply cuma add ke repo
    public void addNewAnimal(Fish fish) {
        if(fish == null) return;
        this.getRepo().addAnimal("Fish", fish);
    }

    //bukan middle man ya soalnya ini main logicnya
    public Fish createNewFishByType(int type, int age, double length, List<Colour> singularFishColours,Pattern Pattern){
		Fish new_fish=null;
        // int age, int lifeSpan, double length, List<Colour> colours, Pattern pattern
			if(type == 3) {
				new_fish = new ClownFish(age, length);
			} else if(type == 2) {
                List<Colour> colours = Arrays.asList(Colour.BLACK, Colour.GOLD, Colour.ORANGE, Colour.YELLOW);
                Colour colour = FishAttributeRandomizer.randomSingleColour(colours);
				new_fish = new GoldFish(age, length, colour);
			} else if (type == 1) {
				new_fish = new Fish(age,length, singularFishColours, Pattern);
			}
            addNewAnimal(new_fish);
            return new_fish;
	}
	//Type of Fish 1 = Reg Fish
	//Type of Fish 2 = GoldFish
	//Type of Fish 3 = ClownFish
	public List<Fish> addRandomFish(int num) {
		for (int i = 0; i < num; i++) {
            int ageTemp = (int)(Math.random() * 10 + 1);
            double lengthTemp = Math.random() * 10 + 1;
            int typeOfFish = (int)(Math.random() * 3 + 1);
            Fish fish = null;
            if(typeOfFish == 1){
                fish = new Fish(ageTemp, lengthTemp, FishAttributeRandomizer.randomColour(), FishAttributeRandomizer.randomPattern());
            }
            if(typeOfFish == 2) {
                List<Colour> colours = Arrays.asList(Colour.BLACK, Colour.GOLD, Colour.ORANGE, Colour.YELLOW);
                Colour colour = FishAttributeRandomizer.randomSingleColour(colours);
                fish = new GoldFish(ageTemp, lengthTemp, colour);
            }
            if(typeOfFish == 3) {

                fish = new ClownFish(ageTemp, lengthTemp);
            }
            addNewAnimal(fish);
        }
        return getAll();
    }

	public static String removeDuplicateWords(String inputString) {
		String[] words = inputString.split("\\s+");
		String result = Arrays.stream(words)
				.distinct()
				.collect(Collectors.joining(" "));

		return result;
	}
}
