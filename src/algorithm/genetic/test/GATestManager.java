package algorithm.genetic.test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import algorithm.AlgorithmListener;
import algorithm.genetic.GAManager;
import algorithm.genetic.GAParameters;
import algorithm.genetic.GeneticAlgorithm;
import algorithm.genetic.factory.GAFactory;
import model.Population;

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
		testNumGenerations();
		
		
	}

	public void testDefault() {
		
		GAManager gaManager = new GAManager(GAFactory.getDefault(), listeners, true);
		gaManager.run();
		
	}
	
	public void testNumGenerations() {

		// TODO - Initial log message

		List<GeneticAlgorithm> algorithms = GAFactory.getGenerations(100, 1000, 100);
		Map<GeneticAlgorithm, List<GAResult>> results = testAlgorithms(algorithms);
		
		for (GeneticAlgorithm ga : results.keySet()) {
			
			double average = calculateFinalDistanceAverage(results.get(ga));
			logGenerations(ga.getParameters().getNumGenerations());
			logAverageFinalDistance(average);
			
		}
		
	}
	
	public void testMutationRates() {
		
		// TODO - Initial log message
		
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
	
	private void logAverageFinalDistance(double average) {
		log("\tAverage Final Distance: " + average);
	}
	
	private void logDistance(boolean initial, Population currentPopulation) {
		log("\t" + (initial ? "Initial distance: " : "Final distance: ") + currentPopulation.getFittest().getDistance());
	}

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
