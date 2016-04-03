package algorithm.genetic.mutation;

import algorithm.genetic.GAParameters;
import model.Tour;

public interface MutationMethod {

	public void mutate(GAParameters parameters, Tour tour);

}
