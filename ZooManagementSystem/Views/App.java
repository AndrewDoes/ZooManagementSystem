package ZooManagementSystem.Views;
import java.util.Scanner;

import ZooManagementSystem.Exceptions.AgeException;
import ZooManagementSystem.Exceptions.HeightException;
import ZooManagementSystem.Services.FishService;
import ZooManagementSystem.Services.Zoo;
import ZooManagementSystem.Services.ZooPrinter;
public class App{
    public void startApp()  throws HeightException, AgeException{
        Zoo zoo = new Zoo();
        UserInterface UI = new UserInterface();
        zoo.initializeService();
        zoo.initializeAnimals();
        Scanner input = new Scanner(System.in);
        int choice = 0;
        while(choice!=14) {
            ZooPrinter zooPrinter = new ZooPrinter(zoo);
            zooPrinter.printMenu();
            System.out.println("Enter your option:");
            choice = input.nextInt();
            switch(choice) {
                case 1:
                    System.out.println( zooPrinter.printZooDetails(zoo.getName(), zoo.getLocation()));
                    break;
                case 2:
                    System.out.println(zooPrinter.printPenguins());
                    break;
                case 3:
                    UI.add_penguin(zoo, input);
                    break;
                case 4:
                    zooPrinter.printAllWildCarnivorous();
                    break;
                case 5:
                    UI.AddCarnivorousAnimal(zoo, input);
                    break;
                case 6:
                    FishService fishService = zoo.getFishService();
                    System.out.println(zooPrinter.printFishes());
                    System.out.println(fishService.MostPopularFishColour());
                    break;
                case 7:
                    UI.add_new_fishes(zoo, input);
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
                    UI.SortPenguins(zoo, input);
                    break;
                case 12:
                    System.out.println(zooPrinter.printDogs());
                    break;
                case 13:
                    UI.AddNewDog(zoo, input);
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
