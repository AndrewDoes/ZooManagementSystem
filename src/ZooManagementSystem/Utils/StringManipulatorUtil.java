package ZooManagementSystem.Utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringManipulatorUtil {
    public static String removeDuplicateWords(String inputString) {
		String[] words = inputString.split("\\s+");
		String result = Arrays.stream(words)
				.distinct()
				.collect(Collectors.joining(" "));

		return result;
	}
}
