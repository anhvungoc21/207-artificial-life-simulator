package lifesim;

public class Cooperator extends Organism {
	
	/**
	 * String for the Type of this Organism
	 */
	private String type;
	
	/**
	 * Double for the cooperation probability of this Organism
	 */
	private double coopProbability;
	
	/**
	 * Constructor for a Cooperator object
	 */
	public Cooperator() {
		super();
		this.type = Organism.COOP_NAME;
		this.coopProbability = Organism.COOP_PROB;
	}
	
	/**
	 * Getter for the type of this Organism
	 * @return String of the type of this Organism
	 */
	@Override
	String getType() {
		return this.type;
	}

	/**
	 * Getter for the cooperation probability of this Organism
	 * @return Double for the cooperation probability of this Organism
	 */
	@Override
	double getCooperationProbability() {
		return this.coopProbability;
	}
	
	/**
	 * Simulates reproduction of a Cooperator
	 * @return a new Cooperator
	 */
	@Override 
	Organism reproduce() {
		return new Cooperator();
	}

	/**
	 * Gets whether this Cooperator cooperates, which is always.
	 * @return Boolean value "true"
	 */
	@Override
	boolean cooperates() {
		return true;
	}

}
