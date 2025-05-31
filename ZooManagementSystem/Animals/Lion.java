package ZooManagementSystem.Animals;

import ZooManagementSystem.Enums.Gender;

public class Lion extends CarnivorousAnimal{
    private final int MaxMeatinKg=25;
    
    public Lion(String name, int age, double weight, Gender gender) {
        super(name,age, 15, weight, gender, 0.02, 0.03);
    }

    public String makeNoise(){
        return "ROAR";
    }

    public double feed(){
        double meat = getWeight()*getAge();
        if(getGender() == Gender.Male) {
            meat*= this.getMeatCalcMale();
            if(meat>MaxMeatinKg)
                return MaxMeatinKg;
            else return meat;
        }
        else {
            meat*=this.getMeatCalcFemale();
            if(meat>MaxMeatinKg)
                return MaxMeatinKg;
            else return meat;
        }
    }

    public String toString(){
        return "Lion.";
    }
}