package algorithm;

import java.util.Random;

public class RandomManager {
	
	public static final Random RANDOM = new Random(123456789);
	
	public static double random() {
		return RANDOM.nextDouble();
	}

}
