package ZooManagementSystem.Views;

import java.util.Scanner;

import ZooManagementSystem.Printer.ZooPrinter;
import ZooManagementSystem.Services.AquariumFishService;
import ZooManagementSystem.Services.Zoo;
import ZooManagementSystem.Utils.AgeException;
import ZooManagementSystem.Utils.HeightException;

public class App {
    public void startApp() throws HeightException, AgeException {
        Zoo zoo = new Zoo();
        zoo.initializeService();
        zoo.initializeAnimals();

        Scanner input = new Scanner(System.in);

        DogUI dogUI = new DogUI(zoo.getDogService());
        PenguinUI penguinUI = new PenguinUI(zoo.getPenguinService(), zoo.getPrinter());
        CarnivoreUI carnivoreUI = new CarnivoreUI(zoo);
        FishUI fishUI = new FishUI(zoo.getFishService());

        int choice = 0;
        while (choice != 14) {
            ZooPrinter zooPrinter = new ZooPrinter(zoo);
            zooPrinter.printMenu();
            System.out.println("Enter your option:");
            choice = input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    System.out.println(zooPrinter.printZooDetails(zoo.getName(), zoo.getLocation()));
                    break;
                case 2:
                    System.out.println(zooPrinter.printPenguins());
                    break;
                case 3:
                    penguinUI.handleAddPenguin(input);
                    break;
                case 4:
                    zooPrinter.printAllWildCarnivorous();
                    break;
                case 5:
                    carnivoreUI.handleAddCarnivorousAnimal(input);
                    break;
                case 6:
                    AquariumFishService fishService = zoo.getFishService();
                    System.out.println(zooPrinter.printFishes());
                    System.out.println(fishService.MostPopularFishColour());
                    break;
                case 7:
                    fishUI.handleAddNewFishes(input);
                    break;
                case 8:
                    zoo.getPrinter().feedAll(zoo);
                    break;
                case 9:
                    System.out.println(zooPrinter.ListentoAllAnimalsinZoo(zoo));
                    break;
                case 10:
                    System.out.println(zoo.getTimeService().ageOneYearAll(zoo));
                    System.out.println("All Other animals have been Aged one year\n");
                    break;
                case 11:
                    penguinUI.handleSortPenguins(input);
                    break;
                case 12:
                    System.out.println(zooPrinter.printDogs());
                    break;
                case 13:
                    dogUI.handleAddNewDog(input);
                    break;
                case 14:
                    System.out.println("Thank You!\nHave a nice day:)");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + input);
            }
        }
        input.close();
    }
}
