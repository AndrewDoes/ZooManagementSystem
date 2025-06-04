package ZooManagementSystem;

public class Dog extends CarnivorousAnimal{
    private DogType Type;
    public Dog(String name, int age, double weight, DogType Type ,Gender gender){
        super(name, age, 14, weight, gender, 0.05, 0.03);
        this.Type=Type;
    }
    public DogType getType() {
        return Type;
    }

    public void setType(DogType type) {
        Type = type;
    }

    public String makeNoise(){
        return "BARK";
    }

    public String toString(){
        return "Dog";
    }
}
