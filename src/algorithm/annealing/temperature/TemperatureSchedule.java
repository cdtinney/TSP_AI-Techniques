package algorithm.annealing.temperature;

public interface TemperatureSchedule {
	
	public double getCurrentTemperature();
	public void cool();
	public boolean isCool();

}
