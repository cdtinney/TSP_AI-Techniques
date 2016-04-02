package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tour {
	
	private List<City> tour;
	private int size;
	
	// GA parameters
	private double fitness = 0;
	private int distance = 0;
	
	public Tour(int size) {
		this.size = size;
		this.tour = new ArrayList<City>(Collections.nCopies(size, null));
	}
	
	public Tour(List<City> tour) {
		this(tour.size());
		this.tour = tour;
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
		
		if (distance == 0) {
			setDistance();
		}
		
		return distance;
		
	}
	
	public double getFitness() {
		
		if (fitness == 0) {
			setFitness();
		}
		
		return fitness;
		
	}

    public void setCity(int position, City city) {
        tour.set(position, city);
        resetParameters();
    }
    
    public void setDistance() {
    	
    	if (distance != 0) {
    		return;
    	}
        	
        int sum = 0;
        for (int i=0; i<tour.size(); i++) {
        	
        	City from = tour.get(i);
        	City to = tour.get((i + 1) % tour.size());
        	
        	if (from != null && to != null) {
        		sum += from.distanceTo(to);
        	}
        	
        }
        
        distance = sum;
    	
    }
    
    public void setFitness() {
    	
    	if (fitness != 0 || distance == 0) {
    		return;
    	}
    	
    	fitness = 1 / distance;
    	
    }
    
    public boolean contains(City city){
        return tour.contains(city);
    }
    
    private void resetParameters() {
    	distance = 0;
    	fitness = 0;
    }
    
    @Override
    public String toString() {
    	
        String str = "|";
        for (int i = 0; i < size; i++) {
        	str += tour.get(i) + "|";
        }
        
        return str;
        
    }
	
}
