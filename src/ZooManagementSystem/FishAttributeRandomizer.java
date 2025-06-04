package ZooManagementSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class FishAttributeRandomizer{
public static List<Integer> generateUniqueRandomList(int length) {
    Set<Integer> uniqueNumbers = new HashSet<>();
    Random random = new Random();

    while (uniqueNumbers.size() < length) {
        uniqueNumbers.add(random.nextInt(10));
    }

    return new ArrayList<>(uniqueNumbers);
}

public static List<Colour> randomColour() {
    List<Colour> allColours = Arrays.asList(Colour.values());
    List<Colour> singularFishColours = new ArrayList<>();
    int length = (int) (Math.random() * 10) + 1;
    List<Integer> randomUniqueNumberList = generateUniqueRandomList(length);

    for (Integer index : randomUniqueNumberList) {
        singularFishColours.add(allColours.get(index));
        // Zoo.numOfFishColours[index]++;
    }

    return singularFishColours;
}

public static Colour randomSingleColour(List<Colour> colour){
    int num = (int)(Math.random()*3)+1;
	return colour.get(num);
}

	//Returns a random enum value of pattern.
	public static Pattern randomPattern() {
		List<Pattern> allPatterns = Arrays.asList(Pattern.values());
		Random random = new Random();
		return allPatterns.get(random.nextInt(allPatterns.size()));
	}
}
