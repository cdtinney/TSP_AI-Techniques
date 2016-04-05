package algorithm.annealing;

import algorithm.annealing.neighbor.NeighborGenerator;
import algorithm.annealing.neighbor.NeighborGeneratorFactory;
import algorithm.annealing.temperature.TemperatureSchedule;
import algorithm.annealing.temperature.TemperatureScheduleFactory;

public class SAParameters {
	
	private NeighborGenerator neighborGenerator = NeighborGeneratorFactory.getDefault();
	private TemperatureSchedule temperatureSchedule = TemperatureScheduleFactory.getDefault();
	
	public SAParameters() {
		// Empty constructor
	}

	public NeighborGenerator getNeighborGenerator() {
		return neighborGenerator;
	}

	public void setNeighborGenerator(NeighborGenerator neighborGenerator) {
		this.neighborGenerator = neighborGenerator;
	}
	
	public TemperatureSchedule getTemperatureSchedule() {
		return temperatureSchedule;
	}

	public void setTemperatureSchedule(TemperatureSchedule temperatureSchedule) {
		this.temperatureSchedule = temperatureSchedule;
	}

}
