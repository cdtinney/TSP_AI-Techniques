package controller;

import application.MainApplication;
import javafx.application.Platform;
import manager.GAManager;
import view.MainView;

public class MainController {

	// View
	private MainView view = new MainView(MainApplication.APP_WIDTH, MainApplication.APP_HEIGHT);
	
	// Model
	private GAManager gaManager = new GAManager();
	
	public void init() {
		
		gaManager.init();		
		gaManager.addListener(tour -> {
			
			// Run on the JavaFX thread
			Platform.runLater(() -> {
				view.updateTour(tour);			
				view.updateText("Distance: " + String.valueOf(tour.getDistance()));
			});
			
		});
		
		view.updateCities(gaManager.getCities());
		
	}
	
	public void run() {		
		gaManager.run();		
	}
	
	public MainView getView() {
		return view;
	}

}
