package ZooManagementSystem;

import java.util.ArrayList;

public class ZooService {
	private ArrayList<Penguin> penguins = new ArrayList<Penguin>();
    private ArrayList<Lion> lions = new ArrayList<Lion>();
    private ArrayList<Tiger> tigers = new ArrayList<Tiger>();
    private ArrayList<Lynx> lynxes = new ArrayList<Lynx>();
    private ArrayList<Dog> dogs = new ArrayList<Dog>();

    public ZooService() {
        penguins.add(new Penguin("Penny", 10, 200));
        penguins.add(new Penguin("Lenny", 10, 198));
        penguins.add(new Penguin("Tim", 10, 195));

        lions.add(new Lion("Bird", 5, 36, Gender.Male));
        lions.add(new Lion("Hungry", 20, 80, Gender.Male));
        lions.add(new Lion("Skinny", 10, 40, Gender.Female));
        lions.add(new Lion("Chunky", 24, 90, Gender.Female));

        tigers.add(new Tiger("Mask", 20, 15, Gender.Male));
        tigers.add(new Tiger("Husk", 5, 20, Gender.Female));

        lynxes.add(new Lynx("Timpo", 8, 15, Gender.Male));

        dogs.add(new Dog("Leo", 8, 10, DogType.Akita, Gender.Male));
    }
    

}
