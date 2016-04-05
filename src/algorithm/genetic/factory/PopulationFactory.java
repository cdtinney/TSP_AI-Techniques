package algorithm.genetic.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.City;
import model.Population;
import model.Tour;

public class PopulationFactory {
	
	public static Population generate(int numTours, int numCities) {
		
		List<City> cities = CityFactory.generateCircle(numCities);
        return new Population(generateRandom(numTours, cities));		
		
	}
	
	private static List<Tour> generateRandom(int num, List<City> cities) {
		
		List<Tour> result = new ArrayList<Tour>();
		for (int i=0; i<num; i++) {
			
			List<City> randomCities = new ArrayList<City>(cities);
			
			// TODO - Uncomment if not testing
			Collections.shuffle(randomCities);
			
			result.add(new Tour(randomCities));
			
		}
		
		return result;
		
	}

}
