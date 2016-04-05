package algorithm.annealing.factory;

import algorithm.annealing.SAParameters;
import algorithm.annealing.SimulatedAnnealing;

public class SAFactory {
	
	public static SimulatedAnnealing getDefault() {
		return new SimulatedAnnealing(new SAParameters());
	}

}
