package algorithm.genetic.factory;

import java.util.ArrayList;
import java.util.List;

import algorithm.genetic.GAParameters;
import algorithm.genetic.GeneticAlgorithm;

public class GAFactory {
	
	public static GeneticAlgorithm getDefault() {
		return new GeneticAlgorithm(new GAParameters());
	}
	
	public static List<GeneticAlgorithm> getAllMutationRates() {
		
		List<GeneticAlgorithm> result = new ArrayList<GeneticAlgorithm>();
		
		int minRate = 5;
		int maxRate = 95;
		int increment = 10;
		
		for (int i=minRate; i<=maxRate; i += increment) {
			result.add(new GeneticAlgorithm(new GAParameters((double) i / 100)));			
		}
		
		return result;
		
	}

}
