package ZooManagementSystem.Services;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ZooManagementSystem.DomainModels.Animal;
import ZooManagementSystem.DomainModels.AquariumFish;
import ZooManagementSystem.DomainModels.Penguin;
import ZooManagementSystem.Repositories.AnimalRepository;
import ZooManagementSystem.Utils.AgeException;
import ZooManagementSystem.Utils.HeightException;

public class PenguinService extends AnimalServices<Penguin> {
    public PenguinService(AnimalRepository repo) {
        super(repo);
    }

    @Override
    public void addNewAnimal(Penguin animal) {
        if (animal == null) return;
        this.getRepo().addAnimal("Penguin", animal);
    }

    public List<Penguin> getAll() {
        List<Animal> animals = this.getRepo().getAnimals("Penguin");
        if (animals == null) return new ArrayList<>();
        List<Penguin> penguins = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal instanceof Penguin) {
                penguins.add((Penguin) animal);
            }
        }
		autoSortPenguins(penguins);
        return penguins;
    }

    public String addNewPenguin(String name, int age, double height, Scanner input) throws HeightException, AgeException {
        if (!getAll().isEmpty()) {
            try {
                AgeException ageException = new AgeException();
                ageException.AgeValidator(age);
                HeightException heightException = new HeightException(this);
                heightException.HeightIsIllegal(height);
            } catch (HeightException e0) {
                height = heightExceptionPenguin(input);
            } catch (AgeException e1) {
                age = ageExceptionPenguin(input);
            }
        }
        Penguin newPenguin = new Penguin(name, age, height);
        addNewAnimal(newPenguin);
        return newPenguin.getName() + " Was added to the flock! :)";
    }

    public void sortPenguinsOnWill(int choice) {
        List<Penguin> penguins = getAll();
        if (penguins.isEmpty()) return;
        this.getRepo().clearAnimals("Penguin");
        if (choice == 1) {
            SortByName(penguins);
        } else if (choice == 2) {
            SortByHeight(penguins);
        } else if (choice == 3) {
            SortByAge(penguins);
        }
        for (Penguin penguin : penguins) {
            penguin.setLastSortWayused(choice);
            addNewAnimal(penguin);
        }
    }

    public void autoSortPenguins(List<Penguin> penguins) {
        if (penguins.isEmpty()) return;
        int lastSort = penguins.get(0).getLastSortWayused();
        this.getRepo().clearAnimals("Penguin");
        if (lastSort == 1) {
            SortByName(penguins);
        } else if (lastSort == 2) {
            SortByHeight(penguins);
        } else if (lastSort == 3) {
            SortByAge(penguins);
        }
        for (Penguin penguin : penguins) {
            penguin.setLastSortWayused(lastSort);
            addNewAnimal(penguin);
        }
    }

    public List<Penguin> SortByHeight(List<Penguin> penguins) {
        if (penguins.isEmpty()) return penguins;
        penguins.sort((p1, p2) -> Double.compare(p2.getHeight(), p1.getHeight()));
        return penguins;
    }

    public List<Penguin> SortByAge(List<Penguin> penguins) {
        if (penguins.isEmpty()) return penguins;
        penguins.sort((p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));
        return penguins;
    }

    public List<Penguin> SortByName(List<Penguin> penguins) {
        if (penguins.isEmpty()) return penguins;
        penguins.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        return penguins;
    }

    public List<AquariumFish> Feed_Penguins(List<AquariumFish> fishes) {
        int lastFish = fishes.size() - 1;
        if (lastFish < 0) return fishes;
        AquariumFish Fish_to_eat = fishes.get(lastFish);
        if (Fish_to_eat != null) {
            System.out.println("Successfully deleted " + Fish_to_eat.toString());
            AquariumFishService fishService = new AquariumFishService(getRepo());
            fishService.remove(Fish_to_eat);
        }
        return fishes;
    }

    public Penguin getLeader() {
        List<Penguin> penguins = getAll();
        if (penguins.isEmpty()) return null;
        return penguins.get(0);
    }

    public double getLeaderHeight() {
        Penguin leader = getLeader();
        return (leader != null) ? leader.getHeight() : 0;
    }

    public double feedAll() {
        List<Penguin> penguins = getAll();
        if (penguins.isEmpty()) return 0;
        double food = 0;
        for (Penguin penguin : penguins) {
            food += penguin.feed();
        }
        return food;
    }

    public double heightExceptionPenguin(Scanner input) {
        Penguin leadPenguin = getLeader();
        if (leadPenguin == null) return 0;
        double height_p = -1;
        while (height_p < 1 || height_p > 200) {
            System.out.println("Height of the new Penguin is illegal (choose a number between 1-" + leadPenguin.getHeight() + " (inclusive))\nPlease re-Enter the height of the new penguin: ");
            height_p = input.nextDouble();
            input.nextLine();
        }
        return height_p;
    }

    public int ageExceptionPenguin(Scanner input) {
        int age_p = -1;
        while (age_p < 1 || age_p > 20) {
            System.out.println("Age of the penguin is illegal (choose a number 1-20 (inclusive)),\nPlease re-Enter the age of the new penguin: ");
            age_p = input.nextInt();
            input.nextLine();
        }
        return age_p;
    }

    @Override
    public void remove(Penguin animal) {
        this.getRepo().removeAnimal("Penguin", animal);
    }
}