package algorithm.annealing.neighbor;

import algorithm.RandomNumber;
import model.City;
import model.Tour;

public class ConsecutiveSwap implements NeighborGenerator {

	@Override
	public Tour generate(Tour tour) {
    	
        Tour neighbor = new Tour(tour);

        int posA = (int) ((neighbor.getSize() - 1) * RandomNumber.random());
        int posB = posA + 1;
        
        City cityA = neighbor.getCity(posA);
        City cityB = neighbor.getCity(posB);
        
        neighbor.setCity(posB, cityA);
        neighbor.setCity(posA, cityB);
        
        return neighbor;
        		
	}

}
