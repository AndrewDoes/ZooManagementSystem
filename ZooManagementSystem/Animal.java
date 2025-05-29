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
    private int age;
    private Gender gender;
    private int happiness;
    private int lifeSpan;
    
    public Animal(int age, int happiness, String Noise, int lifeSpan) {
        this.age = age;
        this.happiness = happiness;
        this.lifeSpan = lifeSpan;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public abstract String makeNoise();
    public abstract double feed();
}
