package algorithm.genetic.crossover;

import model.City;
import model.Tour;
import util.RandomNumber;

public class OnePointCrossover implements CrossoverMethod {

	@Override
	public Tour crossover(Tour parent1, Tour parent2) {
		
		// TODO - Use TourManager class to store information about cities/size
		Tour child = new Tour(parent1.getSize());
		
		// Select one random index
		int index = (int) (RandomNumber.random() * parent1.getSize());
				
		// Add the subset of cities from parent1 [0, index] to the child, in correct positions
		for (int i=0; i<child.getSize(); i++) {
			
			if (i >= index) continue;
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
