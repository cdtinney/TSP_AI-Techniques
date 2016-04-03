package algorithm.genetic.test;

import algorithm.genetic.GAParameters;

public class GAResult {
	
	private GAParameters parameters;
	private int initialDistance;
	private int finalDistance;
	
	public GAResult(GAParameters parameters, int initialDistance, int finalDistance) {
		this.parameters = parameters;
		this.initialDistance = initialDistance;
		this.finalDistance = finalDistance;
	}

	public GAParameters getParameters() {
		return parameters;
	}

	public int getInitialDistance() {
		return initialDistance;
	}

	public int getFinalDistance() {
		return finalDistance;
	}

}
