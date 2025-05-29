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
    protected int age;
    protected int happiness = 100;
    protected int lifeSpan;
    
    public Animal(int age, int lifeSpan) {
        this.age = age;
        this.lifeSpan = lifeSpan;
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

    public abstract String makeNoise();
    public abstract double feed();
    public abstract String toString();
}
