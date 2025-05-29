package ZooManagementSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static ZooManagementSystem.Gender.*;


public class Zoo {
	private static final int max_happiness = 100;
	private static String name;
	private static String location;
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

	private static String getName() {
		return name;
	}

	private static String getLocation() {
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
		out+= "1)Lions Noise: "+ lions[0].makeNoise() + " 2)Tigers Noise: " + tigers[0].makeNoise() + " 3)Penguins Noise: " + penguins.get(0).makeNoise() + " 4)Fishes Noise: " + fishes[0].makeNoise() + "5)Lynxes Noise: " + lynxes[0].makeNoise() + "6)Dogs Noise: " + dogs[0].makeNoise();
		return out;
	}
	
	public static String MostPopularFishColour() {
		int index = -1;
		int secondIndex = -1;
		int max = 0;
		int secondMax = 0;
		for(int i = 0; i < numOfFishColours.length; i++) {
			if(numOfFishColours[i] >= max) {
				secondMax = max;
				max = numOfFishColours[i];
				secondIndex = index;
				index = i;
			}
		}
		
		return "The most common colour in the aquarium are:\n"+Colour.values()[index]+" & " + Colour.values()[secondIndex];
		
	}

	public String ageOneYearAll(){
		String PrintAllDead="";
		for(int i=0;i<numberOfLynxes;i++){
			if(!lynxes[i].ageOneYear()){
				PrintAllDead+=lynxes[i].getName()+" is Dead because of his age\n";
				lynxes[i]=null;
				lynxes[i]=lynxes[numberOfLynxes-1];
				numberOfLynxes--;
				i--;
			} else{
				lynxes[i].setHappiness(lynxes[i].getHappiness()-(int)(Math.random() * 20 + 1));
				if(lynxes[i].getHappiness()<=0){
					PrintAllDead+=lynxes[i].getName() +" is Dead because of his Sadness:(\n";
					lynxes[i]=lynxes[numberOfLynxes-1];
					lynxes[numberOfLynxes-1]=null;
					numberOfLynxes--;
					i--;
				}
			}
		}
		for(int i=0;i<numberOfDogs;i++){
			if(!dogs[i].ageOneYear()){
				PrintAllDead+=dogs[i].getName()+" is Dead because of his age\n";
				dogs[i]=null;
				dogs[i]=dogs[numberOfDogs-1];
				numberOfDogs--;
				i--;
			} else{
				dogs[i].setHappiness(dogs[i].getHappiness()-(int)(Math.random() * 20 + 1));
				if(dogs[i].getHappiness()<=0){
					PrintAllDead+=dogs[i].getName() +" is Dead because of his Sadness:(\n";
					dogs[i]=dogs[numberOfDogs-1];
					dogs[numberOfDogs-1]=null;
					numberOfDogs--;
					i--;
				}
			}
		}
		for(int i=0;i<numberOfTigers;i++){
			if(!tigers[i].ageOneYear()){
				PrintAllDead+=tigers[i].getName()+" is Dead because of his age\n";
				tigers[i]=null;
				tigers[i]=tigers[numberOfTigers-1];
				numberOfTigers--;
				i--;
			} else{
				tigers[i].setHappiness(tigers[i].getHappiness()-(int)(Math.random() * 20 + 1));
				if(tigers[i].getHappiness()<=0){
					PrintAllDead+=tigers[i].getName() +" is Dead because of his Sadness:(\n";
					tigers[i]=tigers[numberOfTigers-1];
					tigers[numberOfTigers-1]=null;
					numberOfTigers--;
					i--;
				}
			}
		}
		for(int i=0;i<numberOfLions;i++){
			if(!lions[i].ageOneYear()){
				PrintAllDead+=lions[i].getName()+" is Dead because of his age\n";
				lions[i]=null;
				lions[i]=lions[numberOfLions-1];
				numberOfLions--;
				i--;
			} else{
				lions[i].setHappiness(lions[i].getHappiness()-(int)(Math.random() * 20 + 1));
				if(lions[i].getHappiness()<=0){
					PrintAllDead+=lions[i].getName() +" is Dead because of his Sadness:(\n";
					lions[i]=lions[numberOfLions-1];
					lions[numberOfLions-1]=null;
					numberOfLions--;
					i--;
				}
			}
		}
		for (int i=0;i<numberOfFishes;i++){
			if(!fishes[i].ageOneYear()){
				PrintAllDead+=fishes[i].toString()+" is Dead because of his age\n";
				fishes[i]=fishes[numberOfFishes-1];
				fishes[numberOfFishes-1]=null;
				numberOfFishes--;
				i--;
			} else {
				fishes[i].setHappiness(fishes[i].getHappiness()-(int)(Math.random() * 20 + 1));
				if(fishes[i].getHappiness()<=0){
					PrintAllDead+= fishes[i].toString() +" is Dead because of his Sadness:(\n";
					fishes[i]=fishes[numberOfFishes-1];
					fishes[numberOfFishes-1]=null;
					numberOfFishes--;
					i--;
				}
			}
		}
		for(int i=0;i<numberOfPenguins;i++){
			if(!penguins.get(i).ageOneYear()){
				PrintAllDead+=penguins.get(i).getName()+" is Dead because of his age\n";
				penguins.remove(i);
				numberOfPenguins--;
				i--;
			} else {
				penguins.get(i).setHappiness(penguins.get(i).getHappiness()-(int)(Math.random()*20+1));
				if(penguins.get(i).getHappiness()<=0){
					PrintAllDead+=penguins.get(i).getName()+" is Dead because of his Sadness\n";
					penguins.remove(i);
					numberOfPenguins--;
					i--;
				}
			}
		}
		return PrintAllDead;
	}

	public static void setName(String name) {
		Zoo.name = name;
	}

	public static void setLocation(String location) {
		Zoo.location = location;
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
