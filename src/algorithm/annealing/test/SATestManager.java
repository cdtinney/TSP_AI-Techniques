package algorithm.annealing.test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import algorithm.AlgorithmListener;
import algorithm.annealing.SimulatedAnnealing;
import algorithm.annealing.factory.SAFactory;
import algorithm.annealing.neighbor.NeighborGenerator;
import algorithm.annealing.temperature.TemperatureSchedule;

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
//		testDefault();
		testTemperatureSchedules();
//		testNeighborGenerators();
		
	}

	private void testDefault() {

		log("Test - Default\n");

		List<SAResult> results = testAlgorithm(SAFactory.getDefault());
		double average = calculateFinalDistanceAverage(results);
		logAverageFinalDistance(average);		
		
	}
	
	private void testTemperatureSchedules() {

		log("Test - Temperature Schedules\n");

		Map<SimulatedAnnealing, List<SAResult>> results = testAlgorithms(SAFactory.getTemperatureSchedules());		
		for (SimulatedAnnealing sa : results.keySet()) {
			
			double average = calculateFinalDistanceAverage(results.get(sa));
			logTemperatureSchedule(sa.getParameters().getTemperatureSchedule());
			logAverageFinalDistance(average);
			
		}
		
	}
	
	private void testNeighborGenerators() {

		log("Test - Neighbor Generators\n");

		Map<SimulatedAnnealing, List<SAResult>> results = testAlgorithms(SAFactory.getNeighborGenerators());		
		for (SimulatedAnnealing sa : results.keySet()) {
			
			double average = calculateFinalDistanceAverage(results.get(sa));
			logNeighborGenerator(sa.getParameters().getNeighborGenerator());
			logAverageFinalDistance(average);
			
		}
		
	}

	private void logNeighborGenerator(NeighborGenerator neighborGenerator) {
		log("Neighbor Generator: " + neighborGenerator.getClass().getSimpleName());
	}

	private Map<SimulatedAnnealing, List<SAResult>> testAlgorithms(List<SimulatedAnnealing> algorithms) {
	
		Map<SimulatedAnnealing, List<SAResult>> results = new LinkedHashMap<SimulatedAnnealing, List<SAResult>>();	
		algorithms.stream().forEach(alg -> {
			
			List<SAResult> singleAlgResults = testAlgorithm(alg);

			if (!results.containsKey(alg)) results.put(alg, new ArrayList<SAResult>());
			results.get(alg).addAll(singleAlgResults);
			
		});
		
		return results;
		
	}
	
	private List<SAResult> testAlgorithm(SimulatedAnnealing saAlgorithm) {
	
		saAlgorithm.addListeners(listeners);
		
		List<SAResult> results = new ArrayList<SAResult>();	
		for (int i=0; i<NUM_TRIALS; i++) {
			
			int initialDistance = saAlgorithm.getCurrentDistance();			
			runSingleAlgorithm(saAlgorithm);				
			int finalDistance = saAlgorithm.getCurrentDistance();
			
			results.add(new SAResult(saAlgorithm.getParameters(), initialDistance, finalDistance));
		
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
	
	private void logTemperatureSchedule(TemperatureSchedule temperatureSchedule) {
		log("Temperature Schedule: " + temperatureSchedule.getClass().getSimpleName());
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
