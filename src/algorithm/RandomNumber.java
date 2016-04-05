package algorithm;

import java.util.Random;

public class RandomNumber {
	
	public static final boolean TEST 		= true;
	
	private static final int DEFAULT_SEED 	= 123456789;
	private static final Random RANDOM 		= new Random(DEFAULT_SEED);
	
	public static double random() {
		return TEST ? RANDOM.nextDouble() : Math.random();
	}
	
}
