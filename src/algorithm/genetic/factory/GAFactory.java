package algorithm.genetic.factory;

import algorithm.genetic.GAParameters;
import algorithm.genetic.GeneticAlgorithm;
import algorithm.genetic.crossover.OrderedCrossoverMethod;
import algorithm.genetic.mutation.SwapMutationMethod;

public class GAFactory {
	
	public static GeneticAlgorithm getDefault() {
		return new GeneticAlgorithm(new GAParameters(new SwapMutationMethod(), new OrderedCrossoverMethod()));
	}
	
//	public static List<GeneticAlgorithm> generate() {
//		
//		List<GeneticAlgorithm> instances = new ArrayList<GeneticAlgorithm>();
//		
//	}

}
