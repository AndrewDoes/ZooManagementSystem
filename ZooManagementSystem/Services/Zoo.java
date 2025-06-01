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
import ZooManagementSystem.Enums.DogType;
import ZooManagementSystem.Enums.Gender;
import ZooManagementSystem.Exceptions.AgeException;
import ZooManagementSystem.Exceptions.HeightException;
import ZooManagementSystem.Repositories.AnimalRepository;


public class Zoo {
	private static final int max_happiness = 100;
	private String name;
	private String location;
	private AnimalRepository repo;
	private FishService fishService;
	private PenguinService penguinService;
	private TigerService tigerService;
	private DogService dogService;
	private LionService	lionService;
	private LynxService lynxService;
    ZooPrinter printer;
	
	public Zoo()   {
		this.name = "Zoo";
		this.location = "Tel Aviv-Yaffo";
		this.repo = new AnimalRepository();
	}

	public void initializeService(){
		if(this.repo == null) this.repo = new AnimalRepository();
		fishService = new FishService(repo);
		penguinService = new PenguinService(repo);
		tigerService = new TigerService(repo);
		dogService = new DogService(repo);
		lionService = new LionService(repo);
		lynxService = new LynxService(repo);
		printer = new ZooPrinter(this);
	}

		public void initializeAnimals(){
		getPenguinService().addNewAnimal(new Penguin("Penny", 10, 200));
		getPenguinService().addNewAnimal(new Penguin("Lenny", 10, 198));
		getPenguinService().addNewAnimal(new Penguin("Tim", 10, 195));
		getLionService().addNewAnimal(new Lion("Bird", 5, 36, Gender.Male));
		getLionService().addNewAnimal(new Lion("Hungry", 20, 80, Gender.Male));
		getLionService().addNewAnimal(new Lion("Skinny", 10, 40, Gender.Female));
		getLionService().addNewAnimal(new Lion("Chunky", 24, 90, Gender.Female));
		getTigerService().addNewAnimal(new Tiger("Mask", 20, 15, Gender.Male));
		getTigerService().addNewAnimal(new Tiger("Husk", 5, 20, Gender.Female));
		getLynxService().addNewAnimal(new Lynx("Timpo", 8, 15, Gender.Male));
		getDogService().addNewAnimal(new Dog("Leo", 8, 10, DogType.Akita, Gender.Male));
		getFishService().addRandomFish(10);
	}
	
	public String getName() {
		return name;
	}


	public String getLocation() {
		return location;
	}

	public void feedAll(){
		System.out.println("The Lions ate: "+lionService.feedAll()+"kg of meat.");
                    System.out.println("The Tigers ate: "+tigerService.feedAll()+"kg of meat.");
					System.out.println("The Lynxes ate: "+lynxService.feedAll()+"kg of meat.");
                    System.out.println("The Fishes ate: "+ String.format("%.2f",fishService.feedAll()) +" meals.\n");
                    System.out.println("The Penguins ate : "+penguinService.feedAll()+" Fishes.\n");
	}
	
	public String Add_New_Penguin(String name_p,int age_p ,double height_p) throws HeightException, AgeException {
		if(penguinService.getSize()>0){
			try {
			AgeException ageException = new AgeException();
			ageException.AgeValidator(age_p);
			HeightException heightException = new HeightException(penguinService);
			heightException.HeightIsIllegal(height_p);
			}
			catch (HeightException e0){
				height_p = penguinService.heightExceptionPenguin();
				
			}
			catch(AgeException e1) {
				age_p = penguinService.ageExceptionPenguin();
				
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
		System.out.println("Animals Added");
	}
	public void AddNewTiger(String name_t,double weight_t , int age_t , int tiger_g ){
		Gender gender = null;
		if(tiger_g==1)
			gender = Gender.Male;
		else
			gender = Gender.Female;
		Tiger newTiger = new Tiger(name_t,age_t,weight_t, gender);
		tigerService.addNewAnimal(newTiger);
		System.out.println("Animals Added");
	}

	public void AddNewLynx(String name_l,double weight_l , int age_l , int lynx_gender ){
		Gender gender = null;
		if(lynx_gender==1)
			gender = Gender.Male;
		else
			gender = Gender.Female;
		Lynx newLynx = new Lynx(name_l,age_l,weight_l, gender);
		lynxService.addNewAnimal(newLynx);
		System.out.println("Animals Added");
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
		System.out.println("Animals Added");
	}
	
	public String ListentoAllAnimalsinZoo(){
		String out="";
		out+= "1)Lions Noise: "+ lionService.getAll().get(0).makeNoise() + "\n2)Tigers Noise: " + tigerService.getAll().get(0).makeNoise() +
		 "\n3)Penguins Noise: " + penguinService.getLeader().makeNoise() + "\n4)Fishes Noise: " + fishService.getAll().get(0).makeNoise() +
		  "\n5)Lynxes Noise: " + lynxService.getAll().get(0).makeNoise() + "\n6)Dogs Noise: " + dogService.getAll().get(0).makeNoise();
		return out;
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


	public PenguinService getPenguinService() {
		return penguinService;
	}


	public void setPenguinService(PenguinService penguinService) {
		this.penguinService = penguinService;
	}


	public TigerService getTigerService() {
		return tigerService;
	}


	public void setTigerService(TigerService tigerService) {
		this.tigerService = tigerService;
	}


	public DogService getDogService() {
		return dogService;
	}


	public void setDogService(DogService dogService) {
		this.dogService = dogService;
	}


	public LionService getLionService() {
		return lionService;
	}


	public void setLionService(LionService lionService) {
		this.lionService = lionService;
	}


	public LynxService getLynxService() {
		return lynxService;
	}


	public void setLynxService(LynxService lynxService) {
		this.lynxService = lynxService;
	}


	public ZooPrinter getPrinter() {
		return printer;
	}


	public void setPrinter(ZooPrinter printer) {
		this.printer = printer;
	}
}
