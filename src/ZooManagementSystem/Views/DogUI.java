package ZooManagementSystem.Views;

import java.util.Scanner;

import ZooManagementSystem.Services.DogService;
import ZooManagementSystem.Utils.InputUtil;

public class DogUI {
        private DogService dogService;

        public DogUI(DogService dogService) {
                this.dogService = dogService;
        }

        public void handleAddNewDog(Scanner input) {
                System.out.println("--- Adding a New Dog ---");
                String name = InputUtil.promptString(input, "Enter the Name of the new Dog:");
                double weight = InputUtil.promptPositiveDouble(input, "Enter the Weight of the new Dog:",
                                "The Weight of the Dog Can't be Negative or zero. Please re-enter a new weight:");
                int age = InputUtil.promptIntInRange(input, "Enter the age of the new Dog:",
                                "The Age of a dog must be between 1-59.", 1, 59);
                int genderChoice = InputUtil.promptIntInRange(input,
                                "Enter the Gender of the new Dog:\n1) Male\n2) Female (Type 1 or 2)",
                                "Invalid choice. Please type 1 or 2.", 1, 2);
                int dogTypeChoice = InputUtil.promptIntInRange(input,
                                "Enter the Type of the new Dog:\n1) Akita\n2) Bulldog\n3) Poodle\n4) Terriers",
                                "Invalid choice. Please select a valid dog type.", 1, 4);

                dogService.AddNewDog(name, age, weight, dogTypeChoice, genderChoice);
                System.out.println(name + " the Dog has been added successfully!");
        }
}