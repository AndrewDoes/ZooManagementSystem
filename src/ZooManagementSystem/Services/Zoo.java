package ZooManagementSystem.Services;

import ZooManagementSystem.DomainModels.Dog;
import ZooManagementSystem.DomainModels.DogType;
import ZooManagementSystem.DomainModels.Gender;
import ZooManagementSystem.DomainModels.Lion;
import ZooManagementSystem.DomainModels.Lynx;
import ZooManagementSystem.DomainModels.Penguin;
import ZooManagementSystem.DomainModels.Tiger;
import ZooManagementSystem.Printer.ZooPrinter;
import ZooManagementSystem.Repositories.AnimalRepository;

public class Zoo {
	private String name;
	private String location;
	public AnimalRepository repo;
	public AquariumFishService fishService;
	public PenguinService penguinService;
	public TigerService tigerService;
	public DogService dogService;
	public LionService lionService;
	public LynxService lynxService;
	public ZooPrinter printer;
	public ZooTimeService timeService;

	public Zoo() {
		this.name = "Zoo";
		this.location = "Tel Aviv-Yaffo";
		this.repo = new AnimalRepository();
	}

	public void initializeService() {
		if (this.repo == null)
			this.repo = new AnimalRepository();
		fishService = new AquariumFishService(repo);
		penguinService = new PenguinService(repo);
		tigerService = new TigerService(repo);
		dogService = new DogService(repo);
		lionService = new LionService(repo);
		lynxService = new LynxService(repo);
		printer = new ZooPrinter(this);
		timeService = new ZooTimeService();
	}

	public void initializeAnimals() {
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

	public AquariumFishService getFishService() {
		return fishService;
	}

	public void setFishService(AquariumFishService fishService) {
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

	public ZooTimeService getTimeService() {
		return timeService;
	}

	public void setTimeService(ZooTimeService timeService) {
		this.timeService = timeService;
	}

}
