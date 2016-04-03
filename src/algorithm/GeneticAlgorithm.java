package algorithm;

import model.City;
import model.Population;
import model.Tour;

public class GeneticAlgorithm {
	
	// Parameters
	private static final double MUTATION_RATE 	= 0.015;
	private static final int 	GROUP_SIZE 		= 5;
	
	// TODO - Configurable crossover method
	// TODO - Configurable mutation method
	
	public static Population evolve(Population initPopulation) {
		
		Population newPopulation = new Population(initPopulation.getSize());
		
		for (int i=0; i<newPopulation.getSize(); i++) {
				
			// Get two of the fittest tours from random groups
			Tour parent1 = fittestFromGroup(initPopulation);
			Tour parent2 = fittestFromGroup(initPopulation);
			
			// Do crossover
			Tour child = crossover(parent1, parent2);
			
			// Add the child to the new population
			newPopulation.setTour(i, child);
			
		}
		
		// Mutate the children
		for (int i=0; i<newPopulation.getSize(); i++) {
			mutate(newPopulation.getTour(i));
		}
		
		return newPopulation;
		
	}
	
	/* TODO - Abstract this so different crossover methods can be compared */
	private static Tour crossover(Tour parent1, Tour parent2) {
		
		// TODO - Use TourManager class to store information about cities/size
		Tour child = new Tour(parent1.getSize());
		
		// Select two random indices
		int rand1 = (int) (Math.random() * parent1.getSize());
		int rand2 = (int) (Math.random() * parent1.getSize());
		
		// Use them to create subset indices
		int startIndex = (rand1 < rand2 ? rand1 : rand2);
		int endIndex = (rand1 > rand2 ? rand1 : rand2);
				
		// Add the subset of cities from parent1 to the child, in correct positions
		for (int i=0; i<child.getSize(); i++) {
			
			if (i <= startIndex || i >= endIndex) continue;
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

	/* TODO - Abstract this so different mutation methods can be compared */
    private static void mutate(Tour tour) {
    	
    	if (Math.random() >= MUTATION_RATE) {
    		return;
    	}
    	
        for (int i=0; i < tour.getSize(); i++) {
        	
	        // Get a second, random city in the tour
	        int randomPos = (int) (tour.getSize() * Math.random());
	        City randomCity = tour.getCity(randomPos);
	
	        // Swap them
	        tour.setCity(randomPos, tour.getCity(i));
	        tour.setCity(i, randomCity);
            
        }
        
    }
	
	private static Tour fittestFromGroup(Population population) {
		
		Population group = new Population(GROUP_SIZE);
		
		// Generate a random group of GROUP_SIZE
		for (int i=0; i<group.getSize(); i++) {
			
			int randomIndex = (int) (Math.random() * group.getSize());
			group.addTour(population.getTour(randomIndex));
			
		}
		
		// Get the fittest tour from that group
		return group.getFittest();
		
	}

}
