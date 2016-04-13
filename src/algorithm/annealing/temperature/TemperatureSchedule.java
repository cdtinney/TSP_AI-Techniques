package algorithm.annealing.temperature;

public interface TemperatureSchedule {
	
	public double getTemperature(int iteration);
	public boolean isCool();
	public void reset();

}
