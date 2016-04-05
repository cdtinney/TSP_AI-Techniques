package controller;

import algorithm.annealing.SimulatedAnnealing;
import algorithm.annealing.neighbor.RandomSwap;
import algorithm.genetic.test.GATestManager;
import application.MainApplication;
import javafx.application.Platform;
import view.MainView;

public class MainController {

	// View
	private MainView view = new MainView(MainApplication.APP_WIDTH, MainApplication.APP_HEIGHT);
	
	// GA Model
	private GATestManager gaTestManager = new GATestManager();
	
	// SA Model - TODO
	private SimulatedAnnealing sa = new SimulatedAnnealing(new RandomSwap());
	
	public void init() {
		
//		gaTestManager.addListener(tour -> {
//			
//			// Run on the JavaFX thread
//			Platform.runLater(() -> {
//				view.updateTour(tour);			
//				view.updateText("Distance: " + String.valueOf(tour.getDistance()));
//			});
//			
//		});
		
	}
	
	public void run() {		
		
		sa.run();
		//gaTestManager.test();
		
	}
	
	public MainView getView() {
		return view;
	}

}
