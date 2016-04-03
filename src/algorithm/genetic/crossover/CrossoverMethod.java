package algorithm.genetic.crossover;

import model.Tour;

public interface CrossoverMethod {
	
	public Tour crossover(Tour parent1, Tour parent2);

}
