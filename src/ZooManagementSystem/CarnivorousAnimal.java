package ZooManagementSystem;

public abstract class CarnivorousAnimal extends Animal {
    private double weight;
    private Gender gender;
    
    public CarnivorousAnimal() {
        super("", 0);
        this.weight = 0;
    }
    
    public CarnivorousAnimal(String name, int age, double weight, Gender gender){
    	super(name, age);
        this.weight = weight;
        this.gender = gender;
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
    
    public boolean ageOneYear(){
        return super.handleAging();
    }
    
    @Override
    public String toString() {
        return String.format("%s, Weight: %.2fkg, Gender: %s",
                super.toString(), weight, gender);
    } 
}
