package ZooManagementSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private String promptString(Scanner input, String message) {
        System.out.println(message);
        return input.nextLine();
    }

    private double promptPositiveDouble(Scanner input, String message, String errorMessage) {
        double value;
        while (true) {
            System.out.println(message);
            String line = input.nextLine();
            try {
                value = Double.parseDouble(line);
                if (value > 0) {
                    return value;
                }
                System.out.println(errorMessage);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    private int promptIntInRange(Scanner input, String message, String errorMessage, int min, int max) {
        int value;
        while (true) {
            System.out.println(message);
            String line = input.nextLine();
            try {
                value = Integer.parseInt(line);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println(errorMessage + " (Expected range: " + min + "-" + max + ")");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

    public void addNewDog(Zoo zoo, Scanner input) {
        System.out.println("--- Adding a New Dog ---");
        String name = promptString(input, "Enter the Name of the new Dog:");
        double weight = promptPositiveDouble(input, "Enter the Weight of the new Dog:",
                "The Weight of the Dog Can't be Negative or zero. Please re-enter a new weight:");
        int age = promptIntInRange(input, "Enter the age of the new Dog:",
                "The Age of a dog must be between 1-59.", 1, 59);
        int genderChoice = promptIntInRange(input, "Enter the Gender of the new Dog:\n1) Male\n2) Female (Type 1 or 2)",
                "Invalid choice. Please type 1 or 2.", 1, 2);
        int dogTypeChoice = promptIntInRange(input, "Enter the Type of the new Dog:\n1) Akita\n2) Bulldog\n3) Poodle\n4) Terriers",
                "Invalid choice. Please select a valid dog type.", 1, 4);

        DogService dogService = zoo.getDogService();
        dogService.AddNewDog(name, age, weight, dogTypeChoice, genderChoice);
        System.out.println(name + " the Dog has been added successfully!");
    }

    public void addPenguin(Zoo zoo, Scanner input) {
        System.out.println("--- Adding a New Penguin ---");
        String name = promptString(input, "Enter the Name of the new penguin:");
        double height = promptPositiveDouble(input, "Enter the height of the new penguin (e.g., in cm):",
                "Height must be a positive value.");
        int age = promptIntInRange(input, "Enter the age of the new penguin:",
                "Please enter a valid age for a penguin (e.g., 1-30).", 1, 30); 

        PenguinService penguinService = zoo.getPenguinService();
        try {
            String resultMessage = penguinService.addNewPenguin(name, age, height); 
            System.out.println(resultMessage);
        } catch (Exception e) { 
            System.err.println("Error adding penguin: " + e.getMessage());
        }
    }

    private String selectCarnivorousAnimalType(Scanner input) {
        List<String> animalTypes = Arrays.asList("lion", "tiger", "lynx");
        String promptMessage = "Which carnivorous animal do you want to add?\nAvailable types: " +
                               String.join(", ", animalTypes) + "\nType the requested animal name (lion/tiger/lynx):";
        while (true) {
            String requestedAnimal = promptString(input, promptMessage).toLowerCase();
            if (animalTypes.contains(requestedAnimal)) {
                return requestedAnimal;
            }
            System.out.println("Invalid animal type. Please choose from: " + String.join(", ", animalTypes));
        }
    }

    public void addCarnivorousAnimal(Zoo zoo, Scanner input) {
        System.out.println("--- Adding a New Carnivorous Animal ---");
        String animalType = selectCarnivorousAnimalType(input);

        String name = promptString(input, "Enter the Name of the new " + animalType + ":");
        double weight = promptPositiveDouble(input, "Enter the Weight of the new " + animalType + ":",
                "The Weight of " + animalType + " Can't be Negative or zero. Please re-enter a new weight:");
        int age = promptIntInRange(input, "Enter the age of the new " + animalType + ":",
                "The Age of a carnivorous animal is between 1-59.", 1, 59);
        int genderChoice = promptIntInRange(input, "Enter the Gender of the new " + animalType + ":\n1) Male\n2) Female (Type 1 or 2)",
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

    private Pattern getFishPattern(Scanner input) {
        while (true) {
            String patternStr = promptString(input, "Enter the Pattern of the Fish: (Type one of these: SPOTS, STRIPES, CLEAR, DOTS)").toUpperCase();
            try {
                return Pattern.valueOf(patternStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid pattern. Please choose from SPOTS, STRIPES, CLEAR, DOTS.");
            }
        }
    }

    private List<Colour> getFishColors(Scanner input) {
        List<Colour> allColours = Arrays.asList(Colour.values());
        List<Colour> singularFishColours = new ArrayList<>();

        System.out.println("Available Colors:");
        for (int i = 0; i < allColours.size(); i++) {
            System.out.println((i + 1) + ") " + allColours.get(i));
        }

        int numberOfColors = promptIntInRange(input, "How many Colors is the Fish?",
                "Number of colors must be positive and not exceed available colors.", 1, allColours.size());

        for (int i = 0; i < numberOfColors; ) {
            int colorNumChoice = promptIntInRange(input, "Select color number " + (i + 1) + " for the fish:",
                    "Invalid color selection.", 1, allColours.size());
            Colour selectedColour = allColours.get(colorNumChoice - 1); 

            if (!singularFishColours.contains(selectedColour)) {
                singularFishColours.add(selectedColour);
                i++; 
            } else {
                System.out.println(selectedColour + " was already added! Please choose a different color.");
            }
        }
        return singularFishColours;
    }

    private void handleAddFishWithDetails(AquariumFishService fishService, Scanner input) {
        System.out.println("--- Adding Fish With Details ---");
        int fishTypeChoice = promptIntInRange(input, "What type of Fish would you like to add?\n1) Generic Fish\n2) GoldFish\n3) ClownFish",
                "Invalid choice.", 1, 3);
        int age = promptIntInRange(input, "Enter the Age of the Fish:",
                "Fish age is typically between 1-15 years.", 1, 15);
        double length = promptPositiveDouble(input, "Enter the Length of the Fish (e.g., in cm):",
                "The length of a fish can't be negative or zero.");

        if (fishTypeChoice == 2) { 
            fishService.createNewFishByType(fishTypeChoice, age, length, null, null);
            System.out.println("The GoldFish was successfully added.");
            return;
        }
        if (fishTypeChoice == 3) { 
            fishService.createNewFishByType(fishTypeChoice, age, length, null, null);
            System.out.println("The ClownFish was successfully added.");
            return;
        }

        Pattern pattern = getFishPattern(input);
        List<Colour> colours = getFishColors(input);

        fishService.createNewFishByType(fishTypeChoice, age, length, colours, pattern);
        System.out.println("The Fish was successfully added.");
    }

    private void handleAddRandomFishes(AquariumFishService fishService, Scanner input) {
        System.out.println("--- Adding Random Fishes ---");
        int numFishes = promptIntInRange(input, "How many random fishes do you want to add?",
                "Please enter a positive number.", 1, Integer.MAX_VALUE);
        fishService.addRandomFish(numFishes);
        System.out.println(numFishes + " new Fishes were Added Successfully.");
    }

    public void addNewFishes(Zoo zoo, Scanner input) {
        AquariumFishService fishService = zoo.getFishService();
        System.out.println("--- Fish Management ---");
        int choice = promptIntInRange(input, "Choose an option:\n1) Add Fish With Details\n2) Add a number of random Fishes",
                "Invalid choice. Please type 1 or 2.", 1, 2);

        if (choice == 1) {
            handleAddFishWithDetails(fishService, input);
        } else {
            handleAddRandomFishes(fishService, input);
        }
    }

    public void sortPenguins(Zoo zoo, Scanner input) {
        ZooPrinter zooPrinter = zoo.getPrinter();
        PenguinService penguinService = zoo.getPenguinService();
        List<Penguin> penguins = penguinService.getAll();

        if (penguins == null || penguins.isEmpty()) {
            System.out.println("There are no penguins to sort.");
            return;
        }

        System.out.println("--- Sorting Penguins ---");
        zooPrinter.printSortingOptions();

        int sortChoice = promptIntInRange(input, "Enter your sorting choice:",
                "Invalid choice. Please select a valid option.", 1, 3);

        switch (sortChoice) {
            case 1:
                penguinService.SortByName(penguins);
                System.out.println("Penguins now sorted By Name in Ascending Order.");
                break;
            case 2:
                penguinService.SortByHeight(penguins);
                System.out.println("Penguins now sorted By Height in Descending Order.");
                break;
            case 3:
                penguinService.SortByAge(penguins);
                System.out.println("Penguins now sorted By Age in Ascending Order.");
                break;
            default:
                System.out.println("Invalid sorting option selected.");
                return;
        }

        for (Penguin penguin : penguins) {
            penguin.setLastSortWayused(sortChoice);
        }
        System.out.println("Penguins have been sorted and their last sort way updated.");
    }
}