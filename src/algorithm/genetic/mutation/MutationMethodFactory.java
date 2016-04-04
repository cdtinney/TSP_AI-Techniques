package algorithm.genetic.mutation;

public class MutationMethodFactory {
	
	public static MutationMethod getDefault() {
		return new SwapMutation();
	}

}
