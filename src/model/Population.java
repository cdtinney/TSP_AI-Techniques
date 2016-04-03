package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class Population {
	
	private List<Tour> tours;	
	private int size;
	
	public Population(int size) {
		this.size = size;
		tours = new ArrayList<Tour>(Collections.nCopies(size, null));
	}
	
	public Population(List<Tour> initialTours) {
		this(initialTours.size());
		tours = initialTours;
	}
	
	public int getSize() {
		return size;
	}
	
	public Tour getTour(int index) {
		return tours.get(index);
	}
	
	public void setTour(int index, Tour tour) {
		tours.set(index, tour);
	}
	
	public void addTour(Tour tour) {
		tours.add(tour);
	}
	
	public Tour getFittest() {		
		
		 return tours.stream()
	            .filter(Objects::nonNull)
                 .min((t1, t2) -> Double.compare(t1.getFitness(), t2.getFitness()))
                 .get();	
		 
	}

}
