package algorithm.annealing.factory;

import java.util.ArrayList;
import java.util.List;

import algorithm.annealing.SAParameters;
import algorithm.annealing.SimulatedAnnealing;
import algorithm.annealing.neighbor.ConsecutiveSwap;
import algorithm.annealing.neighbor.NeighborGenerator;
import algorithm.annealing.neighbor.RandomSwap;
import algorithm.annealing.temperature.ExponentialSchedule;
import algorithm.annealing.temperature.LinearSchedule;
import algorithm.annealing.temperature.TemperatureSchedule;

public class SAFactory {
	
	public static SimulatedAnnealing getDefault() {
		return new SimulatedAnnealing(new SAParameters());
	}

	public static List<SimulatedAnnealing> getNeighborGenerators() {
		
		List<Class<? extends NeighborGenerator>> classes = new ArrayList<Class<? extends NeighborGenerator>>();
		classes.add(ConsecutiveSwap.class);
		classes.add(RandomSwap.class);

		List<SimulatedAnnealing> result = new ArrayList<SimulatedAnnealing>();
		for (Class<? extends NeighborGenerator> clazz : classes) {
			
			SAParameters params = new SAParameters();
			
			try {
				params.setNeighborGenerator(clazz.newInstance());
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
			result.add(new SimulatedAnnealing(params));	
			
		}
		
		return result;
		
	}

	public static List<SimulatedAnnealing> getTemperatureSchedules() {
		
		List<Class<? extends TemperatureSchedule>> classes = new ArrayList<Class<? extends TemperatureSchedule>>();
		classes.add(LinearSchedule.class);
		classes.add(ExponentialSchedule.class);

		List<SimulatedAnnealing> result = new ArrayList<SimulatedAnnealing>();
		for (Class<? extends TemperatureSchedule> clazz : classes) {
			
			SAParameters params = new SAParameters();
			
			try {
				params.setTemperatureSchedule(clazz.newInstance());
				
			} catch (Exception e) {
				e.printStackTrace();
				
			}
			
			result.add(new SimulatedAnnealing(params));	
			
		}
		
		return result;
		
	}

}
