package algorithm.genetic.mutation;

import algorithm.genetic.GAParameters;
import model.City;
import model.Tour;

public class ReverseSubsetMutation implements MutationMethod {

	@Override
	public void mutate(GAParameters parameters, Tour tour) {
		
		// Select two random indices
		int rand1 = (int) (Math.random() * tour.getSize());
		int rand2 = (int) (Math.random() * tour.getSize());
		
		// Use them to create subset indices
		int startIndex = (rand1 < rand2 ? rand1 : rand2);
		int endIndex = (rand1 > rand2 ? rand1 : rand2);
        
        for (int i=0; i < tour.getSize(); i++) {
        	
        	if (Math.random() >= parameters.getMutationRate()) {
        		continue;
        	}
        	
        	for (int j=0; j<tour.getSize(); j++) {
        		
        		if (j >= startIndex && j < endIndex) {
        			
        			int indexA = startIndex;
        			int indexB = endIndex - (endIndex - (j - startIndex));
        			
                	City cityA = tour.getCity(indexA);
                	City cityB = tour.getCity(indexB);
                	
        	        tour.setCity(indexA, cityB);
        	        tour.setCity(indexB, cityA);
        			
        		}
        		
        	}
        	
        }
        
	}

}
