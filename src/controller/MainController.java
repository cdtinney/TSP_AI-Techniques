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
		view.updateCities(gaManager.getCities());
		
	}
	
	public MainView getView() {
		return view;
	}

}
