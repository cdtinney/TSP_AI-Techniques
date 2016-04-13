package algorithm.genetic;

import model.Population;
import model.Tour;

public class GeneticAlgorithm {
	
	private GAParameters parameters;
	
	public GeneticAlgorithm(GAParameters parameters) {
		this.parameters = parameters;
	}
	
	public GAParameters getParameters() {
		return parameters;
	}
	
	public Population evolve(Population initPopulation) {
		
		Population newPopulation = new Population(initPopulation.getSize());
		
		int offset = 0;
		if (parameters.getElitism()) {
			newPopulation.setTour(0, initPopulation.getFittest());
			offset = 1;
		}
		
		for (int i=offset; i<newPopulation.getSize(); i++) {
				
			Tour parent1, parent2;
			
			// Ensure both of the parents are different
			while (true) {
				
				// TODO - Remove parents from initial population?
				parent1 = fittestFromGroup(initPopulation);
				parent2 = fittestFromGroup(initPopulation);
				
				if (parent1 != parent2) {
					break;
				}
				
			}
			
			// Crossover parents to generate a new child Tour, and add it to the new population
			// TODO - Should generate TWO new children
			newPopulation.setTour(i, parameters.getCrossoverMethod().crossover(parent1, parent2));
			
		}
		
		// Mutate the children
		for (int i=offset; i<newPopulation.getSize(); i++) {
			parameters.getMutationMethod().mutate(parameters, newPopulation.getTour(i));
		}
		
		return newPopulation;
		
	}
	
	private Tour fittestFromGroup(Population population) {
		
		Population group = new Population(parameters.getGroupSize());
		
		// Generate a random group of GROUP_SIZE
		for (int i=0; i<group.getSize(); i++) {
			
			int randomIndex = (int) (Math.random() * group.getSize());
			group.setTour(i, population.getTour(randomIndex));
			
		}
		
		// Get the fittest tour from that group
		return group.getFittest();
		
	}

}
