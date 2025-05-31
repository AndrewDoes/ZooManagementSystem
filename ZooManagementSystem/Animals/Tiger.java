package ZooManagementSystem.Animals;

import ZooManagementSystem.Enums.Gender;

public class Tiger extends CarnivorousAnimal{
        public Tiger(String name,int age, double weight,Gender gender){
            super(name,age, 15, weight,gender, 0.02, 0.03);
        }


        public String makeNoise(){
            return "roar";
        }

        public String toString(){
            return "Tiger.";
        }
}
