package algorithm.annealing.test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import algorithm.AlgorithmListener;
import algorithm.annealing.SimulatedAnnealing;
import algorithm.annealing.factory.SAFactory;

public class SATestManager {
	
	// Number of trials to run to determine an average
	private final int NUM_TRIALS = 20;
	
	// Thread delay between iterations (ms)
	private static final int DELAY 			= 10;
	private static final boolean USE_DELAY 	= false;
	
	// Store listeners so they can be added to new GAManager instances
	private List<AlgorithmListener> listeners = new ArrayList<AlgorithmListener>();

	public void test() {
		
		log("Number of trials: " + NUM_TRIALS);
		testDefault();
		
	}
	
	private void testDefault() {

		log("Test Default");

		Map<SimulatedAnnealing, List<SAResult>> results = testAlgorithm(SAFactory.getDefault());		
		for (SimulatedAnnealing sa : results.keySet()) {
			
			double average = calculateFinalDistanceAverage(results.get(sa));
			logAverageFinalDistance(average);
			
		}
		
	}
	
	private Map<SimulatedAnnealing, List<SAResult>> testAlgorithm(SimulatedAnnealing saAlgorithm) {
	
		saAlgorithm.addListeners(listeners);
		Map<SimulatedAnnealing, List<SAResult>> results = new LinkedHashMap<SimulatedAnnealing, List<SAResult>>();
			
			for (int i=0; i<NUM_TRIALS; i++) {
				
				int initialDistance = saAlgorithm.getCurrentDistance();			
				runSingleAlgorithm(saAlgorithm);				
				int finalDistance = saAlgorithm.getCurrentDistance();
				
				if (!results.containsKey(saAlgorithm)) results.put(saAlgorithm, new ArrayList<SAResult>());
				results.get(saAlgorithm).add(new SAResult(saAlgorithm.getParameters(), initialDistance, finalDistance));
			
			}
		
		return results;
		
	}
	
	private void runSingleAlgorithm(SimulatedAnnealing sa) {

		int iteration = 0;
		while (!sa.isFinished()) {
			
			sa.iterate(iteration++);
			if (USE_DELAY) {
				sleep();
			}
			
		}
		
	}
	
	private double calculateFinalDistanceAverage(List<SAResult> results) {
		
		double sum = 0;
		for (int i=0; i<NUM_TRIALS; i++) {
			sum += results.get(i).getFinalDistance();
		}
		
		return sum / (double) NUM_TRIALS;
		
	}
	
	private void logAverageFinalDistance(double average) {
		log(String.format("\tAverage Final Distance: %.2f ", average));
	}
	
	private void log(String str) {
		System.out.println(str);
	}
	
	private void sleep() {
        
        try {
        	Thread.sleep(DELAY);             
            
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
            
        }
		
	}

	public void addListener(AlgorithmListener listener) {
		listeners.add(listener);
	}

}
