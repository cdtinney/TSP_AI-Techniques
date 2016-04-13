package util;

import java.util.Random;

import configuration.TestConfig;

public class RandomNumber {
	
	private static final int DEFAULT_SEED 	= 123456789;
	private static final Random RANDOM 		= new Random(DEFAULT_SEED);
	
	public static double random() {
		return !TestConfig.USE_RANDOMNESS ? RANDOM.nextDouble() : Math.random();
	}
	
}
