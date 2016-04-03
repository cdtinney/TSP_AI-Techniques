package algorithm.genetic;

import java.util.ArrayList;
import java.util.List;

import algorithm.AlgorithmListener;
import algorithm.ObservableAlgorithm;
import algorithm.genetic.factory.CityFactory;
import algorithm.genetic.factory.PopulationFactory;
import model.City;
import model.Population;

public class GAManager implements ObservableAlgorithm {
	
	// Thread delay between iterations (ms)
	private static final int DELAY = 50;
	
	// Model information
	private List<City> cities;
	private Population currentPopulation;
	private GeneticAlgorithm geneticAlgorithm;
	
	// Listeners to be notified when the population changes
	private List<AlgorithmListener> listeners = new ArrayList<AlgorithmListener>();
	
	public GAManager(GeneticAlgorithm geneticAlgorithm) {		
		this.geneticAlgorithm = geneticAlgorithm;
	}
	
	public void init() {
		
		cities = CityFactory.generate();
		currentPopulation = PopulationFactory.generate(cities);
		
		notifyListeners();
		
	}
	
	public void run() {
        
        System.out.println("Initial distance: " + currentPopulation.getFittest().getDistance());
        
        for (int i = 0; i < geneticAlgorithm.getParameters().getNumGenerations(); i++) {     
        	
        	currentPopulation = geneticAlgorithm.evolve(currentPopulation);            
            notifyListeners();
            sleep();
            
        }

        System.out.println("Final distance: " + currentPopulation.getFittest().getDistance());
		
	}

	public List<City> getCities() {
		return cities;
	}
	
	public Population getCurrentPopulation() {
		return currentPopulation;
	}
	
	private void sleep() {
        
        try {
        	Thread.sleep(DELAY);             
            
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
            
        }
		
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
