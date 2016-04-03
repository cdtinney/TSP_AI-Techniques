package controller;

import algorithm.genetic.test.GATestManager;
import application.MainApplication;
import javafx.application.Platform;
import view.MainView;

public class MainController {

	// View
	private MainView view = new MainView(MainApplication.APP_WIDTH, MainApplication.APP_HEIGHT);
	
	// Model
	private GATestManager gaTestManager = new GATestManager();
	
	public void init() {
		
		gaTestManager.addListener(tour -> {
			
			// Run on the JavaFX thread
			Platform.runLater(() -> {
				view.updateTour(tour);			
				view.updateText("Distance: " + String.valueOf(tour.getDistance()));
			});
			
		});
		
	}
	
	public void run() {		
		
		gaTestManager.test();
		
	}
	
	public MainView getView() {
		return view;
	}

}
