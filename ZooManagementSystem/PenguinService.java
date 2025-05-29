package ZooManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class PenguinService extends Services<Penguin> {
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
		if(getAll().size()>0){
			try {
			AgeException ageException = new AgeException();
			ageException.AgeValidator(age);
			HeightException heightException = new HeightException();
			heightException.HeightIsIllegal(height);
			}
			catch (HeightException e0){
				height = Main.heightExceptionPenguin();
				
			}
			catch(AgeException e1) {
				age = Main.ageExceptionPenguin();
				
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
	public static List<Penguin> SortByHeight(List<Penguin> arr) {
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

	public static List<Penguin> SortByAge(List<Penguin> arr) {
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
	public static List<Penguin> SortByName(List<Penguin> arr) {
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
	public void Feed_Penguins(List<Fish> fishes){
		int lastFish= fishes.size()-1;
		// Removing from the aquarium the last fish that was eaten
		Fish Fish_to_eat = fishes.get(lastFish);
		if(Fish_to_eat!=null)
			System.out.println("Successfully deleted " + Fish_to_eat);
		fishes.remove(lastFish);

        //ini code smell feature envy, ganti nanti
        this.getRepo().clearAnimals("Fishes");
        FishService fService = new FishService(this.getRepo());
        for (Fish fish : fishes) {
            fService.addNewAnimal(fish);
        }
	}
}