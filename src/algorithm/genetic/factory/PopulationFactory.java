package algorithm.genetic.factory;

import java.util.List;

import model.City;
import model.Population;
import model.generator.RandomTourGenerator;

public class PopulationFactory {
	
	public static Population generate(List<City> cities) {
		
        RandomTourGenerator gen = new RandomTourGenerator();
        return new Population(gen.generate(cities.size(), cities));		
		
	}

}
