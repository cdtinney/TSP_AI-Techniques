package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tour {
	
	private List<City> cities;
	private int size;
	
	// GA parameters
	private double fitness = 0;
	private int distance = 0;
	
	public Tour(int size) {
		this.size = size;
		this.cities = new ArrayList<City>(Collections.nCopies(size, null));
	}
	
	public Tour(List<City> tour) {
		this(tour.size());
		this.cities = tour;
	}
	
	public Tour(Tour other) {
		this(other.getSize());
		
		for (int i=0; i<size; i++) {
			setCity(i, new City(other.getCity(i).getX(), other.getCity(i).getY()));
		}
		
	}
	
	public int getSize() {
		return size;
	}

	public List<City> getCities() {
		return cities;
	}

    public City getCity(int position) {
        return cities.get(position);
    }

	public int getDistance() {
		
		if (distance == 0) {
			setDistance();
		}
		
		return distance;
		
	}
	
	public double getFitness() {
		
		if (fitness == 0) {
			fitness = 1 / ((double) getDistance());
		}
		
		return fitness;
		
	}

    public void setCity(int position, City city) {
        cities.set(position, city);
        resetParameters();
    }
    
    public void setDistance() {
    	
    	if (distance != 0) {
    		return;
    	}
        	
        int sum = 0;
        for (int i=0; i<cities.size(); i++) {
        	
        	City from = cities.get(i);
        	City to = cities.get((i + 1) % cities.size());
        	
        	if (from != null && to != null) {
        		sum += from.distanceTo(to);
        	}
        	
        }
        
        distance = sum;
    	
    }
    
    public boolean contains(City city){
        return cities.contains(city);
    }
    
    private void resetParameters() {
    	distance = 0;
    	fitness = 0;
    }
    
    @Override
    public String toString() {
    	
        String str = "|";
        for (int i = 0; i < size; i++) {
        	str += cities.get(i) + "|";
        }
        
        return str;
        
    }

	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cities == null) ? 0 : cities.hashCode());
		return result;
		
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tour other = (Tour) obj;
		if (cities == null) {
			if (other.cities != null)
				return false;
		} else {			
			for (int i=0; i<cities.size(); i++) {
				if (!cities.get(i).equals(other.cities.get(i))) {
					return false;
				}
			}
		}
		
		return true;
		
	}
	
}
