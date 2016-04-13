package algorithm.genetic.crossover;

import model.City;
import model.Tour;
import util.RandomNumber;

public class TwoPointCrossover implements CrossoverMethod {

	@Override
	public Tour crossover(Tour parent1, Tour parent2) {
		
		// TODO - Use TourManager class to store information about cities/size
		Tour child = new Tour(parent1.getSize());
		
		// Select two random indices
		int rand1 = (int) (RandomNumber.random() * parent1.getSize());
		int rand2 = (int) (RandomNumber.random() * parent1.getSize());
		
		// Use them to create subset indices
		int startIndex = (rand1 < rand2 ? rand1 : rand2);
		int endIndex = (rand1 > rand2 ? rand1 : rand2);
				
		// Add the subset of cities from parent1 to the child, in correct positions
		for (int i=0; i<child.getSize(); i++) {
			
			if (i < startIndex || i > endIndex) continue;
			child.setCity(i, parent1.getCity(i));
		
		}
		
		// Add the remaining cities from parent2, in correct position
		for (int i=0; i<parent2.getSize(); i++) {			
			
			City parentCity = parent2.getCity(i);
			
			// Skip cities that have already been added to the child
			if (child.contains(parentCity)) {
				continue;
			}
			
			// Add the city in the first available position in the child tour
			for (int j=0; j<child.getSize(); j++) {
				
				if (child.getCity(j) == null) {
					child.setCity(j, parentCity);
					break;
				}
				
			}
			
		}
		
		return child;
		
	}

}
