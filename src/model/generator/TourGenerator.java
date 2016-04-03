package model.generator;

import java.util.List;

import model.City;
import model.Tour;

public interface TourGenerator {
	
	public List<Tour> generate(int num, List<City> cities);

}
