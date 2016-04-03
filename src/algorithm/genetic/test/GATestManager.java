package algorithm.genetic.test;

import algorithm.AlgorithmListener;
import algorithm.genetic.GAManager;
import algorithm.genetic.factory.GAFactory;

public class GATestManager {
	
	private GAManager currentGAManager = new GAManager(GAFactory.getDefault());
	
	public void init() {
		currentGAManager.init();	
	}

	public void addListener(AlgorithmListener listener) {
		currentGAManager.addListener(listener);
	}

	public void run() {
		currentGAManager.run();
	}

}
