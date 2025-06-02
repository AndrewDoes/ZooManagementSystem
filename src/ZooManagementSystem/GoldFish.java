package ZooManagementSystem;

public class GoldFish extends AquariumFish{
	private static Pattern pattern = Pattern.CLEAR;
	private static Colour colour;
	private static final int GOLDFISH_LIFESPAN = 12;
	
	//Constructor,  a random colour will be chosen.
	public GoldFish(int age, double length) {
		super(age, length);
		
		Colour[] allColours = {Colour.BLACK, Colour.GOLD, Colour.ORANGE, Colour.YELLOW};
		int num = randomizeGoldFishColour();
		GoldFish.colour = allColours[num];
		
		Zoo.numOfFishColours[num]++;
	}
	
	private int randomizeGoldFishColour() {
		return (int)(Math.random() * 3) + 1;
	}
	
	public double feed() {
		return 1.0;
	}


	public static Colour getColour() {
		return colour;
	}

	public static void setColour(Colour colour) {
		GoldFish.colour = colour;
	}
	
	public String getColours() {
		String x = "" + colour;
		
		return x;
	}
	
	public String toString() {
		return "GoldFish.";
	}

	@Override
	public boolean ageOneYear(){
		return super.handleAging();
	}


	@Override
	public int getLifeSpan() {
		return GOLDFISH_LIFESPAN;
	}

	@Override
	public Pattern getPattern() {
		return pattern;
	}

}
