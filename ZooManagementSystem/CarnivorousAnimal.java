package ZooManagementSystem;

public abstract class CarnivorousAnimal extends Animal{
    private String name;
    private double weight;
    private Gender gender;
    private final double MeatCalcFemale;
    private final double MeatCalcMale;

    public CarnivorousAnimal(String name, int age, int lifeSpan, double weight, Gender gender, double MeatCalcMale, double MeatCalcFemale) {
        super(age, lifeSpan);
        this.name = name;
        this.weight = weight;
        this.gender = gender;
        this.MeatCalcMale = MeatCalcMale;
        this.MeatCalcFemale = MeatCalcFemale;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

        public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public double getMeatCalcFemale() {
        return MeatCalcFemale;
    }

    public double getMeatCalcMale() {
        return MeatCalcMale;
    }

    public double feed(){
        double meat = (this.weight*this.getAge());
        if(getGender() == Gender.Male) {
            meat*=MeatCalcMale;
            return meat;
        }
        else {
            meat*=MeatCalcFemale;
            return meat;
        }
    }
}
