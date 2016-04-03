package algorithm.genetic.mutation;

import algorithm.genetic.GAParameters;
import model.City;
import model.Tour;

public class SwapMutationMethod implements MutationMethod {

	@Override
	public void mutate(GAParameters parameters, Tour tour) {
    	
        for (int i=0; i < tour.getSize(); i++) {
        	
        	if (Math.random() >= parameters.getMutationRate()) {
        		continue;
        	}
        	
	        // Get a second, random city in the tour
	        int randomPos = (int) (tour.getSize() * Math.random());
	        City randomCity = tour.getCity(randomPos);
	
	        // Swap them
	        tour.setCity(randomPos, tour.getCity(i));
	        tour.setCity(i, randomCity);
            
        }
        
	}

}
