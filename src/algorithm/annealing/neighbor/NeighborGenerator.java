package algorithm.annealing.neighbor;

import model.Tour;

public interface NeighborGenerator {
	
	public Tour generate(Tour tour);

}
