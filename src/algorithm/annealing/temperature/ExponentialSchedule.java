package algorithm.annealing.temperature;

public class ExponentialSchedule implements TemperatureSchedule {
	
	private static final double DEFAULT_INIT_TEMP 		= 10000;
	private static final double DEFAULT_COOLING_RATE 	= 0.97;
	private static final double COOL_THRESHOLD 			= 1;
	
	private double initialTemperature 	= DEFAULT_INIT_TEMP;
	private double currentTemperature 	= DEFAULT_INIT_TEMP;
	private double coolingRate 			= DEFAULT_COOLING_RATE;
	
	public ExponentialSchedule() {
		// Empty constructor
	}
	
	public ExponentialSchedule(double initialTemperature, double coolingRate) {
		this.initialTemperature = this.currentTemperature = initialTemperature;
		this.coolingRate = coolingRate;
	}

	@Override
	public double getTemperature(int iteration) {
		return currentTemperature = initialTemperature * (Math.pow(coolingRate, iteration));
	}

	@Override
	public boolean isCool() {
		return currentTemperature < COOL_THRESHOLD;
	}

}
