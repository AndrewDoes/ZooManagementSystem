package ZooManagementSystem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Zoo {
	private static final int max_happiness = 100;
	private String name;
	private String location;
	private AnimalRepository repo = new AnimalRepository();
	private FishService fishService = new FishService(repo);
	private PenguinService penguinService = new PenguinService(repo);
	private TigerService tigerService = new TigerService(repo);
	private DogService dogService = new DogService(repo);
	private LionService	lionService = new LionService(repo);
	private LynxService lynxService = new LynxService(repo);
	
	public Zoo()   {
		this.name = "Zoo";
		this.location = "Tel Aviv-Yaffo";
	}

	
	public String getName() {
		return name;
	}


	public String getLocation() {
		return location;
	}


	public String printZooDetails() {
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
	
	public  int Feed_lions(){
		List<Lion> lions = lionService.getAll();
		int food_lions=0;
		for (Lion lion : lions) {
			if(lion==null)
				break;
			food_lions += lion.feed();
			lion.setHappiness(max_happiness);
		}
		return food_lions;
	}


	public int Feed_tigers(){
		List<Tiger> tigers = tigerService.getAll();
		int food_tigers=0;
		for(Tiger tiger : tigers){
			if(tiger==null)
				break;
			food_tigers+=tiger.feed();
			tiger.setHappiness(max_happiness);
		}
		return food_tigers;
	}

	public int Feed_lynxes() {
		List<Lynx> lynxes = lynxService.getAll();
		int food_lynxes=0;
		for(Lynx lynx : lynxes) {
			if(lynx == null)
				break;
			food_lynxes += lynx.feed();
			lynx.setHappiness(max_happiness);
		}
		return food_lynxes;
	}

	public int FeedDogs() {
		List<Dog> dogs = dogService.getAll();
		int food_dogs=0;
		for(Dog dog : dogs) {
			if(dog == null)
				break;
			food_dogs += dog.feed();
			dog.setHappiness(max_happiness);
		}
		return food_dogs;
	}
	
	public double Feed_Fishes(){
		List<Fish> fishes = fishService.getAll();
		double food_fishes=0;
		for (Fish fish : fishes) {
			if(fish==null)
				break;
			food_fishes += fish.feed();
			fish.setHappiness(max_happiness);
		}
		return food_fishes;
	}

	public int Feed_penguins(){
		List<Penguin> penguins = penguinService.getAll();
		int count_food_p=0;
		List<Fish> fishes = fishService.getAll();
		// For Each Penguin a Fish was Eaten
		for (Penguin penguin : penguins){
			if(penguin==null){
				break;
			}
			fishes = penguinService.Feed_Penguins(fishes);
			repo.clearAnimals("Fishes");
			FishService fService = new FishService(this.getRepo());
			for (Fish fish : fishes) {
				fService.addNewAnimal(fish);
			}
			penguin.setHappiness(max_happiness); // Just the penguin that ate will return to level 100 of Happiness
		}
		return count_food_p;
	}



	public String Add_New_Penguin(String name_p,double height_p,int age_p) throws HeightException, AgeException {
		if(penguinService.getAll().size()>0){
			try {
			AgeException ageException = new AgeException();
			ageException.AgeValidator(age_p);
			HeightException heightException = new HeightException();
			heightException.HeightIsIllegal(height_p);
			}
			catch (HeightException e0){
				height_p = Main.heightExceptionPenguin();
				
			}
			catch(AgeException e1) {
				age_p = Main.ageExceptionPenguin();
				
			}
		}
		Penguin newPenguin = new Penguin(name_p,age_p,height_p);
		penguinService.addNewAnimal(newPenguin);
		return newPenguin.getName() + " Was added to the flock! :)";
	}

	public void AddNewLion(String name_l,double weight_l , int age_l , int lion_g ){
		Gender gender = null;
		if(lion_g==1)
			gender = Gender.Male;
		else
			gender = Gender.Female;
		Lion newLion = new Lion(name_l,age_l,weight_l, gender);
		lionService.addNewAnimal(newLion);
	}
	public void AddNewTiger(String name_t,double weight_t , int age_t , int tiger_g ){
		Gender gender = null;
		if(tiger_g==1)
			gender = Gender.Male;
		else
			gender = Gender.Female;
		Tiger newTiger = new Tiger(name_t,age_t,weight_t, gender);
		tigerService.addNewAnimal(newTiger);
	}

	public void AddNewLynx(String name_l,double weight_l , int age_l , int lynx_gender ){
		Gender gender = null;
		if(lynx_gender==1)
			gender = Gender.Male;
		else
			gender = Gender.Female;
		Lynx newLynx = new Lynx(name_l,age_l,weight_l, gender);
		lynxService.addNewAnimal(newLynx);
	}

	public void AddNewDog(String name_d , int age_d ,double weight_d , int dogtype , int dog_){
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
		dogService.addNewAnimal(newDog);
	}
	
	public String ListentoAllAnimalsinZoo(){
		String out="";
		out+= "1)Lions Noise: "+ lionService.getAll().get(0).makeNoise() + " 2)Tigers Noise: " + tigerService.getAll().get(0).makeNoise() +
		 " 3)Penguins Noise: " + penguinService.getAll().get(0).makeNoise() + " 4)Fishes Noise: " + fishService.getAll().get(0).makeNoise() +
		  "5)Lynxes Noise: " + lynxService.getAll().get(0).makeNoise() + "6)Dogs Noise: " + dogService.getAll().get(0).makeNoise();
		return out;
	}
	
	public String MostPopularFishColour() {
    List<Fish> fishes = fishService.getAll();
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

	private void assignColourMap(List<Fish> fishes, Map<Colour, Integer> colourCounts) {
		for (Fish fish : fishes) {
		    List<Colour> colours = fish.getColours();
		    for (Colour colour : colours) {
		        colourCounts.put(colour, colourCounts.getOrDefault(colour, 0) + 1);
		    }
		}
	}


	public String ageOneYearAll(){
		List<Animal> animals = repo.getAllAnimal();
		String PrintAllDead="";
		int index = 0;
		PrintAllDead = ageAll(animals, PrintAllDead, index);
		return PrintAllDead;
	}

	private String ageAll(List<Animal> animals, String PrintAllDead, int index) {
		for(Animal animal : animals){
			if(!animal.ageOneYear()){
				PrintAllDead = reportDeathByAge(PrintAllDead, animal);
				animals.remove(index);
			}
			else{
				animal.setHappiness(animal.getHappiness()-(int)(Math.random() * 20 + 1));
				if(animal.getHappiness()<=0){
					PrintAllDead = reportDeathBySadness(PrintAllDead, animal);
					animals.remove(index);
				}
			}
			index++;
		}
		return PrintAllDead;
	}

	private String reportDeathBySadness(String PrintAllDead, Animal animal) {
		if(animal instanceof CarnivorousAnimal){
			PrintAllDead+=((CarnivorousAnimal) animal).getName() +" is Dead because of his Sadness:(\n";
		}else if(animal instanceof Penguin){
			PrintAllDead+=((Penguin) animal).getName() +" is Dead because of his Sadness:(\n";
		}
		else if(animal instanceof Fish){
			PrintAllDead+=((Fish) animal).toString() +" is Dead because of his Sadness:(\n";
		}
		return PrintAllDead;
	}

	private String reportDeathByAge(String PrintAllDead, Animal animal) {
		if(animal instanceof CarnivorousAnimal){
			PrintAllDead+=((CarnivorousAnimal) animal).getName()+" is Dead because of his age\n";
		}else if(animal instanceof Penguin){
			PrintAllDead+=((Penguin) animal).getName()+" is Dead because of his age\n";
		}else if(animal instanceof Fish){
			PrintAllDead+=((Fish) animal).toString()+" is Dead because of his age\n";
		}
		return PrintAllDead;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public AnimalRepository getRepo() {
		return repo;
	}

	public void setRepo(AnimalRepository repo) {
		this.repo = repo;
	}

	public FishService getFishService() {
		return fishService;
	}

	public void setFishService(FishService fishService) {
		this.fishService = fishService;
	}
}
