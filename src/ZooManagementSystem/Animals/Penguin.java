package ZooManagementSystem.Animals;

public class Penguin extends Animal{
	private double height;
	private String name;
	private int LastSortWayused;
	//Constructor
	public Penguin(String name, int age, double height) {
		super(age, 6);
		this.name = name;
		this.height = height;
	}

	public int getLastSortWayused() {
		return LastSortWayused;
	}

	public void setLastSortWayused(int lastSortWayused) {
		LastSortWayused = lastSortWayused;
	}
	
	public double feed() {
		return 1.0;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() { 
		return "Penguin"; 
	 }

	 public String makeNoise() {
		 return "squack";
	 }

	@Override
	public String getDeathReport(String cause) {
		return getName() + " is Dead because of " + cause;
	}
}

