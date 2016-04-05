package algorithm.annealing.neighbor;

public class NeighborGeneratorFactory {
	
	public static NeighborGenerator getDefault() {
		return new NeighborSwap();
	}

}
