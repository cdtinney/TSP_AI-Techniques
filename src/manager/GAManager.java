package manager;

import java.util.ArrayList;
import java.util.List;

import algorithm.AlgorithmListener;
import algorithm.GeneticAlgorithm;
import algorithm.ObservableAlgorithm;
import generator.RandomTourGenerator;
import model.City;
import model.Population;
import model.Tour;

public class GAManager implements ObservableAlgorithm {
	
	private static final int NUM_GENERATIONS 	= 100;
	private static final int DELAY 				= 100;
	
	private List<City> cities;
	private Population currentPopulation;
	
	private List<AlgorithmListener> listeners = new ArrayList<AlgorithmListener>();
	
	public void init() {
		generateCities();
		generateInitPopulation();
	}
	
	public void run() {
        
        System.out.println("Initial distance: " + currentPopulation.getFittest().getDistance());
        System.out.println("Generation #0:" + currentPopulation.getFittest() + "\n");
        
        // Evolve population for certain number of generations
        for (int i = 0; i < NUM_GENERATIONS; i++) {
        	
        	currentPopulation = GeneticAlgorithm.evolve(currentPopulation);
            System.out.println("Generation #" + (i + 1) + ": " + currentPopulation.getFittest());
            notifyListeners();
            
            try {
                Thread.sleep(DELAY);             
                
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
                
            }
            
        }

        System.out.println("\nFinal distance: " + currentPopulation.getFittest().getDistance());
        System.out.println("Solution: " + currentPopulation.getFittest());
		
	}

	public List<City> getCities() {
		return cities;
	}
	
	public Population getCurrentPopulation() {
		return currentPopulation;
	}
	
	private void generateCities() {
		
		cities = new ArrayList<City>();
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
        
	}
	
	private void generateInitPopulation() {
		
        // Generate random tours
        RandomTourGenerator gen = new RandomTourGenerator();
        List<Tour> tours = gen.generate(cities.size(), cities);

        // Initialize population
        currentPopulation = new Population(tours);
        
        // Notify any listeners
        notifyListeners();
		
	}
	
	private void notifyListeners() {
		listeners.stream().forEach(l -> l.onChange(currentPopulation.getFittest()));
	}

	@Override
	public void addListener(AlgorithmListener listener) {
		listeners.add(listener);
	}

	@Override
	public void removeListener(AlgorithmListener listener) {
		listeners.remove(listener);
	}

}
