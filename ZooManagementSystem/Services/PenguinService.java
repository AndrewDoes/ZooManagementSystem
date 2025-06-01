package ZooManagementSystem.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ZooManagementSystem.Animals.Animal;
import ZooManagementSystem.Animals.Fish;
import ZooManagementSystem.Animals.Penguin;
import ZooManagementSystem.Exceptions.AgeException;
import ZooManagementSystem.Exceptions.HeightException;
import ZooManagementSystem.Repositories.AnimalRepository;
import ZooManagementSystem.Views.App;

public class PenguinService extends Services<Penguin> {
	private int lastSortWayused;

    public PenguinService(AnimalRepository repo) {
        super(repo);
    }

    @Override
    public void addNewAnimal(Penguin animal) {
        if (animal == null) return;
        this.getRepo().addAnimal("Penguin", animal);
    }

    public List<Penguin> getAll() {
        List<Animal> animals = this.getRepo().getAnimals("Penguin");
		if(animals.size() == 0) return null;
        List<Penguin> penguins = new ArrayList<Penguin>();
        for (Animal animal : animals) {
            if (animal instanceof Penguin) {
                penguins.add((Penguin) animal);
            }
        }
        return penguins;
    }
    // public static Boolean illegalPenguin(double height_p) {
	// 	return (height_p >= penguins.get(0).getHeight());

	// }

    public String createNewPenguin(String name,double height,int age) throws HeightException, AgeException {
		if(getAll().size() != 0){
			try {
			AgeException ageException = new AgeException();
			ageException.AgeValidator(age);
			HeightException heightException = new HeightException(this);
			heightException.HeightIsIllegal(height);
			}
			catch (HeightException e0){
				height = heightExceptionPenguin();
				
			}
			catch(AgeException e1) {
				age = ageExceptionPenguin();
				
			}
		}
		Penguin newPenguin = new Penguin(name,age,height);
		addNewAnimal(newPenguin);
		return newPenguin.getName() + " Was added to the flock! :)";
	}

    public void sortPenguinsOnWill(int choice){
        List<Penguin> penguins = getAll();
        this.getRepo().clearAnimals("Penguins");
        if (choice == 1) {
            penguins = SortByName(penguins);
        } else if (choice == 2) {
            penguins = SortByHeight(penguins);
        } else if (choice == 3) {
            penguins = SortByAge(penguins);
        }
        for (Penguin penguin : penguins) {
            addNewAnimal(penguin);
        }
    }

    public void autoSortPenguins(){
        List<Penguin> penguins = getAll();
        int lastSort = penguins.get(0).getLastSortWayused();
        this.getRepo().clearAnimals("Penguins");
        if (lastSort == 1) {
            penguins = SortByName(penguins);
        } else if (lastSort == 2) {
            penguins = SortByHeight(penguins);
        } else if (lastSort == 3) {
            penguins = SortByAge(penguins);
        }
        for (Penguin penguin : penguins) {
            addNewAnimal(penguin);
        }
    }

    //Function to Sort the list of the penguies by Hieght
	public List<Penguin> SortByHeight(List<Penguin> arr) {
		Penguin temp;
		for(int i = 0; i<arr.size()-1 && arr.get(i) != null; i++) {
			for(int j = i+1; j < arr.size() && arr.get(j) != null; j++) {
				if (arr.get(i).getHeight() <= arr.get(j).getHeight()) {
					temp= arr.get(i);
					arr.set(i,arr.get(j));
					arr.set(j,temp);
				}
			}
		}
		return arr;
	}

	public List<Penguin> SortByAge(List<Penguin> arr) {
		Penguin temp;
		for(int i = 0; i<arr.size()-1 && arr.get(i) != null; i++) {
			for(int j = i+1; j < arr.size() && arr.get(j) != null; j++) {
				if (arr.get(i).getAge() > arr.get(j).getAge()) {
					temp= arr.get(i);
					arr.set(i,arr.get(j));
					arr.set(j,temp);
				}
			}
		}
		return arr;
	}
	public List<Penguin> SortByName(List<Penguin> arr) {
		Penguin temp;
		for(int i = 0; i<arr.size()-1 && arr.get(i) != null; i++) {
			for(int j = i+1; j < arr.size() && arr.get(j) != null; j++) {
				if (arr.get(j).getName().compareTo(arr.get(i).getName()) > 0 ) {
					temp= arr.get(i);
					arr.set(i,arr.get(j));
					arr.set(j,temp);
				}
			}
		}
		return arr;
	}

    	// Deleting a fish after it was eaten by a penguin
	public List<Fish> Feed_Penguins(List<Fish> fishes){
		int lastFish= fishes.size()-1;
		// Removing from the aquarium the last fish that was eaten
		Fish Fish_to_eat = fishes.get(lastFish);
		if(Fish_to_eat!=null)
			System.out.println("Successfully deleted " + Fish_to_eat);
		fishes.remove(lastFish);

        return fishes;
        //ini code smell feature envy, ganti nanti
	}

	public Penguin getLeader(){
		return getAll().get(0);
	}

	public double getLeaderHeight(){
		return getLeader().getHeight();
	}

	public double feedAll(){
		List<Penguin> penguins = getAll();
		double food = 0;
		for(Penguin penguin : penguins){
			food += penguin.feed();
		}
		return food;
	}

	    public double heightExceptionPenguin() {
        Penguin leadPenguin = getLeader();
		if(leadPenguin == null){
			
		}
    	double height_p = -1;
    	Scanner input_Penguin = new Scanner(System.in);
    	while(height_p < 1 || height_p > 200) {
    	System.out.println("Height of the new Penguin is illegal (choose a number between 1-"+ leadPenguin.getHeight()+" (inclusive))\nPlease re-Enter the height of the new penguin: ");
    	height_p = input_Penguin.nextDouble();
    	}
        input_Penguin.close();
    	return height_p;
    }
    
    public int ageExceptionPenguin() {
    	int age_p = -1;
    	Scanner input_Penguin = new Scanner(System.in);
    	while(age_p < 1 || age_p > 20) {
    	System.out.println("Age of the penguin is illegal (choose a number 1-20 (inclusive)),\nPlease re-Enter the age of the new penguin: ");
        age_p = input_Penguin.nextInt();
    	}
        return age_p;
    }
}