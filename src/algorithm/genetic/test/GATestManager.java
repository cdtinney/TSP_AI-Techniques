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
	
	// Store listeners so they can be added to new GAManager instances
	private List<AlgorithmListener> listeners = new ArrayList<AlgorithmListener>();

	public void addListener(AlgorithmListener listener) {
		listeners.add(listener);
	}

	public void testDefault() {
		
		GAManager gaManager = new GAManager(GAFactory.getDefault(), listeners, true);
		gaManager.run();
		
	}
	
	public void testMutationRates() {
		
		int numTrials = 10;
		
		List<GeneticAlgorithm> algorithms = GAFactory.getAllMutationRates();
		
		Map<GeneticAlgorithm, List<GAResult>> results = new LinkedHashMap<GeneticAlgorithm, List<GAResult>>();
		algorithms.forEach(alg -> {
			
			for (int i=0; i<numTrials; i++) {
			
				GAManager gaManager = new GAManager(alg, listeners, true);
				
				int initialDistance = gaManager.getCurrentDistance();
				
	//			logMutationRate(gaManager.getParameters().getMutationRate());
	//			logDistance(true, gaManager.getCurrentPopulation());
				
				gaManager.run();
				
				int finalDistance = gaManager.getCurrentDistance();
				
				if (!results.containsKey(alg)) results.put(alg, new ArrayList<GAResult>());
				results.get(alg).add(new GAResult(gaManager.getParameters(), initialDistance, finalDistance));
			
			}
			
//			logDistance(false, gaManager.getCurrentPopulation());
			
		});
		
		for (GeneticAlgorithm ga : results.keySet()) {
			
			double sum = 0;
			for (int i=0; i<numTrials; i++) {
				sum += results.get(ga).get(i).getFinalDistance();
			}
			
			double average = sum / (double) numTrials;
			logMutationRate(ga.getParameters().getMutationRate());
			logAverageFinalDistance(average);
			
		}
		
	}
	
	private void logMutationRate(double mutationRate) {
		log("Mutation Rate: " + mutationRate);
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
