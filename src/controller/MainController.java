package controller;

import algorithm.annealing.test.SATestManager;
import algorithm.genetic.test.GATestManager;
import application.MainApplication;
import javafx.application.Platform;
import view.MainView;

public class MainController {

	// View
	private MainView view = new MainView(MainApplication.APP_WIDTH, MainApplication.APP_HEIGHT);
	
	// Model Managers
	private GATestManager gaTestManager = new GATestManager();
	private SATestManager saTestManager = new SATestManager();
	
	public void init() {
		
		saTestManager.addListener(tour -> {

			// Run on the JavaFX thread
			Platform.runLater(() -> {
				view.updateTour(tour);			
				view.updateText("Distance: " + String.valueOf(tour.getDistance()));
			});
			
		});
		
		gaTestManager.addListener(tour -> {
			
			// Run on the JavaFX thread
			Platform.runLater(() -> {
				view.updateTour(tour);			
				view.updateText("Distance: " + String.valueOf(tour.getDistance()));
			});
			
		});
		
	}
	
	public void run() {		
		
		saTestManager.test();
		gaTestManager.test();
		
	}
	
	public MainView getView() {
		return view;
	}

}
