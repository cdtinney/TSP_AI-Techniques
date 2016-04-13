package algorithm.genetic.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.City;
import model.CityFactory;
import model.Population;
import model.Tour;

public class PopulationFactory {
	
	private static final boolean TEST = true;
	
	public static Population generate(int numTours, int numCities) {
		
		List<City> cities = CityFactory.generateCircle(numCities);
        return new Population(generateRandom(numTours, cities));		
		
	}
	
	private static List<Tour> generateRandom(int num, List<City> cities) {
		
		List<Tour> result = new ArrayList<Tour>();
		for (int i=0; i<num; i++) {
			
			List<City> randomCities = new ArrayList<City>(cities);
			
			// If we're not testing, don't shuffle the order of the cities. This will fix the 
			// initial population for all instances.
			if (!TEST) {
				Collections.shuffle(randomCities);
			}
			
			result.add(new Tour(randomCities));
			
		}
		
		return result;
		
	}

}
