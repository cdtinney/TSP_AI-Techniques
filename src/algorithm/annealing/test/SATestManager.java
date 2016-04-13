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
import configuration.TestConfig;

public class SATestManager {
	
	// Store listeners so they can be added to new GAManager instances
	private List<AlgorithmListener> listeners = new ArrayList<AlgorithmListener>();

	public void test() {
		
		if (TestConfig.TEST_SA_OPTIMAL)
			testOptimal();

		if (TestConfig.TEST_SA_DEFAULT)
			testDefault();
		
		if (TestConfig.TEST_SA_TEMPERATURE_SCHEDULE)
			testTemperatureSchedules();
			
		if (TestConfig.TEST_SA_NEIGHBOR_GENERATION)
			testNeighborGenerators();
		
	}

	private void testOptimal() {

		logTestName("[TEST] SA - Optimal");

		List<SAResult> results = testAlgorithm(SAFactory.getOptimal());
		double average = calculateFinalDistanceAverage(results);
		logAverageFinalDistance(average);		
		
		
	}

	private void testDefault() {

		logTestName("[TEST] SA - Default");

		List<SAResult> results = testAlgorithm(SAFactory.getDefault());
		double average = calculateFinalDistanceAverage(results);
		logAverageFinalDistance(average);		
		
	}
	
	private void testTemperatureSchedules() {

		logTestName("[TEST] SA - Temperature Schedules");

		Map<SimulatedAnnealing, List<SAResult>> results = testAlgorithms(SAFactory.getTemperatureSchedules());		
		for (SimulatedAnnealing sa : results.keySet()) {
			
			double average = calculateFinalDistanceAverage(results.get(sa));
			logTemperatureSchedule(sa.getParameters().getTemperatureSchedule());
			logAverageFinalDistance(average);
			
		}
		
	}
	
	private void testNeighborGenerators() {

		logTestName("[TEST] SA - Neighbor Generators");

		Map<SimulatedAnnealing, List<SAResult>> results = testAlgorithms(SAFactory.getNeighborGenerators());		
		for (SimulatedAnnealing sa : results.keySet()) {
			
			double average = calculateFinalDistanceAverage(results.get(sa));
			logNeighborGenerator(sa.getParameters().getNeighborGenerator());
			logAverageFinalDistance(average);
			
		}
		
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
		for (int i=0; i<TestConfig.NUM_TEST_TRIALS; i++) {
			
			int initialDistance = saAlgorithm.getCurrentDistance();			
			runSingleAlgorithm(saAlgorithm);				
			int finalDistance = saAlgorithm.getCurrentDistance();
			
			results.add(new SAResult(saAlgorithm.getParameters(), initialDistance, finalDistance));
		
		}
		
		return results;
		
	}
	
	private void runSingleAlgorithm(SimulatedAnnealing sa) {
		
		// Reset the temperature schedule
		sa.getParameters().getTemperatureSchedule().reset();
		
		// Reset the best tour
		sa.reset();

		int iteration = 0;
		while (!sa.isFinished()) {
			sa.iterate(iteration++);			
		}
		
	}
	
	private double calculateFinalDistanceAverage(List<SAResult> results) {
		
		double sum = 0;
		for (int i=0; i<TestConfig.NUM_TEST_TRIALS; i++) {
			sum += results.get(i).getFinalDistance();
		}
		
		return sum / (double) TestConfig.NUM_TEST_TRIALS;
		
	}

	private void logNeighborGenerator(NeighborGenerator neighborGenerator) {
		log(neighborGenerator.getClass().getSimpleName());
	}
	
	private void logTemperatureSchedule(TemperatureSchedule temperatureSchedule) {
		log(temperatureSchedule.getClass().getSimpleName());
	}
	
	private void logAverageFinalDistance(double average) {
		log(String.format("\tAverage Final Distance: %.2f ", average));
	}
	
	private void logTestName(String str) {
		System.out.println("\n-------------------------------");
		System.out.println(str);
	}
	
	private void log(String str) {
		System.out.println(str);
	}

	public void addListener(AlgorithmListener listener) {
		listeners.add(listener);
	}

}
