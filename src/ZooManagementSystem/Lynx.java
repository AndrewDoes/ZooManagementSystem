package ZooManagementSystem;

public class Lynx extends CarnivorousAnimal{
	 
     public Lynx(String name, int age, double weight, Gender gender) {
    	 super(name, age, 15, weight, gender, 1.1, 1.3);
     }
     
     public double feed() {
    	 double meat = (getWeight()*0.1 + getAge()*2);
    	 if(getGender() == Gender.Male) {
    		 meat *= getMeatCalcMale();
    		 return (meat);
    	 }
    	 else {
    		 meat *= getMeatCalcFemale();
    		 return (meat);
    	 }
     }

     public String makeNoise() {
    	 return "RAAAHHH";
     }

	 public String toString() { 
		 return "Lynx"; 
	 }
}
