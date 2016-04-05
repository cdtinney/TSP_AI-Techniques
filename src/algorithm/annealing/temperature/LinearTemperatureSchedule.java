package algorithm.annealing.temperature;

public class LinearTemperatureSchedule implements TemperatureSchedule {
	
	private static final double DEFAULT_INIT_TEMP 		= 10000;
	private static final double DEFAULT_COOLING_RATE 	= 0.03;
	private static final int 	COOL_THRESHOLD 			= 1;
	
	private double currentTemperature 	= DEFAULT_INIT_TEMP;
	private double coolingRate 			= DEFAULT_COOLING_RATE;
	
	public LinearTemperatureSchedule() {
		// Empty constructor
	}
	
	public LinearTemperatureSchedule(double initialTemperature, double coolingRate) {
		this.currentTemperature = initialTemperature;
		this.coolingRate = coolingRate;
	}

	@Override
	public double getCurrentTemperature() {
		return currentTemperature;
	}

	@Override
	public void cool() {
		currentTemperature *= (1 - coolingRate);
	}

	@Override
	public boolean isCool() {
		return currentTemperature < COOL_THRESHOLD;
	}

}
