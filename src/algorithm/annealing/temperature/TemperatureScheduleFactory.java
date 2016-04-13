package algorithm.annealing.temperature;

public class TemperatureScheduleFactory {
	
	public static TemperatureSchedule getDefault() {
		return new LinearSchedule();
	}

}
