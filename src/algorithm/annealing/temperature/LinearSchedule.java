package algorithm.annealing.temperature;

import configuration.SAConfig;

public class LinearSchedule implements TemperatureSchedule {
	
	private double initialTemperature 	= SAConfig.DEFAULT_INIT_TEMP;
	private double currentTemperature 	= SAConfig.DEFAULT_INIT_TEMP;
	private double coolingRate 			= SAConfig.DEFAULT_COOLING_RATE;
	
	public LinearSchedule() {
		// Empty constructor
	}
	
	public LinearSchedule(double initialTemperature, double coolingRate) {
		this.initialTemperature = this.currentTemperature = initialTemperature;
		this.coolingRate = coolingRate;
	}

	@Override
	public double getTemperature(int iteration) {
		return currentTemperature = initialTemperature - (coolingRate * iteration);
	}

	@Override
	public boolean isCool() {
		return currentTemperature < SAConfig.COOL_THRESHOLD;
	}

	@Override
	public void reset() {
		currentTemperature = initialTemperature;
	}

}
