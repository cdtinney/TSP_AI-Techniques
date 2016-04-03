package algorithm.genetic.factory;

import java.util.ArrayList;
import java.util.List;

import algorithm.genetic.GAParameters;
import algorithm.genetic.GeneticAlgorithm;

public class GAFactory {
	
	public static GeneticAlgorithm getDefault() {
		return new GeneticAlgorithm(new GAParameters());
	}
	
	public static List<GeneticAlgorithm> getMutationRates(double min, double max, double increment) {
		
		List<GeneticAlgorithm> result = new ArrayList<GeneticAlgorithm>();
		
		for (double i=min; i<=max; i += increment) {
			result.add(new GeneticAlgorithm(new GAParameters(i)));			
		}
		
		return result;
		
	}

	public static List<GeneticAlgorithm> getGenerations(int min, int max, int increment) {
		
		List<GeneticAlgorithm> result = new ArrayList<GeneticAlgorithm>();
		
		for (int i=min; i<=max; i += increment) {
			
			GAParameters params = new GAParameters();
			params.setNumGenerations(i);
			result.add(new GeneticAlgorithm(params));		
			
		}
		
		return result;
	}

}
