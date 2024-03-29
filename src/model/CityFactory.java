package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import configuration.TestConfig;

public class CityFactory {
	
	public static List<City> generate() {
		
		if (TestConfig.CIRCLE_CITIES) {
			return generateCircle(TestConfig.NUM_CITIES);
		}
		
		List<City> cities = new ArrayList<City>();
		City city = new City(60, 200);
		cities.add(city);
		City city2 = new City(180, 200);
		cities.add(city2);
		City city3 = new City(80, 180);
		cities.add(city3);
		City city4 = new City(140, 180);
		cities.add(city4);
		City city5 = new City(20, 160);
		cities.add(city5);
		City city6 = new City(100, 160);
		cities.add(city6);
		City city7 = new City(200, 160);
		cities.add(city7);
		City city8 = new City(140, 140);
		cities.add(city8);
		City city9 = new City(40, 120);
		cities.add(city9);
		City city10 = new City(100, 120);
		cities.add(city10);
		City city11 = new City(180, 100);
		cities.add(city11);
		City city12 = new City(60, 80);
		cities.add(city12);
		City city13 = new City(120, 80);
		cities.add(city13);
		City city14 = new City(180, 60);
		cities.add(city14);
		City city15 = new City(20, 40);
		cities.add(city15);
		City city16 = new City(100, 40);
		cities.add(city16);
		City city17 = new City(200, 40);
		cities.add(city17);
		City city18 = new City(20, 20);
		cities.add(city18);
		City city19 = new City(60, 20);
		cities.add(city19);
		City city20 = new City(160, 20);
		cities.add(city20);
		return cities;

	}
	
	public static List<City> generateCircle(int numCities) {

		int center = 100;
		int radius = 75;
		
		List<City> cities = new ArrayList<City>();
		for (int i=0; i<numCities; i++) {
			
		    double x = (center + radius * Math.cos(2 * Math.PI * i / numCities));
		    double y = (center + radius * Math.sin(2 * Math.PI * i / numCities));
			City city = new City((int) x, (int) y);
			cities.add(city);
		}
		
		Collections.shuffle(cities);
		
		return cities;

	}

}
