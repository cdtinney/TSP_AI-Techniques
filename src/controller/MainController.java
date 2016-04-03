package controller;

import application.MainApplication;
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
			view.updateTour(tour);			
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
