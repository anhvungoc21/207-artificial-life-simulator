package lifesim;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * The Population class simulates the Population of an artificial life simulator
 */
public class Population {
	
	/**
	 * Constant for the number of neighbors that receive energy from a reproduction 
	 */
	private static final int NUM_NEIGHBORS = 8;
	
	/**
	 * A HashMap to keep track of each organism counts
	 */
	private Map<String, Integer> counts;
	
	/**
	 * ArrayList to keep all organisms in this Population
	 */
	private List<Organism> organisms;
	
	/**
	 * Constructor for a Population object
	 * @param counts A Map of organism names (String) and their counts (Integer)
	 */
	public Population(Map<String, Integer> counts) {
		
		this.counts = counts;	
		this.organisms = new ArrayList<>();
		
		// Create organisms based on their names and counts
		for (String org: counts.keySet()) {
			int orgCounts = counts.get(org);
			
			if (org.equals(Organism.COOP_NAME)) {
				for (; orgCounts > 0; orgCounts--) {
					this.organisms.add(new Cooperator());
				}
			} else if (org.equals(Organism.DEF_NAME)) {
				for (; orgCounts > 0; orgCounts--) {
					this.organisms.add(new Defector());
				}
			} else if (org.equals(Organism.PAR_COOP_NAME)) {
				for (; orgCounts > 0; orgCounts--) {
					this.organisms.add(new PartialCooperator());
				}
			} else { // Throw exception if name is invalid
				throw new IllegalArgumentException("Invalid organism name provided.");
			}
			
		} // for
		
	} // constructor
	
	/**
	 * Updates all Organisms in this Population
	 */
	public void update() {
		Random rand = new Random();
		
		for (int i = 0; i < this.organisms.size(); i++) {
			Organism org = this.organisms.get(i);
			
			// Updates the Organism by calling its update() method
			org.update();
			
			// Checks if the Organism cooperates
			if (org.cooperates()) {
				org.decrementEnergy(); // this Organism loses 1 energy
				
				// Randomly selects 8 neighbors
				Set<Integer> indices = new HashSet<>();
				while (indices.size() != NUM_NEIGHBORS) {
					int randInd = rand.nextInt();
					
					// Ensures that chosen index is not the current `org`
					if (!(randInd == i)) {
						indices.add(randInd);
					}
				}
				
				// Spreads energy to the neighbors
				for (Integer index: indices) {
					this.organisms.get(index).incrementEnergy();
				}
			}
			
			// Checks if the Organism reproduces
			if (org.getEnergy() >= Organism.REP_ENERGY_LEVEL) {
				int randInd = rand.nextInt();
				Organism oldOrg = this.organisms.get(randInd);
				Organism newOrg = org.reproduce();
				
				// Depletes energy of reproducing Organism
				while (oldOrg.getEnergy() != 0) {
					oldOrg.decrementEnergy();
				}
				
				// Replace random Organism in this Population
				this.organisms.set(rand.nextInt(), newOrg);

				
				// Updates Map of Organism counts
				this.counts.put(oldOrg.getType(), this.counts.get(oldOrg.getType()) - 1);
				this.counts.put(newOrg.getType(), this.counts.get(newOrg.getType()) + 1);
			}
 			
		} // for
		
	} // update
	
	/**
	 * Calculates the cooperation mean of all Organisms in this Population
	 * @return Double value of the cooperation mean of all Organisms
	 */
	public double calculateCooperationMean() {
		double sumCoop = 0;
		for (Organism org: this.organisms) {
			sumCoop += org.getCooperationProbability();
		}
		
		return sumCoop / this.organisms.size();
	}
	
	/**
	 * Gets a Map of each Organism and their count in this Population
	 * @return Map of Organism name (String) and their count (Integer)
	 */
	public Map<String, Integer> getPopulationCounts() {
		return this.counts;
	}
}
