package algorithm.annealing;

import algorithm.annealing.neighbor.NeighborGenerator;
import algorithm.annealing.neighbor.NeighborSwap;
import algorithm.annealing.temperature.ExponentialSchedule;
import algorithm.annealing.temperature.TemperatureSchedule;

public class SAParameters {
	
	private NeighborGenerator neighborGenerator = new NeighborSwap();
	private TemperatureSchedule temperatureSchedule = new ExponentialSchedule();
	
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
