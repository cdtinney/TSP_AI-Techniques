package algorithm.annealing.test;

import algorithm.annealing.SAParameters;

public class SAResult {
	
	private SAParameters parameters;
	private int initialDistance;
	private int finalDistance;
	
	public SAResult(SAParameters parameters, int initialDistance, int finalDistance) {
		this.parameters = parameters;
		this.initialDistance = initialDistance;
		this.finalDistance = finalDistance;
	}

	public SAParameters getParameters() {
		return parameters;
	}

	public int getInitialDistance() {
		return initialDistance;
	}

	public int getFinalDistance() {
		return finalDistance;
	}

}
