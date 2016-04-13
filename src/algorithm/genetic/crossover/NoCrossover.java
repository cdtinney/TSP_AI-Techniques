package algorithm.genetic.crossover;

import model.Tour;

public class NoCrossover implements CrossoverMethod {

	@Override
	public Tour crossover(Tour parent1, Tour parent2) {
		
		// TODO - Use TourManager class to store information about cities/size
		Tour child = new Tour(parent1.getSize());
		
		// Select a parent randomly
		double randomNum = Math.random();
		Tour parent = randomNum > 0.5 ? parent1 : parent2;
		
		// Add cities from parent to the child
		for (int i=0; i<child.getSize(); i++) {
			child.setCity(i, parent.getCity(i));		
		}
		
		return child;
		
	}

}
