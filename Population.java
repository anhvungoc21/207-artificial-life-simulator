package lifesim;

import java.util.HashSet;
import java.util.Map;
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
	 * Array to keep all organisms in this Population
	 */
	private Organism[] organisms;
	
	/**
	 * Constructor for a Population object
	 * @param counts A Map of organism names (String) and their counts (Integer)
	 */
	public Population(Map<String, Integer> counts) {
		
		// Calculate population and initialize array
		int popSize = 0;
		for (Integer orgPop: counts.values()) {
			popSize += orgPop;
		}
		this.organisms = new Organism[popSize];
		this.counts = counts;
		
		// Create organisms based on their names and counts
		int index = 0;
		for (String org: counts.keySet()) {
			int orgCounts = counts.get(org);
			
			if (org.equals(Organism.COOP_NAME)) {
				for (; orgCounts > 0; orgCounts--) {
					index = spawnOrganism(new Cooperator(), index);
				}
			} else if (org.equals(Organism.DEF_NAME)) {
				for (; orgCounts > 0; orgCounts--) {
					index = spawnOrganism(new Defector(), index);
				}
			} else if (org.equals(Organism.PAR_COOP_NAME)) {
				for (; orgCounts > 0; orgCounts--) {
					index = spawnOrganism(new PartialCooperator(), index);
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
		
		for (int i = 0; i < this.organisms.length; i++) {
			Organism org = this.organisms[i];
			
			// Updates the Organism by calling its update() method
			org.update();
			
			// Checks if the Organism cooperates
			if (org.cooperates()) {
				org.decrementEnergy(); // this Organism loses 1 energy
				
				// Randomly selects 8 neighbors that's not the current Organism
				Set<Integer> indices = new HashSet<>();
				while (indices.size() != NUM_NEIGHBORS) {
					int randInd = getRandomIndex();
					if (!(randInd == i)) {
						indices.add(randInd);
					}
				}
				
				// Spreads energy to the neighbors
				for (Integer index: indices) {
					this.organisms[index].incrementEnergy();
				}
			}
			
			// Checks if the Organism reproduces
			if (org.getEnergy() >= Organism.REP_ENERGY_LEVEL) {
				int randInd = getRandomIndex();
				Organism oldOrg = this.organisms[randInd];
				Organism newOrg = org.reproduce();
				
				// Depletes energy of reproducing Organism
				while (oldOrg.getEnergy() != 0) {
					oldOrg.decrementEnergy();
				}
				
				// Replace a random Organism in this Population
				this.organisms[randInd] = newOrg;

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
		
		return sumCoop / this.organisms.length;
	}
	
	/**
	 * Gets a Map of each Organism and their count in this Population
	 * @return Map of Organism name (String) and their count (Integer)
	 */
	public Map<String, Integer> getPopulationCounts() {
		return this.counts;
	}

	/**
	 * Adds a new Organism `org` to this Population at `index`.
	 * Increments index and returns it.
	 * @param org an Organism
	 * @param index index at which to put the new Organism
	 */
	private int spawnOrganism(Organism org, int index) {
		this.organisms[index] = org;
		return index + 1;
	}
	
	/**
	 * Gets a random index of this Population's organisms array
	 * @return Integer of a random index
	 */
	private int getRandomIndex() {
		return (int) (Math.random() * this.organisms.length);
	}
}
