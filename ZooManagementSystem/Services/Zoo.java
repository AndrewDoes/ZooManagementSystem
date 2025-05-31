package ZooManagementSystem.Services;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ZooManagementSystem.Animals.Animal;
import ZooManagementSystem.Animals.CarnivorousAnimal;
import ZooManagementSystem.Animals.Dog;
import ZooManagementSystem.Animals.Fish;
import ZooManagementSystem.Animals.Lion;
import ZooManagementSystem.Animals.Lynx;
import ZooManagementSystem.Animals.Penguin;
import ZooManagementSystem.Animals.Tiger;
import ZooManagementSystem.Enums.Colour;
import ZooManagementSystem.Enums.DogType;
import ZooManagementSystem.Enums.Gender;
import ZooManagementSystem.Exceptions.AgeException;
import ZooManagementSystem.Exceptions.HeightException;
import ZooManagementSystem.Repositories.AnimalRepository;
import ZooManagementSystem.Views.Main;


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
	
	public String Add_New_Penguin(String name_p,double height_p,int age_p) throws HeightException, AgeException {
		if(penguinService.getAll().size()>0){
			try {
			AgeException ageException = new AgeException();
			ageException.AgeValidator(age_p);
			HeightException heightException = new HeightException(penguinService);
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
