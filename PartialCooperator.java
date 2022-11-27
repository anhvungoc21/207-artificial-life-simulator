package lifesim;

/**
 * A PartialCooperator is an Organism that cooperates 50% of the time
 */
public class PartialCooperator extends Organism {
	
	/**
	 * String for the Type of this Organism
	 */
	private String type;
	
	/**
	 * Double for the cooperation probability of this Organism
	 */
	private double coopProbability;
	
	/**
	 * Constructor for a PartialCooperator object
	 */
	public PartialCooperator() {
		super();
		this.type = Organism.PAR_COOP_NAME;
		this.coopProbability = Organism.PAR_COOP_PROB;
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
	 * Simulates reproduction of a PartialCooperator
	 * @return a new PartialCooperator
	 */
	@Override 
	Organism reproduce() {
		return new PartialCooperator();
	}

	/**
	 * Gets whether this PartialCooperator cooperates, which has a 50/50 chance.
	 * @return a random boolean value with a 50/50 chance for either "true" or "false".
	 */
	@Override
	boolean cooperates() {
		return Math.random() < this.coopProbability;  
	}
}
