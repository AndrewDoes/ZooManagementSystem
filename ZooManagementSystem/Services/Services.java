package ZooManagementSystem.Services;

import java.util.List;

import ZooManagementSystem.Repositories.AnimalRepository;

public abstract class Services<T> {
    private AnimalRepository repo;
    public Services(AnimalRepository repo) {
        this.repo = repo;
    }
    public AnimalRepository getRepo() {
        return repo;
    }
    public void setRepo(AnimalRepository repo) {
        this.repo = repo;
    }
    public int getSize(){
        return getAll().size();
    }
    public abstract List<T> getAll();
    public abstract void addNewAnimal(T animal);
    public abstract double feedAll();
}
