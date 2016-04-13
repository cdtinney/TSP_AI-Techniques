package configuration;

public class TestConfig {
	
	public static boolean 	DISPLAY_UI = true;			// If set to false, UI will not be launched
	
	public static boolean 	CIRCLE_CITIES = false;		// If set to true, algorithms will be run using cities shaped in a circle
	public static int 		NUM_CITIES = 20;			// Number of cities in the non-circle city configuration
	
	public static int 		NUM_TEST_TRIALS = 20;		// Number of trials to run for each algorithm to determine an average
	
	public static boolean 	USE_RANDOMNESS = false;		// If false, a constant random seed will be used for all tests
	
	public static boolean 	TEST_GA = true;				// If true, GA tests will be run
	public static boolean	TEST_SA = true;				// If true, SA tests will be run
	
	public static boolean	TEST_GA_DEFAULT 		= true;
	public static boolean	TEST_GA_OPTIMAL 		= true;
	public static boolean	TEST_GA_GROUPS 			= true;
	public static boolean	TEST_GA_POPULATION_SIZE = true;
	public static boolean	TEST_GA_CROSSOVER 		= true;
	public static boolean	TEST_GA_MUTATION 		= true;
	public static boolean	TEST_GA_MUTATION_RATE 	= true;
	public static boolean	TEST_GA_NUM_GENERATIONS = true;

	public static boolean	TEST_SA_DEFAULT 				= true;
	public static boolean	TEST_SA_OPTIMAL 				= true;
	public static boolean	TEST_SA_TEMPERATURE_SCHEDULE 	= true;
	public static boolean	TEST_SA_NEIGHBOR_GENERATION 	= true;

}
