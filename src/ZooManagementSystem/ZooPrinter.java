package ZooManagementSystem;

import java.util.Arrays;
import java.util.List;

public class ZooPrinter {
    private AnimalRepository repo;
	private LionService lionService;
	private TigerService tigerService;
	private LynxService lynxService;
	private DogService dogService;
	private PenguinService penguinService;
	private AquariumFishService fishService;
    public AnimalRepository getRepo() {
		return repo;
	}

	// Put all services from Zoo class here
    public ZooPrinter(Zoo zoo){
        this.repo = zoo.getRepo();
		lionService = zoo.getLionService();
		tigerService = zoo.getTigerService();
		lynxService = zoo.getLynxService();
		dogService = zoo.getDogService();
		penguinService = zoo.getPenguinService();
		fishService = zoo.getFishService();
    }

	public void printMenu() {
        System.out.println("\n1)Show The Zoo Details & Number of pets in it\n"
            + "2)Show all Penguins in the zoo\n"
            + "3)Add Penguin\n"
            + "4)Show all the carnivorous animals\n"
            + "5)Add a new carnivorous animal\n"
            + "6)Show all Fishies\n"
            + "7)Add Fishies to the Aquarium\n"
            + "8)Feed all pets in the zoo\n"
            + "9)Listen to all Animals in the Zoo\n"
            + "10)Make All Animals in the Zoo age One Year\n"
            + "11)Choose a way to sort the Penguins\n"
            + "12)Show all Dogs in the Zoo\n"
            + "13)Add a new Dog\n"
            + "14)Exit\n"
        );
    }

    public String printZooDetails(String name, String location) {
		String forPrint = "Name of the Zoo: " + name + " \nAddress: " + location + "\nin the Zoo there are: \n"
				+ lionService.getSize() + " Lions,\n" + tigerService.getSize()+" Tigers,\n" 
				+ lynxService.getSize()+" Lynxes,\n" + dogService.getSize() +" Dogs,\n" 
				+penguinService.getSize() + " Penguins,\n" + fishService.getSize() + " Fishes.";

		return forPrint;

	}
	
	public String printFishes() {
		List<AquariumFish> fishes = fishService.getAll();
		if(fishes == null) return "There are no Fishes in the Aquarium";
		int numberOfFishes = repo.getAnimals("Fish").size();
		if(numberOfFishes>0) {
			String Fishes_D="";
			String toPrint = "Number of fishes in the Aquarium: " + numberOfFishes + "\n";
			String All_colors = "The Colors of the Fishes: ";
			for (int i = 0; i<fishes.size(); i++) {
				Fishes_D+= (i+1)+")Type Of Fish: "+ fishes.get(i).toString()+" Age: "+ fishes.get(i).getAge() + " Length: " + String.format("%.2f",fishes.get(i).getLength()) + " Pattern: " + fishes.get(i).getPattern()+ " Happiness: "+ fishes.get(i).getHappiness() + ".\n";
				List<Colour> colours = Arrays.asList(fishes.get(i).getColours());
				All_colors += "[";
				for(Colour colour : colours){
					All_colors += colour + " ";
				}
				All_colors += "]";
			}
			All_colors = fishService.removeDuplicateWords(All_colors);
			return Fishes_D+ toPrint + All_colors;
		}
		else {
			String toPrint = "There is no Fishes in the Aquarium!";
			return toPrint;
		}
	}

	public String printPenguins() {
		List<Penguin> penguins = penguinService.getAll();
		if(penguins == null) return "There are no Penguins in the Zoo";
		String forPrint = "The penguins in the Zoo are as follows:\n";
		for (Penguin p : penguins){
			forPrint += "Name: " + p.getName() +  " Age: " + p.getAge() +  " Height: "
					+ p.getHeight() + " Happiness: "+ p.getHappiness() + ".\n";
		}
		return forPrint;
	}

	public String printLions() {
		List<Lion> lions = lionService.getAll();
		if(lions == null) return "There are no Lions in the Zoo";
		String forPrint = "The lions in the Zoo are as follows:\n";
		for (Lion lion : lions) {
			forPrint += "Name: " + lion.getName() + " Age: " + lion.getAge() + " Weight: "
					+ lion.getWeight() + " Happiness: "+ lion.getHappiness() + ".\n";
		}
		return forPrint;
	}

	public String printTigers(){
		List<Tiger> tigers = tigerService.getAll();
		if(tigers == null) return "There are no Tigers in the Zoo";
		String T_forPrint= "The Tigers in the zoo are:\n";
		for(Tiger tiger : tigers){
			T_forPrint+="Name: " + tiger.getName() + " Age: " + tiger.getAge() + " Weight: "
					+ tiger.getWeight() + "Happiness: "+ tiger.getHappiness() + ".\n";
		}
		return T_forPrint;
	}

	public String printLynxes() {
		List<Lynx> lynxes = lynxService.getAll();
		if(lynxes == null) return "There are no Lynxes in the Zoo";
		String forPrint = "The lynxes in the Zoo are as follows:\n";
		for (Lynx lynx : lynxes) {
			forPrint += "Name: " + lynx.getName() + " Age: " + lynx.getAge() + " Weight: "
					+ lynx.getWeight() + " Happiness: "+ lynx.getHappiness() + ".\n";
		}
		return forPrint;
	}

	public String printDogs(){
		List<Dog> dogs = dogService.getAll();
		if(dogs == null) return "There are no Dogs in the Zoo";
		String forPrint = "The dogs in the zoo are as follows:\n";
		for(Dog dog : dogs){
			forPrint += "Name: " + dog.getName() + " Age: " + dog.getAge() + " Weight: "
					+ dog.getWeight() +" Gender: "+ dog.getGender() + " Type: "+ dog.getType() + " Happiness: "+ dog.getHappiness() + ".\n";
		}
		return forPrint;
	}

    String reportDeathBySadness(String PrintAllDead, Animal animal) {
    	if(animal instanceof CarnivorousAnimal){
    		PrintAllDead+=((CarnivorousAnimal) animal).getName() +" is Dead because of his Sadness:(\n";
    	}else if(animal instanceof Penguin){
    		PrintAllDead+=((Penguin) animal).getName() +" is Dead because of his Sadness:(\n";
    	}
    	else if(animal instanceof AquariumFish){
    		PrintAllDead+=((AquariumFish) animal).toString() +" is Dead because of his Sadness:(\n";
    	}
    	return PrintAllDead;
    }

    String reportDeathByAge(String PrintAllDead, Animal animal) {
    	if(animal instanceof CarnivorousAnimal){
    		PrintAllDead+=((CarnivorousAnimal) animal).getName()+" is Dead because of his age\n";
    	}else if(animal instanceof Penguin){
    		PrintAllDead+=((Penguin) animal).getName()+" is Dead because of his age\n";
    	}else if(animal instanceof AquariumFish){
    		PrintAllDead+=((AquariumFish) animal).toString()+" is Dead because of his age\n";
    	}
    	return PrintAllDead;
    }

    public String ListentoAllAnimalsinZoo(Zoo zoo){
    	String out="";
    	out+= "1)Lions Noise: "+ zoo.lionService.getAll().get(0).makeNoise() + "\n2)Tigers Noise: " + zoo.tigerService.getAll().get(0).makeNoise() +
    	 "\n3)Penguins Noise: " + zoo.penguinService.getLeader().makeNoise() + "\n4)Fishes Noise: " + zoo.fishService.getAll().get(0).makeNoise() +
    	  "\n5)Lynxes Noise: " + zoo.lynxService.getAll().get(0).makeNoise() + "\n6)Dogs Noise: " + zoo.dogService.getAll().get(0).makeNoise();
    	return out;
    }

	public void printAllWildCarnivorous() {
	    System.out.println("All carnivorous animals: \n");
	    System.out.println(printLions());
	    System.out.println(printTigers());
	    System.out.println(printLynxes());
	}

	 public void printSortingOptions() {
        System.out.println("Which way would you like to sort the penguins\n" +
                "1)By Name in Ascending Order\n" +
                "2)By Height in Descending Order\n" +
                "3)By Age in Ascending Order\n");
    }

	public void printAddSuccess(){
		System.out.println("Animal added successfully");
	}

	public void feedAll(Zoo zoo){
		System.out.println("The Lions ate: "+zoo.lionService.feedAll()+"kg of meat.");
	                System.out.println("The Tigers ate: "+zoo.tigerService.feedAll()+"kg of meat.");
					System.out.println("The Lynxes ate: "+zoo.lynxService.feedAll()+"kg of meat.");
	                System.out.println("The Fishes ate: "+ String.format("%.2f",zoo.fishService.feedAll()) +" meals.\n");
	                System.out.println("The Penguins ate : "+zoo.penguinService.feedAll()+" Fishes.\n");
	}
}
