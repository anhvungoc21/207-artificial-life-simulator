package lifesim;

/**
 * The Organism class simulates an organism in a population of an artificial life simulator
 */
public abstract class Organism {
	
	/**
	 * Constants for possible names of Organisms in a Population
	 */
	public static final String COOP_NAME = "Cooperator";
	public static final String DEF_NAME = "Defector";
	public static final String PAR_COOP_NAME = "PartialCooperator";	
	
	/**
	 * Constants for the cooperation probabilities of each Organism type
	 */
	public static final double COOP_PROB = 1;
	public static final double DEF_PROB = 0;
	public static final double PAR_COOP_PROB = 0.5;
	
	/**
	 * Constant for the energy level at which an Organism reproduces
	 */
	public static final int REP_ENERGY_LEVEL = 10;
	
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
