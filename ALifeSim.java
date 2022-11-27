package lifesim;

import java.util.HashMap;
import java.util.Map;

/**
 * The ALifeSim class simulates artificial life
 */
public class ALifeSim {
	
	/**
	 * Main method for running the main ALifeSim program
	 */
	public static void main(String[] args) {
		try {
			int iters = Integer.parseInt(args[0]);
			int coopCount = Integer.parseInt(args[1]);
			int defCount = Integer.parseInt(args[2]);
			int parCoopCount = Integer.parseInt(args[3]);
			
			// Initialize Population with appropriate counts for each Organism type
			Map<String, Integer> counts = new HashMap<>();
			counts.put(Organism.COOP_NAME, coopCount);
			counts.put(Organism.DEF_NAME, defCount);
			counts.put(Organism.PAR_COOP_NAME, parCoopCount);
			Population population = new Population(counts);
			
			// Simulate life by carrying out iterations
			for (int i = 0; i < iters; i++) {
				population.update();
			}
			
			// Report final result of simulation
			counts = population.getPopulationCounts();
			System.out.printf("After %d ticks:%n", iters);
			System.out.printf("Cooperators = %d%n", counts.get(Organism.COOP_NAME));
			System.out.printf("Defectors = %d%n", counts.get(Organism.DEF_NAME));
			System.out.printf("Partial = %d%n", counts.get(Organism.PAR_COOP_NAME));
			System.out.printf("%nMean Cooperation Probability = %f%n", population.calculateCooperationMean());

		} catch(ArrayIndexOutOfBoundsException | NumberFormatException e) {
			System.out.println("Invalid program arguments.");
			System.out.println("Usage:");
			System.out.println("  java ALifeSim <#/iterations> <#/cooperators> <#/defectors> <#/partial cooperators>");		
		}

	}
	
}
