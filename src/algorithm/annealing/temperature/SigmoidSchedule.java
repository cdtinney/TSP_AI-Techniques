package algorithm.annealing.temperature;

public class SigmoidSchedule implements TemperatureSchedule {
	
	private static final double DEFAULT_INIT_TEMP 		= 10000;
	private static final double DEFAULT_COOLING_RATE 	= 0.97;
	private static final double COOL_THRESHOLD 			= 1;
	
	private double initialTemperature 	= DEFAULT_INIT_TEMP;
	private double currentTemperature 	= DEFAULT_INIT_TEMP;
	private double coolingRate 			= 0.3;
	
	public SigmoidSchedule() {
		// Empty constructor
	}
	
	public SigmoidSchedule(double initialTemperature, double coolingRate) {
		this.initialTemperature = this.currentTemperature = initialTemperature;
		this.coolingRate = coolingRate;
	}

	@Override
	public double getTemperature(int iteration) {		
		return currentTemperature = (initialTemperature) / (1 + Math.exp(coolingRate * iteration));
	}

	@Override
	public boolean isCool() {
		return currentTemperature < COOL_THRESHOLD;
	}

	@Override
	public void reset() {
		currentTemperature = initialTemperature;
	}

}
