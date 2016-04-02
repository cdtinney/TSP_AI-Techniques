package view;

import java.util.List;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.City;
import model.Tour;

public class MapCanvas extends Canvas {
	
	private GraphicsContext gc;
	
	public MapCanvas() {
		super(400, 400);
		gc = getGraphicsContext2D();
		gc.setFill(Color.BLACK);
	}

	public void updateCities(List<City> cities) {
		
		cities.stream().forEach(c -> {
			gc.fillOval(c.getX(), c.getY(), 10, 10);
		});
		
	}
	
	public void updateTour(Tour tour) {
		// TODO
	}

}
