package ZooManagementSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ZooManagementSystem.Enums.Colour;

public class FishColourService {
    List<Colour> colours = new ArrayList<>(Arrays.asList(Colour.values()));
    public static List<Colour> getFishColours() {
        return new ArrayList<>(Arrays.asList(Colour.values()));
    }
}
