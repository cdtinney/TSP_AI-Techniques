package algorithm.annealing.neighbor;

import model.City;
import model.Tour;

public class RandomSwap implements NeighborGenerator {

	@Override
	public Tour generate(Tour tour) {
    	
        Tour neighbor = new Tour(tour);

        int posA = (int) (neighbor.getSize() * Math.random());
        int posB = (int) (neighbor.getSize() * Math.random());
        
        City cityA = neighbor.getCity(posA);
        City cityB = neighbor.getCity(posB);
        
        neighbor.setCity(posB, cityA);
        neighbor.setCity(posA, cityB);
        
        return neighbor;
        		
	}

}
