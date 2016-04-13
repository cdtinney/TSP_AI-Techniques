package algorithm.genetic;

import algorithm.genetic.crossover.CrossoverMethod;
import algorithm.genetic.crossover.CrossoverMethodFactory;
import algorithm.genetic.mutation.MutationFactory;
import algorithm.genetic.mutation.MutationMethod;
import configuration.GAConfig;

public class GAParameters {
	
	private double mutationRate 	= GAConfig.MUTATION_RATE_DEFAULT;
	private boolean elitism 		= GAConfig.ELITISM_DEFAULT;
	private int numGenerations		= GAConfig.NUM_GENERATIONS_DEFAULT;
	private int populationSize 		= GAConfig.POPULATION_SIZE_DEFAULT;
	private int 	groupSize 		= GAConfig.GROUP_SIZE_DEFAULT;
	
	private MutationMethod mutationMethod 	= MutationFactory.getDefault();
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
	
	public void setCrossoverMethod(CrossoverMethod crossoverMethod) {
		this.crossoverMethod = crossoverMethod;
	}
	
	public MutationMethod getMutationMethod() {
		return mutationMethod;
	}

	public void setMutationMethod(MutationMethod mutationMethod) {
		this.mutationMethod = mutationMethod;
	}

	public double getMutationRate() {
		return mutationRate;
	}

	public void setMutationRate(double mutationRate) {
		this.mutationRate = mutationRate;
	}

	public int getGroupSize() {
		return groupSize;
	}

	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}

	public boolean getElitism() {
		return elitism;
	}

	public void setElitism(boolean elitism) {
		this.elitism = elitism;
	}

	public int getNumGenerations() {
		return numGenerations;
	}
	
	public void setNumGenerations(int num) {
		numGenerations = num;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

}
