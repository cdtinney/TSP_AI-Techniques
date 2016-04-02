package view;

import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import model.City;

public class MainView extends Scene {
	
	private BorderPane root;
	
	private MapCanvas mapCanvas;
	
	public MainView(int width, int height) {
		super(new BorderPane(), width, height);		
		
		root = (BorderPane) getRoot();
		
		addViews();
		
	}
	
	public void updateCities(List<City> cities) {
		mapCanvas.updateCities(cities);
	}
	
	private void addViews() {
		root.setCenter(mapCanvas = new MapCanvas());		
	}
	
}
