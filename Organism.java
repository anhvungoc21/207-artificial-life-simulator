package lifesim;

public abstract class Organism {
	/**
	 * Private field for the energy level of this Organism
	 */
	private int energy;
	
	/**
	 * Constructor for an Organism
	 */
	public Organism() {
		this.energy = 0;
	}
	
	/**
	 * Updates this Organism.
	 * By default, this Organism gains one new energy point.
	 */
	public void update() {
		this.energy++;
	}
	
	/**
	 * Getter for the energy of this Organism
	 * @return Integer value of the energy of this Organism
	 */
	public int getEnergy() {
		return this.energy;
	}
	
	/**
	 * Increments the energy of this Organism
	 */
	public void incrementEnergy() {
		this.energy++;
	}
	
	/**
	 * Decrements the energy of this Organism
	 */
	public void decrementEnergy() {
		this.energy--;
	}
	
	/**
	 * Getter for the type of this Organism
	 * @return String of the type of this Organism
	 */
	abstract String getType();
	
	/**
	 * Simulates a reproduction of this Organism
	 * @return an Organism object that is reproduced 
	 */
	abstract Organism reproduce();
	
	/**
	 * Gets the cooperation probability of this Organism
	 * @return Double value of the cooperation probability of this Organism
	 */
	abstract double getCooperationProbability();
	
	/**
	 * Gets whether this Organism cooperates
	 * @return Boolean value of whether this Organism cooperates
	 */
	abstract boolean cooperates();
	
}
