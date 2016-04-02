package model;

import java.util.ArrayList;
import java.util.List;

public class Tour {
	
	private List<City> tour;
	private int size;
	
	// GA parameters
	private double fitness;
	private int distance;
	
	public Tour(int size) {
		this.size = size;
		this.tour = new ArrayList<City>();
	}
	
	public Tour(List<City> tour) {
		this(tour.size());
		this.tour = tour;
		setParameters();
	}
	
	public int getSize() {
		return size;
	}

	public List<City> getTour() {
		return tour;
	}

    public City getCity(int position) {
        return tour.get(position);
    }

	public int getDistance() {
		return distance;
	}
	
	public double getFitness() {
		return fitness;
	}

    public void setCity(int position, City city) {
        tour.set(position, city);
        setParameters();
    }
    
    public void setDistance() {
    	
    	if (distance != 0) {
    		return;
    	}
        	
        int sum = 0;
        for (int i=0; i<tour.size(); i++) {
        	
        	City from = tour.get(i);
        	City to = tour.get((i + 1) % tour.size());
        	sum += from.distanceTo(to);
        	
        }
        
        distance = sum;
    	
    }
    
    public void setFitness() {
    	
    	if (fitness != 0) {
    		return;
    	}
    	
    	fitness = 1 / distance;
    	
    }
    
    public boolean contains(City city){
        return tour.contains(city);
    }
    
    private void setParameters() {
    	setDistance();
    	setFitness();
    }
	
}
