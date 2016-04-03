package view;

import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.City;
import model.Tour;

public class MapCanvas extends Canvas {
	
	private static final int CANVAS_WIDTH 	= 500;
	private static final int CANVAS_HEIGHT	= 500;
	
	private final int CITY_SIZE 			= 10;
	private final int PATH_WIDTH 			= 2;
	
	private GraphicsContext gc;
	
	public MapCanvas() {
		super(CANVAS_WIDTH, CANVAS_HEIGHT);
		gc = getGraphicsContext2D();
		gc.setLineWidth(PATH_WIDTH);
	}

	public void updateCities(List<City> cities) {
		
		cities.stream().forEach(c -> {
			drawCity(c, null);
		});
		
	}
	
	public void updateTour(Tour tour) {

		// Clear the old canvas
		clear();
		
		List<City> cities = tour.getCities();
		for (int i=0; i<cities.size(); i++) {			
			
			City start = cities.get(i);
			City end = cities.get((i + 1) % cities.size());
			
			// Multiply by 2 to spread the cities out more, and center
			// endpoints on the middle of the circles
			int startX = start.getX()*2 + CITY_SIZE/2;
			int startY = start.getY()*2 + CITY_SIZE/2;
			int endX = end.getX()*2 + CITY_SIZE/2;
			int endY = end.getY()*2 + CITY_SIZE/2;
			
			drawCity(start, i);
			gc.strokeLine(startX, startY, endX, endY);
			
		}
		
	}
	
	private void drawCity(City city, Integer num) {

		gc.setFill(Color.BLACK);
		gc.fillOval(city.getX()*2, city.getY()*2, CITY_SIZE, CITY_SIZE);
		
		if (num != null) {
			gc.setFill(Color.RED);
			gc.setFont(Font.font("Verdana", 20));
			gc.fillText(String.valueOf(num), city.getX()*2, city.getY()*2);
		}
		
	}
	
	private void clear() {
		gc.clearRect(0, 0, getWidth(), getHeight());		
	}

}
