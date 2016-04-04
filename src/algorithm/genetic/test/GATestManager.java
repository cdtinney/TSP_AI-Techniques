package algorithm.genetic.test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import algorithm.AlgorithmListener;
import algorithm.genetic.GAManager;
import algorithm.genetic.GAParameters;
import algorithm.genetic.GeneticAlgorithm;
import algorithm.genetic.crossover.CrossoverMethod;
import algorithm.genetic.factory.GAFactory;
import algorithm.genetic.mutation.MutationMethod;

public class GATestManager {
	
	// Number of trials to run to determine an average
	private final int NUM_TRIALS = 10;
	
	// Store listeners so they can be added to new GAManager instances
	private List<AlgorithmListener> listeners = new ArrayList<AlgorithmListener>();

	public void addListener(AlgorithmListener listener) {
		listeners.add(listener);
	}
	
	public void test() {
		
//		testDefault();
//		testMutationRates();
//		testNumGenerations();
//		testCrossoverMethods();		
		testMutationMethods();
//		testPopulationSizes();
//		testGroupSizes();
		
	}

	private void testDefault() {
		
		GAManager gaManager = new GAManager(GAFactory.getDefault(), listeners, true);
		gaManager.run();
		
	}
	
	private void testGroupSizes() {

		int min = 5, max = 20, increment = 1;
		log(String.format("Testing Group Sizes (min=%d, max=%d, increment=%d)", min, max, increment));

		List<GeneticAlgorithm> algorithms = GAFactory.getGroupSizes(min, max, increment);
		Map<GeneticAlgorithm, List<GAResult>> results = testAlgorithms(algorithms);
		
		for (GeneticAlgorithm ga : results.keySet()) {
			
			double average = calculateFinalDistanceAverage(results.get(ga));
			logGroupSize(ga.getParameters().getGroupSize());
			logAverageFinalDistance(average);
			
		}
		
		
		
	}

	private void testPopulationSizes() {

		int min = 5, max = 100, increment = 1;
		log(String.format("Testing Population Sizes (min=%d, max=%d, increment=%d)", min, max, increment));

		List<GeneticAlgorithm> algorithms = GAFactory.getPopulationSizes(min, max, increment);
		Map<GeneticAlgorithm, List<GAResult>> results = testAlgorithms(algorithms);
		
		for (GeneticAlgorithm ga : results.keySet()) {
			
			double average = calculateFinalDistanceAverage(results.get(ga));
			logPopulationSize(ga.getParameters().getPopulationSize());
			logAverageFinalDistance(average);
			
		}
		
	}
	
	private void testCrossoverMethods() {

		log("Testing Crossover Methods");

		List<GeneticAlgorithm> algorithms = GAFactory.getCrossoverMethods();
		Map<GeneticAlgorithm, List<GAResult>> results = testAlgorithms(algorithms);
		
		for (GeneticAlgorithm ga : results.keySet()) {
			
			double average = calculateFinalDistanceAverage(results.get(ga));
			logCrossoverMethod(ga.getParameters().getCrossoverMethod());
			logAverageFinalDistance(average);
			
		}
		
	}

	private void testMutationMethods() {

		log("Testing Mutation Methods");

		List<GeneticAlgorithm> algorithms = GAFactory.getMutationMethods();
		Map<GeneticAlgorithm, List<GAResult>> results = testAlgorithms(algorithms);
		
		for (GeneticAlgorithm ga : results.keySet()) {
			
			double average = calculateFinalDistanceAverage(results.get(ga));
			logMutationMethod(ga.getParameters().getMutationMethod());
			logAverageFinalDistance(average);
			
		}
		
	}

	private void testNumGenerations() {

		int min = 100, max = 1000, increment = 100;
		log(String.format("Testing Num Generations (min=%d, max=%d, increment=%d)", min, max, increment));

		List<GeneticAlgorithm> algorithms = GAFactory.getGenerations(100, 1000, 100);
		Map<GeneticAlgorithm, List<GAResult>> results = testAlgorithms(algorithms);
		
		for (GeneticAlgorithm ga : results.keySet()) {
			
			double average = calculateFinalDistanceAverage(results.get(ga));
			logGenerations(ga.getParameters().getNumGenerations());
			logAverageFinalDistance(average);
			
		}
		
	}
	
	private void testMutationRates() {

		double min = 0.0, max = 1.00, increment = 0.05;
		log(String.format("Testing Mutation Rates (min=%f, max=%f, increment=%f)", min, max, increment));
		
		List<GeneticAlgorithm> algorithms = GAFactory.getMutationRates(0.00, 1.00, 0.05);	
		Map<GeneticAlgorithm, List<GAResult>> results = testAlgorithms(algorithms);
		
		for (GeneticAlgorithm ga : results.keySet()) {
			
			double average = calculateFinalDistanceAverage(results.get(ga));
			logMutationRate(ga.getParameters().getMutationRate());
			logAverageFinalDistance(average);
			
		}
		
	}
	
	private Map<GeneticAlgorithm, List<GAResult>> testAlgorithms(List<GeneticAlgorithm> algorithms) {
	
		Map<GeneticAlgorithm, List<GAResult>> results = new LinkedHashMap<GeneticAlgorithm, List<GAResult>>();
		algorithms.forEach(alg -> {
			
			for (int i=0; i<NUM_TRIALS; i++) {
			
				GAManager gaManager = new GAManager(alg, listeners, true);
				
				int initialDistance = gaManager.getCurrentDistance();				
				gaManager.run();				
				int finalDistance = gaManager.getCurrentDistance();
				
				if (!results.containsKey(alg)) results.put(alg, new ArrayList<GAResult>());
				results.get(alg).add(new GAResult(gaManager.getParameters(), initialDistance, finalDistance));
			
			}
			
		});
		
		return results;
		
	}
	
	private double calculateFinalDistanceAverage(List<GAResult> results) {
		
		double sum = 0;
		for (int i=0; i<NUM_TRIALS; i++) {
			sum += results.get(i).getFinalDistance();
		}
		
		return sum / (double) NUM_TRIALS;
		
	}
	
	private void logGenerations(int generations) {
		log("Generations: " + generations);
	}
	
	private void logMutationRate(double mutationRate) {
		log(String.format("Mutation Rate: %.2f", mutationRate));
	}
	
	private void logCrossoverMethod(CrossoverMethod crossoverMethod) {
		log("Crossover Method: " + crossoverMethod.getClass().getSimpleName());
	}

	private void logMutationMethod(MutationMethod mutationMethod) {
		log("Mutation Method: " + mutationMethod.getClass().getSimpleName());
	}
	
	private void logPopulationSize(int size) {
		log("Population size: " + size);
	}
	
	private void logGroupSize(int groupSize) {
		log("Group size: " + groupSize);
	}
	
	private void logAverageFinalDistance(double average) {
		log(String.format("\tAverage Final Distance: %.2f ", average));
	}

	@SuppressWarnings("unused")
	private void logParameters(GAParameters parameters) {
		
		log("---- Parameters ----");
		log("\tNumber of Generations: " + parameters.getNumGenerations());
		log("\tMutation Method: " + parameters.getMutationMethod().getClass().getSimpleName());
		log("\tCrossover Method: " + parameters.getCrossoverMethod().getClass().getSimpleName());
		log("\tMutation Rate: " + parameters.getMutationRate());
		log("\tGroup Size: " + parameters.getGroupSize());
		log("\tElitism: " + parameters.getElitism());
		
	}
	
	private void log(String str) {
		System.out.println(str);
	}

}
