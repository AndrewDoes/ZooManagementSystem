package ZooManagementSystem;

import java.util.List;

import ZooManagementSystem.Animals.Dog;
import ZooManagementSystem.Animals.Fish;
import ZooManagementSystem.Animals.Lion;
import ZooManagementSystem.Animals.Lynx;
import ZooManagementSystem.Animals.Penguin;
import ZooManagementSystem.Animals.Tiger;
import ZooManagementSystem.Repositories.AnimalRepository;
import ZooManagementSystem.Services.DogService;
import ZooManagementSystem.Services.FishService;
import ZooManagementSystem.Services.LionService;
import ZooManagementSystem.Services.LynxService;
import ZooManagementSystem.Services.PenguinService;
import ZooManagementSystem.Services.AnimalServices;
import ZooManagementSystem.Services.TigerService;

public abstract class Feeder<T extends AnimalServices<?>> {
    public abstract double feed(T animalService, int max_happiness);
}

class LionFeeder extends Feeder<LionService>{
    public double feed(LionService lionService, int max_happiness){
            List<Lion> lions = lionService.getAll();
            double food_lions=0;
            for (Lion lion : lions) {
                if(lion==null)
                    break;
                food_lions += lion.feed();
                lion.setHappiness(max_happiness);
            }
        return food_lions;
    }
}

class TigerFeeder extends Feeder<TigerService>{
    public double feed(TigerService tigerService, int max_happiness){
        List<Tiger> tigers = tigerService.getAll();
        double food_tigers=0;
        for(Tiger tiger : tigers){
            if(tiger==null)
                break;
            food_tigers+=tiger.feed();
            tiger.setHappiness(max_happiness);
        }
        return food_tigers;
    }
}

class LynxFeeder extends Feeder<LynxService>{
    public double feed(LynxService lynxService, int max_happiness){
        List<Lynx> lynxes = lynxService.getAll();
        double food_lynxes=0;
        for(Lynx lynx : lynxes) {
            if(lynx == null)
                break;
            food_lynxes += lynx.feed();
            lynx.setHappiness(max_happiness);
        }
        return food_lynxes;
    }
}

class DogFeeder extends Feeder<DogService>{
    public double feed(DogService dogService, int max_happiness){
        List<Dog> dogs = dogService.getAll();
        double food_dogs=0;
        for(Dog dog : dogs) {
            if(dog == null)
                break;
            food_dogs += dog.feed();
            dog.setHappiness(max_happiness);
        }
        return food_dogs;
    }
}

class FishFeeder extends Feeder<FishService>{
    public double feed(FishService fishService, int max_happiness){
        List<Fish> fishes = fishService.getAll();
        double food_fishes=0;
        for (Fish fish : fishes) {
            if(fish==null)
                break;
            food_fishes += fish.feed();
            fish.setHappiness(max_happiness);
        }
        return food_fishes;
        
    }
}

class PenguinFeeder extends Feeder<PenguinService>{
    public double feed(PenguinService penguinService, int max_happiness){
        AnimalRepository repo = penguinService.getRepo();
        FishService fishService = new FishService(repo);
        List<Penguin> penguins = penguinService.getAll();
        int count_food_p=0;
        List<Fish> fishes = fishService.getAll();
        // For Each Penguin a Fish was Eaten
        for (Penguin penguin : penguins){
            if(penguin==null){
                break;
            }
            fishes = penguinService.Feed_Penguins(fishes);
            repo.clearAnimals("Fishes");
            FishService fService = new FishService(repo);
            for (Fish fish : fishes) {
                fService.addNewAnimal(fish);
            }
            penguin.setHappiness(max_happiness); // Just the penguin that ate will return to level 100 of Happiness
        }
        return count_food_p;
    }
}