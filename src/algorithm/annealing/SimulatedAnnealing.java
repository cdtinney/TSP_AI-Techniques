package algorithm.annealing;

import algorithm.annealing.neighbor.NeighborGenerator;
import algorithm.genetic.factory.CityFactory;
import model.Tour;

public class SimulatedAnnealing { 
	
	private NeighborGenerator neighborGenerator;
	
	public SimulatedAnnealing(NeighborGenerator neighborGenerator) {
		this.neighborGenerator = neighborGenerator;		
	}
	
	public void run() {
		
		// Set initial temp
        double temp = 10000;

        // Cooling rate
        double coolingRate = 0.003;

        // Initialize intial solution
        Tour currentSolution = new Tour(CityFactory.generate());
        
        System.out.println("Initial solution distance: " + currentSolution.getDistance());

        // Set as current best
        Tour best = new Tour(currentSolution);
        
        // Loop until system has cooled
        while (temp > 1) {
        	
        	Tour neighbour = neighborGenerator.generate(best);
            
            // Get the distance of current and neighbor solutions
            int currentEnergy = currentSolution.getDistance();
            int neighbourEnergy = neighbour.getDistance();

            // Decide if we should accept the neighbor as the current best
            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
                currentSolution = new Tour(neighbour);
            }

            // Keep track of the best solution found
            if (currentSolution.getDistance() < best.getDistance()) {
                best = new Tour(currentSolution);
            }
            
            // Cool system
            temp = temp * (1 - coolingRate);
            
        }

        System.out.println("Final solution distance: " + best.getDistance());
        System.out.println("Tour: " + best);
		
	}
	
	private static double acceptanceProbability(int currentDistance, int newDistance, double temperature) {
		
	    // Always accept a solution with a lower distance
	    if (newDistance < currentDistance) {
	        return 1.0;
	    }
	    
	    // TODO - explain in own words - If the new solution is worse, calculate an acceptance probability
	    return Math.exp((currentDistance - newDistance) / temperature);
	    
	}

}
