package ZooManagementSystem;

public class Lion extends CarnivorousAnimal{
	private final String Lion_Noise = "ROAR"; //
    private final double MeatCalcMale = 0.02; //
    private final double MeatCalcFemale = 0.03; //
    private final int MaxMeatinKg = 25; //
    private static final int LION_LIFE_SPAN = 15;

    public Lion() {
        super();
    }
    
    public Lion(String name, int age, double weight, Gender gender) {
        super(name, age, weight, gender);
    }

    public int howMuchMeatDoesLionEat() {
        double meat = (int)getWeight() * getAge();
        if(getGender() == Gender.Male) {
            meat = MeatCalcMale;
            if(meat > MaxMeatinKg)
                return MaxMeatinKg;
            else return (int)meat;
        }
        else {
            meat *= MeatCalcFemale;
            if(meat>MaxMeatinKg)
                return MaxMeatinKg;
            else return (int)meat;
        }
    }

    @Override
    public double feed() {
        return howMuchMeatDoesLionEat();
    }

    @Override
    public String makeNoise(){
        return Lion_Noise;
    }
	
	@Override
	public int getLifeSpan() {
		return LION_LIFE_SPAN;
	}
}