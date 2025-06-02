package ZooManagementSystem;

public class Dog extends CarnivorousAnimal{
    private final double MeatCalcMale=0.05;
    private final double MeatCalcFemale=0.03;
    private DogType Type;
    private static final int DOG_LIFE_SPAN = 15;
    private final String DogNoise="BARK";
    
    public Dog(String name, int age ,double weight, DogType Type ,Gender gender){
    	super(name, age, weight, gender);
        this.Type = Type;
    }

    public int HowMuchDogEat(){
        double meat = (int)(getWeight()*getAge());
        if(getGender() == Gender.Male) {
            meat *= MeatCalcMale;
            return (int)meat;
        }
        else {
            meat *= MeatCalcFemale;
            return (int)meat;
        }
    }
    
    public DogType getType() {
        return Type;
    }

    public void setType(DogType type) {
        Type = type;
    }

    @Override
    public String makeNoise(){
        return DogNoise;
    }
    
	@Override
	public int getLifeSpan() {
		return DOG_LIFE_SPAN;
	}

	@Override
	public double feed() {
		return HowMuchDogEat();
	}
}
