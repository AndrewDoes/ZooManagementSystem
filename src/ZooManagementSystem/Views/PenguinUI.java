package ZooManagementSystem.Views;

import java.util.List;
import java.util.Scanner;

import ZooManagementSystem.DomainModels.Penguin;
import ZooManagementSystem.Printer.ZooPrinter;
import ZooManagementSystem.Services.PenguinService;
import ZooManagementSystem.Utils.InputUtil;

public class PenguinUI {
    private PenguinService penguinService;
    private ZooPrinter zooPrinter;

    public PenguinUI(PenguinService penguinService, ZooPrinter zooPrinter) {
        this.penguinService = penguinService;
        this.zooPrinter = zooPrinter;
    }

    public void handleAddPenguin(Scanner input) {
        System.out.println("--- Adding a New Penguin ---");
        String name = InputUtil.promptString(input, "Enter the Name of the new penguin:");
        double height = InputUtil.promptPositiveDouble(input, "Enter the height of the new penguin (e.g., in cm):",
                "Height must be a positive value.");
        int age = InputUtil.promptIntInRange(input, "Enter the age of the new penguin:",
                "Please enter a valid age for a penguin (e.g., 1-30).", 1, 30);

        try {
            String resultMessage = penguinService.addNewPenguin(name, age, height, input);
            System.out.println(resultMessage);
        } catch (Exception e) {
            System.err.println("Error adding penguin: " + e.getMessage());
        }
    }

    public void handleSortPenguins(Scanner input) {
        List<Penguin> penguins = penguinService.getAll();

        if (penguins == null || penguins.isEmpty()) {
            System.out.println("There are no penguins to sort.");
            return;
        }

        System.out.println("--- Sorting Penguins ---");
        zooPrinter.printSortingOptions();

        int sortChoice = InputUtil.promptIntInRange(input, "Enter your sorting choice:",
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