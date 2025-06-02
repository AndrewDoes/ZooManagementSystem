package ZooManagementSystem.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ZooManagementSystem.Animals.Animal;
import ZooManagementSystem.Animals.AquariumFish;
import ZooManagementSystem.Animals.ClownFish;
import ZooManagementSystem.Animals.GoldFish;
import ZooManagementSystem.Enums.Colour;
import ZooManagementSystem.Enums.Pattern;
import ZooManagementSystem.Repositories.AnimalRepository;

public class FishService extends AnimalServices<AquariumFish>{
    public FishService(AnimalRepository repo){
        super(repo);
        // checkRepo();
    };

    public List<AquariumFish> getAll(){
        List<Animal> animals = getRepo().getAnimals("Fish");
		if(animals.size() == 0) return null;
        List<AquariumFish> fishes = new ArrayList<>();

        for(Animal animal : animals){
            if(animal instanceof AquariumFish){
                fishes.add((AquariumFish) animal);
            }
        }
        return fishes;
    }

    //ini simply cuma add ke repo
    public void addNewAnimal(AquariumFish fish) {
        if(fish == null) return;
        this.getRepo().addAnimal( "Fish", fish);
    }

    //bukan middle man ya soalnya ini main logicnya
    public AquariumFish createNewFishByType(int type, int age, double length, List<Colour> singularFishColours,Pattern Pattern){
		AquariumFish new_fish=null;
        // int age, int lifeSpan, double length, List<Colour> colours, Pattern pattern
			if(type == 3) {
				new_fish = new ClownFish(age, length);
			} else if(type == 2) {
                List<Colour> colours = Arrays.asList(Colour.BLACK, Colour.GOLD, Colour.ORANGE, Colour.YELLOW);
                Colour colour = FishAttributeRandomizer.randomSingleColour(colours);
				new_fish = new GoldFish(age, length, colour);
			} else if (type == 1) {
				new_fish = new AquariumFish(age,length, singularFishColours, Pattern);
			}
            addNewAnimal(new_fish);
            return new_fish;
	}
	//Type of Fish 1 = Reg Fish
	//Type of Fish 2 = GoldFish
	//Type of Fish 3 = ClownFish
	public List<AquariumFish> addRandomFish(int num) {
		for (int i = 0; i < num; i++) {
            int ageTemp = (int)(Math.random() * 10 + 1);
            double lengthTemp = Math.random() * 10 + 1;
            int typeOfFish = (int)(Math.random() * 3 + 1);
            AquariumFish fish = null;
            if(typeOfFish == 1){
                fish = new AquariumFish(ageTemp, lengthTemp, FishAttributeRandomizer.randomColour(), FishAttributeRandomizer.randomPattern());
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

	public String removeDuplicateWords(String inputString) {
		String[] words = inputString.split("\\s+");
		String result = Arrays.stream(words)
				.distinct()
				.collect(Collectors.joining(" "));

		return result;
	}

    public String MostPopularFishColour() {
        List<AquariumFish> fishes = getAll();
        if(fishes == null) return null;
        Map<Colour, Integer> colourCounts = new HashMap<>();
        assignColourMap(fishes, colourCounts);
        // Find the two most common colours
        Colour mostCommonColour = null;
        Colour secondMostCommonColour = null;
        int maxCount = 0;
        int secondMaxCount = 0;
        return find2MostPopularColour(colourCounts, mostCommonColour, secondMostCommonColour, maxCount, secondMaxCount);
    }

	private String find2MostPopularColour(Map<Colour, Integer> colourCounts, Colour mostCommonColour, Colour secondMostCommonColour,
			int maxCount, int secondMaxCount) {
		for (Map.Entry<Colour, Integer> entry : colourCounts.entrySet()) {
		    int count = entry.getValue();
		    if (count > maxCount) {
		        secondMaxCount = maxCount;
		        maxCount = count;
		        secondMostCommonColour = mostCommonColour;
		        mostCommonColour = entry.getKey();
		    } else if (count > secondMaxCount) {
		        secondMaxCount = count;
		        secondMostCommonColour = entry.getKey();
		    }
		}
		return "The most common colour in the aquarium are:\n" + mostCommonColour + " & " + secondMostCommonColour;
	}

	private void assignColourMap(List<AquariumFish> fishes, Map<Colour, Integer> colourCounts) {
        if(fishes == null) return;
		for (AquariumFish fish : fishes) {
		    List<Colour> colours = fish.getColours();
		    for (Colour colour : colours) {
		        colourCounts.put(colour, colourCounts.getOrDefault(colour, 0) + 1);
		    }
		}
	}

    public double feedAll(){
        List<AquariumFish> fishes = getAll();
        if(fishes == null) return 0;
        double food = 0;
        for(AquariumFish fish : fishes){
            food += fish.feed();
        }
        return food;
    }

    @Override
    public void remove(AquariumFish animal) {
        this.getRepo().removeAnimal("Fish", animal);
    }
}
