package algorithm.genetic;

import algorithm.genetic.crossover.CrossoverMethod;
import algorithm.genetic.mutation.MutationMethod;

public class GAParameters {
	
	private static final double 	MUTATION_RATE_DEFAULT 		= 0.015;
	private static final int 		GROUP_SIZE_DEFAULT 			= 5;
	private static final boolean 	ELITISM_DEFAULT				= true;
	private static final int		NUM_GENERATIONS_DEFAULT		= 100;
	
	private double mutationRate 	= MUTATION_RATE_DEFAULT;
	private int 	groupSize 		= GROUP_SIZE_DEFAULT;
	private boolean elitism 		= ELITISM_DEFAULT;
	private int numGenerations		= NUM_GENERATIONS_DEFAULT;
	
	private MutationMethod mutationMethod;
	private CrossoverMethod crossoverMethod;
	
	public GAParameters() {
		// Default constructor
	}
	
	public GAParameters(MutationMethod mutationMethod, CrossoverMethod crossoverMethod) {
		this.mutationMethod = mutationMethod;
		this.crossoverMethod = crossoverMethod;
	}
	
	public GAParameters(double mutationRate, int groupSize, boolean elitism, int numGenerations) {
		this.mutationRate = mutationRate;
		this.groupSize = groupSize;
		this.elitism = elitism;
		this.numGenerations = numGenerations;
	}
	
	public GAParameters(MutationMethod mutationMethod, 
			double mutationRate, int groupSize, boolean elitism, int numGenerations) {
		this(mutationRate, groupSize, elitism, numGenerations);
		this.mutationMethod = mutationMethod;
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
