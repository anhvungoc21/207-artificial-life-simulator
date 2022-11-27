package lifesim;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Population {
	
	/**
	 * Constants for possible names of organisms in a Population
	 */
	private static final String coopName = "Cooperator";
	private static final String defName = "Defector";
	private static final String parCoopName = "PartialCooperator";
	
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
			
			if (org.equals(coopName)) {
				for (; orgCounts > 0; orgCounts--) {
					this.organisms.add(new Cooperator());
				}
			} else if (org.equals(defName)) {
				for (; orgCounts > 0; orgCounts--) {
					this.organisms.add(new Defector());
				}
			} else if (org.equals(parCoopName)) {
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
		for (Organism org: this.organisms) {
			org.update();
		}
	}
	
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
