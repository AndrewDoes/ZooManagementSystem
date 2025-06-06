package ZooManagementSystem.Views;

import ZooManagementSystem.Utils.AgeException;
import ZooManagementSystem.Utils.HeightException;

public class Main {
    public static void main(String[] args) throws HeightException, AgeException {
        App app = new App();
        app.startApp();
    }
}