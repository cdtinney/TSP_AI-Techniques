package algorithm.genetic;

public class GAParameters {
	
	private static final double 	MUTATION_RATE_DEFAULT 		= 0.015;
	private static final int 		GROUP_SIZE_DEFAULT 			= 5;
	private static final boolean 	ELITISM_DEFAULT				= true;
	private static final int		NUM_GENERATIONS_DEFAULT		= 100;
	
	private double mutationRate 	= MUTATION_RATE_DEFAULT;
	private int 	groupSize 		= GROUP_SIZE_DEFAULT;
	private boolean elitism 		= ELITISM_DEFAULT;
	private int numGenerations		= NUM_GENERATIONS_DEFAULT;
	
	public GAParameters() {
		// Default constructor
	}
	
	public GAParameters(double mutationRate, int groupSize, boolean elitism, int numGenerations) {
		this.mutationRate = mutationRate;
		this.groupSize = groupSize;
		this.elitism = elitism;
		this.numGenerations = numGenerations;
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
