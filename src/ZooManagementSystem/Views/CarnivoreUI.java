package ZooManagementSystem.Views;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ZooManagementSystem.Services.LionService;
import ZooManagementSystem.Services.LynxService;
import ZooManagementSystem.Services.TigerService;
import ZooManagementSystem.Services.Zoo;
import ZooManagementSystem.Utils.InputUtil;

public class CarnivoreUI {
    private Zoo zoo;

    public CarnivoreUI(Zoo zoo) {
        this.zoo = zoo;
    }

    private String selectCarnivorousAnimalType(Scanner input) {
        List<String> animalTypes = Arrays.asList("lion", "tiger", "lynx");
        String promptMessage = "Which carnivorous animal do you want to add?\nAvailable types: " +
                String.join(", ", animalTypes) + "\nType the requested animal name (lion/tiger/lynx):";
        while (true) {
            String requestedAnimal = InputUtil.promptString(input, promptMessage).toLowerCase();
            if (animalTypes.contains(requestedAnimal)) {
                return requestedAnimal;
            }
            System.out.println("Invalid animal type. Please choose from: " + String.join(", ", animalTypes));
        }
    }

    public void handleAddCarnivorousAnimal(Scanner input) {
        System.out.println("--- Adding a New Carnivorous Animal ---");
        String animalType = selectCarnivorousAnimalType(input);

        String name = InputUtil.promptString(input, "Enter the Name of the new " + animalType + ":");
        double weight = InputUtil.promptPositiveDouble(input, "Enter the Weight of the new " + animalType + ":",
                "The Weight of " + animalType + " Can't be Negative or zero. Please re-enter a new weight:");
        int age = InputUtil.promptIntInRange(input, "Enter the age of the new " + animalType + ":",
                "The Age of a carnivorous animal is between 1-59.", 1, 59);
        int genderChoice = InputUtil.promptIntInRange(input,
                "Enter the Gender of the new " + animalType + ":\n1) Male\n2) Female (Type 1 or 2)",
                "Invalid choice. Please type 1 or 2.", 1, 2);

        switch (animalType) {
            case "lion":
                LionService lionService = zoo.getLionService();
                lionService.addNewLion(name, weight, age, genderChoice);
                System.out.println(name + " the Lion has been added successfully!");
                break;
            case "tiger":
                TigerService tigerService = zoo.getTigerService();
                tigerService.addNewTiger(name, weight, age, genderChoice);
                System.out.println(name + " the Tiger has been added successfully!");
                break;
            case "lynx":
                LynxService lynxService = zoo.getLynxService();
                lynxService.addNewLynx(name, weight, age, genderChoice);
                System.out.println(name + " the Lynx has been added successfully!");
                break;
            default:
                System.out.println("Error: Unknown carnivorous animal type selected.");
                break;
        }
    }
}