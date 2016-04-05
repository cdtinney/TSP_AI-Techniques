package algorithm.annealing;

import algorithm.annealing.neighbor.NeighborGenerator;
import algorithm.annealing.neighbor.RandomSwap;
import algorithm.annealing.temperature.LinearTemperatureSchedule;
import algorithm.annealing.temperature.TemperatureSchedule;

public class SAParameters {
	
	private NeighborGenerator neighborGenerator = new RandomSwap();
	private TemperatureSchedule temperatureSchedule = new LinearTemperatureSchedule();
	
	public SAParameters() {
		// Empty constructor
	}

	public NeighborGenerator getNeighborGenerator() {
		return neighborGenerator;
	}
	
	public TemperatureSchedule getTemperatureSchedule() {
		return temperatureSchedule;
	}

}
