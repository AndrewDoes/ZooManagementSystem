package ZooManagementSystem.Views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ZooManagementSystem.Enums.Colour;
import ZooManagementSystem.Enums.Pattern;
import ZooManagementSystem.Services.AquariumFishService;
import ZooManagementSystem.Utils.InputUtil;

public class FishUI {
    private AquariumFishService fishService;

    public FishUI(AquariumFishService fishService) {
        this.fishService = fishService;
    }

    private Pattern getFishPattern(Scanner input) {
        while (true) {
            String patternStr = InputUtil.promptString(input,
                    "Enter the Pattern of the Fish: (Type one of these: SPOTS, STRIPES, CLEAR, DOTS)").toUpperCase();
            try {
                return Pattern.valueOf(patternStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid pattern. Please choose from SPOTS, STRIPES, CLEAR, DOTS.");
            }
        }
    }

    private List<Colour> getFishColors(Scanner input) {
        List<Colour> allColours = Arrays.asList(Colour.values()); // Pastikan enum Colour ada
        List<Colour> singularFishColours = new ArrayList<>();

        System.out.println("Available Colors:");
        for (int i = 0; i < allColours.size(); i++) {
            System.out.println((i + 1) + ") " + allColours.get(i));
        }

        int numberOfColors = InputUtil.promptIntInRange(input, "How many Colors is the Fish?",
                "Number of colors must be positive and not exceed available colors.", 1, allColours.size());

        for (int i = 0; i < numberOfColors;) {
            int colorNumChoice = InputUtil.promptIntInRange(input, "Select color number " + (i + 1) + " for the fish:",
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

    private void handleAddFishWithDetails(Scanner input) {
        System.out.println("--- Adding Fish With Details ---");
        int fishTypeChoice = InputUtil.promptIntInRange(input,
                "What type of Fish would you like to add?\n1) Generic Fish\n2) GoldFish\n3) ClownFish",
                "Invalid choice.", 1, 3);
        int age = InputUtil.promptIntInRange(input, "Enter the Age of the Fish:",
                "Fish age is typically between 1-15 years.", 1, 15);
        double length = InputUtil.promptPositiveDouble(input, "Enter the Length of the Fish (e.g., in cm):",
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

    private void handleAddRandomFishes(Scanner input) {
        System.out.println("--- Adding Random Fishes ---");
        int numFishes = InputUtil.promptIntInRange(input, "How many random fishes do you want to add?",
                "Please enter a positive number.", 1, Integer.MAX_VALUE);
        fishService.addRandomFish(numFishes);
        System.out.println(numFishes + " new Fishes were Added Successfully.");
    }

    public void handleAddNewFishes(Scanner input) {
        System.out.println("--- Fish Management ---");
        int choice = InputUtil.promptIntInRange(input,
                "Choose an option:\n1) Add Fish With Details\n2) Add a number of random Fishes",
                "Invalid choice. Please type 1 or 2.", 1, 2);

        if (choice == 1) {
            handleAddFishWithDetails(input);
        } else { // choice == 2
            handleAddRandomFishes(input);
        }
    }
}