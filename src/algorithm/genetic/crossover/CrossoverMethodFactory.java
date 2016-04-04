package algorithm.genetic.crossover;

public class CrossoverMethodFactory {

	public static CrossoverMethod getDefault() {
		return new TwoPointCrossover();
	}
	
}
