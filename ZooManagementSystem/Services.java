package ZooManagementSystem;

import java.util.List;

public abstract class Services<T> {
    private AnimalRepository repo = new AnimalRepository();
    public Services(AnimalRepository repo) {
        this.repo = repo;
    }
    public AnimalRepository getRepo() {
        return repo;
    }
    public void setRepo(AnimalRepository repo) {
        this.repo = repo;
    }
    public abstract List<T> getAll();
    public abstract void addNewAnimal(T animal);
}
