package ZooManagementSystem.Views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ZooManagementSystem.Animals.Lynx;
import ZooManagementSystem.Animals.Penguin;
import ZooManagementSystem.Enums.Colour;
import ZooManagementSystem.Enums.Pattern;
import ZooManagementSystem.Exceptions.AgeException;
import ZooManagementSystem.Exceptions.HeightException;
import ZooManagementSystem.Services.DogService;
import ZooManagementSystem.Services.FishService;
import ZooManagementSystem.Services.LionService;
import ZooManagementSystem.Services.LynxService;
import ZooManagementSystem.Services.PenguinService;
import ZooManagementSystem.Services.TigerService;
import ZooManagementSystem.Services.Zoo;
import ZooManagementSystem.Services.ZooPrinter;

public class UserInterface {
    public void AddNewDog(Zoo zoo, Scanner input) {
        System.out.println("Enter the Name of the new Dog: ");
        String name= input.nextLine();
        System.out.println("Enter the Weight of the new Dog: ");
        double weight= input.nextDouble();
        while (weight<=0){
            System.out.println("The Weight of the Dog Can't be Negative\n" +
                    "Please Re enter a new weight: ");
            weight=input.nextDouble();
        }
        System.out.println("Enter the age of the new Dog: ");
        int age_d= input.nextInt();
        while (age_d<=0 || age_d>=60){
            System.out.println("The Age of a dog is between 1-60\n" +
                    "Please re Enter a new responsible age: ");
            age_d=input.nextInt();
        }
        System.out.println("Enter the Gender of the new Dog:"+"\n1)Male.\n2)Female. (Type 1 or 2)");
        int animal_gender = input.nextInt();
        System.out.println("Enter the Type of the new Dog:"+"\n1)Akita.\n2)Bulldog.\n3)Poodle.\n4)Terriers.");
        int dogtype = input.nextInt();
        DogService dogService = zoo.getDogService();
        dogService.AddNewDog(name,age_d,weight,dogtype,animal_gender);
    }

    public void add_penguin(Zoo zoo, Scanner input) throws HeightException, AgeException{
        System.out.println("Enter the Name of the new penguin: ");
        Scanner input_Penguin = new Scanner(System.in);
        String name_p = input_Penguin.nextLine();
        System.out.println("Enter the height of the new penguin: ");
        double height_p = input_Penguin.nextDouble();
        System.out.println("Enter the age of the new penguin: ");
        int age_p = input_Penguin.nextInt();
        PenguinService penguinService = zoo.getPenguinService();
        System.out.println(penguinService.addNewPenguin(name_p,age_p,height_p));
    }

    public void AddCarnivorousAnimal(Zoo zoo, Scanner input){
        System.out.println("Which carnivorous animal you want to add?\n");
        System.out.println("lion/tiger/lynx(Type the requested aninmal!)");
        int animal_choice = -1;
        Scanner input_carnivorous = new Scanner(System.in);
        String requested_animal= input_carnivorous.nextLine();
        if(requested_animal.equals("lion"))
            animal_choice=1;
        else if (requested_animal.equals("tiger"))
        	animal_choice=2;
        else if(requested_animal.equals("lynx"))
        	animal_choice=3;
        System.out.println("Enter the Name of the new "+requested_animal+": ");
        String name_animal= input_carnivorous.nextLine();
        System.out.println("Enter the Weight of the new "+requested_animal+": ");
        double weight_animal= input_carnivorous.nextDouble();
        while (weight_animal<=0){
            System.out.println("The Weight of "+requested_animal+"Can't be Negative\n" +
                    "Please Re enter a new weight: ");
            weight_animal=input_carnivorous.nextDouble();
        }
        System.out.println("Enter the age of the new "+requested_animal+": ");
        int age_animal= input_carnivorous.nextInt();
        while (age_animal<=0 || age_animal>=60){
            System.out.println("The Age of a carnivorous animal is between 1-60\n" +
                    "Please re Enter a new responsible age: ");
            age_animal=input_carnivorous.nextInt();
        }
        System.out.println("Enter the Gender of the new "+requested_animal+":"+"\n1)Male.\n2)Female. (Type 1 or 2)");
        int animal_gender = input_carnivorous.nextInt();
        if(animal_choice == 1){
            LionService lionService = zoo.getLionService();
            lionService.addNewLion(name_animal,weight_animal,age_animal,animal_gender);
        }
        else if(animal_choice == 2){
            TigerService tigerService = zoo.getTigerService();
            tigerService.addNewTiger(name_animal,weight_animal,age_animal,animal_gender);
        }
        else{
            LynxService lynxService = zoo.getLynxService();
        	lynxService.addNewLynx(name_animal, weight_animal, age_animal, animal_gender);
        }
    }

    public void add_new_fishes(Zoo zoo, Scanner input) {
        FishService fishService = zoo.getFishService();
        System.out.println("Choose an option:\n1)Add Fish With Details\n2)Add a number of random Fishes\n");
        Pattern Pattern_fish = null;
        Scanner input_choice = new Scanner(System.in);
        int choice_of_adding = input_choice.nextInt();
        if(choice_of_adding == 1) {
            System.out.println("What type of Fish would you like to add?\n1.Fish\n2.GoldFish\n3.ClownFish");
            int type_f = input_choice.nextInt();
            System.out.println("Enter the Age of the Fish:");
            int age_f = input_choice.nextInt();
            while(age_f <= 0 || age_f > 15) {
                System.out.println("Fish as they know on average lives between 10-15 years\n" +
                        "Please Re enter a new responsible age: ");
                age_f = input_choice.nextInt();
            }
            System.out.println("Enter the Length of the Fish:");
            double length_f = input_choice.nextDouble();
            while (length_f <= 0) {
                System.out.println("The length of a fish can't be Negative\n" +
                        "Please Re enter a new fish length: ");
                length_f = input_choice.nextDouble();
            }
            if(type_f == 2) {
                fishService.createNewFishByType(type_f, age_f, length_f, null, null);
                System.out.println("The GoldFish was successfully added");
                return;
            }
            if(type_f == 3) {
                fishService.createNewFishByType(type_f, age_f, length_f, null, null);
                System.out.println("The ClownFish was successfully added");
                return;
            }
            System.out.println("Enter the Pattern of the Fish: (Type one of these Patterns: SPOTS,STRIPES,CLEAR,DOTS )");
            input_choice.skip("[\r\n]+"); // To skip the input of the next line char
            String pattern = input_choice.nextLine();
            if (pattern.equals("DOTS")) {
                Pattern_fish = Pattern.DOTS;
            }
            else if (pattern.equals("SPOTS")) {
                Pattern_fish = Pattern.SPOTS;
            } else if (pattern.equals("STRIPES")) {
                Pattern_fish = Pattern.STRIPES;
            } else if (pattern.equals("CLEAR")) {
                Pattern_fish = Pattern.CLEAR;
            }
            List<Colour> allColours = Arrays.asList(Colour.values());
            System.out.println("How many Colors is the Fish?\n");
            int number_of_colors = input_choice.nextInt();
            List<Colour> singularFishColours = new ArrayList<>(); // Initialize as empty list
            
            for(int i = 0; i < number_of_colors; i++) {
                System.out.println("Choose the Colors :\n1)BLACK, 2)WHITE, 3)GREEN, 4)ORANGE, 5)BLUE, 6)YELLOW, 7)BROWN, 8)GOLD, 9)RED, 10)CYAN");
                int color_num = input_choice.nextInt();
                
                // Adjust index to be 0-based (subtract 1 from user input)
                if (color_num >= 1 && color_num <= 10) {
                    color_num--; // Convert from 1-based to 0-based indexing
                    
                    // Check if color already exists in the list
                    boolean colorExists = false;
                    for (Colour existingColor : singularFishColours) {
                        if (existingColor.equals(allColours.get(color_num))) {
                            System.out.println("This colour was already added!");
                            colorExists = true;
                            i--; // Decrement i to retry this iteration
                            break;
                        }
                    }
                    
                    if (!colorExists) {
                        singularFishColours.add(allColours.get(color_num));
                    }
                } else {
                    System.out.println("Invalid color selection. Please choose a number between 1 and 10.");
                    i--; // Decrement i to retry this iteration
                }
            }
            
            fishService.createNewFishByType(type_f, age_f, length_f, singularFishColours, Pattern_fish);
            System.out.println("The Fish was successfully added");
        } else if (choice_of_adding == 2) {
            System.out.println("How much Fishes you want to add?\n");
            int num_fishes = input_choice.nextInt();
            fishService.addRandomFish(num_fishes);
            System.out.println(num_fishes + " new Fishes were Added Successfully\n");
        }
    }

    public void SortPenguins(Zoo zoo, Scanner input){
        ZooPrinter zooPrinter = zoo.getPrinter();
        zooPrinter.printSortingOptions();
        Scanner in = new Scanner(System.in);
        PenguinService penguinService = new PenguinService(zoo.getRepo());
        List<Penguin> penguins = penguinService.getAll();
        int waytosort= in.nextInt();
        in.nextLine();
        if(waytosort==1){
            penguinService.SortByName(penguins);
            System.out.println("Penguins now sorted By Name in Ascending Order\n");
        } else if (waytosort==2) {
            penguinService.SortByHeight(penguins);
            System.out.println("Penguins now sorted By Height in Descending Order\n\n");
        } else if (waytosort==3) {
            penguinService.SortByAge(penguins);
            System.out.println("Penguins now sorted By Age in Ascending Order\n");
        }
        penguins = penguinService.getAll();
        for(Penguin penguin : penguins){
            penguin.setLastSortWayused(waytosort);
        };
    }
}
