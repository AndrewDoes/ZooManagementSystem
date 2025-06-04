package ZooManagementSystem.Views;

import ZooManagementSystem.Exceptions.AgeException;
import ZooManagementSystem.Exceptions.HeightException;

public class Main {
    public static void main(String[] args) throws HeightException, AgeException {
        App app = new App();
        app.startApp();
    }
}