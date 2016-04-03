package model.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.City;
import model.Tour;

public class RandomTourGenerator implements TourGenerator {
	
	@Override
	public List<Tour> generate(int num, List<City> cities) {
		
		List<Tour> result = new ArrayList<Tour>();
		for (int i=0; i<num; i++) {
			
			List<City> randomCities = new ArrayList<City>(cities);
			//Collections.shuffle(randomCities);
			result.add(new Tour(randomCities));
			
		}
		
		return result;
		
	}

}
