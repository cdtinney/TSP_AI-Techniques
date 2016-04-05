package algorithm.genetic.mutation;

public class MutationFactory {
	
	public static MutationMethod getDefault() {
		return new SwapMutation();
	}

}
