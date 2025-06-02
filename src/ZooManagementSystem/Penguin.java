package ZooManagementSystem;


import java.util.List;

public class Penguin extends Animal{
	private double height;
	private static int LastSortWayused=2; // Defult is by height
	private static int PENGUIN_LIFE_SPAN= 6;
	private final String P_noise;

	//Constructor
	public Penguin(String name, int age, double height) {
		super(name, age);
		this.height = height;
		this.P_noise= "squack";
	}
	
	//Function to Sort the list of the penguies by Hieght
	public static List<Penguin> SortByHeight(List<Penguin> arr) {
		Penguin temp;
		for(int i = 0; i<arr.size()-1 && arr.get(i) != null; i++) {
			for(int j = i+1; j < arr.size() && arr.get(j) != null; j++) {
				if (arr.get(i).height <= arr.get(j).height) {
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

	public static int getLastSortWayused() {
		return LastSortWayused;
	}

	public static void setLastSortWayused(int lastSortWayused) {
		LastSortWayused = lastSortWayused;
	}

	// Deleting a fish after it was eaten by a penguin
	public AquariumFish[] Feed_Penguins(AquariumFish[] fishes){
		AquariumFish[] temp = new AquariumFish[fishes.length-1];
		int i=0;
		while(fishes[i+1] != null){
			temp[i] = fishes[i];
			i++;
		}
		// Removing from the aquarium the last fish that was eaten
		AquariumFish Fish_to_eat = fishes[i];
		if(Fish_to_eat != null)
			System.out.println("Successfully deleted " + Fish_to_eat);
		return temp;
	}
	
	
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public boolean ageOneYear(){
		return super.handleAging();
	}

	@Override
	public int getLifeSpan() {
		return PENGUIN_LIFE_SPAN;
	}
	
	@Override
	public double feed() {
		return 1.0;
	}

	@Override
	public String makeNoise(){
		return P_noise;
	}

}

