package ZooManagementSystem.Services;

import java.util.ArrayList;
import java.util.List;

import ZooManagementSystem.DomainModels.Animal;

public class ZooTimeService {

	public String ageOneYearAll(Zoo zoo) {
		List<Animal> animals = zoo.repo.getAllAnimal();
		String PrintAllDead = "";
		int index = 0;
		PrintAllDead = zoo.timeService.ageAll(zoo, animals, PrintAllDead, index);
		return PrintAllDead;
	}

	String ageAll(Zoo zoo, List<Animal> animals, String PrintAllDead, int index) {
		List<Animal> animalsToRemove = new ArrayList<>();
		for (Animal animal : animals) {
			if (!animal.ageOneYear()) {
				PrintAllDead = zoo.printer.reportDeathByAge(PrintAllDead, animal);
				animalsToRemove.add(animal);
			} else {
				animal.setHappiness(animal.getHappiness() - (int) (Math.random() * 20 + 1));
				if (animal.getHappiness() <= 0) {
					PrintAllDead = zoo.printer.reportDeathBySadness(PrintAllDead, animal);
					animalsToRemove.add(animal);
				}
			}
			index++;
		}

		for (Animal animal : animalsToRemove) {
			zoo.repo.removeAnimal(animal.toString(), animal);
		}
		return PrintAllDead;
	}

}
