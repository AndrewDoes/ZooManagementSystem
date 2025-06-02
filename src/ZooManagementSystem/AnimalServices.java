package ZooManagementSystem;

import java.util.List;

public abstract class AnimalServices<T> {
    private AnimalRepository repo;
    public AnimalServices(AnimalRepository repo) {
        this.repo = repo;
    }
    public AnimalRepository getRepo() {
        return repo;
    }
    public void setRepo(AnimalRepository repo) {
        this.repo = repo;
    }
    public int getSize(){
        if(getAll() == null) return 0;
        return getAll().size();
    }
    public abstract List<T> getAll();
    public abstract void addNewAnimal(T animal);
    public abstract double feedAll();
    public abstract void remove (T animal);
}
