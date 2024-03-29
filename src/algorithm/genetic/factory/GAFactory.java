package algorithm.genetic.factory;

import java.util.ArrayList;
import java.util.List;

import algorithm.genetic.GAParameters;
import algorithm.genetic.GeneticAlgorithm;
import algorithm.genetic.crossover.CrossoverMethod;
import algorithm.genetic.crossover.NoCrossover;
import algorithm.genetic.crossover.OnePointCrossover;
import algorithm.genetic.crossover.TwoPointCrossover;
import algorithm.genetic.mutation.MutationMethod;
import algorithm.genetic.mutation.ReverseSubsetMutation;
import algorithm.genetic.mutation.SwapMutation;
import algorithm.genetic.mutation.SwapOnlyImprovingMutation;

public class GAFactory {
	
	public static GeneticAlgorithm getDefault() {
		return new GeneticAlgorithm(new GAParameters());
	}
	
	public static GeneticAlgorithm getOptimal() {
		
		GAParameters params = new GAParameters();
		params.setMutationMethod(new SwapOnlyImprovingMutation());
		params.setMutationRate(0.05);
		params.setCrossoverMethod(new OnePointCrossover());
		params.setNumGenerations(50);
		params.setPopulationSize(50);
		return new GeneticAlgorithm(params);
		
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

	public static List<GeneticAlgorithm> getCrossoverMethods() {
		
		List<Class<? extends CrossoverMethod>> classes = new ArrayList<Class<? extends CrossoverMethod>>();
		classes.add(TwoPointCrossover.class);
		classes.add(OnePointCrossover.class);
		classes.add(NoCrossover.class);

		List<GeneticAlgorithm> result = new ArrayList<GeneticAlgorithm>();
		for (Class<? extends CrossoverMethod> clazz : classes) {
			
			GAParameters params = new GAParameters();
			
			try {
				params.setCrossoverMethod(clazz.newInstance());
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
			result.add(new GeneticAlgorithm(params));	
			
		}
		
		return result;
		
	}

	public static List<GeneticAlgorithm> getMutationMethods() {
		
		List<Class<? extends MutationMethod>> classes = new ArrayList<Class<? extends MutationMethod>>();
		classes.add(SwapMutation.class);
		classes.add(SwapOnlyImprovingMutation.class);
		classes.add(ReverseSubsetMutation.class);

		List<GeneticAlgorithm> result = new ArrayList<GeneticAlgorithm>();
		for (Class<? extends MutationMethod> clazz : classes) {
			
			GAParameters params = new GAParameters();
			
			try {
				params.setMutationMethod(clazz.newInstance());
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
			result.add(new GeneticAlgorithm(params));	
			
		}
		
		return result;
		
	}

	public static List<GeneticAlgorithm> getPopulationSizes(int min, int max, int increment) {
		
		List<GeneticAlgorithm> result = new ArrayList<GeneticAlgorithm>();
		
		for (int i=min; i<=max; i += increment) {
			
			GAParameters params = new GAParameters();
			params.setPopulationSize(i);
			result.add(new GeneticAlgorithm(params));		
			
		}
		
		return result;
		
	}

	public static List<GeneticAlgorithm> getGroupSizes(int min, int max, int increment) {
		
		List<GeneticAlgorithm> result = new ArrayList<GeneticAlgorithm>();
		
		for (int i=min; i<=max; i += increment) {
			
			GAParameters params = new GAParameters();
			params.setGroupSize(i);
			result.add(new GeneticAlgorithm(params));		
			
		}
		
		return result;
		
	}

}
