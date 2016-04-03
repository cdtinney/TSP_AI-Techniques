package algorithm;

public interface ObservableAlgorithm {
	
	public void addListener(AlgorithmListener listener);
	public void removeListener(AlgorithmListener listener);

}
