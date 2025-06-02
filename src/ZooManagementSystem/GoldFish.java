package ZooManagementSystem;

import java.util.List;

public class GoldFish extends AquariumFish{	
	//Constructor,  a random colour will be chosen.
	public GoldFish(int age, double length, Colour colour) {
		super(age, 12, length , List.of(Colour.BLACK, Colour.GOLD, Colour.ORANGE, Colour.YELLOW), Pattern.CLEAR);
	}
	
	public double feed() {
		return 1.0;
	}

	public Pattern getPattern() {
		return pattern;
	}
	
	public String toString() {
		return "GoldFish";
	}
}
