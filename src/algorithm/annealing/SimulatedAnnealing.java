package algorithm.annealing;

import java.util.ArrayList;
import java.util.List;

import algorithm.AlgorithmListener;
import algorithm.ObservableAlgorithm;
import algorithm.RandomManager;
import algorithm.annealing.neighbor.NeighborGenerator;
import algorithm.annealing.temperature.TemperatureSchedule;
import model.CityFactory;
import model.Tour;

public class SimulatedAnnealing implements ObservableAlgorithm { 
	
	// Model information
	private SAParameters saParameters;
	private Tour bestTour;
	
	// Listeners to be notified when the best tour changes
	private List<AlgorithmListener> listeners = new ArrayList<AlgorithmListener>();
	
	public SimulatedAnnealing() {
		setBestTour(new Tour(CityFactory.generate()));
	}
	
	public SimulatedAnnealing(SAParameters saParameters) {
		this();
		this.saParameters = saParameters;		
	}
	
	public void iterate(int iteration) {

        Tour currentSolution = new Tour(bestTour);
        
        // Store parameter attributes
        TemperatureSchedule schedule = saParameters.getTemperatureSchedule();
        NeighborGenerator generator = saParameters.getNeighborGenerator();
        	
    	Tour neighbour = generator.generate(bestTour);
        
        // Get the distance of current and neighbor solutions
        int currentEnergy = currentSolution.getDistance();
        int neighbourEnergy = neighbour.getDistance();

        // Decide if we should accept the neighbor as the current best
        double currentTemperature = schedule.getTemperature(iteration);
        double acceptance = calculateAcceptance(currentEnergy, neighbourEnergy, currentTemperature);
        if (acceptance == 1.0 || acceptance > RandomManager.random()) {
            currentSolution = new Tour(neighbour);
        }

        // Keep track of the best solution found
        if (currentSolution.getDistance() < bestTour.getDistance()) {
        	setBestTour(new Tour(currentSolution));
        }
		
	}
	
	public int getCurrentDistance() {
		return bestTour.getDistance();
	}

	public SAParameters getParameters() {
		return saParameters;
	}
	
	public boolean isFinished() {
		return saParameters.getTemperatureSchedule().isCool();
	}

	@Override
	public void addListeners(List<AlgorithmListener> listeners) {
		this.listeners.addAll(listeners);
	}
	
	private void setBestTour(Tour tour) {
		this.bestTour = tour;
		notifyListeners();
	}
	
	private void notifyListeners() {
		listeners.stream().forEach(l -> l.onChange(bestTour));
	}
	
	private static double calculateAcceptance(int currentDistance, int newDistance, double currentTemperature) {
		
	    // Always accept a solution with a lower distance
	    if (newDistance < currentDistance) {
	        return 1.0;
	    }
	    
	    // TODO - explain in own words - If the new solution is worse, calculate an acceptance probability
	    return Math.exp((currentDistance - newDistance) / currentTemperature);
	    
	}

}
