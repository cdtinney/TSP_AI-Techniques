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
	private static final int DELAY 			= 50;
	private static final boolean USE_DELAY 	= false;
	
	// Model information
	private List<City> cities;
	private Population currentPopulation;
	private GeneticAlgorithm geneticAlgorithm;
	
	// Listeners to be notified when the population changes
	private List<AlgorithmListener> listeners = new ArrayList<AlgorithmListener>();
	
	public GAManager(GeneticAlgorithm geneticAlgorithm, List<AlgorithmListener> listeners, boolean init) {		
		this.geneticAlgorithm = geneticAlgorithm;
		this.listeners.addAll(listeners);
		
		if (init) {
			init();
		}
		
	}
	
	public void init() {
		
		cities = CityFactory.generate();
		currentPopulation = PopulationFactory.generate(geneticAlgorithm.getParameters().getPopulationSize(), cities);
		
		notifyListeners();
		
	}
	
	public int getCurrentDistance() {
		return currentPopulation.getFittest().getDistance();
	}
	
	public GAParameters getParameters() {
		return geneticAlgorithm.getParameters();
	}
	
	public void run() {
        
        for (int i = 0; i < geneticAlgorithm.getParameters().getNumGenerations(); i++) {     
        	
        	currentPopulation = geneticAlgorithm.evolve(currentPopulation);            
            notifyListeners();
            
            if (USE_DELAY) {
                sleep();
            }
            
        }
		
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
	
	public void addListeners(List<AlgorithmListener> listeners) {
		this.listeners.addAll(listeners);
	}

	@Override
	public void removeListener(AlgorithmListener listener) {
		listeners.remove(listener);
	}

}
