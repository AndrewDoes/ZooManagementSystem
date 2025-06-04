package ZooManagementSystem;

// - age
// - happiness
// - Lifespan
// - Noise
// - getter setter dari atribut di atas
// - feed()
// - toString()
// - ageOneYear()
// - makeNoise()

public abstract class Animal {
    private String name;
    private int age;
    private int happiness = 100;
    private int lifeSpan;
    
    public Animal(String name, int age, int lifeSpan) {
        this.name = name;
        this.age = age;
        this.lifeSpan = lifeSpan;
    }

    public Animal(int age, int lifeSpan){
        this(null, age, lifeSpan);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(int lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public boolean ageOneYear(){
        this.age+=1;
        return this.age <= lifeSpan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String makeNoise();
    public abstract double feed();
    public abstract String toString();

}
