package ZooManagementSystem;

import java.util.List;

import ZooManagementSystem.Animals.Dog;
import ZooManagementSystem.Animals.Fish;
import ZooManagementSystem.Animals.Lion;
import ZooManagementSystem.Animals.Lynx;
import ZooManagementSystem.Animals.Penguin;
import ZooManagementSystem.Animals.Tiger;
import ZooManagementSystem.Repositories.AnimalRepository;
import ZooManagementSystem.Services.DogService;
import ZooManagementSystem.Services.FishService;
import ZooManagementSystem.Services.LionService;
import ZooManagementSystem.Services.LynxService;
import ZooManagementSystem.Services.PenguinService;
import ZooManagementSystem.Services.TigerService;

public class ZooPrinter {
    private AnimalRepository repo;
    // Put all services from Zoo class here
    private LionService lionService = new LionService(repo);
    private TigerService tigerService = new TigerService(repo);
    private LynxService lynxService = new LynxService(repo);
    private DogService dogService = new DogService(repo);
    private PenguinService penguinService = new PenguinService(repo);
    private FishService fishService = new FishService(repo);
    public ZooPrinter(AnimalRepository repo){
        this.repo = repo;
    }

    	public String printZooDetails(String name, String location) {
		String forPrint = "Name of the Zoo: " + name + " \nAddress: " + location + "\nin the Zoo there are: \n"
				+ lionService.getAll().size() + " Lions,\n" + tigerService.getAll().size()+" Tigers,\n" 
				+ lynxService.getAll().size()+" Lynxes,\n" + dogService.getAll().size() +" Dogs,\n" 
				+penguinService.getAll().size() + " Penguins,\n" + fishService.getAll().size() + " Fishes.";

		return forPrint;

	}
	
	public String printFishes() {
		List<Fish> fishes = fishService.getAll();
		int numberOfFishes = repo.getAnimals("Fish").size();
		if(numberOfFishes>0) {
			String Fishes_D="";
			String toPrint = "Number of fishes in the Aquarium: " + numberOfFishes + "\n";
			String All_colors = "The Colors of the Fishes: ";
			for (int i = 0; fishes.get(i) != null; i++) {
				Fishes_D+= (i+1)+")Type Of Fish: "+ fishes.get(i).toString()+" Age: "+ fishes.get(i).getAge() + " Length: " + String.format("%.2f",fishes.get(i).getLength()) + " Pattern: " + fishes.get(i).getPattern()+ " Happiness: "+ fishes.get(i).getHappiness() + ".\n";
				All_colors += fishes.get(i).getColours() + " ";
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
		String forPrint = "The penguins in the Zoo are as follows:\n";
		for (Penguin p : penguins){
			forPrint += "Name: " + p.getName() +  " Age: " + p.getAge() +  " Height: "
					+ p.getHeight() + " Happiness: "+ p.getHappiness() + ".\n";
		}
		return forPrint;
	}

	public String printLions() {
		List<Lion> lions = lionService.getAll();
		String forPrint = "The lions in the Zoo are as follows:\n";
		for (Lion lion : lions) {
			forPrint += "Name: " + lion.getName() + " Age: " + lion.getAge() + " Weight: "
					+ lion.getWeight() + " Happiness: "+ lion.getHappiness() + ".\n";
		}
		return forPrint;
	}

	public String printTigers(){
		List<Tiger> tigers = tigerService.getAll();
		String T_forPrint= "The Tigers in the zoo are:\n";
		for(Tiger tiger : tigers){
			T_forPrint+="Name: " + tiger.getName() + " Age: " + tiger.getAge() + " Weight: "
					+ tiger.getWeight() + "Happiness: "+ tiger.getHappiness() + ".\n";
		}
		return T_forPrint;
	}

	public String printLynxes() {
		List<Lynx> lynxes = lynxService.getAll();
		String forPrint = "The lynxes in the Zoo are as follows:\n";
		for (Lynx lynx : lynxes) {
			forPrint += "Name: " + lynx.getName() + " Age: " + lynx.getAge() + " Weight: "
					+ lynx.getWeight() + " Happiness: "+ lynx.getHappiness() + ".\n";
		}
		return forPrint;
	}

	public String printDogs(){
		List<Dog> dogs = dogService.getAll();
		String forPrint = "The dogs in the zoo are as follows:\n";
		for(Dog dog : dogs){
			forPrint += "Name: " + dog.getName() + " Age: " + dog.getAge() + " Weight: "
					+ dog.getWeight() +" Gender: "+ dog.getGender() + " Type: "+ dog.getType() + " Happiness: "+ dog.getHappiness() + ".\n";
		}
		return forPrint;
	}
}
