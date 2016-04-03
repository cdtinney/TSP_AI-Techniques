package algorithm.genetic;

import algorithm.genetic.crossover.CrossoverMethod;
import algorithm.genetic.crossover.CrossoverMethodFactory;
import algorithm.genetic.mutation.MutationMethod;
import algorithm.genetic.mutation.MutationMethodFactory;

public class GAParameters {
	
	private static final double 	MUTATION_RATE_DEFAULT 		= 0.015;
	private static final int 		GROUP_SIZE_DEFAULT 			= 5;
	private static final boolean 	ELITISM_DEFAULT				= true;
	private static final int		NUM_GENERATIONS_DEFAULT		= 100;
	
	private double mutationRate 	= MUTATION_RATE_DEFAULT;
	private int 	groupSize 		= GROUP_SIZE_DEFAULT;
	private boolean elitism 		= ELITISM_DEFAULT;
	private int numGenerations		= NUM_GENERATIONS_DEFAULT;
	
	private MutationMethod mutationMethod 	= MutationMethodFactory.getDefault();
	private CrossoverMethod crossoverMethod = CrossoverMethodFactory.getDefault();
	
	public GAParameters() {
		// Default constructor
	}
	
	public GAParameters(double mutationRate) {
		this.mutationRate = mutationRate;
	}
	
	public CrossoverMethod getCrossoverMethod() {
		return crossoverMethod;
	}
	
	public MutationMethod getMutationMethod() {
		return mutationMethod;
	}

	public double getMutationRate() {
		return mutationRate;
	}

	public int getGroupSize() {
		return groupSize;
	}

	public boolean getElitism() {
		return elitism;
	}

	public int getNumGenerations() {
		return numGenerations;
	}

}
