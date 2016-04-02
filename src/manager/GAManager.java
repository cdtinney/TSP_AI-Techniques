package manager;

import java.util.ArrayList;
import java.util.List;

import algorithm.GeneticAlgorithm;
import generator.RandomTourGenerator;
import model.City;
import model.Population;
import model.Tour;

public class GAManager {
	
	public static void run() {

		// Create a list of cities
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
        
        // Generate random tours
        RandomTourGenerator gen = new RandomTourGenerator();
        List<Tour> tours = gen.generate(cities.size(), cities);

        // Initialize population
        Population pop = new Population(tours);
        System.out.println("Initial distance: " + pop.getFittest().getDistance());
        System.out.println("Generation #0:" + pop.getFittest() + "\n");

        // Evolve population for 100 generations
        for (int i = 0; i < 100; i++) {
            pop = GeneticAlgorithm.evolve(pop);
            System.out.println("Generation #" + (i + 1) + ": " + pop.getFittest());
        }

        // Print final results
        System.out.println("Finished");
        System.out.println("Final distance: " + pop.getFittest().getDistance());
        System.out.println("Solution:");
        System.out.println(pop.getFittest());
		
	}

}
