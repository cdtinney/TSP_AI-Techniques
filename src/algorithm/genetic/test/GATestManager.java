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
import configuration.TestConfig;

public class GATestManager {
	
	// Store listeners so they can be added to new GAManager instances
	private List<AlgorithmListener> listeners = new ArrayList<AlgorithmListener>();
	
	public void test() {
		
		if (TestConfig.TEST_GA_OPTIMAL)
			testOptimal();
		
		if (TestConfig.TEST_GA_DEFAULT)
			testDefault();
		
		if (TestConfig.TEST_GA_MUTATION)
			testMutationMethods();
		
		if (TestConfig.TEST_GA_MUTATION_RATE)
			testMutationRates();
		
		if (TestConfig.TEST_GA_NUM_GENERATIONS)
			testNumGenerations();
			
		if (TestConfig.TEST_GA_CROSSOVER)
			testCrossoverMethods();		
			
		if (TestConfig.TEST_GA_POPULATION_SIZE)
			testPopulationSizes();
			
		if (TestConfig.TEST_GA_GROUPS)
			testGroupSizes();
		
	}

	public void addListener(AlgorithmListener listener) {
		listeners.add(listener);
	}

	private void testOptimal() {

		logTestName("[TEST] GA - Optimal");

		List<GAResult> results = testAlgorithm(GAFactory.getOptimal());
		double average = calculateFinalDistanceAverage(results);
		logAverageFinalDistance(average);	
		
	}

	private void testDefault() {
		
		logTestName("[TEST] GA - Default");

		List<GAResult> results = testAlgorithm(GAFactory.getDefault());
		double average = calculateFinalDistanceAverage(results);
		logAverageFinalDistance(average);		
		
	}

	private void testGroupSizes() {

		int min = 2, max = 10, increment = 1;
		logTestName(String.format("[TEST] GA - Group Size (min=%d, max=%d, increment=%d)", min, max, increment));

		List<GeneticAlgorithm> algorithms = GAFactory.getGroupSizes(min, max, increment);		
		Map<GeneticAlgorithm, List<GAResult>> results = testAlgorithms(algorithms);
		
		for (GeneticAlgorithm ga : results.keySet()) {
			
			double average = calculateFinalDistanceAverage(results.get(ga));
			logGroupSize(ga.getParameters().getGroupSize());
			logAverageFinalDistance(average);
			
		}
		
	}

	private void testPopulationSizes() {

		int min = 5, max = 100, increment = 5;
		logTestName(String.format("[TEST] GA - Population Size (min=%d, max=%d, increment=%d)", min, max, increment));

		List<GeneticAlgorithm> algorithms = GAFactory.getPopulationSizes(min, max, increment);
		Map<GeneticAlgorithm, List<GAResult>> results = testAlgorithms(algorithms);
		
		for (GeneticAlgorithm ga : results.keySet()) {
			
			double average = calculateFinalDistanceAverage(results.get(ga));
			logPopulationSize(ga.getParameters().getPopulationSize());
			logAverageFinalDistance(average);
			
		}
		
	}
	
	private void testCrossoverMethods() {

		logTestName("[TEST] GA - Crossover Method");

		List<GeneticAlgorithm> algorithms = GAFactory.getCrossoverMethods();
		Map<GeneticAlgorithm, List<GAResult>> results = testAlgorithms(algorithms);
		
		for (GeneticAlgorithm ga : results.keySet()) {
			
			double average = calculateFinalDistanceAverage(results.get(ga));
			logCrossoverMethod(ga.getParameters().getCrossoverMethod());
			logAverageFinalDistance(average);
			
		}
		
	}

	private void testMutationMethods() {

		logTestName("[TEST] GA - Mutation Method");

		List<GeneticAlgorithm> algorithms = GAFactory.getMutationMethods();
		Map<GeneticAlgorithm, List<GAResult>> results = testAlgorithms(algorithms);
		
		for (GeneticAlgorithm ga : results.keySet()) {
			
			double average = calculateFinalDistanceAverage(results.get(ga));
			logMutationMethod(ga.getParameters().getMutationMethod());
			logAverageFinalDistance(average);
			
		}
		
	}

	private void testNumGenerations() {

		int min = 25, max = 200, increment = 25;
		logTestName(String.format("[TEST] GA - Num Generations (min=%d, max=%d, increment=%d)", min, max, increment));

		List<GeneticAlgorithm> algorithms = GAFactory.getGenerations(min, max, increment);
		Map<GeneticAlgorithm, List<GAResult>> results = testAlgorithms(algorithms);
		
		for (GeneticAlgorithm ga : results.keySet()) {
			
			double average = calculateFinalDistanceAverage(results.get(ga));
			logGenerations(ga.getParameters().getNumGenerations());
			logAverageFinalDistance(average);
			
		}
		
	}
	
	private void testMutationRates() {

		double min = 0.05, max = 0.75, increment = 0.05;
		logTestName(String.format("[TEST] GA - Mutation Rate (min=%f, max=%f, increment=%f)", min, max, increment));
		
		List<GeneticAlgorithm> algorithms = GAFactory.getMutationRates(min, max, increment);	
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
			
			for (int i=0; i<TestConfig.NUM_TEST_TRIALS; i++) {
			
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
	
	private List<GAResult> testAlgorithm(GeneticAlgorithm alg) {
		
		List<GAResult> results = new ArrayList<GAResult>();	
		for (int i=0; i<TestConfig.NUM_TEST_TRIALS; i++) {
			
			GAManager gaManager = new GAManager(alg, listeners, true);
			
			int initialDistance = gaManager.getCurrentDistance();				
			gaManager.run();				
			int finalDistance = gaManager.getCurrentDistance();
			
			results.add(new GAResult(alg.getParameters(), initialDistance, finalDistance));
		
		}
		
		return results;
		
	}
	
	private double calculateFinalDistanceAverage(List<GAResult> results) {
		
		double sum = 0;
		for (int i=0; i<TestConfig.NUM_TEST_TRIALS; i++) {
			sum += results.get(i).getFinalDistance();
		}
		
		return sum / (double) TestConfig.NUM_TEST_TRIALS;
		
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
	
	private void logTestName(String str) {
		System.out.println("\n-------------------------------");
		System.out.println(str);
	}

}
