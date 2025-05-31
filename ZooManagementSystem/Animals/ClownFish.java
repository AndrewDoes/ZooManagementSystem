package ZooManagementSystem.Animals;

import java.util.List;

import ZooManagementSystem.Zoo;
import ZooManagementSystem.Enums.Colour;
import ZooManagementSystem.Enums.Pattern;

public class ClownFish extends Fish {
	public ClownFish(int age, double length) {
		super(age, 8, length, List.of(Colour.ORANGE, Colour.BLACK, Colour.WHITE), Pattern.STRIPES);
		Zoo.numOfFishColours[0]++;
		Zoo.numOfFishColours[2]++;
		Zoo.numOfFishColours[4]++;
	}
	
	public double feed() {
		return 2.0;
	}
	
	public String toString() {
		return "ClownFish.";
	}

	@Override
	public Pattern getPattern() {
		return pattern;
	}
}