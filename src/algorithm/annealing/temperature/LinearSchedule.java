package algorithm.annealing.temperature;

public class LinearSchedule implements TemperatureSchedule {
	
	private static final double DEFAULT_INIT_TEMP 		= 10000;
	private static final double DEFAULT_COOLING_RATE 	= 5;
	private static final double COOL_THRESHOLD 			= 1;
	
	private double initialTemperature 	= DEFAULT_INIT_TEMP;
	private double currentTemperature 	= DEFAULT_INIT_TEMP;
	private double coolingRate 			= DEFAULT_COOLING_RATE;
	
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
		return currentTemperature < COOL_THRESHOLD;
	}

}
